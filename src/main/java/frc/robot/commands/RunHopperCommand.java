package frc.robot.commands;

import static frc.robot.Constants.HopperConstants.HOPPER_POWER;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.HopperSubsystem;

public class RunHopperCommand extends Command {
    private HopperSubsystem hopperSubsystem;

    public RunHopperCommand(HopperSubsystem hopperSubsystem) {
        this.hopperSubsystem = hopperSubsystem;

        addRequirements(hopperSubsystem);
    }

    @Override
    public void execute() {
        hopperSubsystem.setHopper(HOPPER_POWER);
    }

    @Override
    public void end(boolean interrupted) {
        hopperSubsystem.stop();
    }
}
