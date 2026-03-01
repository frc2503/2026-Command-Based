package frc.robot;

import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.Commands;
import edu.wpi.first.wpilibj2.command.button.CommandXboxController;
import static frc.robot.Constants.OperatorConstants.*;

import frc.robot.subsystems.*;
import frc.robot.commands.*;

public class RobotContainer {
  // The driver's controller
  private final CommandXboxController driverController = new CommandXboxController(DRIVER_CONTROLLER_PORT);

  // The operator's controller
  private final CommandXboxController operatorController = new CommandXboxController(OPERATOR_CONTROLLER_PORT);

  private final VisionSubsystem visionSubsystem = new VisionSubsystem();

  private final SwerveSubsystem swerveSubsystem = new SwerveSubsystem(visionSubsystem);
  private final SwerveCommand swerveCommand = new SwerveCommand(swerveSubsystem, () -> driverController.getLeftX(), () -> -driverController.getLeftY(), () -> -driverController.getRightX(), () -> true);

  private final IntakeSubsystem intakeSubsystem = new IntakeSubsystem();
  private final IntakeFuelCommand intakeFuel = new IntakeFuelCommand(intakeSubsystem);
  private final ToggleIntakeArm toggleIntake = new ToggleIntakeArm(intakeSubsystem);

  private final HopperSubsystem hopperSubsystem = new HopperSubsystem();
  private final RunHopperCommand runHopper = new RunHopperCommand(hopperSubsystem);

  //private final ShooterSubsystem shooterSubsystem = new ShooterSubsystem();
  //private final SpitFuelCommand spitFuel = new SpitFuelCommand(intakeSubsystem, hopperSubsystem, shooterSubsystem);

  public RobotContainer() {
    configureBindings();
  }
  private void configureBindings() {
    driverController.a().whileTrue(intakeFuel);
    driverController.x().onTrue(toggleIntake);
    driverController.y().toggleOnTrue(runHopper);

    //driverController.leftTrigger().whileTrue(spitFuel);
  }

  public Command getAutonomousCommand() {
    return Commands.print("No autonomous command configured");
  }

  public void onTeleopInit() {
    swerveSubsystem.setDefaultCommand(swerveCommand);
  }

  public void periodicStuff() {
    System.out.println("Absolute Encoder Position: ");
    System.out.println(intakeSubsystem.getAbsoluteEncoderPosition());
  }
}
