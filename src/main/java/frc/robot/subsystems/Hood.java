package frc.robot.subsystems;

import frc.robot.Constants.*;

import java.util.SortedMap;

import com.revrobotics.CANSparkMax;
import com.revrobotics.RelativeEncoder;
import com.revrobotics.SparkMaxPIDController;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import frc.robot.SensorBoard;

public class Hood {
    private SensorBoard m_sensorControl;
    public final CANSparkMax m_hoodMotor;
    public final RelativeEncoder m_hoodEncoder;
    public SparkMaxPIDController m_hoodPID;
    private final VisionLookup m_visionLookup;
    private final Vision m_vision;

    public Hood(SensorBoard sensorboard, VisionLookup visionLookup, Vision vision){
        m_sensorControl = sensorboard;
        m_visionLookup = visionLookup;
        m_vision = vision;
        m_hoodMotor = new CANSparkMax(Constants.kCANID_MotorHood, MotorType.kBrushless);
        m_hoodEncoder = m_hoodMotor.getEncoder();
        m_hoodEncoder.setPosition(0);
        m_hoodPID = m_hoodMotor.getPIDController();
    }

        // real value: 88.7/1.0833
        // Use (88.7/1.0833)/2 to test and not break hood
        // Subtract 30 for padding
    public void setHoodPowerForward(double power){
        while(m_hoodEncoder.getPosition() < ((88.7-30)/1.0833)){
            m_hoodMotor.set(power);
        }
        m_hoodMotor.set(0);
    }

    //power should be negative
    //Need to set lower limit that is slightly above 0 to account for drift
    public void setHoodPowerBackward(double power){
        while(m_hoodEncoder.getPosition() > 1){
            m_hoodMotor.set(power);
        }
        m_hoodMotor.set(0);
    }

    /** @return desired hood position based on angleMap */
    public double getHoodPOS(SortedMap<Double, Double> map, double distance){
        double desiredHood = m_visionLookup.shooterInterpolation(map, distance);
        //To Do : Need to update hoodAngleOut and hoodAngleIn with real values during testing
        if(desiredHood > Constants.hoodAngleOut){
            return Constants.hoodAngleOut;
        } else if(desiredHood < Constants.hoodAngleIn){
            return Constants.hoodAngleIn;
        } else{
            return desiredHood;
        }
    }

    //Need to add translation from angle to motor revs
    /** Set desired hood position */
    public void setHoodAngleLimelight(){
        if(m_hoodEncoder.getPosition() < (Constants.hoodAngleToMotorRevs * getHoodPOS(m_visionLookup.angleMap, m_vision.SHOOTER_FROM_TARGET))){
            while(m_hoodEncoder.getPosition() < (Constants.hoodAngleToMotorRevs * getHoodPOS(m_visionLookup.angleMap, m_vision.SHOOTER_FROM_TARGET))){
                m_hoodMotor.set(0.1);
            }
            m_hoodMotor.set(0);
        }
        else{
            while(m_hoodEncoder.getPosition() > (Constants.hoodAngleToMotorRevs * getHoodPOS(m_visionLookup.angleMap, m_vision.SHOOTER_FROM_TARGET))){
                m_hoodMotor.set(-0.1);
            } 
            m_hoodMotor.set(0);
        } 
    }

    public void setHoodPosition(double setpoint){
        if(setpoint < m_hoodEncoder.getPosition()){
            while(m_hoodEncoder.getPosition() > setpoint + 0.24){
                m_hoodMotor.set(-0.1);
            }
        }
        else{
            while(m_hoodEncoder.getPosition() < setpoint - 0.24){
                m_hoodMotor.set(0.1);
            }
        }
        m_hoodMotor.set(0);
    }
}
