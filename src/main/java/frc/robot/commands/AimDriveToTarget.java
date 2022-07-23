package frc.robot.commands;

import static frc.robot.Constants.*;
import frc.robot.subsystems.Drivetrain;
import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.SensorBoard;
import frc.robot.OI;
import frc.robot.subsystems.Vision;


public class AimDriveToTarget extends CommandBase{
    SensorBoard m_sensorControl;
    OI m_humanControl;
    Drivetrain m_drivetrain;
    Vision m_vision;
    PIDController turnController;
    final double ANGULAR_P;
    final double ANGULAR_D;
    double forwardSpeed;
    double rotationSpeed;



    public AimDriveToTarget (SensorBoard sensorControl, OI humanControl, Drivetrain drivetrain, Vision vision) {
        m_sensorControl = sensorControl;
        m_humanControl = humanControl;
        m_drivetrain = drivetrain;
        m_vision = vision;
        ANGULAR_P = 0.1;
        ANGULAR_D = 0.0;
        turnController = new PIDController(ANGULAR_P, 0, ANGULAR_D);


        addRequirements(drivetrain);
    }

    @Override
    public void initialize() {
    }

    @Override
    public void execute() {
        //read values periodically
        var results = m_vision.m_limelight.getLatestResult();
        rotationSpeed = -turnController.calculate(results.getBestTarget().getYaw(), 0);
        if (results.hasTargets()) {
            forwardSpeed = -m_drivetrain.m_driveTrainPID.calculate(m_vision.DISTANCE_FROM_TARGET, m_vision.GOAL_RANGE_METERS);
        } else {
            forwardSpeed = 0;
            rotationSpeed = 0;
        }
        m_drivetrain.arcadeDrive(forwardSpeed, rotationSpeed);
    }

    @Override
    public void end(boolean interrupted) {
    }

    @Override
    public boolean isFinished() {
        return !m_vision.m_limelight.getLatestResult().hasTargets() || ((m_vision.DISTANCE_FROM_TARGET == m_vision.GOAL_RANGE_METERS) && (rotationSpeed == 0));
    }
}