package frc.robot.commands;

//Spins three motors: the intake motor, the conveyor, and the tower. 

import frc.robot.subsystems.FuelSubsystem;
import static frc.robot.Constants.FuelConstants.*;


import edu.wpi.first.wpilibj2.command.Command;

public class Intake extends Command {
    FuelSubsystem fuelSubsystem;

    public Intake(FuelSubsystem fuelSubsystem) {
        this.fuelSubsystem = fuelSubsystem;
        addRequirements(fuelSubsystem);
    }
//on initialization-------------------------------
    @Override
    public void initialize(){
        fuelSubsystem.setIntakeVelocity(INTAKE_RPM);
        fuelSubsystem.setConveyorVelocity(CONVEYOR_RPM);
        fuelSubsystem.setFeederVelocity(FEEDER_RPM);
    }

    @Override
    public void end(boolean interrupted) {
        fuelSubsystem.setIntakeVelocity(0);
        fuelSubsystem.setConveyorVelocity(0);
        fuelSubsystem.setFeederVelocity(0);
    }
//On finished-------------------------------------
    @Override
    public boolean isFinished() {
        return false;
    }
}

