package frc.robot.commands;

import static frc.robot.Constants.FuelConstants.*;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.IntakeArmSubsystem;
import frc.robot.subsystems.IntakeRollerSubsystem;
import frc.robot.subsystems.HopperSubsystem;

public class FuelCommand extends Command {

    private final IntakeRollerSubsystem intakeRollerSubsystem;
    private final HopperSubsystem hopperSubsystem;

    public FuelCommand(IntakeArmSubsystem intakeArmSubsystem, IntakeRollerSubsystem intakeRollerSubsystem, HopperSubsystem hopperSubsystem) {
        this.intakeRollerSubsystem = intakeRollerSubsystem;
        this.hopperSubsystem = hopperSubsystem;
        addRequirements(intakeRollerSubsystem, hopperSubsystem);
    }

    @Override
    public void initialize(){
        intakeRollerSubsystem.setIntakeVelocity(INTAKE_ROLLER_RPM);
        hopperSubsystem.setHopperVelocity(HOPPER_RPM);
    }

    @Override
    public void execute() {
    }

    @Override
    public void end(boolean interrupted) {
        intakeRollerSubsystem.setIntakeVelocity(0);
        hopperSubsystem.setHopperVelocity(0);
    }

    @Override
    public boolean isFinished() {
        return false;
    }
}
