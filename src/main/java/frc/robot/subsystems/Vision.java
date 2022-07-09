package frc.robot.subsystems;
import edu.wpi.first.math.util.Units;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.networktables.NetworkTableInstance;

import org.photonvision.PhotonCamera;

import static frc.robot.Constants.*;
import frc.robot.SensorBoard;

public class Vision extends SubsystemBase{

    private SensorBoard m_sensorControl;
    public PhotonCamera m_limelight;
    private double CAMERA_HEIGHT_METERS;
    private double TARGET_HEIGHT_METERS;
    NetworkTable table;
    NetworkTableEntry tx;
    NetworkTableEntry ty;
    NetworkTableEntry ta;
    public double x;
    public double y;
    public double area;

    public Vision(SensorBoard sensorBoard) {
        final String CAMERA_NETWORKTABLE_NAME = "photonvision";
        m_sensorControl = sensorBoard;
        m_limelight = new PhotonCamera(CAMERA_NETWORKTABLE_NAME);
        
        //Camera height = 30 inches
        //Target height = 105 inches
        CAMERA_HEIGHT_METERS = Units.inchesToMeters(30);
        TARGET_HEIGHT_METERS = Units.inchesToMeters(105);


    }

    @Override
    public void periodic() {
        //read values periodically
        var results = m_limelight.getLatestResult();
        
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

        // //post to smart dashboard periodically
        // SmartDashboard.putNumber("LimelightX", x);
        // SmartDashboard.putNumber("LimelightY", y);
        // SmartDashboard.putNumber("LimelightArea", area);
        // System.out.println(x);
    }

    @Override
    public void simulationPeriodic() {

    }

    
}
