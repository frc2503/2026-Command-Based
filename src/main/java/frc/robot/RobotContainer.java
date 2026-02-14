// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.Commands;
import edu.wpi.first.wpilibj2.command.button.CommandXboxController;
import static frc.robot.Constants.OperatorConstants.*;
import static frc.robot.Constants.FuelConstants.*;

import frc.robot.subsystems.*;
import frc.robot.commands.*;

public class RobotContainer {

  private final IntakeArmSubsystem intakeArmSubsystem = new IntakeArmSubsystem();
  private final IntakeRollerSubsystem intakeRollerSubsystem = new IntakeRollerSubsystem(intakeArmSubsystem);
  private final HopperSubsystem hopperSubsystem = new HopperSubsystem();
  private final TowerSubsystem towerSubsystem = new TowerSubsystem();

    // The driver's controller
  private final CommandXboxController driverController = new CommandXboxController(DRIVER_CONTROLLER_PORT);

  // The operator's controller
  private final CommandXboxController operatorController = new CommandXboxController(OPERATOR_CONTROLLER_PORT);

  public RobotContainer() {
    configureBindings();

    // Default command for hopper to keep it running at a low speed to prevent binding
    hopperSubsystem.setDefaultCommand(
      Commands.run(() -> hopperSubsystem.setHopperVelocity(HOPPER_RPM * 0.1), hopperSubsystem)
    );
  }

  private void configureBindings() {
      operatorController.leftBumper().onTrue(new ToggleIntakeArm(intakeArmSubsystem));
      operatorController.rightTrigger().whileTrue(new FuelCommand(intakeArmSubsystem, intakeRollerSubsystem, hopperSubsystem));
      operatorController.rightBumper().whileTrue(new FeedCommand(towerSubsystem));
      operatorController.leftTrigger().whileTrue(new ReverseFuelCommand(intakeRollerSubsystem, hopperSubsystem, towerSubsystem));
  }

  public Command getAutonomousCommand() {
    return Commands.print("No autonomous command configured");
  }
}
