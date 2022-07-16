package frc.robot.subsystems;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;
import static frc.robot.Constants.*;
import frc.robot.SensorBoard;


public class Indexer extends SubsystemBase{

    private SensorBoard m_sensorControl;
    private CANSparkMax m_indexMotor;

    boolean m_top, m_bottom, m_intaking, m_half, m_full;

    public Indexer(SensorBoard sensorBoard) {
        m_sensorControl = sensorBoard;
        m_indexMotor = new CANSparkMax(kCANID_MotorIndex, MotorType.kBrushless);
        m_indexMotor.setInverted(true); 

        m_top = false;
        m_bottom = false;
        m_intaking = false;

        m_half = false;
        m_full = false;

    }

    @Override
    public void periodic() {
        if (!EOS) {
            m_top = m_sensorControl.getTopLightSensor();
            m_bottom = m_sensorControl.getBottomLightSensor();
            m_intaking = m_sensorControl.isIntaking();

            m_half = ((m_top && !m_bottom) || (!m_top && m_bottom));
            m_full = (m_top && m_bottom);
        }

    }

    public void setIndexRollersPower(double power) {
        m_indexMotor.set(power);
    }

    public double getIndexRollersPower() {
        return m_indexMotor.get();
    }

    public void ballOne() {
        if (!m_top) {
            setIndexRollersPower(INDEX_ROLLERS_FORWARD_POWER);
        }
        else {
            setIndexRollersPower(INDEX_RESET_POWER);
        }
    }

    public void ballTwo() {
        if (m_top && !m_bottom) {
            if (!m_bottom) {
                setIndexRollersPower(INDEX_ROLLERS_REVERSE_POWER);
            }
        }
        else if (m_top && m_bottom){
            //already 2 balls
            setIndexRollersPower(INDEX_RESET_POWER);
        }
        else if (m_bottom && !m_top) {
            setIndexRollersPower(INDEX_ROLLERS_FORWARD_POWER);
        }
        else { //has a ball but it's not detected?
            setIndexRollersPower(INDEX_ROLLERS_FORWARD_POWER);
        }
    }

    public void reset() {
        setIndexRollersPower(INDEX_RESET_POWER);
    }

    public boolean getHalf() {
        return m_half;
    }

    public boolean getFull() {
        return m_full;
    }

    public void runEosConveyer(double power) {
        setIndexRollersPower(power);
    }

    @Override
    public void simulationPeriodic() {

    }
}
