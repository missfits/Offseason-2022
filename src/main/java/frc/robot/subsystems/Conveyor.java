package frc.robot.subsystems;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import com.revrobotics.CANSparkMax;
import com.revrobotics.RelativeEncoder;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import frc.robot.SensorBoard;
import static frc.robot.Constants.Constants.*;

public class Conveyor extends SubsystemBase{

    private SensorBoard m_sensorControl;

    public final CANSparkMax m_conveyorMotor;
    public final RelativeEncoder m_conveyorEncoder;

    public Conveyor(SensorBoard sensorBoard) {
        m_sensorControl = sensorBoard;
        m_conveyorMotor = new CANSparkMax(kCANID_MotorConveyor, MotorType.kBrushless);
        m_conveyorEncoder = m_conveyorMotor.getEncoder();


    }

    @Override
    public void periodic() {
    }

    public void setConveyorPower(double power) {
        m_conveyorMotor.set(power);
    }

    public double getConveyorPower(double power) {
        return m_conveyorMotor.get();
    }

    public void setConveyorPosition(double position) {
        m_conveyorEncoder.setPosition(position);
    }

    public double getConveyorPosition(double position) {
        return m_conveyorEncoder.getPosition();
    }

    @Override
    public void simulationPeriodic() {

    }
}
