package frc.robot;

public final class Constants {

  public final class FuelConstants{
    public static final int INTAKE_ID = 1;
    public static final int HOPPER_ID = 2;
    public static final int INTAKE_ACTION_ID = 3;
    public static final int TOWER_ID = 4;

    public static final int INTAKE_CURRENT_LIMIT = 60;
    public static final int HOPPER_CURRENT_LIMIT = 60;
    public static final int INTAKE_ACTION_CURRENT_LIMIT = 60;
    public static final int TOWER_CURRENT_LIMIT = 60;

    public static final double INTAKE_KP = 0.0001;
    public static final double INTAKE_KI = 0.0;
    public static final double INTAKE_KD = 0.0;
    public static final double INTAKE_RPM = 2000;

    public static final double HOPPER_KP = 0.0001;
    public static final double HOPPER_KI = 0.0;
    public static final double HOPPER_KD = 0.0;
    public static final double HOPPER_RPM = 2000;
    
    public static final double TOWER_KP = 0.0001;
    public static final double TOWER_KI = 0.0;
    public static final double TOWER_KD = 0.0;
    public static final double TOWER_RPM = 2000;

    public static final double INTAKE_ACTION_KP = 0.1;
    public static final double INTAKE_ACTION_KI = 0.0;
    public static final double INTAKE_ACTION_KD = 0.0;

    public static final double INTAKE_ACTION_UP_DEGREES = 90.0;
    public static final double INTAKE_ACTION_DOWN_DEGREES = -90.0;
  
  }

  public final class OperatorConstants{
    public static final int DRIVER_CONTROLLER_PORT = 0;
    public static final int OPERATOR_CONTROLLER_PORT = 1;
  }

}
