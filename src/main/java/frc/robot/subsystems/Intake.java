package frc.robot.subsystems;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;
import static frc.robot.Constants.*;
import frc.robot.SensorBoard;
import static edu.wpi.first.wpilibj.DoubleSolenoid.Value.*;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.PneumaticsModuleType;

public class Intake extends SubsystemBase{

    private SensorBoard m_sensorControl;

    private final CANSparkMax m_intakeMotor;

    private DoubleSolenoid m_intakeSolenoid;
    // private final Compressor m_compressor;
    

    public Intake(SensorBoard sensorBoard) {
        m_sensorControl = sensorBoard;

        m_intakeMotor = new CANSparkMax(kCANID_MotorIntake, MotorType.kBrushless);

        m_intakeSolenoid = new DoubleSolenoid(PneumaticsModuleType.CTREPCM, 7, 6);
        // m_compressor = new Compressor(PneumaticsModuleType.CTREPCM);
        

        m_intakeMotor.setInverted(true);

        raiseIntakeArm();


    }

    @Override
    public void periodic() {
    }

    public void setIntakeRollersPower(double power) {
        m_intakeMotor.set(power);
    }

    public double getIntakeRollersPower(double power) {
        return m_intakeMotor.get();
    }

    public void lowerIntakeArm() {
        m_intakeSolenoid.set(kForward);
    }

    public void raiseIntakeArm() {
        m_intakeSolenoid.set(kReverse);
        
    }

    @Override
    public void simulationPeriodic() {

    }
}
