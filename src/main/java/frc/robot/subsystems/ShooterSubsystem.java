package frc.robot.subsystems;

import com.ctre.phoenix6.configs.Slot0Configs;
import com.ctre.phoenix6.configs.TalonFXConfiguration;
import com.ctre.phoenix6.controls.VelocityVoltage;
import com.ctre.phoenix6.hardware.TalonFX;
import com.ctre.phoenix6.signals.NeutralModeValue;

import static edu.wpi.first.units.Units.Second;
import static frc.robot.Constants.ShooterConstants.*;

import java.util.Optional;

import edu.wpi.first.units.measure.AngularVelocity;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.DriverStation.Alliance;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;


public class ShooterSubsystem extends SubsystemBase {
    private final TalonFX shooterFlywheelMotor;
    private final VelocityVoltage velocityControl;

    public ShooterSubsystem() {
        shooterFlywheelMotor = new TalonFX(SHOOTER_FLYWHEEL_CAN_ID);

        TalonFXConfiguration shooterFlywheelConfig = new TalonFXConfiguration();
        shooterFlywheelConfig.MotorOutput.NeutralMode = NeutralModeValue.Coast;
        shooterFlywheelConfig.CurrentLimits.StatorCurrentLimit = SHOOTER_FLYWHEEL_CURRENT_LIMIT;
        shooterFlywheelMotor.getConfigurator().apply(shooterFlywheelConfig);

        Slot0Configs pidConfig = new Slot0Configs();
        pidConfig.kS = SHOOTER_FLYWHEEL_KS;
        pidConfig.kV = SHOOTER_FLYWHEEL_KV;
        pidConfig.kP = SHOOTER_FLYWHEEL_KP;
        pidConfig.kI = SHOOTER_FLYWHEEL_KI;
        pidConfig.kD = SHOOTER_FLYWHEEL_KD;
        shooterFlywheelMotor.getConfigurator().apply(pidConfig);

        velocityControl = new VelocityVoltage(0).withSlot(0);
    }

    public void setShooterFlywheel(AngularVelocity speed) {
        shooterFlywheelMotor.setControl(velocityControl.withVelocity(speed));
    }

    public void stop() {
        shooterFlywheelMotor.set(0);
    }

    public AngularVelocity getVelocity() {
        return shooterFlywheelMotor.getVelocity().getValue();
    }
    
    public boolean isHubActive() {
        Optional<Alliance> alliance = DriverStation.getAlliance();
        if (alliance.isEmpty()) {
            return false;
        }
        if (DriverStation.isAutonomousEnabled()) {
            return true;
        }
        if (!DriverStation.isTeleopEnabled()) {
            return false;
        }

        double matchTime = DriverStation.getMatchTime();
        String gameData = DriverStation.getGameSpecificMessage();
        if (gameData.isEmpty()) {
            return true;
        }

        boolean blueActiveFirst = gameData.charAt(0) != 'B';

        boolean shift1Active = switch (alliance.get()) {
            case Red -> !blueActiveFirst;
            case Blue -> blueActiveFirst;
        };

        if (matchTime > 130 || matchTime <= 30) {
            return true;
        } else if ((matchTime - 30) % 25 <= Constants.FUEL_FLIGHT_TIME.in(Second)) {
            return true;
        } else {
            return ((matchTime - 30) % 50 > 25 && shift1Active) || ((matchTime - 30) % 50 < 25 && !shift1Active);
        }
    }
}
