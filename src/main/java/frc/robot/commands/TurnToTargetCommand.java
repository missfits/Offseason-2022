package frc.robot.commands;

import frc.robot.subsystems.Drivetrain;

import static frc.robot.Constants.*;

import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.SensorBoard;
import frc.robot.OI;
import frc.robot.subsystems.Vision;


public class TurnToTargetCommand extends CommandBase{
    SensorBoard m_sensorControl;
    OI m_humanControl;
    Drivetrain m_drivetrain;
    Vision m_vision;
    double forwardSpeed;
    double rotationSpeed;
    PIDController turnController;
    final double ANGULAR_P;
    final double ANGULAR_I;
    final double ANGULAR_D;

    // Robot turns towards target

    public TurnToTargetCommand (SensorBoard sensorControl, OI humanControl, Drivetrain drivetrain, Vision vision) {
        m_sensorControl = sensorControl;
        m_humanControl = humanControl;
        m_drivetrain = drivetrain;
        m_vision = vision;
        ANGULAR_P = 0.075;
        ANGULAR_I = 0;
        ANGULAR_D = 0.0;
        turnController = new PIDController(ANGULAR_P, ANGULAR_I, ANGULAR_D);

        addRequirements(drivetrain);
    }

    @Override
    public void initialize() {
    }

    @Override
    public void execute() {
        forwardSpeed = -m_humanControl.XBOX1.getY();
        var results = m_vision.m_limelight.getLatestResult();
        if (results.hasTargets()) {
            //Calculates angle needed to turn towards target using PID
            rotationSpeed = turnController.calculate(results.getBestTarget().getYaw(), 0);
        } else {
            rotationSpeed = 0;
        }
        m_drivetrain.arcadeDrive(forwardSpeed, rotationSpeed);
        //m_sensorControl.setTargetYaw(results.getBestTarget().getYaw());
    }

    @Override
    public void end(boolean interrupted) {
    }

    @Override
    public boolean isFinished() {
        return !m_vision.m_limelight.getLatestResult().hasTargets() || (rotationSpeed == 0);
    }
}