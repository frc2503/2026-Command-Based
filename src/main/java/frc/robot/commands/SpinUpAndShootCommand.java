package frc.robot.commands;

import static frc.robot.Constants.HopperConstants.HOPPER_POWER;
import static frc.robot.Constants.ShooterConstants.*;

import edu.wpi.first.units.measure.AngularVelocity;
import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.FeederSubsystem;
import frc.robot.subsystems.HopperSubsystem;
import frc.robot.subsystems.ShooterSubsystem;

public class SpinUpAndShootCommand extends Command {
    private ShooterSubsystem shooterSubsystem;
    private FeederSubsystem feederSubsystem;
    private HopperSubsystem hopperSubsystem;
    private final AngularVelocity flywheelVelocity;
    private final double feederPower;


    public SpinUpAndShootCommand(ShooterSubsystem shooterSubsystem, FeederSubsystem feederSubsystem, HopperSubsystem hopperSubsystem, AngularVelocity flywheelVelocity, double feederPower) {
        this.shooterSubsystem = shooterSubsystem;
        this.feederSubsystem = feederSubsystem;
        this.hopperSubsystem = hopperSubsystem;
        this.feederPower = feederPower;
        this.flywheelVelocity = flywheelVelocity;

        addRequirements(shooterSubsystem, feederSubsystem, hopperSubsystem);
    }

    @Override
    public void execute() {
        shooterSubsystem.setShooterFlywheel(flywheelVelocity);

        if (shooterSubsystem.getVelocity().isNear(flywheelVelocity, SHOOTER_FLYWHEEL_SPEED_TOLERANCE)) {
            feederSubsystem.setShooterFeeder(feederPower);
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
