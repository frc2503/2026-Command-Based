package frc.robot;

public final class Constants {

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
