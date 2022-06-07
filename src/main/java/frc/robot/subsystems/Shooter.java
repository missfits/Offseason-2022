package frc.robot.subsystems;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;
import static frc.robot.Constants.*;
import frc.robot.SensorBoard;
import com.revrobotics.RelativeEncoder;

import edu.wpi.first.math.controller.PIDController;

public class Shooter extends SubsystemBase{
    
    private final CANSparkMax m_shooterMotor;
    private RelativeEncoder m_encoder;

    private SensorBoard m_sensorControl;
    
    private double m_pFac;
    private double m_iFac;
    private double m_dFac;

    private double m_tolerance;
    private double m_currVel;
    private double m_currEnc;

    private double m_minVel;
    private double m_maxVel;

    private double m_distance;

    private int m_numTimesAtSpeed;

    private PIDController m_flywheelPID;

    private boolean m_atSpeed;

    public Shooter(SensorBoard sensorBoard) {
        m_shooterMotor = new CANSparkMax(SHOOTER_MOTOR_PORT, MotorType.kBrushless);
        m_sensorControl = sensorBoard;
        m_shooterMotor.setInverted(false); //confirm
        m_encoder = m_shooterMotor.getEncoder();
        
        m_tolerance = 0.0;
        m_currVel = 0.0;
        m_numTimesAtSpeed = 0;

        m_minVel = 0.0;
        m_maxVel = 1.0;

        m_atSpeed = false;

        m_pFac = m_sensorControl.getFlywheelPEntry();
        m_iFac = m_sensorControl.getFlywheelIEntry();
        m_dFac = m_sensorControl.getFlywheelDEntry();
        m_flywheelPID = new PIDController(m_pFac, m_iFac, m_dFac);

        m_distance = m_sensorControl.getLimelightDistance();

        System.out.println("end of shooter constructor");

    }

    @Override
    public void periodic() {
        m_currVel = getFlywheelVelocity();
        m_currEnc = m_encoder.getPosition();
        m_sensorControl.setFlywheelStates(m_currVel, m_currEnc);


    }

    public void configFlywheelPID() {
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
        return m_encoder.getVelocity();
    }

    public void setFlywheelSpeedRPM(double desiredVelocity) { //in RPM, since using currVel
        m_shooterMotor.set(m_sensorControl.clamp(m_flywheelPID.calculate(m_currVel, desiredVelocity), m_minVel, m_maxVel));
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
        m_flywheelPID.setSetpoint(setpoint);
    }

    public void setFlywheelPower(double power) {
        m_shooterMotor.set(power);
    }

    @Override
    public void simulationPeriodic() {

    }
}
