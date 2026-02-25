package frc.robot.commands;

import static frc.robot.Constants.FuelConstants.*;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.IntakeSubsystem;
import frc.robot.subsystems.HopperSubsystem;

public class FuelCommand extends Command {

    private final HopperSubsystem hopperSubsystem;
    private final IntakeSubsystem intakeSubsystem;

    public FuelCommand(IntakeSubsystem intakeSubsystem, HopperSubsystem hopperSubsystem) {
        this.intakeSubsystem = intakeSubsystem;
        this.hopperSubsystem = hopperSubsystem;
        addRequirements(intakeSubsystem, hopperSubsystem);
    }

    @Override
    public void initialize(){
        intakeSubsystem.setIntake(INTAKE_ROLLER_VOLTAGE);
        hopperSubsystem.setHopper(HOPPER_VOLTAGE);
    }

    @Override
    public void execute() {
    }

    @Override
    public void end(boolean interrupted) {
        intakeSubsystem.setIntake(0);
        hopperSubsystem.setHopper(0);
    }

    @Override
    public boolean isFinished() {
        return false;
    }
}
