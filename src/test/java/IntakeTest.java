import static org.junit.jupiter.api.Assertions.assertEquals;

import static org.junit.Assert.*;
import org.junit.*;

import frc.robot.subsystems.Intake;

import org.junit.jupiter.api.Test;

class MyFirstJUnitJupiterTests {

    private final Intake intake = new Intake(null);

    @Before // this method will run before each test
    public void setup() {
        
    }

    @After // this method will run after each test
    public void shutdown() throws Exception {
        intake.close(); // destroy our intake object
    }

    @Test // marks this method as a test
    public void doesntWorkWhenClosed() {
        intake.retract(); // close the intake
        intake.activate(0.5); // try to activate the motor
        assertEquals(0.0, simMotor.getSpeed(), DELTA); // make sure that the value set to the motor is 0
    }

    @Test
    public void worksWhenOpen() {
        intake.deploy();
        intake.activate(0.5);
        assertEquals(0.5, simMotor.getSpeed(), DELTA);
    }

    @Test
    public void retractTest() {
        intake.retract();
        assertEquals(DoubleSolenoid.Value.kReverse, simPiston.get());
    }

    @Test
    public void deployTest() {
        intake.deploy();
        assertEquals(DoubleSolenoid.Value.kForward, simPiston.get());
    }




    @Test
    void addition() {
        intake.setIntakeRollersPower(1);
    }

}