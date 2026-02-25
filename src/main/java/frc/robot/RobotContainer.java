package frc.robot;

import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.Commands;
import edu.wpi.first.wpilibj2.command.button.CommandXboxController;
import static frc.robot.Constants.OperatorConstants.*;
import static frc.robot.Constants.FuelConstants.*;

import frc.robot.subsystems.*;
import frc.robot.commands.*;

public class RobotContainer {

  private final IntakeSubsystem intakeSubsystem = new IntakeSubsystem();
  private final HopperSubsystem hopperSubsystem = new HopperSubsystem();
  private final LaunchSubsystem towerSubsystem = new LaunchSubsystem();

    // The driver's controller
  private final CommandXboxController driverController = new CommandXboxController(DRIVER_CONTROLLER_PORT);

  // The operator's controller
  private final CommandXboxController operatorController = new CommandXboxController(OPERATOR_CONTROLLER_PORT);

  public RobotContainer() {
    configureBindings();

    // Default command for hopper to keep it running at a low speed to prevent binding
    hopperSubsystem.setDefaultCommand(
      Commands.run(() -> hopperSubsystem.setHopper(HOPPER_VOLTAGE * 0.1), hopperSubsystem)
    );
  }

  private void configureBindings() {
      operatorController.leftBumper().onTrue(new ToggleIntakeArm(intakeSubsystem));
      operatorController.rightTrigger().whileTrue(new FuelCommand(intakeSubsystem, hopperSubsystem));
      operatorController.rightBumper().whileTrue(new LaunchCommand(towerSubsystem));
      operatorController.leftTrigger().whileTrue(new ReverseFuelCommand(intakeSubsystem, hopperSubsystem, towerSubsystem));
  }

  public Command getAutonomousCommand() {
    return Commands.print("No autonomous command configured");
  }
}
