// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import java.security.spec.EncodedKeySpec;

//import java.util.Timer;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import com.ctre.phoenix.motorcontrol.can.VictorSPX;

import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import edu.wpi.first.wpilibj2.command.button.POVButton;
import edu.wpi.first.wpilibj.Encoder;

/**
 * The VM is configured to automatically run this class, and to call the functions corresponding to
 * each mode, as described in the TimedRobot documentation. If you change the name of this class or
 * the package after creating this project, you must also update the build.gradle file in the
 * project.
 */
public class Robot extends TimedRobot {

  private TalonSRX leftMotor1 = new TalonSRX(1);
  private TalonSRX leftMotor2 = new TalonSRX(2);

  private TalonSRX rightMotor1 = new TalonSRX(3);
  private TalonSRX rightMotor2 = new TalonSRX(4);
  private VictorSPX armMotor = new VictorSPX(10);

  private XboxController driver1Controller = new XboxController(0);
  private double factor = .25;
  private Timer auto_timer = new Timer();
  private Timer test_timer = new Timer();
  private Encoder motorEncoder = new Encoder (0,1);

  

 

  //private XboxController driver3Controller = new XboxController(3);
  
   //*This function is run when the robot is first started up and should be used for any
   //* initialization code.
  // */
  @Override
  public void robotInit() {
    rightMotor1.setInverted(true);
    rightMotor2.setInverted(true);
    armMotor.setInverted(true);
  }


  public void setDriveMotors(double Left1, double Left2, double Right1, double Right2)
  {
    leftMotor1.set(ControlMode.PercentOutput,(Left1) * factor);
    leftMotor2.set(ControlMode.PercentOutput, (Left2) * factor);

    rightMotor1.set(ControlMode.PercentOutput,(Right1) * factor);
    rightMotor2.set(ControlMode.PercentOutput, (Right2) * factor);

  }
  public void setarmMotor (double motorValue)
  {
    armMotor.set(ControlMode.PercentOutput,(motorValue) * factor);
  

  }


  public void DriveStraight() {
    setDriveMotors(1,1,1,.9);
  }
  public void DriveBackward(){
    setDriveMotors(-1,-1,-1,-1);
  }
  public void DriveRight() {
setDriveMotors(1,1,0,0);
  }
  public void DriveLeft() {
    setDriveMotors(0,0,1,1);
  }
  public void DriveStop() {
    setDriveMotors(0,0,0,0);
  }
    /**
   * This function is called every 20 ms, no matter the mode. Use this for items like diagnostics
   * that you want ran during disabled, autonomous, teleoperated and test.
   *
   * <p>This runs after the mode specific periodic functions, but before LiveWindow and
   * SmartDashboard integrated updating.
   */
  @Override
  public void robotPeriodic() {}

  /**
   * This autonomous (along with the chooser code above) shows how to select between different
   * autonomous modes using the dashboard. The sendable chooser code works with the Java
   * SmartDashboard. If you prefer the LabVIEW Dashboard, remove all of the chooser code and
   * uncomment the getString line to get the auto name from the text box below the Gyro
   *
   * <p>You can add additional auto modes by adding additional comparisons to the switch structure
   * below with additional strings. If using the SendableChooser make sure to add them to the
   * chooser code above as well.
   */
  @Override
  public void autonomousInit() {
    auto_timer.start();
    setarmMotor(1);
    motorEncoder.reset();
  }

  /** This function is called periodically during autonomous. */
  @Override
  public void autonomousPeriodic(){
    double distance = Math.abs(motorEncoder.getDistance());
    
    if(Math.abs(distance) < 45000){
      System.out.println (distance);
        DriveRight();
        
      }
    else if (Math.abs(distance) > 90000){
      System.out.println (distance);
        DriveLeft();

    }
    else if (Math.abs(distance) < 80000){
      //motorEncoder.reset();
    }
    }
  
