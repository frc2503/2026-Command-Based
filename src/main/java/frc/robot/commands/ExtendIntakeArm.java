package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.InstantCommand;
import frc.robot.subsystems.IntakeSubsystem;

public class ExtendIntakeArm extends InstantCommand {
  private final IntakeSubsystem intakeSubsystem;

  public ExtendIntakeArm(IntakeSubsystem intakeSubsystem) {
    this.intakeSubsystem = intakeSubsystem;
    addRequirements(intakeSubsystem);
  }

  @Override
  public void initialize() {
    intakeSubsystem.setIntakePosition(false);
  }

  @Override
  public boolean isFinished() {
    return intakeSubsystem.isIntakeActuallyUp() == false;
  }
}
