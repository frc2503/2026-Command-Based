package frc.robot.commands;

import static frc.robot.Constants.FuelConstants.*;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.IntakeSubsystem;
import frc.robot.subsystems.HopperSubsystem;
import frc.robot.subsystems.LaunchSubsystem;

public class ReverseFuelCommand extends Command {

    private final IntakeSubsystem intakeSubsystem;
    private final HopperSubsystem hopperSubsystem;
    private final LaunchSubsystem launchSubsystem;

    public ReverseFuelCommand(IntakeSubsystem intakeSubsystem, HopperSubsystem hopperSubsystem, LaunchSubsystem launchSubsystem) {
        this.intakeSubsystem = intakeSubsystem;
        this.hopperSubsystem = hopperSubsystem;
        this.launchSubsystem = launchSubsystem;
        addRequirements(intakeSubsystem, hopperSubsystem, launchSubsystem);
    }

    @Override
    public void initialize() {
        intakeSubsystem.setIntake(INTAKE_ROLLER_VOLTAGE);
        hopperSubsystem.setHopper(HOPPER_VOLTAGE);
        launchSubsystem.setLaunch1(LAUNCH_1_VOLTAGE);
        launchSubsystem.setLaunch2(LAUNCH_2_VOLTAGE);
    }

    @Override
    public void end(boolean interrupted) {
        intakeSubsystem.setIntake(0);
        hopperSubsystem.setHopper(0);
        launchSubsystem.setLaunch1(0);
        launchSubsystem.setLaunch2(0);
    }

    @Override
    public boolean isFinished() {
        return false;
    }
}
