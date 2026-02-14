package frc.robot.subsystems;

import com.revrobotics.spark.SparkBase;
import com.revrobotics.ResetMode;
import com.revrobotics.PersistMode;
import com.revrobotics.spark.SparkClosedLoopController;
import com.revrobotics.spark.SparkLowLevel.MotorType;
import com.revrobotics.spark.config.SparkMaxConfig;

import static frc.robot.Constants.FuelConstants.*;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import com.revrobotics.spark.SparkMax;

public class IntakeArmSubsystem extends SubsystemBase {
    private final SparkMax intakeArmMotor;
    private final SparkClosedLoopController intakeArmController;

    private boolean isIntakeUp = false;

    public IntakeArmSubsystem() {
        intakeArmMotor = new SparkMax(INTAKE_ARM_ID, MotorType.kBrushless);

        SparkMaxConfig intakeArmConfig = new SparkMaxConfig();
        intakeArmConfig.smartCurrentLimit(INTAKE_ARM_CURRENT_LIMIT);
        intakeArmConfig.closedLoop.pid(INTAKE_ARM_KP, INTAKE_ARM_KI, INTAKE_ARM_KD);
        intakeArmConfig.encoder.positionConversionFactor(1.0);
        intakeArmMotor.configure(intakeArmConfig, ResetMode.kResetSafeParameters, PersistMode.kPersistParameters);
        intakeArmController = intakeArmMotor.getClosedLoopController();
    }

    public void setIntakePosition(double degrees) {
        intakeArmController.setSetpoint(degrees, SparkBase.ControlType.kPosition);
        if (degrees == INTAKE_ARM_UP_DEGREES) {
            isIntakeUp = true;
        } else if (degrees == INTAKE_ARM_DOWN_DEGREES) {
            isIntakeUp = false;
        }
    }

    public void toggleIntake() {
        if (isIntakeUp) {
            setIntakePosition(INTAKE_ARM_DOWN_DEGREES);
        } else {
            setIntakePosition(INTAKE_ARM_UP_DEGREES);
        }
    }

    public boolean isIntakeUp() {
        return isIntakeUp;
    }

    public void stop() {
        intakeArmMotor.set(0);
    }
}
