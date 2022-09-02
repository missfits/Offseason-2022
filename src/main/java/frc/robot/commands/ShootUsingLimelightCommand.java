
package frc.robot.commands;

import frc.robot.subsystems.Conveyor;
import frc.robot.subsystems.Drivetrain;
import frc.robot.subsystems.Shooter;

import static frc.robot.Constants.Constants.*;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.SensorBoard;
import frc.robot.OI;
import frc.robot.subsystems.Vision;


public class ShootUsingLimelightCommand extends CommandBase{
    private SensorBoard m_sensorControl;
    private OI m_humanControl;
    private Vision m_vision;
    private Shooter m_shooter;
    



    public ShootUsingLimelightCommand (SensorBoard sensorControl, OI humanControl, Vision vision, Shooter shooter) {
        m_sensorControl = sensorControl;
        m_humanControl = humanControl;
        m_vision = vision;
        isShooting = true;
    }

    @Override
    public void initialize() {
    }

    @Override
    public void execute() {
        var results = m_vision.m_limelight.getLatestResult();
        if (results.hasTargets()) {
            //Call turn to target
            m_vision.turnToTarget();
            
            //Setup
            m_shooter.setHoodAngleLimelight();
            m_shooter.shootSetup();

            //Shoot ball
            m_shooter.launch();

            
        } else {
            
        }
        
    }

    @Override
    public void end(boolean interrupted) {
        isShooting = false;
    }

    @Override
    public boolean isFinished() {
        //when should it finish?
        //Change
        return !(m_humanControl.isDown(m_humanControl.getDesiredButton(kControllerID_XBOX, kButtonID_XboxRB))); 
    }
}

