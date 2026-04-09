package frc.robot.subsystems;

import static edu.wpi.first.units.Units.Degree;
import static edu.wpi.first.units.Units.DegreesPerSecond;
import static edu.wpi.first.units.Units.Meter;
import static edu.wpi.first.units.Units.MetersPerSecond;
import static edu.wpi.first.units.Units.Radians;
import static edu.wpi.first.units.Units.RadiansPerSecond;

import java.io.File;
import java.io.IOException;
import java.util.Optional;

import com.pathplanner.lib.auto.AutoBuilder;
import com.pathplanner.lib.config.PIDConstants;
import com.pathplanner.lib.config.RobotConfig;
import com.pathplanner.lib.controllers.PPHolonomicDriveController;

import edu.wpi.first.math.controller.ProfiledPIDController;
import edu.wpi.first.math.geometry.Pose2d;
import edu.wpi.first.math.geometry.Rotation2d;
import edu.wpi.first.math.geometry.Translation2d;
import edu.wpi.first.math.kinematics.ChassisSpeeds;
import edu.wpi.first.math.trajectory.TrapezoidProfile;
import edu.wpi.first.networktables.StructPublisher;
import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.Filesystem;
import edu.wpi.first.wpilibj.RobotBase;
import edu.wpi.first.wpilibj.DriverStation.Alliance;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;
import static frc.robot.Constants.AutoConstants.*;
import frc.robot.LimelightHelpers.PoseEstimate;
import swervelib.SwerveDrive;
import swervelib.parser.SwerveParser;
import swervelib.telemetry.SwerveDriveTelemetry;
import swervelib.telemetry.SwerveDriveTelemetry.TelemetryVerbosity;

public class SwerveSubsystem extends SubsystemBase {
    private SwerveDrive swerveDrive;
    private VisionSubsystem visionSubsystem;
    private Optional<Rotation2d> target = Optional.empty();
    private ProfiledPIDController anglePidController;

    private StructPublisher<Pose2d> posePublisher;

    public SwerveSubsystem(VisionSubsystem visionSubsystem) {
        SwerveDriveTelemetry.verbosity = TelemetryVerbosity.HIGH;
        try {
            swerveDrive = new SwerveParser(new File(Filesystem.getDeployDirectory(), "swerve")).createSwerveDrive(Constants.MAXIMUM_MODULE_VELOCITY.in(MetersPerSecond));
        } catch (IOException e) {
            System.out.println("ERROR: Failed to load swerve configs");
        }

        swerveDrive.setCosineCompensator(RobotBase.isReal());
        swerveDrive.resetOdometry(new Pose2d(new Translation2d(2, 2), new Rotation2d()));

        anglePidController = new ProfiledPIDController(Constants.SWERVE_ANGLE_KP, Constants.SWERVE_ANGLE_KI, Constants.SWERVE_ANGLE_KD, new TrapezoidProfile.Constraints(Constants.MAXIMUM_ANGULAR_VELOCITY.in(DegreesPerSecond), 10));
        anglePidController.setTolerance(Constants.SWERVE_ANGLE_TOLERANCE.in(Degree));
        anglePidController.enableContinuousInput(0, 360);

        this.visionSubsystem = visionSubsystem;

        posePublisher = Constants.NETWORK_TABLE.getStructTopic("RobotPose", Pose2d.struct).publish();
        configureAutoBuilder();
    }

    public void drive(double x, double y, double rot, boolean fieldOriented) {
        Translation2d translation = new Translation2d(y, x).times(Constants.MAXIMUM_VELOCITY.in(MetersPerSecond));
        rot *= Constants.MAXIMUM_ANGULAR_VELOCITY.in(RadiansPerSecond);

        if (target.isPresent()) {
            rot = anglePidController.calculate(getPose().getRotation().getDegrees(), target.get().getDegrees());
        }

        Translation2d swerveVelocity = new Translation2d(swerveDrive.getFieldVelocity().vxMetersPerSecond, swerveDrive.getFieldVelocity().vyMetersPerSecond);
        double swerveAngularVelocity = swerveDrive.getFieldVelocity().omegaRadiansPerSecond;

        if (translation.equals(Translation2d.kZero) && rot == 0
            && swerveVelocity.getNorm() < Constants.MINIMUM_MODULE_VELOCITY.in(MetersPerSecond)
            && Math.abs(swerveAngularVelocity) < Constants.MINIMUM_ANGULAR_VELOCITY.in(RadiansPerSecond)) {
            swerveDrive.lockPose();
        } else {
            swerveDrive.drive(translation, rot, fieldOriented, false);
        }
    }

    public void drive(ChassisSpeeds chassisSpeeds) {
        swerveDrive.drive(chassisSpeeds);
    }

    public void stop() {
        swerveDrive.drive(Translation2d.kZero, 0, false, false);
    }

    public boolean isInAllianceZone() {
        if (isOnBlueAlliance()) {
            return swerveDrive.getPose().getMeasureX().in(Meter) <= Constants.ALLIANCE_ZONE_WIDTH.in(Meter);
        } else {
            return swerveDrive.getPose().getMeasureX().in(Meter) >= Constants.FIELD_LENGTH.in(Meter) - Constants.ALLIANCE_ZONE_WIDTH.in(Meter);
        }
    }

    public boolean isOnBlueAlliance() {
        Optional<Alliance> alliance = DriverStation.getAlliance();
        if (alliance.isPresent()) {
            if (alliance.get() == DriverStation.Alliance.Blue) {
                System.out.println("Blue");
            } else {
                System.out.println("Red");
            }
            return alliance.get() == DriverStation.Alliance.Blue;
        }
        System.out.println("No Alliance: Red");
        return false;
    }

    public Pose2d getPose() {
        return swerveDrive.getPose();
    }

    public void resetPose(Pose2d pose) {
        swerveDrive.resetOdometry(pose);
    }

    public Translation2d getFieldVelocity() {
        return new Translation2d(swerveDrive.getFieldVelocity().vxMetersPerSecond, swerveDrive.getFieldVelocity().vyMetersPerSecond);
    }

    public ChassisSpeeds getRobotRelativeSpeeds() {
        return swerveDrive.getRobotVelocity();
    }

    public boolean setTarget(Rotation2d rotation2d) {
        target = Optional.of(rotation2d);
        return anglePidController.atSetpoint();
    }

    public void cancelTargeting() {
        target = Optional.empty();
    }

    private void configureAutoBuilder() {
        RobotConfig config;
        try{
            config = RobotConfig.fromGUISettings();

            AutoBuilder.configure(
                this::getPose,
                this::resetPose,
                this::getRobotRelativeSpeeds,
                (speeds, feedforwards) -> drive(speeds),
                new PPHolonomicDriveController(
                        new PIDConstants(AUTO_TRANSLATION_KP, AUTO_TRANSLATION_KI, AUTO_TRANSLATION_KD), // Translation PID constants
                        new PIDConstants(AUTO_ROTATION_KP, AUTO_ROTATION_KI, AUTO_ROTATION_KD) // Rotation PID constants
                ),
                config,
                () -> { return !isOnBlueAlliance(); },
                this
            );
        } catch (Exception e) {
            e.printStackTrace();
        }
  }

    @Override
    public void periodic() {
        Optional<PoseEstimate> estimate = visionSubsystem.getPoseEstimate(swerveDrive.getGyro().getRotation3d(), swerveDrive.getGyro().getYawAngularVelocity());

        if (estimate.isPresent()) {
            swerveDrive.addVisionMeasurement(estimate.get().pose, estimate.get().timestampSeconds);
        }

        posePublisher.set(getPose());
    }
}