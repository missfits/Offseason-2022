package frc.robot.subsystems;
import edu.wpi.first.wpilibj2.command.PrintCommand;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

import static frc.robot.Constants.Constants.*;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import frc.robot.SensorBoard;
import com.revrobotics.RelativeEncoder;
import com.revrobotics.SparkMaxPIDController;

import edu.wpi.first.math.controller.PIDController;

public class Shooter extends SubsystemBase{
    
    public final CANSparkMax m_shooterMotor;
    private final CANSparkMax m_hoodMotor;
    private final CANSparkMax m_rollerMotor;
    public final RelativeEncoder m_flyWheelEncoder;
    private Vision m_vision;

    private SensorBoard m_sensorControl;
    
    private double m_fFac;
    private double m_pFac;
    private double m_iFac;
    private double m_dFac;

    private double m_tolerance;
    private double m_currVel;
    private double m_currEnc;

    private double m_minVel;
    private double m_maxVel;

    private double m_distance;
    private double m_error;

    private int m_numTimesAtSpeed;

    public SparkMaxPIDController m_flywheelPID;

    private boolean m_atSpeed;

    public Shooter(SensorBoard sensorBoard, Vision vision) {
        m_shooterMotor = new CANSparkMax(kCANID_MotorShooter, MotorType.kBrushless);
        m_hoodMotor = new CANSparkMax(kCANID_MotorHood, MotorType.kBrushless);
        m_rollerMotor = new CANSparkMax(kCANID_MotorRoller, MotorType.kBrushless);
        m_sensorControl = sensorBoard;
        m_shooterMotor.setInverted(false); //confirm
        m_flyWheelEncoder = m_shooterMotor.getEncoder();
        
        m_tolerance = 50;
        m_currVel = 0.0;
        m_numTimesAtSpeed = 0;
        m_error = 0.0;

        m_minVel = -1.0;
        m_maxVel = 1.0;

        m_atSpeed = false;

        m_fFac = m_sensorControl.getFlywheelFEntry();
        m_pFac = m_sensorControl.getFlywheelPEntry();
        m_iFac = m_sensorControl.getFlywheelIEntry();
        m_dFac = m_sensorControl.getFlywheelDEntry();
        
        m_flywheelPID = m_shooterMotor.getPIDController();
        configFlywheelPID();
        m_flywheelPID.setOutputRange(m_minVel, m_maxVel);

        m_distance = m_sensorControl.getLimelightDistance();

        System.out.println("end of shooter constructor");

    }

    @Override
    public void periodic() {
        m_currVel = getFlywheelVelocity();
        m_currEnc = m_flyWheelEncoder.getPosition();
        m_sensorControl.setFlywheelStates(m_currVel, m_currEnc);
        m_sensorControl.setFlywheelError(m_error);
        // configFlywheelPID();

    }

    public void configFlywheelPID() {
        m_fFac = m_sensorControl.getFlywheelFEntry();
        m_pFac = m_sensorControl.getFlywheelPEntry();
        m_iFac = m_sensorControl.getFlywheelIEntry();
        m_dFac = m_sensorControl.getFlywheelDEntry();
        m_flywheelPID.setFF(m_fFac);
        m_flywheelPID.setP(m_pFac);
        m_flywheelPID.setI(m_iFac);
        m_flywheelPID.setD(m_dFac);
    }

    public boolean isFlywheelAtSpeed(double rpm) {
        m_tolerance = rpm * 0.02;

        if (rpm > m_currVel - m_tolerance) {
            m_numTimesAtSpeed ++;
            if (m_numTimesAtSpeed >= 5) {
                m_atSpeed = true;
            }
        }
        else {
            m_numTimesAtSpeed = 0;
            m_atSpeed = false;
        }

        return m_atSpeed;
    }



    public double getFlywheelVelocity() {
        return m_flyWheelEncoder.getVelocity();
    }

    public void setFlywheelSpeedRPM(double desiredVelocity) { //in RPM, since using currVel
        m_error = desiredVelocity - m_currVel;
        setFlywheelSetpoint(desiredVelocity);
    }

    public double calculateFlywheelVelocityDesired() { //feet
        if (m_distance > 5.0) {
            return 2000.0;
        }
        else {
            return 1650.0;
        }


    }

    public double calculateStaticFlywheelVelocity() {
        return m_sensorControl.getStaticFlywheelVelocityDesired();
    }

    public void setFlywheelSetpoint(double setpoint) {
        m_flywheelPID.setReference(setpoint, CANSparkMax.ControlType.kVelocity);
    }

    public void setFlywheelPower(double power) {
        m_shooterMotor.set(power);
    }

    public void setFlywheelVelocityLimelight(){
        m_flywheelPID.setReference(m_vision.getDesiredWheelVelocity(), CANSparkMax.ControlType.kVelocity);
    }

    @Override
    public void simulationPeriodic() {

    }
}
