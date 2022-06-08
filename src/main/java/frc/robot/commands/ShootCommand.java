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

        System.out.println("end of shoot command constructor");
    }

    @Override
    public void initialize() {
        m_desiredVelocity = m_shooter.calculateStaticFlywheelVelocity();
    }

    @Override
    public void execute() {
        m_desiredVelocity = m_shooter.calculateStaticFlywheelVelocity(); //desired
        System.out.println(m_desiredVelocity);
        m_shooter.setFlywheelSpeedRPM(m_desiredVelocity); 
        if (m_shooter.isFlywheelAtSpeed(m_desiredVelocity)) {
            if (m_humanControl.isDown(m_humanControl.getDesiredButton(XBOX_CONTROLLER_PORT, SHOOT_BUTTON_ID))) {
                //index balls
                System.out.println("shooting");
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
        m_shooter.setFlywheelSetpoint(FLYWHEEL_RESET_POWER);
        m_shooter.setFlywheelPower(FLYWHEEL_RESET_POWER);
        //index 0
    }

    @Override
    public boolean isFinished() {
        return !(m_humanControl.isDown(m_humanControl.getDesiredButton(XBOX_CONTROLLER_PORT, SHOOT_BUTTON_ID))); //XBOX_CONTROLLER_PORT, SHOOT_BUTTON_ID
    }
}
