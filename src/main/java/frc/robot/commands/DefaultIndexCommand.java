package frc.robot.commands;

import static frc.robot.Constants.*;
import frc.robot.subsystems.Indexer;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.SensorBoard;
import frc.robot.OI;


public class DefaultIndexCommand extends CommandBase{
    SensorBoard m_sensorControl;
    OI m_humanControl;
    Indexer m_indexer;

    boolean m_intaking, m_top, m_bottom;

    public DefaultIndexCommand (SensorBoard sensorControl, OI humanControl, Indexer indexer) {
        m_sensorControl = sensorControl;
        m_humanControl = humanControl;
        m_indexer = indexer;

        m_intaking = false;
        m_top = false;
        m_bottom = false;

        addRequirements(indexer);
    }

    @Override
    public void initialize() {

    }

    @Override
    public void execute() {
        if (!EOS) {
            if (m_intaking) {
                if (m_top || m_bottom) {
                    if (m_top && m_bottom) {
                        m_indexer.reset();
                    }
                    m_indexer.ballTwo();
                }
                else {
                    m_indexer.ballOne();
                }
            }
            else {
                if (m_bottom && !m_top) {
                    m_indexer.ballOne();
                }
            }
        }
        // else if (m_intaking) {
        //     m_indexer.runEosConveyer(INDEX_ROLLERS_FORWARD_POWER);
        // }
    }

    public void end(boolean interrupted) {
        m_indexer.reset();
    }
}