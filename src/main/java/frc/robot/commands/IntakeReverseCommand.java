package frc.robot.commands;

import static frc.robot.Constants.*;
import frc.robot.subsystems.Intake;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.SensorBoard;
import frc.robot.OI;


public class IntakeReverseCommand extends CommandBase{
    
    Intake m_intake;
    SensorBoard m_sensorControl;
    OI m_humanControl;

    public IntakeReverseCommand(SensorBoard sensorControl, OI humanControl, Intake intake) {
        m_sensorControl = sensorControl;
        m_humanControl = humanControl;
        m_intake = intake;

        m_intake.raiseIntakeArm();

    }

    @Override
    public void initialize() {

    }

    @Override
    public void execute() {
        m_intake.setIntakeRollersPower(INTAKE_ROLLERS_REVERSE_POWER);
        m_intake.lowerIntakeArm();
    }

    @Override
    public void end(boolean interrupted) {
        m_intake.setIntakeRollersPower(INTAKE_ROLLERS_RESET_POWER);
        m_intake.raiseIntakeArm();
    }

    @Override
    public boolean isFinished() {
        return !(m_humanControl.isDown(m_humanControl.getDesiredButton(XBOX_CONTROLLER_PORT, INTAKE_BUTTON_ID))); 
    }
}
