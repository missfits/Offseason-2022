package frc.robot.subsystems;

import frc.robot.Constants;
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

    public Hood(SensorBoard sensorboard){
        m_sensorControl = sensorboard;
        m_hoodMotor = new CANSparkMax(Constants.kCANID_MotorHood, MotorType.kBrushless);
        m_hoodEncoder = m_hoodMotor.getEncoder();
        m_hoodEncoder.setPosition(0);
        m_hoodPID = m_hoodMotor.getPIDController();
    }

    public void setHoodPowerForward(double power){
        while(m_hoodEncoder.getPosition() < 88.7/1.0833){
            m_hoodMotor.set(power);
        }
        m_hoodMotor.set(0);
    }

    public void setHoodPowerBackward(double power){
        while(m_hoodEncoder.getPosition() > 0){
            m_hoodMotor.set(-0.1);
        }
        m_hoodMotor.set(0);
    }
}
