package frc.robot.commands;

import static frc.robot.Constants.*;
import frc.robot.subsystems.Drivetrain;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.SensorBoard;
import frc.robot.OI;
import frc.robot.subsystems.Vision;


public class LimelightDriveCommand extends CommandBase{
    SensorBoard m_sensorControl;
    OI m_humanControl;
    Drivetrain m_drivetrain;
    Vision m_vision;



    public LimelightDriveCommand (SensorBoard sensorControl, OI humanControl, Drivetrain drivetrain, Vision vision) {
        m_sensorControl = sensorControl;
        m_humanControl = humanControl;
        m_drivetrain = drivetrain;
        m_vision = vision;

        addRequirements(drivetrain);
    }

    @Override
    public void initialize() {
    }

    @Override
    public void execute() {
        //read values periodically
        double forwardSpeed;
        var results = m_vision.m_limelight.getLatestResult();
        if (results.hasTargets()) {
            forwardSpeed = -m_drivetrain.m_driveTrainPID.calculate(m_vision.DISTANCE_FROM_TARGET, m_vision.GOAL_RANGE_METERS);
        } else {
            forwardSpeed = 0;
        }
        m_drivetrain.tankDrive(forwardSpeed, forwardSpeed);
    }

    @Override
    public void end(boolean interrupted) {
    }

    @Override
    public boolean isFinished() {
        return !m_vision.m_limelight.getLatestResult().hasTargets() || (m_vision.DISTANCE_FROM_TARGET == m_vision.GOAL_RANGE_METERS);
    }
}