package frc.robot.commands;

import frc.robot.subsystems.Shooter;
import frc.robot.subsystems.Conveyor;
import frc.robot.subsystems.Indexer;

import static frc.robot.Constants.Constants.*;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.SensorBoard;
import frc.robot.OI;


public class ShootCommand extends CommandBase{
    
    Shooter m_shooter;
    SensorBoard m_sensorControl;
    OI m_humanControl;
    Indexer m_indexer;
    Conveyor m_conveyor;

    private double m_desiredVelocity;

    public ShootCommand(SensorBoard sensorControl, OI humanControl, Shooter shooter, Indexer indexer, Conveyor conveyor) {
        m_sensorControl = sensorControl;
        m_humanControl = humanControl;
        m_shooter = shooter;
        m_indexer = indexer;

        m_desiredVelocity = 0.0;

        System.out.println("end of shoot command constructor");
    }

    @Override
    public void initialize() {
        m_desiredVelocity = m_shooter.calculateStaticFlywheelVelocity();
        m_shooter.launch(m_desiredVelocity);
    }

    @Override
    public void execute() {
        
        /* m_desiredVelocity = m_shooter.calculateStaticFlywheelVelocity(); //desired
        // System.out.println(m_desiredVelocity);
        m_shooter.setFlywheelSpeedRPM(m_desiredVelocity); 
        if (m_shooter.isFlywheelAtSpeed(m_desiredVelocity)) {
        // if (true) {
            if (m_humanControl.isDown(m_humanControl.getDesiredButton(kControllerID_XBOX, kButtonID_XboxB))) {
                //index balls
                System.out.println("shooting");
                m_indexer.runEosConveyer(INDEX_ROLLERS_FORWARD_POWER);
            }
            else {
                m_indexer.reset();
            }
        }
        else {
            m_indexer.reset();
        } */

        //m_shooter.setFlywheelPower(0.4); //velocity = 2000

    }

    @Override
    public void end(boolean interrupted) {
        m_conveyor.setConveyorPower(0.0);
        m_shooter.setFlywheelSetpoint(FLYWHEEL_RESET_POWER);
        m_indexer.reset();
        //index 0
    }

    @Override
    public boolean isFinished() {
        // if (!(m_humanControl.isDown(m_humanControl.getDesiredButton(kControllerID_XBOX, kButtonID_XboxX)))){
        //     System.out.println("finished!");
        // }
        return !(m_humanControl.isDown(m_humanControl.getDesiredButton(kControllerID_XBOX, kButtonID_XboxX))); //kControllerID_XBOX, kButtonID_XboxB
    }
}
