package frc.robot.subsystems;

import com.revrobotics.ResetMode;
import com.revrobotics.PersistMode;
import com.revrobotics.spark.SparkLowLevel.MotorType;
import com.revrobotics.spark.config.SparkMaxConfig;

import static frc.robot.Constants.FuelConstants.*;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import com.revrobotics.spark.SparkMax;

public class LaunchSubsystem extends SubsystemBase {
    private final SparkMax launch1Motor;
    private final SparkMax launch2Motor;

    public LaunchSubsystem() {
        launch1Motor = new SparkMax(LAUNCH_1_ID, MotorType.kBrushless);
        launch2Motor = new SparkMax(LAUNCH_2_ID, MotorType.kBrushless);

        SparkMaxConfig launch1Config = new SparkMaxConfig();
        launch1Config.smartCurrentLimit(LAUNCH_1_CURRENT_LIMIT);
        launch1Motor.configure(launch1Config, ResetMode.kResetSafeParameters, PersistMode.kPersistParameters);

        SparkMaxConfig launch2Config = new SparkMaxConfig();
        launch2Config.smartCurrentLimit(LAUNCH_2_CURRENT_LIMIT);
        launch2Motor.configure(launch2Config, ResetMode.kResetSafeParameters, PersistMode.kPersistParameters);
    }

    public void setLaunch1(double voltage) {
        launch1Motor.setVoltage(voltage);
    }
    public void setLaunch2(double voltage) {
        launch2Motor.setVoltage(voltage);
    }
    

    public void stop() {
        launch1Motor.set(0);
        launch2Motor.set(0);
    }
}
