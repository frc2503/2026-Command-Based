package frc.robot.subsystems;

import com.revrobotics.ResetMode;
import com.revrobotics.PersistMode;
import com.revrobotics.spark.SparkLowLevel.MotorType;
import com.revrobotics.spark.config.SparkBaseConfig.IdleMode;
import com.revrobotics.spark.config.SparkMaxConfig;

import static frc.robot.Constants.ShooterConstants.*;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

import com.revrobotics.spark.SparkMax;

public class FeederSubsystem extends SubsystemBase {
    private final SparkMax shooterFeederMotor;

    public FeederSubsystem() {
        shooterFeederMotor = new SparkMax(SHOOTER_FEEDER_CAN_ID, MotorType.kBrushless);

        SparkMaxConfig shooterFeederConfig = new SparkMaxConfig();
        shooterFeederConfig.idleMode(IdleMode.kBrake);
        shooterFeederConfig.smartCurrentLimit(SHOOTER_FEEDER_CURRENT_LIMIT);
        shooterFeederMotor.configure(shooterFeederConfig, ResetMode.kResetSafeParameters, PersistMode.kPersistParameters);
    }

    public void setShooterFeeder(double power) {
        shooterFeederMotor.set(power);
    }

    public void stop() {
        shooterFeederMotor.set(0);
    }
 
}
