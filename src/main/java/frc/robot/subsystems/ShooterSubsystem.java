package frc.robot.subsystems;

import com.ctre.phoenix6.configs.TalonFXConfiguration;
import com.ctre.phoenix6.hardware.TalonFX;
import com.ctre.phoenix6.signals.NeutralModeValue;

import static frc.robot.Constants.ShooterConstants.*;
import edu.wpi.first.wpilibj2.command.SubsystemBase;


public class ShooterSubsystem extends SubsystemBase {
    private final TalonFX shooterFlywheelMotor;

    public ShooterSubsystem() {
        shooterFlywheelMotor = new TalonFX(SHOOTER_FLYWHEEL_CAN_ID);

        TalonFXConfiguration shooterFlywheelConfig = new TalonFXConfiguration();
        shooterFlywheelConfig.MotorOutput.NeutralMode = NeutralModeValue.Coast;
        shooterFlywheelConfig.CurrentLimits.StatorCurrentLimit = SHOOTER_FLYWHEEL_CURRENT_LIMIT;
        shooterFlywheelMotor.getConfigurator().apply(shooterFlywheelConfig);

    }

    public void setShooterFlywheel(double power) {
        shooterFlywheelMotor.set(power);
    }

    public void stop() {
        shooterFlywheelMotor.set(0);
    }
 
}
