import static org.junit.Assert.*;
import org.junit.*;

import frc.robot.subsystems.VisionLookup;

public class VisionLookupTest {
    private VisionLookup visionLookupTest = new VisionLookup();
    
    @Test
    public void desiredVelTest(){
        //double vel = subsystems.m_visionTest.getDesiredWheelVelocity(subsystems.m_visionLookupTest.testMap, 0.5);
        double vel = visionLookupTest.shooterInterpolation(visionLookupTest.testMap, 0.5);
        assertEquals(7.5, vel, 0);
    }
}
