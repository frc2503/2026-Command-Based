package frc.robot.subsystems;

import com.revrobotics.spark.ClosedLoopSlot;
import com.revrobotics.spark.FeedbackSensor;
import com.revrobotics.spark.SparkAbsoluteEncoder;
import com.revrobotics.spark.SparkBase;
import com.revrobotics.ResetMode;
import com.ctre.phoenix6.configs.TalonFXConfiguration;
import com.ctre.phoenix6.hardware.TalonFX;
import com.ctre.phoenix6.signals.NeutralModeValue;
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
    private final SparkAbsoluteEncoder intakeArmAbsoluteEncoder;
    private final TalonFX intakeRollerMotor;

    private IntakeState intendedState = IntakeState.RETRACTED;

    public IntakeSubsystem() {
        intakeArmMotor = new SparkMax(INTAKE_ARM_ID, MotorType.kBrushless);
        intakeArmController = intakeArmMotor.getClosedLoopController();
        intakeArmAbsoluteEncoder = intakeArmMotor.getAbsoluteEncoder();

        SparkMaxConfig intakeArmConfig = new SparkMaxConfig();
        intakeArmConfig.idleMode(IdleMode.kBrake);
        intakeArmConfig.smartCurrentLimit(INTAKE_ARM_CURRENT_LIMIT);
        intakeArmConfig.inverted(false);
        intakeArmConfig.closedLoop.pid(INTAKE_ARM_KP, INTAKE_ARM_KI, INTAKE_ARM_KD);
        intakeArmConfig.closedLoop.allowedClosedLoopError(INTAKE_ARM_ALLOWED_ERROR.in(Rotation), ClosedLoopSlot.kSlot0);
        intakeArmConfig.closedLoop.feedbackSensor(FeedbackSensor.kAbsoluteEncoder);
        intakeArmConfig.absoluteEncoder.setSparkMaxDataPortConfig();
        intakeArmConfig.absoluteEncoder.inverted(true);
        intakeArmConfig.absoluteEncoder.zeroOffset(INTAKE_ARM_ENCODER_OFFSET);
        intakeArmMotor.configure(intakeArmConfig, ResetMode.kResetSafeParameters, PersistMode.kPersistParameters);

        intakeRollerMotor = new TalonFX(INTAKE_ROLLER_ID);

        TalonFXConfiguration shooterFlywheelConfig = new TalonFXConfiguration();
        shooterFlywheelConfig.MotorOutput.NeutralMode = NeutralModeValue.Coast;
        shooterFlywheelConfig.CurrentLimits.StatorCurrentLimit = INTAKE_ROLLER_CURRENT_LIMIT;
        intakeRollerMotor.getConfigurator().apply(shooterFlywheelConfig);

        setIntakeState(intendedState);
    }

    public void setIntake(double power) {
        intakeRollerMotor.set(-power);
    }

    private void setIntakeAngle(Angle angle) {
        intakeArmController.setSetpoint(angle.in(Rotation), SparkBase.ControlType.kPosition);
    }

    public void setIntakeState(IntakeState state) {
        intendedState = state;
    }

    public void toggleIntake() {
        if (intendedState == IntakeState.RETRACTED) {
            setIntakeState(IntakeState.EXTENDED);
        } else {
            setIntakeState(IntakeState.RETRACTED);
        }
    }

    public IntakeState getIntakeIntendedState() {
        return intendedState;
    }

    public IntakeState getIntakeActualState() {
        Angle halfExtended = INTAKE_ARM_EXTENDED_ANGLE.minus(INTAKE_ARM_RETRACTED_ANGLE).div(2).plus(INTAKE_ARM_RETRACTED_ANGLE);
        if (intakeArmAbsoluteEncoder.getPosition() > halfExtended.in(Rotation)) {
            return IntakeState.EXTENDED;
        } else {
            return IntakeState.RETRACTED;
        }
    }

    public void stop() {
        intakeRollerMotor.set(0);
    }

    @Override
    public void periodic() {
        if (intendedState == IntakeState.EXTENDED) {
            if (intakeArmAbsoluteEncoder.getPosition() > INTAKE_ARM_EXTENDED_ANGLE.minus(INTAKE_ARM_ALLOWED_ERROR).in(Rotation)) {
                intakeArmMotor.set(INTAKE_ARM_HOLD_POWER);
            } else {
                setIntakeAngle(INTAKE_ARM_EXTENDED_ANGLE);
            }
        } else {
            setIntakeAngle(INTAKE_ARM_RETRACTED_ANGLE);
        }
    }

    public static enum IntakeState {
        RETRACTED,
        EXTENDED
    }
}