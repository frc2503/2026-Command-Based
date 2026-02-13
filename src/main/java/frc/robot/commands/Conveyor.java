package frc.robot.commands;

//spins the conveyor and tower motors.

import frc.robot.subsystems.FuelSubsystem;
import static frc.robot.Constants.FuelConstants.*;

import edu.wpi.first.wpilibj2.command.Command;

public class Conveyor extends Command {

    FuelSubsystem fuelSubsystem;

    public Conveyor(FuelSubsystem fuelSubsystem) {
        this.fuelSubsystem = fuelSubsystem;
        addRequirements(fuelSubsystem);
    }

//On initialization-----------------------------
    @Override
    public void initialize(){
        fuelSubsystem.setConveyorVelocity(CONVEYOR_RPM);
        fuelSubsystem.setFeederVelocity(FEEDER_RPM);
    }

    @Override
    public void end(boolean interrupted) {
        fuelSubsystem.setConveyorVelocity(0);
        fuelSubsystem.setFeederVelocity(0);
    }

//On finished-----------------------------------
    @Override
    public boolean isFinished() {
        return false;
    }
}
