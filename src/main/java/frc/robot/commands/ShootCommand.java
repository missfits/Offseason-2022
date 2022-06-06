package frc.robot.commands;

import static frc.robot.Constants.*;
import frc.robot.subsystems.Shooter;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Robot;
import frc.robot.SensorBoard;
import frc.robot.OI;


public class ShootCommand extends CommandBase{
    
    Shooter m_shooter;
    SensorBoard m_sensorControl;
    OI m_humanControl;

    private double m_desiredVelocity;

    public ShootCommand(SensorBoard sensorControl, OI humanControl, Shooter shooter) {
        m_sensorControl = sensorControl;
        m_humanControl = humanControl;
        m_shooter = shooter;

        m_desiredVelocity = 0.0;
    }

    @Override
    public void initialize() {
        m_desiredVelocity = 0.0;
    }

    @Override
    public void execute() {
        m_desiredVelocity = m_shooter.calculateFlywheelVelocityDesired(); //staticflywheelvel
        m_shooter.setFlywheelSpeedRPM(m_desiredVelocity); 
        
        if (m_shooter.isFlywheelAtSpeed(m_desiredVelocity)) {
            if (m_humanControl.isDown(m_humanControl.getDesiredButton(0, 2))) {
                //index balls
            }
            else {
                //index = 0
            }
        }
        else {
            //index = 0
        }
    }

    @Override
    public void end(boolean interrupted) {
        //index 0
    }

    @Override
    public boolean isFinished() {
        return !(m_humanControl.isDown(m_humanControl.getDesiredButton(XBOX_CONTROLLER_PORT, SHOOT_BUTTON_ID))); //XBOX_CONTROLLER_PORT, SHOOT_BUTTON_ID
    }
}
