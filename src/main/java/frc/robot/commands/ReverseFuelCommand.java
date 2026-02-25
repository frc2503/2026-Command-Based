package frc.robot.commands;

import static frc.robot.Constants.FuelConstants.*;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.IntakeSubsystem;
import frc.robot.subsystems.HopperSubsystem;
import frc.robot.subsystems.TowerSubsystem;

public class ReverseFuelCommand extends Command {

    private final IntakeSubsystem intakeSubsystem;
    private final HopperSubsystem hopperSubsystem;
    private final TowerSubsystem towerSubsystem;

    public ReverseFuelCommand(IntakeSubsystem intakeSubsystem, HopperSubsystem hopperSubsystem, TowerSubsystem towerSubsystem) {
        this.intakeSubsystem = intakeSubsystem;
        this.hopperSubsystem = hopperSubsystem;
        this.towerSubsystem = towerSubsystem;
        addRequirements(intakeSubsystem, hopperSubsystem, towerSubsystem);
    }

    @Override
    public void initialize() {
        intakeSubsystem.setIntake(INTAKE_ROLLER_VOLTAGE);
        hopperSubsystem.setHopper(HOPPER_VOLTAGE);
        towerSubsystem.setTower(TOWER_VOLTAGE);
    }

    @Override
    public void end(boolean interrupted) {
        intakeSubsystem.setIntake(0);
        hopperSubsystem.setHopper(0);
        towerSubsystem.setTower(0);
    }

    @Override
    public boolean isFinished() {
        return false;
    }
}
