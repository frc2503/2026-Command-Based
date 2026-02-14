package frc.robot.subsystems;

import com.revrobotics.ResetMode;
import com.revrobotics.PersistMode;
import com.revrobotics.spark.SparkClosedLoopController;
import com.revrobotics.spark.SparkLowLevel.MotorType;
import com.revrobotics.spark.config.SparkMaxConfig;

import static frc.robot.Constants.FuelConstants.*;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import com.revrobotics.spark.SparkMax;

public class IntakeRollerSubsystem extends SubsystemBase {
    private final SparkMax intakeRollerMotor;
    private final SparkClosedLoopController intakeRollerController;
    private final IntakeArmSubsystem intakeArmSubsystem;

    public IntakeRollerSubsystem(IntakeArmSubsystem intakeArmSubsystem) {
        this.intakeArmSubsystem = intakeArmSubsystem;
        intakeRollerMotor = new SparkMax(INTAKE_ROLLER_ID, MotorType.kBrushless);

        SparkMaxConfig intakeRollerConfig = new SparkMaxConfig();
        intakeRollerConfig.smartCurrentLimit(INTAKE_ROLLER_CURRENT_LIMIT);
        intakeRollerConfig.closedLoop.pid(INTAKE_ROLLER_KP, INTAKE_ROLLER_KI, INTAKE_ROLLER_KD);
        intakeRollerMotor.configure(intakeRollerConfig, ResetMode.kResetSafeParameters, PersistMode.kPersistParameters);
        intakeRollerController = intakeRollerMotor.getClosedLoopController();
    }

    public void setIntakeVelocity(double rpm) {
        if (intakeArmSubsystem.isIntakeUp() && rpm != 0) {
            intakeRollerController.setSetpoint(0, SparkMax.ControlType.kVelocity);
        } else {
            intakeRollerController.setSetpoint(rpm, SparkMax.ControlType.kVelocity);
        }
    }

    @Override
    public void periodic() {
        if (intakeArmSubsystem.isIntakeUp()) {
            intakeRollerMotor.set(0);
        }
    }

    public void stop() {
        intakeRollerMotor.set(0);
    }
}