  public void autonomousPeriodic2() {
      /*if (auto_timer.get() > 2) {
              DriveBackward();
      }
      if (auto_timer.get() > 6) {
        DriveStop();
      }
      if (auto_timer.get() > 6.2) {
        setarmMotor(1);
      }*/
    
      if (auto_timer.get() > 2) {
      //leftMotor1.set(ControlMode.PercentOutput,( driver1_lefty - driver1_leftx) * factor);
      //leftMotor2.set(ControlMode.PercentOutput, ( driver1_lefty - driver1_leftx) * factor);

      //rightMotor1.set(ControlMode.PercentOutput,( driver1_righty - driver1_rightx) * factor);
      //rightMotor2.set(ControlMode.PercentOutput, ( driver1_righty - driver1_rightx) * factor);
      //System.out.println("startingmotors".get());
      if (auto_timer.get() < 5) {
         DriveStraight();
        //setarmMotor(1);
        // setDriveMotors(1,1,1,1);  // DriveStraight
      }
      else if(auto_timer.get() < 6.5){
       // setDriveMotors(0,0,1,1); // DriveLeft
        //setRunMotors(1,1,0,0); // DriveRight
        //setarmMotor(0);
        DriveLeft();
        
      }
      else if(auto_timer.get() < 10){
       // setDriveMotors(1,1,1,1);  // DriveStraight
       //setarmMotor(1);
       DriveStraight();
      }
      else if(auto_timer.get() < 11.5){
        //setDriveMotors(1,1,0,0); // DriveRight
        //setRunMotors(0,0,1,1); // DriveLeft
        setarmMotor(0);
        DriveRight();
      }
      else if(auto_timer.get() < 14){
       // setDriveMotors(1,1,1,1);  // DriveStraight
       setarmMotor(1);
       DriveStraight();
      }
      else if(auto_timer.get() < 16.5){
        //setDriveMotors(0,0,0,0); //StopDriving
        DriveStop();
        setarmMotor(0);

      }
    }
    
    
  }

  /** This function is called once when teleop is enabled. */
  @Override
  public void teleopInit() {  
   motorEncoder.reset();
    test_timer.start();
    //setarmMotor(1);

  }
  


  


  /** This function is called periodically during operator control. */
  @Override
  public void teleopPeriodic() {
   // motorEncoder.setDistancePerPulse(4.0 / 256.0);
   //distance is the motorEncoder value
    double distance = Math.abs(motorEncoder.getDistance());
  
    double rate = motorEncoder.getRate();
    motorEncoder.setSamplesToAverage(5);
    System.out.println (distance + "/" + rate);

   double driver1_leftx= driver1Controller.getLeftX();
   double driver1_lefty= driver1Controller.getLeftY();
   
   if(driver1_leftx < .5 && driver1_leftx > -.5){
    driver1_leftx = 0;
   }

    leftMotor1.set(ControlMode.PercentOutput,( -driver1_lefty + driver1_leftx) * factor);
    leftMotor2.set(ControlMode.PercentOutput, ( -driver1_lefty + driver1_leftx) * factor);

    rightMotor1.set(ControlMode.PercentOutput, ( -driver1_lefty - driver1_leftx) * factor);
    rightMotor2.set(ControlMode.PercentOutput, ( -driver1_lefty - driver1_leftx) * factor);

    if(Math.abs(distance) > 5000){
      DriveRight();
    }
    else if (Math.abs(distance) > 25000){
      System.out.println (distance);
      motorEncoder.reset();
    }


    if(driver1Controller.getBButtonPressed())
    {
      setarmMotor(1);

     
    }
    if(driver1Controller.getBButtonReleased())
    {
      setarmMotor(0);
      motorEncoder.reset();
    }

    if(driver1Controller.getYButtonPressed())
    {
      setarmMotor(-1);
    }
    if(driver1Controller.getYButtonReleased())
    {
      setarmMotor(0);
    }



    
    if(driver1Controller.getAButton())
    {
      factor = .75;
    }
    else{
      factor = .25;
    }
  
  //driver1Controller.getPOV();

  }

  /** This function is called once when the robot is disabled. */
  @Override
  public void disabledInit (){} 
  

  /** This function is called periodically when disabled. */
  @Override
  public void disabledPeriodic () {} 


  /** This function is called once when test mode is enabled. */
  @Override
  public void testInit () {}

  /** This function is called periodically during test mode. */
  @Override
  public void testPeriodic() {}

  /** This function is called once when the robot is first started up. */
  @Override
  public void simulationInit() {}

  /** This function is called periodically whilst in simulation. */
  @Override
  public void simulationPeriodic() {}
}
