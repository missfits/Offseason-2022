package frc.robot.subsystems;
import edu.wpi.first.wpilibj2.command.PrintCommand;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import edu.wpi.first.wpilibj2.command.WaitCommand;

import static frc.robot.Constants.Constants.*;

import java.util.SortedMap;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import frc.robot.SensorBoard;
import com.revrobotics.RelativeEncoder;
import com.revrobotics.SparkMaxPIDController;
import com.revrobotics.SparkMaxRelativeEncoder;

import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.motorcontrol.MotorController;
import edu.wpi.first.wpilibj.motorcontrol.MotorControllerGroup;

public class Shooter extends SubsystemBase{
    
    private final CANSparkMax m_shooterMotor1;
    private final CANSparkMax m_shooterMotor2;
    private final MotorController m_shooterMotorController1;
    private final MotorController m_shooterMotorController2;
    private final SparkMaxRelativeEncoder m_flywheelEncoder1;
    private final SparkMaxRelativeEncoder m_flywheelEncoder2;
    private final MotorControllerGroup m_shooterGroup;

    private Vision m_vision;
    private Conveyor m_conveyor;
    private VisionLookup m_visionLookup;
    private Hood m_hood;

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

    private double m_desiredFlywheelVel;

    public Shooter(SensorBoard sensorBoard, Vision vision, Conveyor conveyor, VisionLookup visionLookup, Hood hood) {
        m_shooterMotor1 = new CANSparkMax(kCANID_MotorShooter1, MotorType.kBrushless);
        m_shooterMotor2 = new CANSparkMax(kCANID_MotorShooter2, MotorType.kBrushless);
        m_sensorControl = sensorBoard;
        m_hood = hood;

        m_flywheelEncoder1 = (SparkMaxRelativeEncoder) m_shooterMotor1.getEncoder(SparkMaxRelativeEncoder.Type.kHallSensor, 42);
        m_flywheelEncoder2 = (SparkMaxRelativeEncoder) m_shooterMotor2.getEncoder(SparkMaxRelativeEncoder.Type.kHallSensor, 42);

        m_shooterMotor2.follow(m_shooterMotor1);

        m_shooterMotorController1 = m_shooterMotor1;
        m_shooterMotorController2 = m_shooterMotor2;

        m_shooterGroup = new MotorControllerGroup(m_shooterMotorController1, m_shooterMotorController2);
    
        m_shooterGroup.setInverted(false); //confirm
        m_conveyor = conveyor;

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

        m_desiredFlywheelVel = m_sensorControl.getStaticFlywheelVelocityDesired();
        
        m_flywheelPID = m_shooterMotor1.getPIDController();
        configFlywheelPID();
        m_flywheelPID.setOutputRange(m_minVel, m_maxVel);

        m_distance = m_sensorControl.getLimelightDistance();

        System.out.println("end of shooter constructor");

    }

    @Override
    public void periodic() {
        m_currVel = getFlywheelVelocity();
        m_currEnc = m_flywheelEncoder1.getPosition();
        m_sensorControl.setFlywheelStates(m_currVel, m_currEnc);
        m_sensorControl.setFlywheelError(m_error);
        // configFlywheelPID();

    }

    
    public void setFlywheelVelocity(double velocity) {
        m_shooterMotor1.set(velocity);
        //m_flywheelPID.setReference(velocity, CANSparkMax.ControlType.kVelocity);
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
            if (m_numTimesAtSpeed >= 2) {
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
        return m_flywheelEncoder1.getVelocity();
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
        m_shooterMotor1.set(setpoint);
        //m_flywheelPID.setReference(setpoint, CANSparkMax.ControlType.kVelocity);
    }

    /** @return the desired flywheel velocity using lookup table */
    public double getDesiredWheelVelocity(SortedMap<Double, Double> map, double distance){
        return m_visionLookup.shooterInterpolation(map, distance);
     }
    /** Set desired flywheel velocity based on limelight calculations */
    public void setFlywheelVelocityLimelight(){
        m_flywheelPID.setReference(getDesiredWheelVelocity(m_vision.m_visionLookup.velocityMap, m_vision.SHOOTER_FROM_TARGET), CANSparkMax.ControlType.kVelocity);
    }

    

    //Create types for angles, motor revs, inherit/composition from int, double

    /** Set desired hood angle before shooting */
    public void shootSetup(){
        m_hood.setHoodAngleLimelight();
    }

    /** Run conveyor backwards, set flywheel to desired velocity, run conveyor forward and shoot balls */
    public void launch(double velocity){
        //m_shooterMotor1.set(0.5);
        
        //WaitCommand wait = new WaitCommand(0.01);
        //Move balls away from flywheel
        //m_conveyor.setConveyorPosition(0);
        m_conveyor.setConveyorPower(-0.1);
        try{ Thread.sleep(2000);} catch(Exception e){System.out.println("No sleep");}
        m_conveyor.setConveyorPower(0.0);
        // while(m_conveyor.getConveyorPosition() > -4){
        //     try{ Thread.sleep(10);} catch(Exception e){System.out.println("No sleep");}
        // }
        //m_conveyor.setConveyorPower(0.0);
        m_shooterMotor1.set(velocity);
        try{Thread.sleep(2000);} catch(Exception e){System.out.println("No sleep");}
        m_conveyor.setConveyorPower(0.1);
        try{Thread.sleep(3000);} catch(Exception e){System.out.println("No sleep");}
        //m_shooterMotor1.set(0.0);
        //try{Thread.sleep(5000);} catch(Exception e){System.out.println("No sleep");}
        //m_shooterMotor1.set(velocity);
        //try{ Thread.sleep(1000);} catch(Exception e){System.out.println("No sleep");}
        //setFlywheelSetpoint(velocity);
        //setFlywheelVelocityLimelight();
        //Wait for flywheel to speed up to desired velocity
        //if(isFlywheelAtSpeed(getDesiredWheelVelocity(m_vision.m_visionLookup.velocityMap, m_vision.SHOOTER_FROM_TARGET))){
        // while(!isFlywheelAtSpeed(velocity)){
        //     try{ Thread.sleep(10);} catch(Exception e){System.out.println("No sleep");}
        // }
        //Run conveyor forward to shoot balls
        //m_conveyor.setConveyorPower(0.5);
        //try{Thread.sleep(5000);} catch(Exception e){System.out.println("No sleep");}

    }


    @Override
    public void simulationPeriodic() {

    }

    
}
