package frc.robot.commands;

//Add turret later. Will spin up turret and feed fuel from the hopper with a button

import static frc.robot.Constants.FuelConstants.*;
import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.TowerSubsystem;

public class LaunchCommand extends Command {
    private final TowerSubsystem towerSubsystem;

    public LaunchCommand(TowerSubsystem towerSubsystem) {
        this.towerSubsystem = towerSubsystem;
        addRequirements(towerSubsystem);
    }

    @Override
    public void initialize() {
        towerSubsystem.setTower(TOWER_VOLTAGE);
    }

    @Override
    public void end(boolean interrupted) {
        towerSubsystem.stop();
    }

    @Override
    public boolean isFinished() {
        return false;
    }
}
