package frc.robot.commands;

//spins the conveyor and tower motors.

import frc.robot.subsystems.FuelSubsystem;
import static frc.robot.Constants.FuelConstants.*;

public class Conveyor {

    FuelSubsystem fuelSubsystem;

//On initialization-----------------------------
    public void initialize(){
        fuelSubsystem
            .setConveyorMotor(CONVEYOR_VOLTAGE);
        fuelSubsystem
            .setFeederMotor(FEEDER_VOLTAGE);
    }
//On finished-----------------------------------
    public boolean isFinished() {
        return false;
    }
}
