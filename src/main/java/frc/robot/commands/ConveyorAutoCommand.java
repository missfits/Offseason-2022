package frc.robot.commands;

import static frc.robot.Constants.*;

import frc.robot.subsystems.Conveyor;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.SensorBoard;
import frc.robot.OI;

import edu.wpi.first.wpilibj.Timer;



public class ConveyorAutoCommand extends CommandBase{
    private final Timer timer = new Timer();
  private final double duration;
    Conveyor m_conveyor;

    public ConveyorAutoCommand(double duration, Conveyor conveyor) {

        m_conveyor = conveyor;
        this.duration = duration;
        
    }

    protected boolean isTimed() {
        return this.duration > 0.0;
      }

    @Override
    public void initialize() {
        timer.reset();
    timer.start();
    }

    @Override
    public void execute() {
       m_conveyor.setConveyorPower(-1);
       double timeSoFar = timer.get();
    //Creates ramp for motors
    double multiplier = (isTimed() && timeSoFar < 0.5)? 2 * timeSoFar : 1.0;
  
    }

    @Override
    public void end(boolean interrupted) {
        m_conveyor.setConveyorPower(0.0);
    }

    @Override
    public boolean isFinished() {
        if (isTimed() && timer.get() > this.duration){
            return true;
          }
          return false;
        }
}

