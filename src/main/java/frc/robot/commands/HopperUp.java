package frc.robot.commands;

import frc.robot.subsystems.FuelSubsystem;
import static frc.robot.Constants.FuelConstants.*;

//moves the intake up, when its not collecting fuel.

import edu.wpi.first.wpilibj2.command.Command;

public class HopperUp extends Command {
    FuelSubsystem fuelSubsystem;

    public HopperUp(FuelSubsystem fuelSubsystem) {
        this.fuelSubsystem = fuelSubsystem;
        addRequirements(fuelSubsystem);
    }

//On initialization-----------------------------
    @Override
    public void initialize(){
        fuelSubsystem.setHopperPosition(HOPPER_UP_DEGREES);
    }
//On finished-----------------------------------
    @Override
    public boolean isFinished() {
        return false;
    }
}
