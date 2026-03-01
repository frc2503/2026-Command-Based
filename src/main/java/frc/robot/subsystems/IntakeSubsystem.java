package frc.robot.subsystems;

import com.revrobotics.spark.ClosedLoopSlot;
import com.revrobotics.spark.SparkBase;
import com.revrobotics.ResetMode;
import com.revrobotics.PersistMode;
import com.revrobotics.spark.SparkClosedLoopController;
import com.revrobotics.spark.SparkLowLevel.MotorType;
import com.revrobotics.spark.config.SparkBaseConfig.IdleMode;
import com.revrobotics.spark.config.SparkMaxConfig;

import static edu.wpi.first.units.Units.Rotation;
import static frc.robot.Constants.IntakeConstants.*;

import edu.wpi.first.units.measure.Angle;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

import com.revrobotics.spark.SparkMax;

public class IntakeSubsystem extends SubsystemBase {
    private final SparkMax intakeArmMotor;
    private final SparkClosedLoopController intakeArmController;
    private final SparkMax intakeRollerMotor;

    private boolean isIntakeUp = true;

    public IntakeSubsystem() {
        intakeArmMotor = new SparkMax(INTAKE_ARM_ID, MotorType.kBrushless);

        SparkMaxConfig intakeArmConfig = new SparkMaxConfig();
        intakeArmConfig.idleMode(IdleMode.kBrake);
        intakeArmConfig.smartCurrentLimit(INTAKE_ARM_CURRENT_LIMIT);
        intakeArmConfig.closedLoop.pid(INTAKE_ARM_KP, INTAKE_ARM_KI, INTAKE_ARM_KD);
        intakeArmConfig.closedLoop.allowedClosedLoopError(INTAKE_ARM_ALLOWED_ERROR.in(Rotation), ClosedLoopSlot.kSlot0);
        intakeArmConfig.encoder.positionConversionFactor(1/INTAKE_ARM_GEAR_RATIO);
        intakeArmMotor.configure(intakeArmConfig, ResetMode.kResetSafeParameters, PersistMode.kPersistParameters);
        intakeArmController = intakeArmMotor.getClosedLoopController();

        intakeRollerMotor = new SparkMax(INTAKE_ROLLER_ID, MotorType.kBrushless);

        SparkMaxConfig intakeRollerConfig = new SparkMaxConfig();
        intakeRollerConfig.idleMode(IdleMode.kCoast);
        intakeRollerConfig.smartCurrentLimit(INTAKE_ROLLER_CURRENT_LIMIT);
        intakeRollerMotor.configure(intakeRollerConfig, ResetMode.kResetSafeParameters, PersistMode.kPersistParameters);
    }

    public void setIntake(double power) {
        intakeRollerMotor.set(-power);
    }

    public void setIntakeAngle(Angle angle) {
        intakeArmController.setSetpoint(-angle.in(Rotation), SparkBase.ControlType.kPosition);
    }

    public void toggleIntake() {
        if (isIntakeUp) {
            setIntakeAngle(INTAKE_ARM_DOWN_ANGLE);
        } else {
            setIntakeAngle(INTAKE_ARM_UP_ANGLE);
        }
        isIntakeUp = !isIntakeUp;
    }

    public boolean isIntakeIntendedUp() {
        return isIntakeUp;
    }

    public boolean isIntakeActuallyUp() {
        return intakeArmMotor.getEncoder().getPosition() > -INTAKE_ARM_DOWN_ANGLE.in(Rotation)/2;
    }

    public void stop() {
        intakeRollerMotor.set(0);
    }

    @Override
    public void periodic() {
        System.out.println(intakeArmMotor.getAbsoluteEncoder().getPosition());
    }
}
