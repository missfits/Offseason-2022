package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.SensorBoard;

import static frc.robot.Constants.Constants.*;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;
import com.revrobotics.SparkMaxRelativeEncoder;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.motorcontrol.MotorController;
import edu.wpi.first.wpilibj.motorcontrol.MotorControllerGroup;
import edu.wpi.first.math.controller.PIDController;
import java.util.ArrayList;


public class Drivetrain extends SubsystemBase{

    private final CANSparkMax m_leftPrimary;
    private final CANSparkMax m_leftSecondary;
    private final CANSparkMax m_rightPrimary;
    private final CANSparkMax m_rightSecondary;

    public final SparkMaxRelativeEncoder m_leftPrimaryEncoder;
    public final SparkMaxRelativeEncoder m_leftSecondaryEncoder;
    public final SparkMaxRelativeEncoder m_rightPrimaryEncoder;
    public final SparkMaxRelativeEncoder m_rightSecondaryEncoder;

    public static DifferentialDrive m_robotDrive;

    private final MotorController m_leftPrimaryController;
    private final MotorController m_leftSecondaryController;
    private final MotorController m_rightPrimaryController;
    private final MotorController m_rightSecondaryController;

    private final MotorControllerGroup m_leftGroup;
    private final MotorControllerGroup m_rightGroup;

    private final double m_pFac;
    private final double m_iFac;
    private final double m_dFac;
    public PIDController m_driveTrainPID;
    private final SensorBoard m_sensorControl;


    public Drivetrain(SensorBoard sensorControl) {
        m_sensorControl = sensorControl;
        m_leftPrimary = new CANSparkMax(kCANID_MotorLeft1, MotorType.kBrushless);
        m_leftSecondary = new CANSparkMax(kCANID_MotorLeft2, MotorType.kBrushless);
        m_rightPrimary = new CANSparkMax(kCANID_MotorRight1, MotorType.kBrushless);
        m_rightSecondary = new CANSparkMax(kCANID_MotorRight2, MotorType.kBrushless);

        m_leftPrimaryEncoder = (SparkMaxRelativeEncoder) m_leftPrimary.getEncoder(SparkMaxRelativeEncoder.Type.kHallSensor, 42);
        m_leftSecondaryEncoder = (SparkMaxRelativeEncoder) m_leftSecondary.getEncoder(SparkMaxRelativeEncoder.Type.kHallSensor, 42);
        m_rightPrimaryEncoder = (SparkMaxRelativeEncoder) m_rightPrimary.getEncoder(SparkMaxRelativeEncoder.Type.kHallSensor, 42);
        m_rightSecondaryEncoder = (SparkMaxRelativeEncoder) m_rightSecondary.getEncoder(SparkMaxRelativeEncoder.Type.kHallSensor, 42);

        m_leftSecondary.follow(m_leftPrimary);
        m_rightSecondary.follow(m_rightPrimary);

        m_leftPrimaryController = m_leftPrimary;
        m_leftSecondaryController = m_leftSecondary;
        m_rightPrimaryController = m_rightPrimary;
        m_rightSecondaryController = m_rightSecondary;

        m_leftGroup = new MotorControllerGroup(m_leftPrimaryController, m_leftSecondaryController);
        m_rightGroup = new MotorControllerGroup(m_rightPrimaryController, m_rightSecondaryController);

        m_robotDrive = new DifferentialDrive(m_leftGroup, m_rightGroup);
        m_leftGroup.setInverted(true);
    
        m_pFac = m_sensorControl.getFlywheelFEntry();
        m_iFac = 0.0;
        m_dFac = 0.0;
        m_driveTrainPID = new PIDController(m_pFac, m_iFac, m_dFac);
    }

    @Override
    public void periodic() {
        logFaults(m_leftPrimary, true);
        logFaults(m_leftSecondary, true);
        logFaults(m_rightPrimary, true);
        logFaults(m_rightSecondary, true);
        logFaults(m_leftPrimary, false);
        logFaults(m_leftSecondary, false);
        logFaults(m_rightPrimary, false);
        logFaults(m_rightSecondary, false);
    }

    public void tankDrive(double leftThrust, double rightThrust) {
        m_robotDrive.tankDrive(leftThrust, rightThrust);
    }

    @Override
    public void simulationPeriodic() {

    }

    public void arcadeDrive(double forwardSpeed, double rotationSpeed) {
        m_robotDrive.arcadeDrive(forwardSpeed, rotationSpeed);
    }

    /** Prints a list of faults for a given CANSparkMax motor controller to the Rio log if faults are detected
     * @param controller the CANSparkMax controller to check for faults
     * @param stickyFault is true for checking sticky faults, false for checking regular faults
     */
    public void logFaults(CANSparkMax controller, boolean stickyFault){
        short faults = (stickyFault) ? controller.getStickyFaults() : controller.getFaults();
        String faultType = (stickyFault)? "Sticky Faults" : "Faults";
        if (faults > 0){
            ArrayList<CANSparkMax.FaultID> idList = new ArrayList<>();
            for (int i = 0; i < 15; i++){
                if (faults % 2 == 1){                                   
                    idList.add(CANSparkMax.FaultID.fromId(i));          // adds fault to list if corresponding bit is 1
                }
                faults /= 2;
            }
            System.out.println(controller + " " + faultType + ": " + idList);
        }
    }
}
