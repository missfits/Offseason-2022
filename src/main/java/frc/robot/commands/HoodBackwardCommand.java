package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants;
import frc.robot.OI;
import frc.robot.SensorBoard;
import frc.robot.subsystems.Hood;
import frc.robot.subsystems.Indexer;
import frc.robot.subsystems.Shooter;

public class HoodBackwardCommand extends CommandBase{
    private Hood m_hood;
    SensorBoard m_sensorControl;
    OI m_humanControl;
    Indexer m_indexer;

    public HoodBackwardCommand(SensorBoard sensorControl, OI humanControl, Hood hood, Indexer indexer){
        m_hood = hood;
        m_sensorControl = sensorControl;
        m_humanControl = humanControl;
        m_indexer = indexer;

    }

    @Override
    public void initialize() {

    }

    @Override
    public void execute() {
        m_hood.m_hoodEncoder.setPosition(0);
    }
    
    @Override
    public void end(boolean interrupted) {
    }

    @Override
    public boolean isFinished() {
        return !(m_humanControl.isDown(m_humanControl.getDesiredButton(Constants.kControllerID_XBOX, Constants.kButtonID_XboxBack))); //kControllerID_XBOX, kButtonID_XboxB
    }
}
