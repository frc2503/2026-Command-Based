package frc.robot.subsystems;

import com.revrobotics.ResetMode;
import com.revrobotics.PersistMode;
import com.revrobotics.spark.SparkLowLevel.MotorType;
import com.revrobotics.spark.config.SparkBaseConfig.IdleMode;
import com.revrobotics.spark.config.SparkMaxConfig;

import static frc.robot.Constants.HopperConstants.*;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import com.revrobotics.spark.SparkMax;

public class HopperSubsystem extends SubsystemBase {
    private final SparkMax hopperMotor;

    public HopperSubsystem() {
        hopperMotor = new SparkMax(HOPPER_ID, MotorType.kBrushless);

        SparkMaxConfig hopperConfig = new SparkMaxConfig();
        hopperConfig.idleMode(IdleMode.kCoast);
        hopperConfig.smartCurrentLimit(HOPPER_CURRENT_LIMIT);
        hopperMotor.configure(hopperConfig, ResetMode.kResetSafeParameters, PersistMode.kPersistParameters);
    }

    public void setHopper(double power) {
        hopperMotor.set(power);
    }

    public void stop() {
        hopperMotor.set(0);
    }
}
