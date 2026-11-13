package frc.robot;

import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.SwerveSubsystem;

/**
 * A command that forces the swerve drivetrain to continuously face an AprilTag
 * while allowing the driver to maintain manual translation control (X and Y).
 */
public class TurnToLimelight extends Command {
    private final SwerveSubsystem m_swerveSubsystem;
    private final java.util.function.DoubleSupplier m_translationXSupplier;
    private final java.util.function.DoubleSupplier m_translationYSupplier;

    // PID Controller to turn the robot based on the Limelight's horizontal offset (tx)
    // Tune kP if the rotation is too slow or oscillates. 
    // Start small (e.g., 0.03) and adjust upwards as needed.
    private final PIDController m_turnPID = new PIDController(0.03, 0.0, 0.0);

    private final NetworkTable m_limelightTable;

    /**
     * @param swerveSubsystem     Your robot's swerve subsystem
     * @param translationXSupplier Left stick X or Y for forward/backward translation
     * @param translationYSupplier Left stick Y or X for strafe translation
     */
    public TurnToLimelight(
            SwerveSubsystem swerveSubsystem, 
            java.util.function.DoubleSupplier translationXSupplier, 
            java.util.function.DoubleSupplier translationYSupplier) {
        
        this.m_swerveSubsystem = swerveSubsystem;
        this.m_translationXSupplier = translationXSupplier;
        this.m_translationYSupplier = translationYSupplier;

        // Fetch the standard default "limelight" NetworkTable
        this.m_limelightTable = NetworkTableInstance.getDefault().getTable("limelight");

        // Set the PID setpoint to 0 (we want the AprilTag perfectly centered on the crosshair)
        m_turnPID.setSetpoint(0.0);
        
        // Add a small tolerance so the robot doesn't jitter trying to get perfectly to 0.0001
        m_turnPID.setTolerance(1.0); // 1 degree tolerance

        // Declare dependency on the swerve drive subsystem
        addRequirements(m_swerveSubsystem);
    }

    @Override
    public void initialize() {
        // Reset the PID internal state whenever the command starts
        m_turnPID.reset();
    }

    @Override
    public void execute() {
        // Read target values from Limelight via NetworkTables
        double tv = m_limelightTable.getEntry("tv").getDouble(0.0);
        double tx = m_limelightTable.getEntry("tx").getDouble(0.0);

        double rotationSpeed = 0.0;

        // If tv == 1, a valid target is seen
        if (tv == 1.0) {
            // Calculate rotational speed needed to bring tx to 0
            rotationSpeed = m_turnPID.calculate(tx);
        } else {
            // If no target is seen, don't spin wildly. Keep rotation at zero.
            rotationSpeed = 0.0;
        }

        // Pass manual translation inputs along with our automated tracking rotation to your swerve subsystem
        m_swerveSubsystem.drive(
            m_translationXSupplier.getAsDouble(), 
            m_translationYSupplier.getAsDouble(), 
            rotationSpeed, 
            true
        );
    }

    @Override
    public void end(boolean interrupted) {
        // When the button is released or toggled off, stop the robot's movement completely
        m_swerveSubsystem.drive(0, 0, 0, true);
    }

    @Override
    public boolean isFinished() {
        // This command runs continuously until toggled off
        return false;
    }
}