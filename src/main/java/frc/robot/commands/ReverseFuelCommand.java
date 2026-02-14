package frc.robot.commands;

import static frc.robot.Constants.FuelConstants.*;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.IntakeRollerSubsystem;
import frc.robot.subsystems.HopperSubsystem;
import frc.robot.subsystems.TowerSubsystem;

public class ReverseFuelCommand extends Command {

    private final IntakeRollerSubsystem intakeRollerSubsystem;
    private final HopperSubsystem hopperSubsystem;
    private final TowerSubsystem towerSubsystem;

    public ReverseFuelCommand(IntakeRollerSubsystem intakeRollerSubsystem, HopperSubsystem hopperSubsystem, TowerSubsystem towerSubsystem) {
        this.intakeRollerSubsystem = intakeRollerSubsystem;
        this.hopperSubsystem = hopperSubsystem;
        this.towerSubsystem = towerSubsystem;
        addRequirements(intakeRollerSubsystem, hopperSubsystem, towerSubsystem);
    }

    @Override
    public void initialize() {
        intakeRollerSubsystem.setIntakeVelocity(-INTAKE_ROLLER_RPM);
        hopperSubsystem.setHopperVelocity(-HOPPER_RPM);
        towerSubsystem.setTowerVelocity(-TOWER_RPM);
    }

    @Override
    public void end(boolean interrupted) {
        intakeRollerSubsystem.setIntakeVelocity(0);
        hopperSubsystem.setHopperVelocity(0);
        towerSubsystem.setTowerVelocity(0);
    }

    @Override
    public boolean isFinished() {
        return false;
    }
}
