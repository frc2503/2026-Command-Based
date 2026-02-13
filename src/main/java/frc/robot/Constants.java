package frc.robot;

public final class Constants {

  public final class FuelConstants{
    public static final int INTAKE_ID = 1;
    public static final int CONVEYOR_ID = 2;
    public static final int HOPPER_ID = 3;
    public static final int FEEDER_ID = 4;

    public static final int INTAKE_CURRENT_LIMIT = 60;
    public static final int CONVEYOR_CURRENT_LIMIT = 60;
    public static final int HOPPER_CURRENT_LIMIT = 60;
    public static final int FEEDER_CURRENT_LIMIT = 60;

    public static final double INTAKE_KP = 0.0001;
    public static final double INTAKE_KI = 0.0;
    public static final double INTAKE_KD = 0.0;
    public static final double INTAKE_RPM = 2000;

    public static final double CONVEYOR_KP = 0.0001;
    public static final double CONVEYOR_KI = 0.0;
    public static final double CONVEYOR_KD = 0.0;
    public static final double CONVEYOR_RPM = 2000;
    
    public static final double FEEDER_KP = 0.0001;
    public static final double FEEDER_KI = 0.0;
    public static final double FEEDER_KD = 0.0;
    public static final double FEEDER_RPM = 2000;

    public static final double HOPPER_KP = 0.1;
    public static final double HOPPER_KI = 0.0;
    public static final double HOPPER_KD = 0.0;

    public static final double HOPPER_UP_DEGREES = 90.0;
    public static final double HOPPER_DOWN_DEGREES = -90.0;
  
  }

  public final class OperatorConstants{
    public static final int DRIVER_CONTROLLER_PORT = 0;
    public static final int OPERATOR_CONTROLLER_PORT = 1;
}

  }


