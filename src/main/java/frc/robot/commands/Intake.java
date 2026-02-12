package frc.robot.commands;

//Spins three motors: the intake motor, the conveyor, and the tower. 

import frc.robot.subsystems.FuelSubsystem;
import static frc.robot.Constants.FuelConstants.*;


public class Intake{
    FuelSubsystem fuelSubsystem;
//on initialization-------------------------------
    public void initialize(){
        fuelSubsystem
            .setIntakeMotor(INTAKE_VOLTAGE);
        fuelSubsystem
            .setConveyorMotor(CONVEYOR_VOLTAGE);
        fuelSubsystem
            .setFeederMotor(FEEDER_VOLTAGE);
    }
//On finished-------------------------------------
    public boolean isFinished() {
        return false;
    }
}

