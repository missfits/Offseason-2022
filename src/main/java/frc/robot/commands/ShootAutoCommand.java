package frc.robot.commands;

import static frc.robot.Constants.*;
import frc.robot.subsystems.Shooter;
import frc.robot.subsystems.Indexer;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.SensorBoard;
import frc.robot.OI;


public class ShootAutoCommand extends CommandBase{
    
    Shooter m_shooter;
    SensorBoard m_sensorControl;
    OI m_humanControl;
    Indexer m_indexer;
    private Timer m_timer;

    private double m_desiredVelocity;

    public ShootAutoCommand(SensorBoard sensorControl, OI humanControl, Shooter shooter, Indexer indexer) {
        m_sensorControl = sensorControl;
        m_humanControl = humanControl;
        m_shooter = shooter;
        m_indexer = indexer;

        m_desiredVelocity = 0.0;

        System.out.println("end of shoot command constructor");
    }

    @Override
    public void initialize() {
        //m_desiredVelocity = m_shooter.calculateStaticFlywheelVelocity();
    }

    @Override
    public void execute() {

        m_shooter.setShooterPower(0.6);

    }

    @Override
    public void end(boolean interrupted) {
        m_shooter.setFlywheelSetpoint(FLYWHEEL_RESET_POWER);
        m_shooter.setFlywheelPower(FLYWHEEL_RESET_POWER);
        m_indexer.reset();
        //index 0
    }

    @Override
    public boolean isFinished() {
        return !(m_humanControl.isDown(m_humanControl.getDesiredButton(kControllerID_XBOX, kButtonID_XboxX))); //kControllerID_XBOX, kButtonID_XboxB
    }
}
