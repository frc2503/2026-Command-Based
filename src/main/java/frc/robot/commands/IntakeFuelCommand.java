package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.Command;

import static frc.robot.Constants.HopperConstants.HOPPER_POWER;
import static frc.robot.Constants.IntakeConstants.*;

import frc.robot.subsystems.HopperSubsystem;
import frc.robot.subsystems.IntakeSubsystem;

public class IntakeFuelCommand extends Command {
    private IntakeSubsystem intakeSubsystem;
    private HopperSubsystem hopperSubsystem;

    public IntakeFuelCommand(IntakeSubsystem intakeSubsystem, HopperSubsystem hopperSubsystem) {
        this.intakeSubsystem = intakeSubsystem;
        this.hopperSubsystem = hopperSubsystem;

        addRequirements(intakeSubsystem, hopperSubsystem);
    }

    @Override
    public void initialize() {
        intakeSubsystem.setIntakePosition(false);
    }

    @Override
    public void execute() {
        if (!intakeSubsystem.isIntakeActuallyUp()) {
            intakeSubsystem.setIntake(INTAKE_ROLLER_POWER);
            hopperSubsystem.setHopper(HOPPER_POWER);
        } else {
            intakeSubsystem.stop();
            hopperSubsystem.stop();
        }
    }

    @Override
    public void end(boolean interrupted) {
        intakeSubsystem.stop();
        hopperSubsystem.stop();
    }
}
