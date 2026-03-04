package frc.robot.commands;

//Add turret later. Will spin up turret and feed fuel from the hopper with a button

import static frc.robot.Constants.ShooterConstants.*;
import static frc.robot.Constants.HopperConstants.*;
import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.FeederSubsystem;
import frc.robot.subsystems.HopperSubsystem;

public class FeedCommand extends Command {
    private final FeederSubsystem feedSubsystem;
    private HopperSubsystem hopperSubsystem;

    public FeedCommand(FeederSubsystem feedSubsystem, HopperSubsystem hopperSubsystem) {
        this.feedSubsystem = feedSubsystem;
        this.hopperSubsystem = hopperSubsystem;
        addRequirements(feedSubsystem, hopperSubsystem);
    }

    @Override
    public void initialize() {
        feedSubsystem.setShooterFeeder(SHOOTER_FEEDER_POWER);
        hopperSubsystem.setHopper(HOPPER_POWER);
    }

    @Override
    public void end(boolean interrupted) {
        feedSubsystem.stop();
        hopperSubsystem.stop();
    }

    @Override
    public boolean isFinished() {
        return false;
    }
}
