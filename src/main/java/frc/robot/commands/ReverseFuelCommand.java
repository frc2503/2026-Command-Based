package frc.robot.commands;

import static frc.robot.Constants.FuelConstants.*;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.FuelSubsystem;

public class ReverseFuelCommand extends Command {

    private final FuelSubsystem fuelSubsystem;

    public ReverseFuelCommand(FuelSubsystem fuelSubsystem) {
        this.fuelSubsystem = fuelSubsystem;
        addRequirements(fuelSubsystem);
    }

    @Override
    public void initialize() {
        fuelSubsystem.setIntakeVelocity(0);
        fuelSubsystem.setHopperVelocity(-HOPPER_RPM);
        fuelSubsystem.setTowerVelocity(-TOWER_RPM);
    }

    @Override
    public void end(boolean interrupted) {
        fuelSubsystem.setIntakeVelocity(0);
        fuelSubsystem.setHopperVelocity(0);
        fuelSubsystem.setTowerVelocity(0);
    }

    @Override
    public boolean isFinished() {
        return false;
    }
}
