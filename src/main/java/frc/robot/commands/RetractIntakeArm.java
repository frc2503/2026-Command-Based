package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.InstantCommand;
import frc.robot.subsystems.IntakeSubsystem;

public class RetractIntakeArm extends InstantCommand {
  private final IntakeSubsystem intakeSubsystem;

  public RetractIntakeArm(IntakeSubsystem intakeSubsystem) {
    this.intakeSubsystem = intakeSubsystem;
    addRequirements(intakeSubsystem);
  }

  @Override
  public void initialize() {
    intakeSubsystem.setIntakeState(IntakeSubsystem.IntakeState.RETRACTED);
  }

  @Override
  public boolean isFinished() {
      return intakeSubsystem.getIntakeActualState() == IntakeSubsystem.IntakeState.RETRACTED;
  }
}
