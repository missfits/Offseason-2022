// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.XboxController;
import frc.robot.subsystems.Shooter;
import frc.robot.subsystems.Intake;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.CommandScheduler;

import frc.robot.commands.*;

import static frc.robot.Constants.*;

/**
 * This class is where the bulk of the robot should be declared. Since Command-based is a
 * "declarative" paradigm, very little robot logic should actually be handled in the {@link Robot}
 * periodic methods (other than the scheduler calls). Instead, the structure of the robot (including
 * subsystems, commands, and button mappings) should be declared here.
 */
public class RobotContainer {
  // The robot's subsystems and commands are defined here...
  private final SensorBoard m_sensorControl;
  

  private final OI m_humanControl;

  private final Shooter m_shooter;
  private final Intake m_intake;

  private final ShootCommand m_shootCommand;
  private final IntakeCommand m_intakeCommand;
  private final IntakeReverseCommand m_intakeReverseCommand;



  /** The container for the robot. Contains subsystems, OI devices, and commands. */
  public RobotContainer() {
    // Configure the button bindings
    configureButtonBindings();
    m_sensorControl = new SensorBoard();


    m_humanControl = new OI();

    m_shooter = new Shooter(m_sensorControl);
    m_intake = new Intake(m_sensorControl);

    m_shootCommand = new ShootCommand(m_sensorControl, m_humanControl, m_shooter);
    m_intakeCommand = new IntakeCommand(m_sensorControl, m_humanControl, m_intake);
    m_intakeReverseCommand = new IntakeReverseCommand(m_sensorControl, m_humanControl, m_intake);
    
  }

  /**
   * Use this method to define your button->command mappings. Buttons can be created by
   * instantiating a {@link GenericHID} or one of its subclasses ({@link
   * edu.wpi.first.wpilibj.Joystick} or {@link XboxController}), and then passing it to a {@link
   * edu.wpi.first.wpilibj2.command.button.JoystickButton}.
   */
  private void configureButtonBindings() {}

  /**
   * Use this to pass the autonomous command to the main {@link Robot} class.
   *
   * @return the command to run in autonomous
   */
  // public Command getAutonomousCommand() {
  //   // An ExampleCommand will run in autonomous

  // }
  public void updateControls() {
    if (m_humanControl.isDown(m_humanControl.getDesiredButton(XBOX_CONTROLLER_PORT, SHOOT_BUTTON_ID))) { //XBOX_CONTROLLER_PORT, SHOOT_BUTTON_ID
      CommandScheduler.getInstance().schedule(m_shootCommand);
    }

    if(m_humanControl.isDown(m_humanControl.getDesiredButton(XBOX_CONTROLLER_PORT, INTAKE_BUTTON_ID)) || m_humanControl.isDown(m_humanControl.getDesiredButton(LEFT_JOY_PORT, INTAKE_BUTTON_DRIVER_ID))) {
      CommandScheduler.getInstance().schedule(m_intakeCommand);
    }

    if(m_humanControl.isDown(m_humanControl.getDesiredButton(XBOX_CONTROLLER_PORT, INTAKE_REVERSE_BUTTON_ID)) || m_humanControl.isDown(m_humanControl.getDesiredButton(LEFT_JOY_PORT, INTAKE_REVERSE_BUTTON_DRIVER_ID))) {
      CommandScheduler.getInstance().schedule(m_intakeReverseCommand);
    }
  }
}
