package frc.robot;

import edu.wpi.first.apriltag.AprilTagFieldLayout;
import edu.wpi.first.apriltag.AprilTagFields;
import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.first.units.Units;
import edu.wpi.first.units.measure.AngularVelocity;
import edu.wpi.first.units.measure.Distance;
import edu.wpi.first.units.measure.LinearVelocity;

public final class Constants {
  public static final NetworkTableInstance NETWORK_TABLE = NetworkTableInstance.getDefault();

  public static final LinearVelocity MAXIMUM_VELOCITY = Units.MetersPerSecond.of(4);
  public static final AngularVelocity MAXIMUM_ANGULAR_VELOCITY = Units.DegreesPerSecond.of(270);
  public static final LinearVelocity MAXIMUM_MODULE_VELOCITY = Units.MetersPerSecond.of(5);

  public static final AprilTagFieldLayout APRIL_TAG_LAYOUT = AprilTagFieldLayout.loadField(AprilTagFields.k2026RebuiltAndymark);
  public static final Distance FIELD_LENGTH = Units.Inch.of(651.22);
  public static final Distance FIELD_WIDTH = Units.Inch.of(317.69);
  public static final Distance ALLIANCE_ZONE_WIDTH = Units.Inch.of(182.11);

  public static final String LEFT_LIMELIGHT_NAME = "left";
  public static final String RIGHT_LIMELIGHT_NAME = "right";

  public final class FuelConstants{

    public static final int INTAKE_ROLLER_ID = 15;
    public static final int HOPPER_ID = 7;
    public static final int INTAKE_ARM_ID = 14;
    public static final int LAUNCH_1_ID = 1;
    public static final int LAUNCH_2_ID = 2;

    public static final int INTAKE_ROLLER_CURRENT_LIMIT = 60;
    public static final int HOPPER_CURRENT_LIMIT = 60;
    public static final int INTAKE_ARM_CURRENT_LIMIT = 60;
    public static final int LAUNCH_1_CURRENT_LIMIT = 60;
    public static final int LAUNCH_2_CURRENT_LIMIT = 60;

    public static final double INTAKE_ROLLER_VOLTAGE = 1;
    public static final double HOPPER_VOLTAGE = 1;
    public static final double LAUNCH_1_VOLTAGE = 1;
    public static final double LAUNCH_2_VOLTAGE = -1;

    public static final double INTAKE_ARM_KP = 0.1;
    public static final double INTAKE_ARM_KI = 0.0;
    public static final double INTAKE_ARM_KD = 0.0;

    public static final double INTAKE_ARM_GEAR_RATIO = 20;
    public static final double INTAKE_ARM_UP_DEGREES = 0;
    public static final double INTAKE_ARM_DOWN_DEGREES = -90.0;
  }

  public final class OperatorConstants{
    public static final int DRIVER_CONTROLLER_PORT = 0;
    public static final int OPERATOR_CONTROLLER_PORT = 1;
  }

}
