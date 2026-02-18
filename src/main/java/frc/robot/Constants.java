package frc.robot;

public final class Constants {

  public final class FuelConstants{
    public static final int INTAKE_ROLLER_ID = 1;
    public static final int HOPPER_ID = 2;
    public static final int INTAKE_ARM_ID = 3;
    public static final int TOWER_ID = 4;

    public static final int INTAKE_ROLLER_CURRENT_LIMIT = 60;
    public static final int HOPPER_CURRENT_LIMIT = 60;
    public static final int INTAKE_ARM_CURRENT_LIMIT = 60;
    public static final int TOWER_CURRENT_LIMIT = 60;

    public static final double INTAKE_ROLLER_KP = 0.0001;
    public static final double INTAKE_ROLLER_KI = 0.0;
    public static final double INTAKE_ROLLER_KD = 0.0;
    public static final double INTAKE_ROLLER_RPM = 2000;

    public static final double HOPPER_KP = 0.0001;
    public static final double HOPPER_KI = 0.0;
    public static final double HOPPER_KD = 0.0;
    public static final double HOPPER_RPM = 2000;
    
    public static final double TOWER_KP = 0.0001;
    public static final double TOWER_KI = 0.0;
    public static final double TOWER_KD = 0.0;
    public static final double TOWER_RPM = 2000;

    public static final double INTAKE_ARM_KP = 0.1;
    public static final double INTAKE_ARM_KI = 0.0;
    public static final double INTAKE_ARM_KD = 0.0;

    public static final double INTAKE_ARM_GEAR_RATIO = 1;
    public static final double INTAKE_ARM_CONVERSION_FACTOR = 1;
    public static final double INTAKE_ARM_UP_DEGREES = 0;
    public static final double INTAKE_ARM_DOWN_DEGREES = -90.0;
  }

  public final class OperatorConstants{
    public static final int DRIVER_CONTROLLER_PORT = 0;
    public static final int OPERATOR_CONTROLLER_PORT = 1;
  }

}
