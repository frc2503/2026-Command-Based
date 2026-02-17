package frc.robot.subsystems;

import com.revrobotics.ResetMode;
import com.revrobotics.PersistMode;
import com.revrobotics.spark.SparkClosedLoopController;
import com.revrobotics.spark.SparkLowLevel.MotorType;
import com.revrobotics.spark.config.SparkMaxConfig;

import static frc.robot.Constants.FuelConstants.*;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import com.revrobotics.spark.SparkMax;

public class TurretSubsystem extends SubsystemBase{
    private final SparkMax spinMotor;
    private final SparkMax flywheelMotor;
    private final SparkMax aimMotor;
    private final SparkClosedLoopController spinController;
    private final SparkClosedLoopController flywheelController;
    private final SparkClosedLoopController aimController;

    public TurretSubsystem(){
        spinMotor = new SparkMax(TURRET_SPIN_ID, MotorType.kBrushless);
        flywheelMotor = new SparkMax(TURRET_FLYWHEEL_ID, MotorType.kBrushless);
        aimMotor = new SparkMax(TURRET_AIM_ID, MotorType.kBrushless);
        
        
        SparkMaxConfig spinConfig = new SparkMaxConfig();
        spinConfig.smartCurrentLimit(TURRET_SPIN_CURRENT_LIMIT);
        spinConfig.closedLoop.pid(TURRET_SPIN_KP, TURRET_SPIN_KI, TURRET_SPIN_KD);
        spinMotor.configure(spinConfig, ResetMode.kResetSafeParameters, PersistMode.kPersistParameters);
        spinController = spinMotor.getClosedLoopController();

        SparkMaxConfig flywheelConfig = new SparkMaxConfig();
        flywheelConfig.smartCurrentLimit(TURRET_FLYWHEEL_CURRENT_LIMIT);
        flywheelConfig.closedLoop.pid(TURRET_FLYWHEEL_KP, TURRET_FLYWHEEL_KI, TURRET_FLYWHEEL_KD);
        flywheelMotor.configure(flywheelConfig, ResetMode.kResetSafeParameters, PersistMode.kPersistParameters);
        flywheelController = flywheelMotor.getClosedLoopController();

        SparkMaxConfig aimConfig = new SparkMaxConfig();
        aimConfig.smartCurrentLimit(TURRET_AIM_CURRENT_LIMIT);
        aimConfig.closedLoop.pid(TURRET_AIM_KP, TURRET_AIM_KI, TURRET_AIM_KD);
        aimMotor.configure(aimConfig, ResetMode.kResetSafeParameters, PersistMode.kNoPersistParameters);
        aimController = aimMotor.getClosedLoopController();
    }


    public void SetSpinVelocity(double rpm){
        spinController.setSetpoint(rpm , SparkMax.ControlType.kVelocity);
    }
    public void SetFlywheelVelocity(double rpm){
        flywheelController.setSetpoint(rpm, SparkMax.ControlType.kVelocity);
    }
    public void SetAimVelocity(double degrees){
        aimController.setSetpoint(TURRET_AIM_DEGREES, SparkMax.ControlType.kPosition);
    }


    public void stop() {
        spinMotor.set(0);
        flywheelMotor.set(0);
        aimMotor.set(0);
    }
}