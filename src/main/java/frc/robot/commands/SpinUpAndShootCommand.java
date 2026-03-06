package frc.robot.commands;

import static frc.robot.Constants.HopperConstants.HOPPER_POWER;
import static frc.robot.Constants.ShooterConstants.*;
import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.FeederSubsystem;
import frc.robot.subsystems.HopperSubsystem;
import frc.robot.subsystems.ShooterSubsystem;

public class SpinUpAndShootCommand extends Command {
    private ShooterSubsystem shooterSubsystem;
    private FeederSubsystem feederSubsystem;
    private HopperSubsystem hopperSubsystem;


    public SpinUpAndShootCommand(ShooterSubsystem shooterSubsystem, FeederSubsystem feederSubsystem, HopperSubsystem hopperSubsystem) {
        this.shooterSubsystem = shooterSubsystem;
        this.feederSubsystem = feederSubsystem;
        this.hopperSubsystem = hopperSubsystem;


        addRequirements(shooterSubsystem, feederSubsystem, hopperSubsystem);
    }

    @Override
    public void execute() {
        shooterSubsystem.setShooterFlywheel(SHOOTER_FLYWHEEL_SPEED);
        

        if (shooterSubsystem.getVelocity().isNear(SHOOTER_FLYWHEEL_SPEED, SHOOTER_FLYWHEEL_SPEED_TOLERANCE)) {
            feederSubsystem.setShooterFeeder(SHOOTER_FEEDER_POWER);
            hopperSubsystem.setHopper(HOPPER_POWER);
        } else {
            feederSubsystem.stop();
        }
    }

    @Override
    public void end(boolean interrupted) {
        shooterSubsystem.stop();
        feederSubsystem.stop();
        hopperSubsystem.stop();
    }
}
