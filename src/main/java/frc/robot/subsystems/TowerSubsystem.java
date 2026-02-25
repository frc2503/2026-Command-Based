package frc.robot.subsystems;

import com.revrobotics.ResetMode;
import com.revrobotics.PersistMode;
import com.revrobotics.spark.SparkLowLevel.MotorType;
import com.revrobotics.spark.config.SparkMaxConfig;

import static frc.robot.Constants.FuelConstants.*;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import com.revrobotics.spark.SparkMax;

public class TowerSubsystem extends SubsystemBase {
    private final SparkMax towerMotor;

    public TowerSubsystem() {
        towerMotor = new SparkMax(TOWER_ID, MotorType.kBrushless);

        SparkMaxConfig towerConfig = new SparkMaxConfig();
        towerConfig.smartCurrentLimit(TOWER_CURRENT_LIMIT);
        towerMotor.configure(towerConfig, ResetMode.kResetSafeParameters, PersistMode.kPersistParameters);
    }

    public void setTower(double voltage) {
        towerMotor.setVoltage(voltage);
    }

    public void stop() {
        towerMotor.set(0);
    }
}
