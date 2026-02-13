// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.Commands;
import edu.wpi.first.wpilibj2.command.button.CommandXboxController;
import static frc.robot.Constants.OperatorConstants.*;
import frc.robot.subsystems.FuelSubsystem;
import frc.robot.commands.Conveyor;
import frc.robot.commands.ToggleHopper;
import frc.robot.commands.Intake;

public class RobotContainer {

  private final FuelSubsystem fuelSubsystem = new FuelSubsystem();

    // The driver's controller
  private final CommandXboxController driverController = new CommandXboxController(DRIVER_CONTROLLER_PORT);

  // The operator's controller
  private final CommandXboxController operatorController = new CommandXboxController(OPERATOR_CONTROLLER_PORT);

  public RobotContainer() {
    configureBindings();
  }

  private void configureBindings() {
      operatorController.rightBumper().onTrue(new ToggleHopper(fuelSubsystem));
      operatorController.a().whileTrue(new Intake(fuelSubsystem));
      operatorController.b().whileTrue(new Conveyor(fuelSubsystem));
  }

  public Command getAutonomousCommand() {
    return Commands.print("No autonomous command configured");
  }
}
