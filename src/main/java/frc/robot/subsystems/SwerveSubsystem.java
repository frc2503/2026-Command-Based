package frc.robot.subsystems;

import static edu.wpi.first.units.Units.Degree;
import static edu.wpi.first.units.Units.DegreesPerSecond;
import static edu.wpi.first.units.Units.Meter;
import static edu.wpi.first.units.Units.MetersPerSecond;
import static edu.wpi.first.units.Units.RadiansPerSecond;

import java.io.File;
import java.io.IOException;
import java.util.Optional;

import edu.wpi.first.math.controller.ProfiledPIDController;
import edu.wpi.first.math.geometry.Pose2d;
import edu.wpi.first.math.geometry.Rotation2d;
import edu.wpi.first.math.geometry.Translation2d;
import edu.wpi.first.math.trajectory.TrapezoidProfile;
import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.first.networktables.StructPublisher;
import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.Filesystem;
import edu.wpi.first.wpilibj.RobotBase;
import edu.wpi.first.wpilibj.DriverStation.Alliance;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;
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
    }

    public void drive(double x, double y, double rot, boolean fieldOriented) {
        Translation2d translation = new Translation2d(y, x);
        
        //Apply deadzones
        translation = translation.getNorm() > 0.075 ? translation.times(Constants.MAXIMUM_VELOCITY.in(MetersPerSecond)) : Translation2d.kZero;
        rot = Math.abs(rot) > 0.075 ? rot * Constants.MAXIMUM_ANGULAR_VELOCITY.in(RadiansPerSecond) : 0;

        if (target.isEmpty()) {
            swerveDrive.drive(translation, rot, fieldOriented, false);
        } else {
            swerveDrive.drive(translation, -anglePidController.calculate(getPose().getRotation().getDegrees(), target.get().getDegrees()), fieldOriented, false);
        }
    }

    public void stop() {
        swerveDrive.drive(Translation2d.kZero, 0, false, false);
    }

    public boolean isInAllianceZone() {
        if (DriverStation.getAlliance().get() == Alliance.Red) {
            return swerveDrive.getPose().getMeasureX().in(Meter) >= Constants.FIELD_LENGTH.in(Meter) - Constants.ALLIANCE_ZONE_WIDTH.in(Meter);
        } else {
            return swerveDrive.getPose().getMeasureX().in(Meter) <= Constants.ALLIANCE_ZONE_WIDTH.in(Meter);
        }
    }

    public Pose2d getPose() {
        return swerveDrive.getPose();
    }

    public Translation2d getFieldVelocity() {
        return new Translation2d(swerveDrive.getFieldVelocity().vxMetersPerSecond, swerveDrive.getFieldVelocity().vyMetersPerSecond);
    }

    public boolean setTarget(Rotation2d rotation2d) {
        target = Optional.of(rotation2d);
        return anglePidController.atSetpoint();
    }

    public void cancelTargeting() {
        target = Optional.empty();
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