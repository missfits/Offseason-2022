package frc.robot.commands;

import frc.robot.subsystems.Drivetrain;

import static frc.robot.Constants.Constants.*;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.SensorBoard;
import frc.robot.OI;


public class DefaultDriveCommand extends CommandBase{
    SensorBoard m_sensorControl;
    OI m_humanControl;
    Drivetrain m_drivetrain;



    public DefaultDriveCommand (SensorBoard sensorControl, OI humanControl, Drivetrain drivetrain) {
        m_sensorControl = sensorControl;
        m_humanControl = humanControl;
        m_drivetrain = drivetrain;

        addRequirements(drivetrain);
    }

    @Override
    public void initialize() {

    }

    @Override
    public void execute() {
        m_drivetrain.tankDrive(m_humanControl.getLeftJoyY(), m_humanControl.getRightJoyY());
    }
}