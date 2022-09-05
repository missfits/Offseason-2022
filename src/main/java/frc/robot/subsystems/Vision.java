package frc.robot.subsystems;
import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.math.util.Units;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

import static frc.robot.Constants.Constants.*;

import java.util.HashMap;
import java.util.Map;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.networktables.NetworkTableInstance;

import org.photonvision.PhotonCamera;
import org.photonvision.PhotonUtils;

import frc.robot.subsystems.*;
import frc.robot.OI;
import frc.robot.RobotContainer;
import frc.robot.SensorBoard;

public class Vision extends SubsystemBase{

    private SensorBoard m_sensorControl;
    public PhotonCamera m_limelight;
    NetworkTableEntry tx;
    NetworkTableEntry ty;
    NetworkTableEntry ta;
    public double x;
    public double y;
    public double area;
    final double CAMERA_HEIGHT_METERS;
    final double TARGET_HEIGHT_METERS;
    final double CAMERA_PITCH_RADIANS;
    public final double GOAL_RANGE_METERS;
    public double DISTANCE_FROM_TARGET;
    public double SHOOTER_FROM_TARGET;
    public VisionLookup m_visionLookup;
    private double forwardSpeed;
    private double rotationSpeed;
    private PIDController turnController;
    private final double ANGULAR_P;
    private final double ANGULAR_D;
    private Drivetrain m_drivetrain;
    private OI m_humanControl;
    
    

    public Vision(SensorBoard sensorBoard, VisionLookup visionLookup, Drivetrain drivetrain, OI humanControl) {
        final String CAMERA_NETWORKTABLE_NAME = "limelight";
        m_sensorControl = sensorBoard;
        m_visionLookup = visionLookup;
        m_limelight = new PhotonCamera(CAMERA_NETWORKTABLE_NAME);
        m_humanControl = humanControl;
        m_drivetrain = drivetrain;
        ANGULAR_P = 0.1;
        ANGULAR_D = 0.0;
        turnController = new PIDController(ANGULAR_P, 0, ANGULAR_D);


        //Camera height = 30 inches
        //Target height = 105 inches
        CAMERA_HEIGHT_METERS = Units.inchesToMeters(30);
        TARGET_HEIGHT_METERS = Units.inchesToMeters(105);

        //Angle of limelight relative to horizontal
        CAMERA_PITCH_RADIANS = Units.degreesToRadians(45);

        // How far from the target we want to be
        GOAL_RANGE_METERS = Units.feetToMeters(3);

        //Calculated distance of limelight from target
        DISTANCE_FROM_TARGET = -2;

        //Calculated distance of shooter from target
        SHOOTER_FROM_TARGET = -2;
    }

    @Override
    public void periodic() {
        //read values periodically
        var results = m_limelight.getLatestResult();
        
        // Finds center of target relative to top left corner of view
        if (results.hasTargets()) {
            var bestTarget = results.getBestTarget();
            if (bestTarget != null) {
                area = bestTarget.getArea();
                var corners = bestTarget.getCorners();

                x = 0.0; 
                y = 0.0;
                for (var corner : corners) {
                    x += corner.x;
                    y += corner.y;
                }
                x /= corners.size();
                y /= corners.size();
            }
        }

        //Getting the distance of targets
        if (results.hasTargets()) {
            DISTANCE_FROM_TARGET = (2 * PhotonUtils.calculateDistanceToTargetMeters(CAMERA_HEIGHT_METERS, TARGET_HEIGHT_METERS, CAMERA_PITCH_RADIANS, Units.degreesToRadians(results.getBestTarget().getPitch()))); // idk why this needs to be doubled but it works now
            //To Do : Need to add offset
            SHOOTER_FROM_TARGET = (2 * PhotonUtils.calculateDistanceToTargetMeters(CAMERA_HEIGHT_METERS, TARGET_HEIGHT_METERS, CAMERA_PITCH_RADIANS, Units.degreesToRadians(results.getBestTarget().getPitch())));
        } else {
            // If we have no targets
            DISTANCE_FROM_TARGET = -1;
            SHOOTER_FROM_TARGET = -1;
        }
        
    }

    @Override
    public void simulationPeriodic() {

    }

    /** @return desired hood position based on angleMap */
    public double getHoodPOS(){
        double desiredHood = shooterInterpolation(m_visionLookup.angleMap);
        //To Do : Need to update hoodAngleOut and hoodAngleIn with real values during testing
        if(desiredHood > hoodAngleOut){
            return hoodAngleOut;
        } else if(desiredHood < hoodAngleIn){
            return hoodAngleIn;
        } else{
            return desiredHood;
        }
    }

    /** @return the desired flywheel velocity using lookup table */
    public double getDesiredWheelVelocity(){
        return shooterInterpolation(m_visionLookup.velocityMap);
     }

    /** Generalized interpolation code for shooter */
    public double shooterInterpolation(HashMap<Double, Double> map){
        double distance = SHOOTER_FROM_TARGET; //in meters
        double originalDistance = distance;
        double lowerDistance = 1000;  
        //Find lower distance in range by going through keys in map
        for (double x : map.keySet()){
            if(x < originalDistance && x < lowerDistance){
                lowerDistance = x;
            }
        }
    
        //if either of the int values are higher than the highest lookup table value,
        //set the values to the highest lookup table value
        if(lowerDistance > m_visionLookup.largestKey(map)){
            lowerDistance = m_visionLookup.largestKey(map);
        }

        double upperDistance = lowerDistance + 1;

        //gets angle from the lookup table
        double lowerVal = map.get(lowerDistance);
        double upperVal = map.get(upperDistance);
    
        //multiply the difference in the distance and floored value by the slope to get desired position of hood for that small distance 
        //then add that to the desired position of the lower floored value
        double desiredVal = ((upperVal - lowerVal)*(originalDistance - lowerDistance)  + lowerVal);
        return desiredVal;
    }


    /** Does not end until yaw = 0 or there are no targets
     * @return true if robot fully turns to target
     */
    public boolean turnToTarget(){
        forwardSpeed = -m_humanControl.XBOX1.getY();
        var results = m_limelight.getLatestResult();
        Timer timer = new Timer();
        timer.start();
        while(timer.get() < 60){
            // If there is a target, turn to target until yaw = 0, then return true
            // If there is no target, return false

            if (results.hasTargets()) {
                //Calculates angle needed to turn towards target using PID
                rotationSpeed = -turnController.calculate(results.getBestTarget().getYaw(), 0);
            } else {
                //Return false if there are no targets
                return false;
            
            }
            m_drivetrain.arcadeDrive(forwardSpeed, rotationSpeed);
            m_sensorControl.setTargetYaw(results.getBestTarget().getYaw());
            if(rotationSpeed == 0){
                //End function if robot has already turned
                return true;
            }
        }
        //If times exceeds 60 seconds, print out error message and return false
        if(results.hasTargets()){
            System.out.println("Function timed out; Yaw = " + results.getBestTarget().getYaw());
            return false;
        }
        else{
            System.out.println("Function timed out; No target found");
            return false;
        }
        
    }
       
    
}
