package frc.robot;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class SensorBoard {

    public SensorBoard() {

        SmartDashboard.putNumber("flywheel P", 0.0);
        SmartDashboard.putNumber("flywheel I", 0.0);
        SmartDashboard.putNumber("flywheel D", 0.0);

        SmartDashboard.putNumber("flywheel position", 0.0);
        SmartDashboard.putNumber("flywheel velocity", 0.0);

        SmartDashboard.putNumber("distance", 0.0);
        SmartDashboard.putNumber("static flywheel vel", 0.0);

    }

    public double clamp(double in, double min, double max) {
        if (in < min) {
            return min;
        }
        if (in > max) {
            return max;
        }
        return in;
    }

    public void setFlywheelStates(double currVel, double currEnc) {
        SmartDashboard.putNumber("flywheel position", currEnc);
        SmartDashboard.putNumber("flywheel velocity", currVel);
    }

    public double getFlywheelPEntry() {
        return SmartDashboard.getNumber("flywheel P", 0.0);
    }

    public double getFlywheelIEntry() {
        return SmartDashboard.getNumber("flywheel I", 0.0);
    }

    public double getFlywheelDEntry() {
        return SmartDashboard.getNumber("flywheel D", 0.0);
    }

    public double getLimelightDistance() {
        return SmartDashboard.getNumber("distance", 0.0);
    }

    public double getStaticFlywheelVelocityDesired() {
        return SmartDashboard.getNumber("static flywheel vel", 0.0);
    }
    
}
