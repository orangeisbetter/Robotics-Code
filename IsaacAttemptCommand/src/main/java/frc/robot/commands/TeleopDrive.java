// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import frc.robot.subsystems.DriveTrain;
import frc.robot.subsystems.ExampleSubsystem;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj2.command.CommandBase;

/** An example command that uses an example subsystem. */
public class TeleopDrive extends CommandBase {
  @SuppressWarnings({"PMD.UnusedPrivateField", "PMD.SingularField"})
  private final DriveTrain driveSubsystem;
  private Joystick logiJoystick;
  private Joystick xbox;
  
  /**
   * Creates a new ExampleCommand.
   *
   * @param subsystem The subsystem used by this command.
   */
  public TeleopDrive(DriveTrain driveSubsystem, Joystick logiJoystick, Joystick xbox) {
    this.logiJoystick = logiJoystick;
    this.xbox = xbox;
    
    this.driveSubsystem = driveSubsystem;
    // Use addRequirements() here to declare subsystem dependencies.
    addRequirements(driveSubsystem);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {}

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    double drivingSpeed = logiJoystick.getRawAxis(1);
    double turningSpeed = logiJoystick.getRawAxis(4);

    turningSpeed *= 0.75;

    drivingSpeed = Math.copySign(Math.pow(drivingSpeed, 2), drivingSpeed);
    turningSpeed = Math.copySign(Math.pow(turningSpeed, 2), turningSpeed);

    driveSubsystem.telopDrive(drivingSpeed, turningSpeed);
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {}

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}
