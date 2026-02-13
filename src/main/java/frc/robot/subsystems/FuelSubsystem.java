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
    private final SparkMax HopperMotor;
    private final SparkClosedLoopController HopperController;
    private final SparkMax IntakeMotor;
    private final SparkClosedLoopController IntakeController;
    private final SparkMax FeederMotor;
    private final SparkClosedLoopController FeederController;
    private final SparkMax ConveyorMotor;
    private final SparkClosedLoopController ConveyorController;

    public FuelSubsystem() {

    //Setting the motors-------------------------------------------------------

        HopperMotor = new SparkMax(HOPPER_ID, MotorType.kBrushless);
        IntakeMotor = new SparkMax(INTAKE_ID, MotorType.kBrushless);
        FeederMotor = new SparkMax(FEEDER_ID, MotorType.kBrushless);
        ConveyorMotor = new SparkMax(CONVEYOR_ID, MotorType.kBrushless);

    //Defining the Motors------------------------------------------------------

        SparkMaxConfig hopperConfig = new SparkMaxConfig();
        hopperConfig.smartCurrentLimit(HOPPER_CURRENT_LIMIT);
        hopperConfig.closedLoop.pid(HOPPER_KP, HOPPER_KI, HOPPER_KD);
        hopperConfig.encoder.positionConversionFactor(1.0);
        HopperMotor.configure(hopperConfig, ResetMode.kResetSafeParameters, PersistMode.kPersistParameters);
        HopperController = HopperMotor.getClosedLoopController();

        SparkMaxConfig intakeConfig = new SparkMaxConfig();
        intakeConfig.smartCurrentLimit(INTAKE_CURRENT_LIMIT);
        intakeConfig.closedLoop.pid(INTAKE_KP, INTAKE_KI, INTAKE_KD);
        IntakeMotor.configure(intakeConfig, ResetMode.kResetSafeParameters, PersistMode.kPersistParameters);
        IntakeController = IntakeMotor.getClosedLoopController();

        SparkMaxConfig conveyorConfig = new SparkMaxConfig();
        conveyorConfig.smartCurrentLimit(CONVEYOR_CURRENT_LIMIT);
        conveyorConfig.closedLoop.pid(CONVEYOR_KP, CONVEYOR_KI, CONVEYOR_KD);
        ConveyorMotor.configure(conveyorConfig, ResetMode.kResetSafeParameters, PersistMode.kPersistParameters);
        ConveyorController = ConveyorMotor.getClosedLoopController();

        SparkMaxConfig feederConfig = new SparkMaxConfig();
        feederConfig.smartCurrentLimit(FEEDER_CURRENT_LIMIT);
        feederConfig.closedLoop.pid(FEEDER_KP, FEEDER_KI, FEEDER_KD);
        FeederMotor.configure(feederConfig, ResetMode.kResetSafeParameters, PersistMode.kPersistParameters);
        FeederController = FeederMotor.getClosedLoopController();
    }
    //Setting motor position and speed--------------------------------------------------

    public void setIntakeVelocity(double rpm) {
        IntakeController.setSetpoint(rpm, SparkMax.ControlType.kVelocity);
    }

    private boolean isHopperUp = false;

    public void setHopperPosition(double degrees) {
        HopperController.setSetpoint(degrees, SparkBase.ControlType.kPosition);
        if (degrees == HOPPER_UP_DEGREES) {
            isHopperUp = true;
        } else if (degrees == HOPPER_DOWN_DEGREES) {
            isHopperUp = false;
        }
    }

    public void toggleHopper() {
        if (isHopperUp) {
            setHopperPosition(HOPPER_DOWN_DEGREES);
        } else {
            setHopperPosition(HOPPER_UP_DEGREES);
        }
    }

    public void setConveyorVelocity(double rpm) {
        ConveyorController.setSetpoint(rpm, SparkMax.ControlType.kVelocity);
    }

    public void setFeederVelocity(double rpm) {
        FeederController.setSetpoint(rpm, SparkMax.ControlType.kVelocity);
    }

    public void stop(){
        FeederMotor.set(0);
        ConveyorMotor.set(0);
        IntakeMotor.set(0);
        HopperMotor.set(0);
    }   
}

