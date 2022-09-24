
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
    private boolean robotHasTurned;

    public ShootUsingLimelightCommand (SensorBoard sensorControl, OI humanControl, Vision vision, Shooter shooter) {
        m_sensorControl = sensorControl;
        m_humanControl = humanControl;
        m_vision = vision;
        isShooting = true;
        robotHasTurned = false;
    }

    @Override
    public void initialize() {
    }

    @Override
    public void execute() {
        var results = m_vision.m_limelight.getLatestResult();
        if (results.hasTargets()) {
            robotHasTurned = m_vision.turnToTarget();
            //Shoot balls if robot has fully turned to target and yaw = 0
            if(robotHasTurned == true){
                m_shooter.shootSetup();
                m_shooter.launch(m_shooter.getDesiredWheelVelocity(m_vision.m_visionLookup.velocityMap, m_vision.SHOOTER_FROM_TARGET));
            }
            
        } else {
            System.out.println("No targets found");
        }
        
    }

    @Override
    public void end(boolean interrupted) {
        m_shooter.setFlywheelVelocity(-1000);
    }

    @Override
    public boolean isFinished() {
        //when should it finish?
        //Change
        return !(m_humanControl.isDown(m_humanControl.getDesiredButton(kControllerID_XBOX, kButtonID_XboxRB))); 
    }
}

