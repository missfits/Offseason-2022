package frc.robot.subsystems;
import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.math.util.Units;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.networktables.NetworkTableInstance;

import org.photonvision.PhotonCamera;
import org.photonvision.PhotonUtils;

import frc.robot.subsystems.*;
import static frc.robot.Constants.*;
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
    

    public Vision(SensorBoard sensorBoard) {
        final String CAMERA_NETWORKTABLE_NAME = "photonvision";
        m_sensorControl = sensorBoard;
        m_limelight = new PhotonCamera(CAMERA_NETWORKTABLE_NAME);

        //Camera height = 30 inches
        //Target height = 105 inches
        CAMERA_HEIGHT_METERS = Units.inchesToMeters(30);
        TARGET_HEIGHT_METERS = Units.inchesToMeters(105);

        //Angle of limelight relative to horizontal
        CAMERA_PITCH_RADIANS = Units.degreesToRadians(45);

        // How far from the target we want to be
        GOAL_RANGE_METERS = Units.feetToMeters(3);

        //Calculated distance from target
        DISTANCE_FROM_TARGET = -2;
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
        } else {
            // If we have no targets
            DISTANCE_FROM_TARGET = -1;
        }
        m_sensorControl.setLimelightDistance(DISTANCE_FROM_TARGET);
    }

    @Override
    public void simulationPeriodic() {

    }
    
    
}
