package frc.robot.commands;

//Add turret later. Will spin up turret and feed fuel from the hopper with a button

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.ShooterSubsystem;

public class LaunchCommand extends Command {
    private final ShooterSubsystem launchSubsystem;
    private final AngularVelocity speed

    public LaunchCommand(ShooterSubsystem launchSubsystem, AngularVelocity speed) {
        this.launchSubsystem = launchSubsystem;
        this.speed = speed;
        addRequirements(launchSubsystem);
    }

    @Override
    public void initialize() {
        launchSubsystem.setShooterFlywheel(speed);
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
