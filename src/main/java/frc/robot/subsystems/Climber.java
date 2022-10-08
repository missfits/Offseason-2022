// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;
//import edu.wpi.first.wpilibj.motorcontrol.MotorController;
//import edu.wpi.first.wpilibj.motorcontrol.MotorControllerGroup;
import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;
//import com.revrobotics.CANSparkMax;
//import com.revrobotics.CANSparkMaxLowLevel.MotorType;
import com.revrobotics.SparkMaxRelativeEncoder;

import static frc.robot.Constants.*;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.SensorBoard;

public class Climber extends SubsystemBase {

  private final CANSparkMax m_climberMotor1 = new CANSparkMax(kCANID_MotorClimber1, MotorType.kBrushless);
  private final CANSparkMax m_climberMotor2 = new CANSparkMax(kCANID_MotorClimber2, MotorType.kBrushless);
  public final SparkMaxRelativeEncoder m_climberEncoder1 = (SparkMaxRelativeEncoder) m_climberMotor1.getEncoder(SparkMaxRelativeEncoder.Type.kHallSensor, 42);
  public final SparkMaxRelativeEncoder m_climberEncoder2 = (SparkMaxRelativeEncoder) m_climberMotor2.getEncoder(SparkMaxRelativeEncoder.Type.kHallSensor, 42);
  private final SensorBoard m_sensorControl;
/** Climber Subsystem */
  public Climber(SensorBoard sensorBoard) {
      m_sensorControl = sensorBoard;
      m_climberMotor2.follow(m_climberMotor1);
      m_climberEncoder1.setPosition(0);
      m_climberEncoder2.setPosition(0);

  }
  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }

  @Override
  public void simulationPeriodic() {
    // This method will be called once per scheduler run during simulation
  }
  
  //extends climber to full length determined by encoder value (continues motor speed until the maximum encoder value is reached)
  public void climberUp(){
    //if(m_climberEncoder1.getPosition() < climberMaxEncoderPosition){
      m_climberMotor1.set(1.0);
      m_climberMotor2.set(1.0);
    //}
    //else{
      //m_climberMotor1.set(0);
      //m_climberMotor2.set(0);
    //}
  }

  //retracts climber to shortest length determined by encoder value (reverses motor speed until the minimum encoder value is reached)
  public void climberDown(){
    //if(m_climberEncoder1.getPosition() > climberLowestEncoderPosition){
      m_climberMotor1.set(-1.0);
      m_climberMotor2.set(-1.0);
    //}
    // else{
    //   m_climberMotor1.set(0);
    //   m_climberMotor2.set(0);
    // }
  }
  
  public void climberReverse(){
    m_climberMotor1.set(-1.0);
    m_climberMotor2.set(-1.0);
  }

  //set the climber speed to zero (the climber is off)
  public void climberOff(){
    m_climberMotor1.set(0);
    m_climberMotor2.set(0);
  }

  //set the climber speed at a slower speed than normal
  public void climberLow(){
    m_climberMotor1.set(0.1);
    m_climberMotor2.set(0.1);
  }

  public void climberReset(){
    
  }

}
