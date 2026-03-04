package frc.robot.commands;

import static edu.wpi.first.units.Units.Meter;
import static edu.wpi.first.units.Units.Second;
import static frc.robot.Constants.ShooterConstants.*;
import static frc.robot.Constants.HopperConstants.*;

import edu.wpi.first.math.geometry.Translation2d;
import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.DriverStation.Alliance;
import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.Constants;
import frc.robot.subsystems.SwerveSubsystem;
import frc.robot.subsystems.FeederSubsystem;
import frc.robot.subsystems.HopperSubsystem;
import frc.robot.subsystems.ShooterSubsystem;

public class TargetCommand extends Command {
    private ShooterSubsystem shooterSubsystem;
    private FeederSubsystem feederSubsystem;
    private HopperSubsystem hopperSubsystem;
    private SwerveSubsystem swerveSubsystem;

    public TargetCommand(ShooterSubsystem shooterSubsystem, FeederSubsystem feederSubsystem, HopperSubsystem hopperSubsystem, SwerveSubsystem swerveSubsystem) {
        this.shooterSubsystem = shooterSubsystem;
        this.feederSubsystem = feederSubsystem;
        this.hopperSubsystem = hopperSubsystem;
        this.swerveSubsystem = swerveSubsystem;

        addRequirements(shooterSubsystem, feederSubsystem, hopperSubsystem);
    }

    @Override
    public void execute() {
        // if (shooterSubsystem.isHubActive() && swerveSubsystem.isInAllianceZone()) {
            shooterSubsystem.setShooterFlywheel(SHOOTER_FLYWHEEL_SPEED);

            Translation2d hubPosition;
            if (DriverStation.getAlliance().get() == Alliance.Red) {
                hubPosition = new Translation2d(Constants.FIELD_LENGTH.in(Meter) - Constants.ALLIANCE_ZONE_WIDTH.in(Meter), Constants.FIELD_WIDTH.in(Meter) / 2);
            } else {
                hubPosition = new Translation2d(Constants.ALLIANCE_ZONE_WIDTH.in(Meter), Constants.FIELD_WIDTH.in(Meter) / 2);
            }
            //hubPosition = hubPosition.minus(swerveSubsystem.getFieldVelocity().times(Constants.FUEL_FLIGHT_TIME.in(Second)));

            if (swerveSubsystem.setTarget(hubPosition.minus(swerveSubsystem.getPose().getTranslation()).getAngle()) && shooterSubsystem.getVelocity().isNear(SHOOTER_FLYWHEEL_SPEED, SHOOTER_FLYWHEEL_SPEED_TOLERANCE)) {
                System.out.println(hubPosition.minus(swerveSubsystem.getPose().getTranslation()).getAngle());
                feederSubsystem.setShooterFeeder(SHOOTER_FEEDER_POWER);
                hopperSubsystem.setHopper(HOPPER_POWER);
            }
        // } else {
        //     shooterSubsystem.stop();
        //     feederSubsystem.stop();
        //     hopperSubsystem.stop();
        //     swerveSubsystem.cancelTargeting();
        // }
    }

    @Override
    public void end(boolean interrupted) {
        shooterSubsystem.stop();
        feederSubsystem.stop();
        hopperSubsystem.stop();
        swerveSubsystem.cancelTargeting();
    }
}
