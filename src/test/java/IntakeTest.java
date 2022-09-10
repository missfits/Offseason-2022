
import static org.junit.Assert.*;
import org.junit.*;

import edu.wpi.first.wpilibj.simulation.PWMSim;
import frc.robot.subsystems.Intake;

class MyFirstJUnitJupiterTests {
    PWMSim simMotor;
    public static final double DELTA = 1e-2; // acceptable deviation range

    @Before // this method will run before each test
    public void setup() {
        simMotor = new PWMSim(1); // create our simulation PWM motor controller
    }

    @After // this method will run after each test
    public void shutdown() throws Exception {
        simMotor.setSpeed(0);
        //intake.close(); // destroy our intake object
    }

    // @Test // marks this method as a test
    // public void doesntWorkWhenClosed() {
    //     assertEquals(0.0, simMotor.getSpeed(), DELTA); // make sure that the value set to the motor is 0
    // }

    // @Test
    // public void worksWhenOpen() {
        
    // }

    // @Test
    // public void retractTest() {
        
    // }

    @Test
    public void deployTest() {
        simMotor.setSpeed(10);
        System.out.println("Test");
    }




    // @Test
    // void addition() {
    //     intake.setIntakeRollersPower(1);
    // }

}