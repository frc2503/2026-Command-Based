package frc.robot.commands;

import static frc.robot.Constants.IntakeConstants.*;
import static frc.robot.Constants.HopperConstants.*;
import static frc.robot.Constants.ShooterConstants.*;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.IntakeSubsystem;
import frc.robot.subsystems.HopperSubsystem;
import frc.robot.subsystems.FeederSubsystem;


public class SpitFuelCommand extends Command {
    private final IntakeSubsystem intakeSubsystem;
    private final HopperSubsystem hopperSubsystem;
    private final FeederSubsystem feederSubsystem;

    public SpitFuelCommand(IntakeSubsystem intakeSubsystem, HopperSubsystem hopperSubsystem, FeederSubsystem feederSubsystem) {
        this.intakeSubsystem = intakeSubsystem;
        this.hopperSubsystem = hopperSubsystem;
        this.feederSubsystem = feederSubsystem;
        addRequirements(intakeSubsystem, hopperSubsystem, feederSubsystem);
    }


    @Override
    public void initialize() {
        intakeSubsystem.setIntakeState(IntakeSubsystem.IntakeState.EXTENDED);
    }

    @Override
    public void execute() {
        if (intakeSubsystem.getIntakeActualState() == IntakeSubsystem.IntakeState.EXTENDED) {
            intakeSubsystem.setIntake(-INTAKE_ROLLER_POWER);
        } else {
            intakeSubsystem.stop();
        }
        hopperSubsystem.setHopper(-HOPPER_POWER);
        feederSubsystem.setShooterFeeder(-SHOOTER_FEEDER_POWER);
    }

    @Override
    public void end(boolean interrupted) {
        intakeSubsystem.stop();
        hopperSubsystem.stop();
        feederSubsystem.stop();
    }
}
