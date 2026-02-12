package frc.robot.commands;

import frc.robot.subsystems.FuelSubsystem;

//Moves the intake down, to collect fuel.

import frc.robot.subsystems.FuelSubsystem;
import static frc.robot.Constants.FuelConstants.*;

public class HopperDown {

    FuelSubsystem fuelSubsystem;

//On initialization-----------------------------
    public void initialize(){
        fuelSubsystem
            .setHopperMotor(HOPPER_DOWN_VOLTAGE);
    }
//On finished-----------------------------------
    public boolean isFinished() {
        return false;
    }
}
