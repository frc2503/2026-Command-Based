package frc.robot.commands;

//Add turret later. Will spin up turret and feed fuel from the hopper with a button

import static frc.robot.Constants.ShooterConstants.*;
import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.ShooterSubsystem;

public class LaunchCommand extends Command {
    private final ShooterSubsystem launchSubsystem;

    public LaunchCommand(ShooterSubsystem launchSubsystem) {
        this.launchSubsystem = launchSubsystem;
        addRequirements(launchSubsystem);
    }

    @Override
    public void initialize() {
        launchSubsystem.setShooterFlywheel(SHOOTER_FLYWHEEL_POWER);
        launchSubsystem.setShooterFeeder(SHOOTER_FEEDER_POWER);
    }

    @Override
    public void end(boolean interrupted) {
        launchSubsystem.stop();
    }

    @Override
    public boolean isFinished() {
        return false;
    }
}
