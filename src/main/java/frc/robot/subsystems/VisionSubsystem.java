package frc.robot.subsystems;

import static edu.wpi.first.units.Units.Degree;
import static edu.wpi.first.units.Units.DegreesPerSecond;
import static edu.wpi.first.units.Units.Radian;

import java.util.Optional;

import edu.wpi.first.math.geometry.Rotation3d;
import edu.wpi.first.units.measure.AngularVelocity;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;
import frc.robot.LimelightHelpers;
import frc.robot.LimelightHelpers.PoseEstimate;

public class VisionSubsystem extends SubsystemBase {
    public Optional<PoseEstimate> getPoseEstimate(Rotation3d rotation, AngularVelocity yawRate) {
        if (Math.abs(yawRate.in(DegreesPerSecond)) > 360) {
            return Optional.empty();
        }
        
        LimelightHelpers.SetRobotOrientation(Constants.LEFT_LIMELIGHT_NAME,
                                             Radian.of(rotation.getZ()).plus(Degree.of(90)).in(Degree),
                                             yawRate.in(DegreesPerSecond),
                                             Radian.of(rotation.getY()).in(Degree),
                                             0,
                                             Radian.of(rotation.getX()).in(Degree),
                                             0);
        PoseEstimate leftEstimate = LimelightHelpers.getBotPoseEstimate_wpiBlue_MegaTag2(Constants.LEFT_LIMELIGHT_NAME);

        LimelightHelpers.SetRobotOrientation(Constants.RIGHT_LIMELIGHT_NAME,
                                             Radian.of(rotation.getZ()).plus(Degree.of(90)).in(Degree),
                                             yawRate.in(DegreesPerSecond),
                                             Radian.of(rotation.getY()).in(Degree),
                                             0,
                                             Radian.of(rotation.getX()).in(Degree),
                                             0);
        PoseEstimate rightEstimate = LimelightHelpers.getBotPoseEstimate_wpiBlue_MegaTag2(Constants.RIGHT_LIMELIGHT_NAME);

        if (leftEstimate.tagCount + rightEstimate.tagCount > 0) {
            if (Math.abs(leftEstimate.timestampSeconds - rightEstimate.timestampSeconds) > 0.02) {
                if (leftEstimate.tagCount > 0 && leftEstimate.timestampSeconds > rightEstimate.timestampSeconds) {
                    return Optional.of(leftEstimate);
                } else if (rightEstimate.tagCount > 0) {
                    return Optional.of(rightEstimate);
                } else {
                    return Optional.empty();
                }
            } else if (leftEstimate.tagCount >= rightEstimate.tagCount) {
                return Optional.of(leftEstimate);
            } else {
                return Optional.of(rightEstimate);
            }
        } else {
            return Optional.empty();
        }
    }
}
