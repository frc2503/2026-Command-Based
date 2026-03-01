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
    private final ShooterSubsystem launchSubsystem;

    public SpitFuelCommand(IntakeSubsystem intakeSubsystem, HopperSubsystem hopperSubsystem, ShooterSubsystem launchSubsystem) {
        this.intakeSubsystem = intakeSubsystem;
        this.hopperSubsystem = hopperSubsystem;
        this.launchSubsystem = launchSubsystem;
        addRequirements(intakeSubsystem, hopperSubsystem, launchSubsystem);
    }

    @Override
    public void initialize() {
        if (!intakeSubsystem.isIntakeUp()) {
            intakeSubsystem.setIntakeAngle(INTAKE_ARM_DOWN_ANGLE);
        }
    }

    @Override
    public void execute() {
        if (intakeSubsystem.isIntakeUp()) {
            intakeSubsystem.setIntake(-INTAKE_ROLLER_POWER);
        } else {
            intakeSubsystem.stop();
        }
        hopperSubsystem.setHopper(-HOPPER_POWER);
        launchSubsystem.setShooterFeeder(-SHOOTER_FEEDER_POWER);
    }

    @Override
    public void end(boolean interrupted) {
        intakeSubsystem.stop();
        hopperSubsystem.stop();
        launchSubsystem.stop();
    }

    @Override
    public boolean isFinished() {
        return false;
    }
}
