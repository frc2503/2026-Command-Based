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
  public static final AngularVelocity MAXIMUM_ANGULAR_VELOCITY = Units.DegreesPerSecond.of(270);
  public static final LinearVelocity MAXIMUM_MODULE_VELOCITY = Units.MetersPerSecond.of(5);

  public static final Time FUEL_FLIGHT_TIME = Units.Second.of(3);

  public static final AprilTagFieldLayout APRIL_TAG_LAYOUT = AprilTagFieldLayout.loadField(AprilTagFields.k2026RebuiltAndymark);
  public static final Distance FIELD_LENGTH = Units.Inch.of(651.22);
  public static final Distance FIELD_WIDTH = Units.Inch.of(317.69);
  public static final Distance ALLIANCE_ZONE_WIDTH = Units.Inch.of(182.11);

  public static final String LEFT_LIMELIGHT_NAME = "left";
  public static final String RIGHT_LIMELIGHT_NAME = "right";

  public final class IntakeConstants {
    public static final int INTAKE_ROLLER_ID = 15;
    public static final double INTAKE_ROLLER_POWER = 0.1;
    public static final int INTAKE_ROLLER_CURRENT_LIMIT = 20;

    public static final int INTAKE_ARM_ID = 14;
    public static final Angle INTAKE_ARM_UP_ANGLE = Units.Degree.of(0);
    // Should probably be 110, set low for testing
    public static final Angle INTAKE_ARM_DOWN_ANGLE = Units.Degree.of(50);
    public static final double INTAKE_ARM_GEAR_RATIO = 20;
    public static final int INTAKE_ARM_CURRENT_LIMIT = 60;
    public static final Angle INTAKE_ARM_ALLOWED_ERROR = Units.Degrees.of(1);

    public static final double INTAKE_ARM_KP = 2.5;
    public static final double INTAKE_ARM_KI = 0.0;
    public static final double INTAKE_ARM_KD = 0.0;
  }

  public final class HopperConstants {
    public static final int HOPPER_ID = 7;
    public static final double HOPPER_POWER = 0.6;
    public static final int HOPPER_CURRENT_LIMIT = 20;
  }

  public final class ShooterConstants {
    public static final int SHOOTER_FLYWHEEL_CAN_ID = 1;
    public static final double SHOOTER_FLYWHEEL_POWER = 1;
    public static final int SHOOTER_FLYWHEEL_CURRENT_LIMIT = 60;
    
    public static final int SHOOTER_FEEDER_CAN_ID = 2;
    public static final double SHOOTER_FEEDER_POWER = -1;
    public static final int SHOOTER_FEEDER_CURRENT_LIMIT = 60;
  }

  public final class OperatorConstants{
    public static final int DRIVER_CONTROLLER_PORT = 0;
    public static final int OPERATOR_CONTROLLER_PORT = 1;
  }

}
