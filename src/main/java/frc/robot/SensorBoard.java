package frc.robot;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.DigitalInput;
import static frc.robot.Constants.*;

public class SensorBoard {

    DigitalInput m_topLightSensor, m_bottomLightSensor;
    public boolean m_intaking;

    public SensorBoard() {

        if (!EOS) {
            m_topLightSensor = new DigitalInput(TOP_LIGHT_SENSOR_PORT);
            m_bottomLightSensor = new DigitalInput(BOTTOM_LIGHT_SENSOR_PORT);
        }
        

        SmartDashboard.putNumber("flywheel P", 0.0);
        SmartDashboard.putNumber("flywheel I", 0.0);
        SmartDashboard.putNumber("flywheel D", 0.0);

        SmartDashboard.putNumber("flywheel position", 0.0);
        SmartDashboard.putNumber("flywheel velocity", 0.0);

        SmartDashboard.putNumber("distance", 0.0);
        SmartDashboard.putNumber("static flywheel vel", 0.0);

        m_intaking = false;

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
    
    public boolean getTopLightSensor() {
        if (!EOS){
            return !m_topLightSensor.get();
        }
        return false;
    }

    public boolean getBottomLightSensor() {
        if (!EOS) {
            return !m_bottomLightSensor.get();
        }
        return false;
    }

    public void setIsIntaking(boolean isIntaking) {
        m_intaking = isIntaking;
    }

    public boolean isIntaking() {
        return m_intaking;
    }
}
