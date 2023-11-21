/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.XboxController;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;

public class Robot extends TimedRobot {
  // Create objects for each of the drive motors, all TalonSRX's
  private TalonSRX leftMotor1 = new TalonSRX(1);
  private TalonSRX leftMotor2 = new TalonSRX(2);

  private TalonSRX rightMotor1 = new TalonSRX(3);
  private TalonSRX rightMotor2 = new TalonSRX(4);

  // Create object for driver 1's controller, it is an XboxController object with ID 0
  private XboxController driver1Controller = new XboxController(0);

  // Method that is called once when the robot is turned on
  @Override
  public void robotInit() {
    // Because of logistics, we need to invert the left motors
    leftMotor1.setInverted(true);
    leftMotor2.setInverted(true);
  }

  // Method that runs repeadedly as long as the robot is turned on
  @Override
  public void robotPeriodic() { }

  // Method that runs once when the autonomous period starts
  @Override
  public void autonomousInit() { }

  // Method that runs repeatedly for as long as the autonomous period lasts
  @Override
  public void autonomousPeriodic() { }

  // Method that runs once when the teleop period starts
  @Override
  public void teleopInit() { }

  // Method that runs repeatedly for as long as the teleop period lasts
  @Override
  public void teleopPeriodic() {
    leftMotor1.set(ControlMode.PercentOutput, (driver1Controller.getLeftY() - driver1Controller.getLeftX()) / 2);
    leftMotor2.set(ControlMode.PercentOutput, (driver1Controller.getLeftY() - driver1Controller.getLeftX()) / 2);

    rightMotor1.set(ControlMode.PercentOutput, (driver1Controller.getLeftY() + driver1Controller.getLeftX()) / 2);
    rightMotor2.set(ControlMode.PercentOutput, (driver1Controller.getLeftY() + driver1Controller.getLeftX()) / 2);
  }
}
