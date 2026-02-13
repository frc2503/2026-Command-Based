package frc.robot.subsystems;

//defines all the motors for the fuel commands.

import com.revrobotics.spark.SparkBase;
import com.revrobotics.ResetMode;
import com.revrobotics.PersistMode;
import com.revrobotics.spark.SparkClosedLoopController;
import com.revrobotics.spark.SparkLowLevel.MotorType;
import com.revrobotics.spark.config.SparkMaxConfig;

import static frc.robot.Constants.FuelConstants.*;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

import com.revrobotics.spark.SparkMax;

public class FuelSubsystem extends SubsystemBase {
    private final SparkMax IntakeActionMotor;
    private final SparkClosedLoopController IntakeActionController;
    private final SparkMax IntakeMotor;
    private final SparkClosedLoopController IntakeController;
    private final SparkMax TowerMotor;
    private final SparkClosedLoopController TowerController;
    private final SparkMax HopperMotor;
    private final SparkClosedLoopController HopperController;

    public FuelSubsystem() {

    //Setting the motors-------------------------------------------------------

        IntakeActionMotor = new SparkMax(INTAKE_ACTION_ID, MotorType.kBrushless);
        IntakeMotor = new SparkMax(INTAKE_ID, MotorType.kBrushless);
        TowerMotor = new SparkMax(TOWER_ID, MotorType.kBrushless);
        HopperMotor = new SparkMax(HOPPER_ID, MotorType.kBrushless);

    //Defining the Motors------------------------------------------------------

        SparkMaxConfig intakeActionConfig = new SparkMaxConfig();
        intakeActionConfig.smartCurrentLimit(INTAKE_ACTION_CURRENT_LIMIT);
        intakeActionConfig.closedLoop.pid(INTAKE_ACTION_KP, INTAKE_ACTION_KI, INTAKE_ACTION_KD);
        intakeActionConfig.encoder.positionConversionFactor(1.0);
        IntakeActionMotor.configure(intakeActionConfig, ResetMode.kResetSafeParameters, PersistMode.kPersistParameters);
        IntakeActionController = IntakeActionMotor.getClosedLoopController();

        SparkMaxConfig intakeConfig = new SparkMaxConfig();
        intakeConfig.smartCurrentLimit(INTAKE_CURRENT_LIMIT);
        intakeConfig.closedLoop.pid(INTAKE_KP, INTAKE_KI, INTAKE_KD);
        IntakeMotor.configure(intakeConfig, ResetMode.kResetSafeParameters, PersistMode.kPersistParameters);
        IntakeController = IntakeMotor.getClosedLoopController();

        SparkMaxConfig hopperConfig = new SparkMaxConfig();
        hopperConfig.smartCurrentLimit(HOPPER_CURRENT_LIMIT);
        hopperConfig.closedLoop.pid(HOPPER_KP, HOPPER_KI, HOPPER_KD);
        HopperMotor.configure(hopperConfig, ResetMode.kResetSafeParameters, PersistMode.kPersistParameters);
        HopperController = HopperMotor.getClosedLoopController();

        SparkMaxConfig towerConfig = new SparkMaxConfig();
        towerConfig.smartCurrentLimit(TOWER_CURRENT_LIMIT);
        towerConfig.closedLoop.pid(TOWER_KP, TOWER_KI, TOWER_KD);
        TowerMotor.configure(towerConfig, ResetMode.kResetSafeParameters, PersistMode.kPersistParameters);
        TowerController = TowerMotor.getClosedLoopController();
    }
    //Setting motor position and speed--------------------------------------------------

    public void setIntakeVelocity(double rpm) {
        IntakeController.setSetpoint(rpm, SparkMax.ControlType.kVelocity);
    }

    private boolean isIntakeUp = false;

    public void setIntakePosition(double degrees) {
        IntakeActionController.setSetpoint(degrees, SparkBase.ControlType.kPosition);
        if (degrees == INTAKE_ACTION_UP_DEGREES) {
            isIntakeUp = true;
        } else if (degrees == INTAKE_ACTION_DOWN_DEGREES) {
            isIntakeUp = false;
        }
    }

    public void toggleIntake() {
        if (isIntakeUp) {
            setIntakePosition(INTAKE_ACTION_DOWN_DEGREES);
        } else {
            setIntakePosition(INTAKE_ACTION_UP_DEGREES);
        }
    }

    public boolean isIntakeUp() {
        return isIntakeUp;
    }

    public void setHopperVelocity(double rpm) {
        HopperController.setSetpoint(rpm, SparkMax.ControlType.kVelocity);
    }

    public void setTowerVelocity(double rpm) {
        TowerController.setSetpoint(rpm, SparkMax.ControlType.kVelocity);
    }

    public void stop(){
        TowerMotor.set(0);
        HopperMotor.set(0);
        IntakeMotor.set(0);
        IntakeActionMotor.set(0);
    }   
}
