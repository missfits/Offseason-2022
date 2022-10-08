// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import frc.robot.subsystems.Shooter;
import frc.robot.subsystems.Intake;
import frc.robot.subsystems.Climber;
import frc.robot.subsystems.Conveyor;
import frc.robot.subsystems.Drivetrain;
import frc.robot.subsystems.Hood;
import frc.robot.subsystems.Indexer;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.CommandScheduler;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.commands.*;

import static frc.robot.Constants.*;

/**
 * This class is where the bulk of the robot should be declared. Since Command-based is a
 * "declarative" paradigm, very little robot logic should actually be handled in the {@link Robot}
 * periodic methods (other than the scheduler calls). Instead, the structure of the robot (including
 * subsystems, commands, and button mappings) should be declared here.
 */
public class RobotContainer {
  public static Command m_DriveStraightCommand;
  final SendableChooser<Command> m_chooser = new SendableChooser<>();


  // The robot's subsystems and commands are defined here...
  private final SensorBoard m_sensorControl;
  

  private final OI m_humanControl;

  //Creating subsystem objects
  private final Shooter m_shooter;
  private final Intake m_intake;
  private final Drivetrain m_drivetrain;
  private final Indexer m_indexer;
  private final Conveyor m_conveyor;
  private final Hood m_hood;
  public final Climber m_climber;

  //Creating instances of commands
  private final ShootCommand m_shootCommand;
  private final IntakeCommand m_intakeCommand;
  private final IntakeReverseCommand m_intakeReverseCommand;
  private final IntakeForwardCommand m_intakeForwardCommand;
  private final DefaultDriveCommand m_defaultDriveCommand;
  private final DefaultIndexCommand m_defaultIndexCommand;
  private final ConveyorCommand m_conveyorCommand;
  private final HoodForwardCommand m_hoodForwardCommand;
  private final HoodBackwardCommand m_hoodBackwardCommand;
  private final ShooterBackwards m_shooterBackwards;
  private final ConveyorBackwards m_conveyorBackwards;
  private final ClimberUpCommand m_climberUpCommand;
  private final ClimberDownCommand m_climberDownCommand;



  /** The container for the robot. Contains subsystems, OI devices, and commands. */
  public RobotContainer() {

    // Configure the button bindings
    configureButtonBindings();
    m_sensorControl = new SensorBoard();


    m_humanControl = new OI();

    m_shooter = new Shooter(m_sensorControl);
    m_intake = new Intake(m_sensorControl);
    m_drivetrain = new Drivetrain(m_sensorControl);
    m_indexer = new Indexer(m_sensorControl);
    m_conveyor = new Conveyor(m_sensorControl);
    m_hood = new Hood(m_sensorControl);
    m_climber = new Climber(m_sensorControl);

    m_defaultDriveCommand = new DefaultDriveCommand(m_sensorControl, m_humanControl, m_drivetrain);
    m_defaultIndexCommand = new DefaultIndexCommand(m_sensorControl, m_humanControl, m_indexer);

    m_drivetrain.setDefaultCommand(m_defaultDriveCommand);
    m_indexer.setDefaultCommand(m_defaultIndexCommand);

    m_shootCommand = new ShootCommand(m_sensorControl, m_humanControl, m_shooter, m_indexer);
    m_intakeCommand = new IntakeCommand(m_sensorControl, m_humanControl, m_intake);
    m_intakeReverseCommand = new IntakeReverseCommand(m_sensorControl, m_humanControl, m_intake);
    m_intakeForwardCommand = new IntakeForwardCommand(m_sensorControl, m_humanControl, m_intake);
    m_conveyorCommand = new ConveyorCommand(m_sensorControl, m_humanControl, m_conveyor);
    m_hoodForwardCommand = new HoodForwardCommand(m_sensorControl, m_humanControl, m_hood, m_indexer);
    m_hoodBackwardCommand = new HoodBackwardCommand(m_sensorControl, m_humanControl, m_hood, m_indexer);
    m_shooterBackwards = new ShooterBackwards(m_sensorControl, m_humanControl, m_shooter, m_indexer);
    m_conveyorBackwards = new ConveyorBackwards(m_sensorControl, m_humanControl, m_conveyor);
    m_climberUpCommand = new ClimberUpCommand(m_humanControl, m_climber);
    m_climberDownCommand = new ClimberDownCommand(m_humanControl, m_climber);
    m_DriveStraightCommand = new DriveStraightCommand(5, m_drivetrain);

    System.out.println("end of robot container constructor");
  }

