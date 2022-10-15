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
    private final Timer timer = new Timer();
  private final double duration;

    private double m_desiredVelocity;

    public ShootAutoCommand(double duration, Shooter shooter, Indexer indexer) {
        m_shooter = shooter;
        m_indexer = indexer;

        this.duration = duration;

        m_desiredVelocity = 0.0;

        System.out.println("end of shoot command constructor");
    }

    protected boolean isTimed() {
        return this.duration > 0.0;
      }

    @Override
    public void initialize() {
        timer.reset();
    timer.start();
        //m_desiredVelocity = m_shooter.calculateStaticFlywheelVelocity();
    }

    @Override
    public void execute() {
        double timeSoFar = timer.get();
        double multiplier = (isTimed() && timeSoFar < 0.5)? 2 * timeSoFar : 1.0;
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
        if (isTimed() && timer.get() > this.duration){
            return true;
          }
          return false;
        }
}
