package frc.robot;

import edu.wpi.first.apriltag.AprilTagFieldLayout;
import edu.wpi.first.apriltag.AprilTagFields;
import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.first.units.Units;
import edu.wpi.first.units.measure.Angle;
import edu.wpi.first.units.measure.AngularVelocity;
import edu.wpi.first.units.measure.Distance;
import edu.wpi.first.units.measure.LinearVelocity;
import edu.wpi.first.units.measure.Time;

public final class Constants {
  public static final NetworkTableInstance NETWORK_TABLE = NetworkTableInstance.getDefault();

  public static final LinearVelocity MAXIMUM_VELOCITY = Units.MetersPerSecond.of(4);
  public static final AngularVelocity MAXIMUM_ANGULAR_VELOCITY = Units.DegreesPerSecond.of(420);
  public static final LinearVelocity MAXIMUM_MODULE_VELOCITY = Units.MetersPerSecond.of(5);
  public static final AngularVelocity MINIMUM_ANGULAR_VELOCITY = Units.DegreesPerSecond.of(5);
  public static final LinearVelocity MINIMUM_MODULE_VELOCITY = Units.MetersPerSecond.of(.8);

  public static final Time FUEL_FLIGHT_TIME = Units.Second.of(3);

  public static final AprilTagFieldLayout APRIL_TAG_LAYOUT = AprilTagFieldLayout.loadField(AprilTagFields.k2026RebuiltAndymark);
  public static final Distance FIELD_LENGTH = Units.Inch.of(651.22);
  public static final Distance FIELD_WIDTH = Units.Inch.of(317.69);
  public static final Distance ALLIANCE_ZONE_WIDTH = Units.Inch.of(182.11);

  public static final double SWERVE_ANGLE_KP = .3;
  public static final double SWERVE_ANGLE_KI = 0;
  public static final double SWERVE_ANGLE_KD = 0;
  public static final Angle SWERVE_ANGLE_TOLERANCE = Units.Degree.of(1);

  public static final String LEFT_LIMELIGHT_NAME = "left";
  public static final String RIGHT_LIMELIGHT_NAME = "right";

  public final class IntakeConstants {
    public static final int INTAKE_ROLLER_ID = 15;
    public static final double INTAKE_ROLLER_POWER = 0.8;
    public static final int INTAKE_ROLLER_CURRENT_LIMIT = 40;
    public static final int INTAKE_ARM_ID = 14;
    public static final double INTAKE_ARM_ENCODER_OFFSET = 0.69;
    public static final Angle INTAKE_ARM_UP_ANGLE = Units.Rotation.of(0.080);
    public static final Angle INTAKE_ARM_DOWN_ANGLE = Units.Rotation.of(0.45);
    public static final double INTAKE_ARM_GEAR_RATIO = 20;
    public static final int INTAKE_ARM_CURRENT_LIMIT = 60;
    public static final Angle INTAKE_ARM_ALLOWED_ERROR = Units.Degrees.of(1);

    public static final double INTAKE_ARM_KP = .9;
    public static final double INTAKE_ARM_KI = 0.00001;
    public static final double INTAKE_ARM_KD = 7;
  }

  public final class HopperConstants {
    public static final int HOPPER_ID = 7;
    public static final double HOPPER_POWER = 0.6;
    public static final int HOPPER_CURRENT_LIMIT = 30;
  }

  public final class ShooterConstants {
    public static final int SHOOTER_FLYWHEEL_CAN_ID = 4;
    public static final AngularVelocity SHOOTER_FLYWHEEL_SPEED = Units.RotationsPerSecond.of(50);
    public static final AngularVelocity SHOOTER_FLYWHEEL_SPEED_TOLERANCE = Units.RotationsPerSecond.of(5);
    public static final double SHOOTER_FLYWHEEL_KS = 0.1;
    public static final double SHOOTER_FLYWHEEL_KV = 0.12;
    public static final double SHOOTER_FLYWHEEL_KP = 0.11;
    public static final double SHOOTER_FLYWHEEL_KI = 0;
    public static final double SHOOTER_FLYWHEEL_KD = 0;
    public static final int SHOOTER_FLYWHEEL_CURRENT_LIMIT = 60;

    
    public static final int SHOOTER_FEEDER_CAN_ID = 3;
    public static final double SHOOTER_FEEDER_POWER = .3;
    public static final int SHOOTER_FEEDER_CURRENT_LIMIT = 60;
  }

  public final class OperatorConstants {
    public static final int DRIVER_CONTROLLER_PORT = 0;
    //public static final int OPERATOR_CONTROLLER_PORT = 1;
  }

  public final class AutoConstants {
    public static final double AUTO_TRANSLATION_KP = 1;
    public static final double AUTO_TRANSLATION_KI = 0.0;
    public static final double AUTO_TRANSLATION_KD = 0.0;

    public static final double AUTO_ROTATION_KP = 1;
    public static final double AUTO_ROTATION_KI = 0.0;
    public static final double AUTO_ROTATION_KD = 0.0;
  }
}
