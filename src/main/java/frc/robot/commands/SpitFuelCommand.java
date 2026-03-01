package frc.robot.commands;

import static frc.robot.Constants.IntakeConstants.*;
import static frc.robot.Constants.HopperConstants.*;
import static frc.robot.Constants.ShooterConstants.*;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.IntakeSubsystem;
import frc.robot.subsystems.HopperSubsystem;
import frc.robot.subsystems.ShooterSubsystem;

public class SpitFuelCommand extends Command {
    private final IntakeSubsystem intakeSubsystem;
    private final HopperSubsystem hopperSubsystem;
    private final ShooterSubsystem shooterSubsystem;

    public SpitFuelCommand(IntakeSubsystem intakeSubsystem, HopperSubsystem hopperSubsystem, ShooterSubsystem shooterSubsystem) {
        this.intakeSubsystem = intakeSubsystem;
        this.hopperSubsystem = hopperSubsystem;
        this.shooterSubsystem = shooterSubsystem;
        addRequirements(intakeSubsystem, hopperSubsystem, shooterSubsystem);
    }


    @Override
    public void initialize() {
        if (intakeSubsystem.isIntakeIntendedUp()) {
            intakeSubsystem.toggleIntake();
        }
    }

    @Override
    public void execute() {
        if (!intakeSubsystem.isIntakeActuallyUp()) {
            intakeSubsystem.setIntake(-INTAKE_ROLLER_POWER);
        } else {
            intakeSubsystem.stop();
        }
        hopperSubsystem.setHopper(-HOPPER_POWER);
        shooterSubsystem.setShooterFeeder(-SHOOTER_FEEDER_POWER);
    }

    @Override
    public void end(boolean interrupted) {
        intakeSubsystem.stop();
        hopperSubsystem.stop();
        shooterSubsystem.stop();
    }

    @Override
    public boolean isFinished() {
        return false;
    }
}
