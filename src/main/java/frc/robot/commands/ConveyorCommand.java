package frc.robot.commands;

import frc.robot.Constants.*;

import frc.robot.subsystems.Conveyor;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.SensorBoard;
import frc.robot.OI;


public class ConveyorCommand extends CommandBase{
    
    Conveyor m_conveyor;
    SensorBoard m_sensorControl;
    OI m_humanControl;

    public ConveyorCommand(SensorBoard sensorControl, OI humanControl, Conveyor conveyor) {
        m_sensorControl = sensorControl;
        m_humanControl = humanControl;
        m_conveyor = conveyor;
    }

    @Override
    public void initialize() {

    }

    @Override
    public void execute() {
       m_conveyor.setConveyorPower(0.5);
    }

    @Override
    public void end(boolean interrupted) {
        m_conveyor.setConveyorPower(0.0);
    }

    @Override
    public boolean isFinished() {
        return !(m_humanControl.isDown(m_humanControl.getDesiredButton(Constants.kControllerID_XBOX, Constants.kButtonID_XboxLB))); 
    }
}

