package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.InstantCommand;
import frc.robot.subsystems.FuelSubsystem;

public class ToggleHopper extends InstantCommand {
  private final FuelSubsystem fuelSubsystem;

  public ToggleHopper(FuelSubsystem fuelSubsystem) {
    this.fuelSubsystem = fuelSubsystem;
    addRequirements(fuelSubsystem);
  }

  @Override
  public void initialize() {
    fuelSubsystem.toggleHopper();
  }
}
