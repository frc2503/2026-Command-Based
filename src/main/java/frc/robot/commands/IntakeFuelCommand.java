package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.Command;

import static frc.robot.Constants.IntakeConstants.*;

import frc.robot.subsystems.IntakeSubsystem;

public class IntakeFuelCommand extends Command {
    private IntakeSubsystem intakeSubsystem;


    public IntakeFuelCommand(IntakeSubsystem intakeSubsystem) {
        this.intakeSubsystem = intakeSubsystem;

        addRequirements(intakeSubsystem);
    }

    @Override
    public void initialize() {
        intakeSubsystem.setIntakeState(IntakeSubsystem.IntakeState.EXTENDED);
    }

    @Override
    public void execute() {
        if (intakeSubsystem.getIntakeActualState() == IntakeSubsystem.IntakeState.EXTENDED) {
            intakeSubsystem.setIntake(INTAKE_ROLLER_POWER);
        } else {
            intakeSubsystem.stop();
        }
    }

    @Override
    public void end(boolean interrupted) {
        intakeSubsystem.stop();
    }
}
