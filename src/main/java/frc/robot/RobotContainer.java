package frc.robot;

import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.button.CommandXboxController;
import static frc.robot.Constants.OperatorConstants.*;
import static frc.robot.Constants.ShooterConstants.*;

import com.pathplanner.lib.auto.AutoBuilder;
import com.pathplanner.lib.auto.NamedCommands;

import frc.robot.subsystems.*;
import frc.robot.commands.*;

public class RobotContainer {
  // The driver's controller
  private final CommandXboxController driverController = new CommandXboxController(DRIVER_CONTROLLER_PORT);

  // The operator's controller
  private final CommandXboxController operatorController = new CommandXboxController(OPERATOR_CONTROLLER_PORT);

  private final VisionSubsystem visionSubsystem = new VisionSubsystem();

  private final SwerveSubsystem swerveSubsystem = new SwerveSubsystem(visionSubsystem);
  private final SwerveCommand swerveCommand = new SwerveCommand(swerveSubsystem, () -> -applyDeadzoneAndCurve(driverController.getLeftX()), () -> -applyDeadzoneAndCurve(driverController.getLeftY()), () -> -applyDeadzoneAndCurve(driverController.getRightX()), () -> driverController.getLeftTriggerAxis() <= .5);

  private final HopperSubsystem hopperSubsystem = new HopperSubsystem();

  private final IntakeSubsystem intakeSubsystem = new IntakeSubsystem();
  private final IntakeFuelCommand intakeFuel = new IntakeFuelCommand(intakeSubsystem);
  private final ToggleIntakeArm toggleIntake = new ToggleIntakeArm(intakeSubsystem);
  private final ExtendIntakeArm extendIntake = new ExtendIntakeArm(intakeSubsystem);
  private final RetractIntakeArm retractIntake = new RetractIntakeArm(intakeSubsystem);

  private final ShooterSubsystem shooterSubsystem = new ShooterSubsystem();
  
  private final FeederSubsystem feederSubsystem = new FeederSubsystem();

  private final SpinUpAndShootCommand spinUpAndShoot = new SpinUpAndShootCommand(shooterSubsystem, feederSubsystem, hopperSubsystem, SHOOTER_FEEDER_POWER);
  private final SpinUpAndShootCommand spinUpAndShootFaster = new SpinUpAndShootCommand(shooterSubsystem, feederSubsystem, hopperSubsystem, SHOOTER_FEEDER_HIGH_POWER);
  private final TargetCommand targetAndShoot = new TargetCommand(shooterSubsystem, feederSubsystem, hopperSubsystem, swerveSubsystem);

  private final SpitFuelCommand spitFuel = new SpitFuelCommand(intakeSubsystem, hopperSubsystem, feederSubsystem);

  private final SendableChooser<Command> autoChooser;

  public RobotContainer() {
    configureBindings();
    configureNamedCommands();
    autoChooser = AutoBuilder.buildAutoChooser();
    SmartDashboard.putData(autoChooser);
  }

  private void configureBindings() {
    driverController.a().whileTrue(spitFuel);
    //driverController.x().whileTrue(targetAndShoot);
    driverController.rightTrigger().whileTrue(spinUpAndShoot);
    driverController.rightBumper().whileTrue(spinUpAndShootFaster);
    
    driverController.leftBumper().onTrue(toggleIntake);
    driverController.leftTrigger().whileTrue(intakeFuel);
    operatorController.leftBumper().onTrue(toggleIntake);
    operatorController.leftTrigger().whileTrue(intakeFuel);
  }

  private void configureNamedCommands() {
    NamedCommands.registerCommand("intakeFuel", intakeFuel);
    NamedCommands.registerCommand("extendIntake", extendIntake);
    NamedCommands.registerCommand("retractIntake", retractIntake);
    NamedCommands.registerCommand("spinUpAndShoot", spinUpAndShoot);
    NamedCommands.registerCommand("targetAndShoot", targetAndShoot);
  }

  private double applyDeadzoneAndCurve(double controllerInput) {
    if (Math.abs(controllerInput) < DEADZONE) {
      return 0;
    }
    return Math.pow(Math.abs(controllerInput), OUTPUT_CURVE) * controllerInput / Math.abs(controllerInput);
  }

  public void disabledPeriodic() {
    swerveSubsystem.disabledPeriodic();
  }

  public Command getAutonomousCommand() {
    return autoChooser.getSelected();
  }

  public void onTeleopInit() {
    swerveSubsystem.setDefaultCommand(swerveCommand);
  }
}
