package frc.robot;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.DigitalInput;
import static frc.robot.Constants.*;

public class SensorBoard {

    DigitalInput m_topLightSensor, m_bottomLightSensor;
    public boolean m_intaking;

    public SensorBoard() {

        if (!EOS) {
            m_topLightSensor = new DigitalInput(kSensorID_TopLight);
            m_bottomLightSensor = new DigitalInput(kSensorID_BottomLight);
        }
        

        SmartDashboard.putNumber("flywheel P", FLYWHEEL_P_FAC);
        SmartDashboard.putNumber("flywheel I", FLYWHEEL_I_FAC);
        SmartDashboard.putNumber("flywheel D", FLYWHEEL_D_FAC);
        SmartDashboard.putNumber("flywheel F", FLYWHEEL_F_FAC);

        SmartDashboard.putNumber("flywheel position", 0.0);
        SmartDashboard.putNumber("flywheel velocity", 0.0);

        SmartDashboard.putNumber("drivetrain P", DRIVETRAIN_P_FAC);
        SmartDashboard.putNumber("drivetrain I", DRIVETRAIN_I_FAC);
        SmartDashboard.putNumber("drivetrain D", DRIVETRAIN_D_FAC);

        SmartDashboard.putNumber("distance", 0.0);
        SmartDashboard.putNumber("static flywheel vel", 2000.0);

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

    public double getFlywheelFEntry() {
        return SmartDashboard.getNumber("flywheel F", FLYWHEEL_F_FAC);
    }

    public double getFlywheelPEntry() {
        return SmartDashboard.getNumber("flywheel P", FLYWHEEL_P_FAC);
    }

    public double getFlywheelIEntry() {
        return SmartDashboard.getNumber("flywheel I", FLYWHEEL_I_FAC);
    }

    public double getFlywheelDEntry() {
        return SmartDashboard.getNumber("flywheel D", FLYWHEEL_D_FAC);
    }

    public double getDrivetrainPEntry() {
        return SmartDashboard.getNumber("drivetrain P", DRIVETRAIN_P_FAC);
    }

    public double getDrivetrainIEntry() {
        return SmartDashboard.getNumber("drivetrain I", DRIVETRAIN_I_FAC);
    }

    public double getDrivetrainDEntry() {
        return SmartDashboard.getNumber("drivetrain D", DRIVETRAIN_D_FAC);
    }

    public double getLimelightDistance() {
        return SmartDashboard.getNumber("distance", 0.0);
    }

    public double getStaticFlywheelVelocityDesired() {
        return SmartDashboard.getNumber("static flywheel vel", 2000.0);
    }

    public void setFlywheelError(double error) {
        SmartDashboard.putNumber("flywheel errror", error);
    }

    public void setTargetYaw(double yaw){
        SmartDashboard.putNumber("target yaw", yaw);
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
