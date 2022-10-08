// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import frc.robot.subsystems.Climber;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants;
import frc.robot.OI;
import frc.robot.Robot;

/** Climber down command uses Climber subsystem */
public class ClimberDownCommand extends CommandBase {
  @SuppressWarnings({"PMD.UnusedPrivateField", "PMD.SingularField"})
  private final Climber m_climber;
  private final OI m_humanControl;

  /**
   * Creates a new ClimberDownCommand.
   *
   * @param subsystem The subsystem used by this command.
   */
  public ClimberDownCommand(OI humanControl, Climber subsystem) {
    m_climber = subsystem;
    m_humanControl = humanControl;
    // Use addRequirements() here to declare subsystem dependencies.
    addRequirements(m_climber);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
  }

  // Called every time the scheduler runs while the command is scheduled.
  //theoretically keeps the climber retracted
  @Override
  public void execute() {
      m_climber.climberDown();
  }

  // Called once the command ends or is interrupted.
  //stops the climber motor
  @Override
  public void end(boolean interrupted) {
      m_climber.climberOff();
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return !(m_humanControl.isDown(m_humanControl.getDesiredButton(Constants.kControllerID_XBOX, Constants.kButtonID_XboxRB))); //kControllerID_XBOX, kButtonID_XboxB
  }
}
