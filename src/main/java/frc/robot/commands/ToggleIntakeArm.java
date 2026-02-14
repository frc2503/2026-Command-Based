package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.InstantCommand;
import frc.robot.subsystems.IntakeArmSubsystem;

public class ToggleIntakeArm extends InstantCommand {
  private final IntakeArmSubsystem intakeArmSubsystem;

  public ToggleIntakeArm(IntakeArmSubsystem intakeArmSubsystem) {
    this.intakeArmSubsystem = intakeArmSubsystem;
    addRequirements(intakeArmSubsystem);
  }

  @Override
  public void initialize() {
    intakeArmSubsystem.toggleIntake();
  }
}
