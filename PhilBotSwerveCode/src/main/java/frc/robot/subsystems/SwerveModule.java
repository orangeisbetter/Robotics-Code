// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.wpilibj.DutyCycleEncoder;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class SwerveModule extends SubsystemBase {
  private CANSparkMax motor1;
  private CANSparkMax motor2;

  private DutyCycleEncoder encoder;

  private PIDController angleController;

  /** Creates a new ExampleSubsystem. */
  public SwerveModule(int m1Address, int m2Address, int encoderAddress) {
    motor1 = new CANSparkMax(m1Address, MotorType.kBrushless);
    motor2 = new CANSparkMax(m2Address, MotorType.kBrushless);

    encoder = new DutyCycleEncoder(encoderAddress);

    angleController = new PIDController(0.2, 0, 0);
  }

  public void setDifferential(double speed, double angle) {
    double linearSpeed = speed;
    double angularSpeed;

    angularSpeed = angleController.calculate(angle, getAngle());

    setMotors(linearSpeed, angularSpeed);
  }

  public double getAngle() {
    return encoder.getAbsolutePosition() * 2 * Math.PI;
  }

  public void setMotors(double linearSpeed, double turnSpeed) {
    motor1.set(turnSpeed + linearSpeed);
    motor2.set(turnSpeed - linearSpeed);
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }

  @Override
  public void simulationPeriodic() {
    // This method will be called once per scheduler run during simulation
  }
}
