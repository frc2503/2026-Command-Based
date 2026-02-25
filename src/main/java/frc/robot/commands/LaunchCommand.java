package frc.robot.commands;

//Add turret later. Will spin up turret and feed fuel from the hopper with a button

import static frc.robot.Constants.FuelConstants.*;
import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.LaunchSubsystem;

public class LaunchCommand extends Command {
    private final LaunchSubsystem launchSubsystem;

    public LaunchCommand(LaunchSubsystem launchSubsystem) {
        this.launchSubsystem = launchSubsystem;
        addRequirements(launchSubsystem);
    }

    @Override
    public void initialize() {
        launchSubsystem.setLaunch1(LAUNCH_1_VOLTAGE);
        launchSubsystem.setLaunch2(LAUNCH_2_VOLTAGE);
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
