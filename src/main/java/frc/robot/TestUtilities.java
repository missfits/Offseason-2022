package frc.robot;
import frc.robot.subsystems.*;

public class TestUtilities {
  private SensorBoard m_sensorControlTest;
  private OI m_humanControlTest;
  public Shooter m_shooterTest ;
  public Intake m_intakeTest ;
  public Drivetrain m_drivetrainTest ;
  public Indexer m_indexerTest ;
  public Vision m_visionTest ;
  public VisionLookup m_visionLookupTest ;
  public Conveyor m_conveyorTest;

    public void testUtilities(){

        m_sensorControlTest = new SensorBoard();
        m_humanControlTest = new OI();
        m_intakeTest = new Intake(m_sensorControlTest);
        m_drivetrainTest = new Drivetrain(m_sensorControlTest);
        m_indexerTest = new Indexer(m_sensorControlTest);
        m_visionTest = new Vision(m_sensorControlTest, m_visionLookupTest, m_drivetrainTest, m_humanControlTest);
        m_shooterTest = new Shooter(m_sensorControlTest, m_visionTest, m_conveyorTest, m_visionLookupTest);
        m_conveyorTest = new Conveyor(m_sensorControlTest);
        m_visionLookupTest = new VisionLookup();

       
    }
}
