package frc.robot;

import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.Commands;
import edu.wpi.first.wpilibj2.command.button.CommandXboxController;
import frc.robot.Constants.OperatorConstants;
import frc.robot.subsystems.SwerveSubsystem;

public class RobotContainer {
    private final SwerveSubsystem swerveSubsystem = new SwerveSubsystem();
    private final CommandXboxController driverController =
        new CommandXboxController(OperatorConstants.DRIVER_CONTROLLER_PORT);

    public RobotContainer() {
        // Default teleop swerve drive using the left stick for translation and right stick X for rotation
        swerveSubsystem.setDefaultCommand(
            Commands.run(
                () -> swerveSubsystem.drive(
                    -driverController.getLeftY(),
                    -driverController.getLeftX(),
                    -driverController.getRightX(),
                    true
                ),
                swerveSubsystem
            )
        );

        configureBindings();
    }

    private void configureBindings() {
        // Press A on the Xbox controller to toggle TurnToLimelight on and off
        driverController.a().toggleOnTrue(
            new TurnToLimelight(
                swerveSubsystem,
                () -> -driverController.getLeftY(),
                () -> -driverController.getLeftX()
            )
        );
    }

    public Command getAutonomousCommand() {
        return Commands.none();
    }

    public void onTeleopInit() {
    }
}
