// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import com.ctre.phoenix.motorcontrol.can.VictorSPX;

import edu.wpi.first.wpilibj.motorcontrol.MotorController;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import static frc.robot.Constants.DriveConstants.*;

public class CANDriveSubsystem extends SubsystemBase {
  private final TalonSRX leftFollower;
  private final VictorSPX leftLeader;
  private final TalonSRX rightFollower;
  private final VictorSPX rightLeader;

  private final DifferentialDrive drive;

  public CANDriveSubsystem() {
    leftLeader = new VictorSPX(LEFT_LEADER_ID);
    leftFollower = new TalonSRX(LEFT_FOLLOWER_ID);
    rightLeader = new VictorSPX(RIGHT_LEADER_ID);
    rightFollower = new TalonSRX(RIGHT_FOLLOWER_ID);

    // Set followers to follow leaders
    leftFollower.follow(leftLeader);
    rightFollower.follow(rightLeader);

    drive = new DifferentialDrive(
      new PhoenixMotorControllerAdapter(leftLeader), 
      new PhoenixMotorControllerAdapter(rightLeader)
    );

    leftLeader.setInverted(true);
  }

  @Override
  public void periodic() {
  }

//  public void driveTank(double leftSpeed, double rightSpeed) {
//    drive.tankDrive(leftSpeed, rightSpeed);
//  }

  public void arcadeDrive(double speed, double rotation) {
    drive.arcadeDrive(speed, rotation);
  }

  /**
   * Adapter to convert Phoenix IMotorController to WPILib MotorController
   */
  private class PhoenixMotorControllerAdapter implements MotorController {
    private final com.ctre.phoenix.motorcontrol.IMotorController motor;

    public PhoenixMotorControllerAdapter(com.ctre.phoenix.motorcontrol.IMotorController motor) {
      this.motor = motor;
    }

    @Override
    public void set(double speed) {
      motor.set(com.ctre.phoenix.motorcontrol.ControlMode.PercentOutput, speed);
    }

    @Override
    public double get() {
      return motor.getMotorOutputPercent();
    }

    @Override
    public void setInverted(boolean isInverted) {
      motor.setInverted(isInverted);
    }

    @Override
    public boolean getInverted() {
      return motor.getInverted();
    }

    @Override
    public void disable() {
      motor.set(com.ctre.phoenix.motorcontrol.ControlMode.Disabled, 0);
    }

    @Override
    public void stopMotor() {
      motor.set(com.ctre.phoenix.motorcontrol.ControlMode.Disabled, 0);
    }
  }

}