  /**
   * Use this method to define your button->command mappings. Buttons can be created by
   * instantiating a {@link GenericHID} or one of its subclasses ({@link
   * edu.wpi.first.wpilibj.Joystick} or {@link XboxController}), and then passing it to a {@link
   * edu.wpi.first.wpilibj2.command.button.JoystickButton}.
   */
  private void configureButtonBindings() {
    // m_humanControl.updateButtons();
  }

  /**
   * Use this to pass the autonomous command to the main {@link Robot} class.
   *
   * @return the command to run in autonomous
   */
  // public Command getAutonomousCommand() {
  //   // An ExampleCommand will run in autonomous

  // }

  public void updateButtons() {
    m_humanControl.updateButtons();
  }
  public void updateControls() {

    // System.out.println("updating controls");
    if (m_humanControl.isDown(m_humanControl.getDesiredButton(kControllerID_XBOX, kButtonID_XboxX))) { //kControllerID_XBOX, kButtonID_XboxB
      CommandScheduler.getInstance().schedule(m_shootCommand);
    }

    if(m_humanControl.isDown(m_humanControl.getDesiredButton(kControllerID_XBOX, kButtonID_XboxA)) || m_humanControl.isDown(m_humanControl.getDesiredButton(kControllerID_LeftJoy, kButtonID_Drive2))) {
      CommandScheduler.getInstance().schedule(m_intakeCommand);
      m_sensorControl.setIsIntaking(true);
    }
    else {
      m_sensorControl.setIsIntaking(false);
    }

    if(m_humanControl.isDown(m_humanControl.getDesiredButton(kControllerID_XBOX, kButtonID_XboxStart)) || m_humanControl.isDown(m_humanControl.getDesiredButton(kControllerID_LeftJoy, kButtonID_Drive4))) {
      CommandScheduler.getInstance().schedule(m_conveyorBackwards);
    }

    if(m_humanControl.isDown(m_humanControl.getDesiredButton(kControllerID_XBOX, kButtonID_XboxB)) || m_humanControl.isDown(m_humanControl.getDesiredButton(kControllerID_LeftJoy, kButtonID_Drive4))) {
      CommandScheduler.getInstance().schedule(m_climberUpCommand);
    }

    if(m_humanControl.isDown(m_humanControl.getDesiredButton(kControllerID_XBOX, kButtonID_XboxRB)) || m_humanControl.isDown(m_humanControl.getDesiredButton(kControllerID_LeftJoy, kButtonID_Drive4))) {
      CommandScheduler.getInstance().schedule(m_climberDownCommand);
    }

    if(m_humanControl.isDown(m_humanControl.getDesiredButton(kControllerID_XBOX, kButtonID_XboxY)) || m_humanControl.isDown(m_humanControl.getDesiredButton(kControllerID_LeftJoy, kButtonID_Drive4))) {
      CommandScheduler.getInstance().schedule(m_conveyorCommand);
    }


    if(m_humanControl.isDown(m_humanControl.getDesiredButton(kControllerID_XBOX, kButtonID_XboxBack)) || m_humanControl.isDown(m_humanControl.getDesiredButton(kControllerID_LeftJoy, kButtonID_Drive5))) {
      CommandScheduler.getInstance().schedule(m_hoodForwardCommand);
    }
  }

    //does nothing
    public static SequentialCommandGroup m_justTaxi = new SequentialCommandGroup(
      new  DriveStraightCommand(1.0, m_drivetrain)
    );




    public Command getAutonomousCommand() {
      //Poll Sendable Chooser
      return m_chooser.getSelected();
    }
}
