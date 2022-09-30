package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants.Constants;
import frc.robot.OI;
import frc.robot.SensorBoard;
import frc.robot.subsystems.Hood;
import frc.robot.subsystems.Indexer;
import frc.robot.subsystems.Shooter;

public class HoodPositionCommand extends CommandBase{
    private Hood m_hood;
    SensorBoard m_sensorControl;
    OI m_humanControl;
    Indexer m_indexer;

    public HoodPositionCommand(SensorBoard sensorControl, OI humanControl, Hood hood, Indexer indexer){
        m_hood = hood;
        m_sensorControl = sensorControl;
        m_humanControl = humanControl;
        m_indexer = indexer;

    }

    @Override
    public void initialize() {
        m_hood.setHoodPosition(m_sensorControl.getHoodPositionDesired());
    }

    @Override
    public void execute() {
        
    }
    
    @Override
    public void end(boolean interrupted) {
        
    }

    @Override
    public boolean isFinished() {
        return !(m_humanControl.isDown(m_humanControl.getDesiredButton(Constants.kControllerID_XBOX, Constants.kButtonID_XboxBack))); //kControllerID_XBOX, kButtonID_XboxB
    }
}