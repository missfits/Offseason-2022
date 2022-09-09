import static org.junit.jupiter.api.Assertions.assertEquals;

import static org.junit.Assert.*;
import org.junit.*;

import frc.robot.subsystems.Intake;

import org.junit.jupiter.api.Test;

class MyFirstJUnitJupiterTests {

    private final Intake intake = new Intake(null);

    @Test
    void addition() {
        intake.setIntakeRollersPower(1);
    }

}