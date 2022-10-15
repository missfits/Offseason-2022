// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Drivetrain;
import edu.wpi.first.wpilibj.Timer;

/** DriveStraightCommand usues DriveTrain subsystem */
public class DriveStraightCommand extends CommandBase {
  @SuppressWarnings({"PMD.UnusedPrivateField", "PMD.SingularField"})
  private static Drivetrain m_drivetrain; //although it is unused, the class cannot function without it
  private final Timer timer = new Timer();
  private final double duration;

  /**
   * Creates a new Driving Command
   *
   * @param subsystem The subsystem used by this command.
   */
  public DriveStraightCommand(Drivetrain subsystem) {
    // call the timed constructor with invalid 'time'
    this(-1, subsystem);
  }

  /**
   * Creates a new duration Driving Command
   *
   * @param duration The time this command runs (autonomous mode) in seconds.
   * @param subsystem The subsystem used by this command.
   */
  public DriveStraightCommand(double duration, Drivetrain subsystem){
    // Use addRequirements() here to declare subsystem dependencies.
    m_drivetrain = subsystem;
    addRequirements(subsystem); // What does this do?
    this.duration = duration;
  }

  protected boolean isTimed() {
    return this.duration > 0.0;
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    timer.reset();
    timer.start();
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    System.out.println("drive forward time:"+ timer.get());
    double timeSoFar = timer.get();
    //Creates ramp for motors
    double multiplier = (isTimed() && timeSoFar < 0.5)? 2 * timeSoFar : 1.0;
    Drivetrain.m_robotDrive.tankDrive(multiplier * 0.5, multiplier * 0.5);
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
      Drivetrain.m_robotDrive.stopMotor();
  }

  // Returns true when the command should end. (when indicated time is reached)
  @Override
  public boolean isFinished() {
    if (isTimed() && timer.get() > this.duration){
      return true;
    }
    return false;
  }
}