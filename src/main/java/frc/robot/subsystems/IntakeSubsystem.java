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

public class IntakeSubsystem extends SubsystemBase {
    private final SparkMax intakeArmMotor;
    private final SparkClosedLoopController intakeArmController;
    private final SparkMax intakeRollerMotor;
    private final SparkClosedLoopController intakeRollerController;

    private boolean isIntakeUp = false;

    public IntakeSubsystem() {
        intakeArmMotor = new SparkMax(INTAKE_ARM_ID, MotorType.kBrushless);

        SparkMaxConfig intakeArmConfig = new SparkMaxConfig();
        intakeArmConfig.smartCurrentLimit(INTAKE_ARM_CURRENT_LIMIT);
        intakeArmConfig.closedLoop.pid(INTAKE_ARM_KP, INTAKE_ARM_KI, INTAKE_ARM_KD);
        intakeArmConfig.encoder.positionConversionFactor((INTAKE_ARM_CONVERSION_FACTOR/INTAKE_ARM_GEAR_RATIO)*360);
        intakeArmMotor.configure(intakeArmConfig, ResetMode.kResetSafeParameters, PersistMode.kPersistParameters);
        intakeArmController = intakeArmMotor.getClosedLoopController();

        intakeRollerMotor = new SparkMax(INTAKE_ROLLER_ID, MotorType.kBrushless);

        SparkMaxConfig intakeRollerConfig = new SparkMaxConfig();
        intakeRollerConfig.smartCurrentLimit(INTAKE_ROLLER_CURRENT_LIMIT);
        intakeRollerConfig.closedLoop.pid(INTAKE_ROLLER_KP, INTAKE_ROLLER_KI, INTAKE_ROLLER_KD);
        intakeRollerMotor.configure(intakeRollerConfig, ResetMode.kResetSafeParameters, PersistMode.kPersistParameters);
        intakeRollerController = intakeRollerMotor.getClosedLoopController();
    }

    public void setIntakeVelocity(double rpm) {
        if (isIntakeUp() && rpm != 0) {
            intakeRollerController.setSetpoint(0, SparkMax.ControlType.kVelocity);
        } else {
            intakeRollerController.setSetpoint(rpm, SparkMax.ControlType.kVelocity);
        }
    }

    public void setIntakePosition(double degrees) {
        intakeArmController.setSetpoint(degrees, SparkBase.ControlType.kPosition);
    }

    @Override
    public void periodic() {
        double currentPosition = intakeArmMotor.getEncoder().getPosition();
        if (Math.abs(currentPosition - INTAKE_ARM_UP_DEGREES) < 5) { // 5 degrees tolerance
            isIntakeUp = true;
        } else {
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
