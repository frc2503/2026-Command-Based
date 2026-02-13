package frc.robot.commands;

import static frc.robot.Constants.FuelConstants.*;

import edu.wpi.first.wpilibj2.command.Command;

//Intake up: runs only hopper and tower motors.
//Intake down: runs intake, hopper, and tower motors.

import frc.robot.subsystems.FuelSubsystem;

public class FuelCommand extends Command {

    FuelSubsystem fuelSubsystem;

    public FuelCommand(FuelSubsystem fuelSubsystem) {
        this.fuelSubsystem = fuelSubsystem;
        addRequirements(fuelSubsystem);
    }

//On initialization-----------------------------
    @Override
    public void initialize(){
        if (fuelSubsystem.isIntakeUp()) {
            // Intake is up: run only hopper and tower
            fuelSubsystem.setIntakeVelocity(0);
            fuelSubsystem.setHopperVelocity(HOPPER_RPM);
            fuelSubsystem.setTowerVelocity(TOWER_RPM);
        } else {
            // Intake is down: run intake, hopper, and tower
            fuelSubsystem.setIntakeVelocity(INTAKE_RPM);
            fuelSubsystem.setHopperVelocity(HOPPER_RPM);
            fuelSubsystem.setTowerVelocity(TOWER_RPM);
        }
    }

    @Override
    public void end(boolean interrupted) {
        fuelSubsystem.setIntakeVelocity(0);
        fuelSubsystem.setHopperVelocity(0);
        fuelSubsystem.setTowerVelocity(0);
    }

//On finished-----------------------------------
    @Override
    public boolean isFinished() {
        return false;
    }
}
