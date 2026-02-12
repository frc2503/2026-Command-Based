package frc.robot.commands;

import frc.robot.subsystems.FuelSubsystem;
import static frc.robot.Constants.FuelConstants.*;

//moves the intake up, when its not collecting fuel.

public class HopperUp {
    FuelSubsystem fuelSubsystem;

//On initialization-----------------------------
    public void initialize(){
        fuelSubsystem
            .setHopperMotor(HOPPER_UP_VOLTAGE);
    }
//On finished-----------------------------------
    public boolean isFinished() {
        return false;
    }
}
