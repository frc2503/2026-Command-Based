package frc.robot.subsystems;

//defines all the motors for the fuel commands.

import com.revrobotics.spark.SparkBase.PersistMode;
import com.revrobotics.spark.SparkBase.ResetMode;
import com.revrobotics.spark.SparkLowLevel.MotorType;
import com.revrobotics.spark.config.SparkMaxConfig;

import static frc.robot.Constants.FuelConstants.*;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

import com.revrobotics.spark.SparkMax;

public class FuelSubsystem extends SubsystemBase {
    private final SparkMax HopperMotor;
    private final SparkMax IntakeMotor;
    private final SparkMax FeederMotor;
    private final SparkMax ConveyorMotor;

    public FuelSubsystem() {

    //Setting the motors-------------------------------------------------------
        HopperMotor = new SparkMax(HOPPER_ID, MotorType.kBrushless);
        IntakeMotor = new SparkMax(INTAKE_ID, MotorType.kBrushless);
        FeederMotor = new SparkMax(FEEDER_ID, MotorType.kBrushless);
        ConveyorMotor = new SparkMax(CONVEYOR_ID, MotorType.kBrushless);

    //Defining the Motors------------------------------------------------------

        SparkMaxConfig hopperConfig = new SparkMaxConfig();
        hopperConfig.smartCurrentLimit(HOPPER_CURRENT_LIMIT);
        HopperMotor.configure(hopperConfig, ResetMode.kResetSafeParameters, PersistMode.kPersistParameters);

        SparkMaxConfig intakeConfig = new SparkMaxConfig();
        intakeConfig.smartCurrentLimit(INTAKE_CURRENT_LIMIT);
        IntakeMotor.configure(intakeConfig, ResetMode.kResetSafeParameters, PersistMode.kPersistParameters);

        SparkMaxConfig conveyorConfig = new SparkMaxConfig();
        conveyorConfig.smartCurrentLimit(CONVEYOR_CURRENT_LIMIT);
        ConveyorMotor.configure(conveyorConfig, ResetMode.kResetSafeParameters, PersistMode.kPersistParameters);

        SparkMaxConfig feederConfig = new SparkMaxConfig();
        feederConfig.smartCurrentLimit(FEEDER_CURRENT_LIMIT);
        FeederMotor.configure(feederConfig, ResetMode.kResetSafeParameters, PersistMode.kPersistParameters);
    }

    //Setting motor voltage--------------------------------------------------

    public void setIntakeMotor(double voltage) {
        IntakeMotor.setVoltage(voltage);
    }
    public void setHopperMotor(double voltage) {
        HopperMotor.setVoltage(voltage);
    }

    public void setConveyorMotor(double voltage) {
        ConveyorMotor.setVoltage(voltage);
    }

    public void setFeederMotor(double voltage) {
        FeederMotor.setVoltage(voltage);
    }

    public void stop(){
        FeederMotor.set(0);
        ConveyorMotor.set(0);
        IntakeMotor.set(0);
        HopperMotor.set(0);
    }   

}
