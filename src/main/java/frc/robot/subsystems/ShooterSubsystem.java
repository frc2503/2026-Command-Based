package frc.robot.subsystems;

import com.revrobotics.ResetMode;
import com.revrobotics.PersistMode;
import com.revrobotics.spark.SparkLowLevel.MotorType;
import com.revrobotics.spark.config.SparkBaseConfig.IdleMode;
import com.revrobotics.spark.config.SparkMaxConfig;

import static frc.robot.Constants.ShooterConstants.*;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import com.revrobotics.spark.SparkMax;

public class ShooterSubsystem extends SubsystemBase {
    private final SparkMax shooterFlywheelMotor;
    private final SparkMax shooterFeederMotor;

    public ShooterSubsystem() {
        shooterFlywheelMotor = new SparkMax(SHOOTER_FLYWHEEL_CAN_ID, MotorType.kBrushless);
        shooterFeederMotor = new SparkMax(SHOOTER_FEEDER_CAN_ID, MotorType.kBrushless);

        SparkMaxConfig shooterFlywheelConfig = new SparkMaxConfig();
        shooterFlywheelConfig.idleMode(IdleMode.kCoast);
        shooterFlywheelConfig.smartCurrentLimit(SHOOTER_FLYWHEEL_CURRENT_LIMIT);
        shooterFlywheelMotor.configure(shooterFlywheelConfig, ResetMode.kResetSafeParameters, PersistMode.kPersistParameters);

        SparkMaxConfig shooterFeederConfig = new SparkMaxConfig();
        shooterFeederConfig.idleMode(IdleMode.kBrake);
        shooterFeederConfig.smartCurrentLimit(SHOOTER_FEEDER_CURRENT_LIMIT);
        shooterFeederMotor.configure(shooterFeederConfig, ResetMode.kResetSafeParameters, PersistMode.kPersistParameters);
    }

    public void setShooterFlywheel(double power) {
        shooterFlywheelMotor.setVoltage(power);
    }
    public void setShooterFeeder(double power) {
        shooterFeederMotor.setVoltage(power);
    }
    

    public void stop() {
        shooterFlywheelMotor.set(0);
        shooterFeederMotor.set(0);
    }
}
