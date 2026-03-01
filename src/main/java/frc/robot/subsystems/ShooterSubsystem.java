package frc.robot.subsystems;

import com.revrobotics.ResetMode;
import com.ctre.phoenix6.configs.TalonFXConfiguration;
import com.ctre.phoenix6.hardware.TalonFX;
import com.ctre.phoenix6.signals.NeutralModeValue;
import com.revrobotics.PersistMode;
import com.revrobotics.spark.SparkLowLevel.MotorType;
import com.revrobotics.spark.config.SparkBaseConfig.IdleMode;
import com.revrobotics.spark.config.SparkMaxConfig;

import static frc.robot.Constants.ShooterConstants.*;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

import com.revrobotics.spark.SparkMax;

public class ShooterSubsystem extends SubsystemBase {
    private final TalonFX shooterFlywheelMotor;
    private final SparkMax shooterFeederMotor;

    public ShooterSubsystem() {
        shooterFlywheelMotor = new TalonFX(SHOOTER_FLYWHEEL_CAN_ID);
        shooterFeederMotor = new SparkMax(SHOOTER_FEEDER_CAN_ID, MotorType.kBrushless);

        TalonFXConfiguration shooterFlywheelConfig = new TalonFXConfiguration();
        shooterFlywheelConfig.MotorOutput.NeutralMode = NeutralModeValue.Coast;
        shooterFlywheelConfig.CurrentLimits.StatorCurrentLimit = SHOOTER_FLYWHEEL_CURRENT_LIMIT;
        shooterFlywheelMotor.getConfigurator().apply(shooterFlywheelConfig);

        SparkMaxConfig shooterFeederConfig = new SparkMaxConfig();
        shooterFeederConfig.idleMode(IdleMode.kBrake);
        shooterFeederConfig.smartCurrentLimit(SHOOTER_FEEDER_CURRENT_LIMIT);
        shooterFeederMotor.configure(shooterFeederConfig, ResetMode.kResetSafeParameters, PersistMode.kPersistParameters);
    }

    public void setShooterFlywheel(double power) {
        shooterFlywheelMotor.set(power);
    }
    public void setShooterFeeder(double power) {
        shooterFeederMotor.set(power);
    }

    public void stop() {
        shooterFlywheelMotor.set(0);
        shooterFeederMotor.set(0);
    }
}
