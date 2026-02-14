package frc.robot.subsystems;

import com.revrobotics.ResetMode;
import com.revrobotics.PersistMode;
import com.revrobotics.spark.SparkClosedLoopController;
import com.revrobotics.spark.SparkLowLevel.MotorType;
import com.revrobotics.spark.config.SparkMaxConfig;

import static frc.robot.Constants.FuelConstants.*;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import com.revrobotics.spark.SparkMax;

public class HopperSubsystem extends SubsystemBase {
    private final SparkMax hopperMotor;
    private final SparkClosedLoopController hopperController;

    public HopperSubsystem() {
        hopperMotor = new SparkMax(HOPPER_ID, MotorType.kBrushless);

        SparkMaxConfig hopperConfig = new SparkMaxConfig();
        hopperConfig.smartCurrentLimit(HOPPER_CURRENT_LIMIT);
        hopperConfig.closedLoop.pid(HOPPER_KP, HOPPER_KI, HOPPER_KD);
        hopperMotor.configure(hopperConfig, ResetMode.kResetSafeParameters, PersistMode.kPersistParameters);
        hopperController = hopperMotor.getClosedLoopController();
    }

    public void setHopperVelocity(double rpm) {
        hopperController.setSetpoint(rpm, SparkMax.ControlType.kVelocity);
    }

    public void stop() {
        hopperMotor.set(0);
    }
}
