package frc.robot.commands;

import frc.robot.subsystems.Shooter;
import frc.robot.subsystems.VisionLookup;
import frc.robot.subsystems.Conveyor;
import frc.robot.subsystems.Hood;
import frc.robot.subsystems.Indexer;

import static frc.robot.Constants.Constants.*;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.SensorBoard;
import frc.robot.ButtonReader;
import frc.robot.OI;


public class ShootCommand extends CommandBase{
    
    Shooter m_shooter;
    SensorBoard m_sensorControl;
    OI m_humanControl;
    Indexer m_indexer;
    Conveyor m_conveyor;
    Double m_distance;
    VisionLookup m_visionLookup;
    Hood m_hood;
    ButtonReader m_button;

    private double m_desiredVelocity;
    private double m_hoodPosition;

    public ShootCommand(SensorBoard sensorControl, Shooter shooter, Indexer indexer, Conveyor conveyor, VisionLookup visionLookup, Hood hood, double distance, ButtonReader button) {
        m_sensorControl = sensorControl;
        m_shooter = shooter;
        m_indexer = indexer;
        m_distance = distance;
        m_visionLookup = visionLookup;
        m_desiredVelocity = m_shooter.getDesiredWheelVelocity(m_visionLookup.velocityMap, m_distance);
        m_hoodPosition = m_hood.getHoodPOS(m_visionLookup.angleMap, m_distance);
        m_button = button;

        System.out.println("end of shoot command constructor");
    }

    @Override
    public void initialize() {
        m_hood.setHoodPosition(m_hoodPosition);
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
        m_shooter.setFlywheelVelocity(-0.1);
        m_indexer.reset();
        //index 0
    }

    @Override
    public boolean isFinished() {
        // if (!(m_humanControl.isDown(m_humanControl.getDesiredButton(kControllerID_XBOX, kButtonID_XboxX)))){
        //     System.out.println("finished!");
        // }
        //return !(m_humanControl.isDown(m_humanControl.getDesiredButton(kControllerID_XBOX, kButtonID_XboxX))); //kControllerID_XBOX, kButtonID_XboxB
        return !(m_button.isDown());
    }
}
