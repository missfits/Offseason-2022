// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import static frc.robot.Constants.Constants.*;

import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import frc.robot.subsystems.Shooter;
import frc.robot.subsystems.Intake;
import frc.robot.subsystems.Conveyor;
import frc.robot.subsystems.Drivetrain;
import frc.robot.subsystems.Hood;
import frc.robot.subsystems.Indexer;
import frc.robot.subsystems.Vision;
import frc.robot.subsystems.VisionLookup;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.CommandScheduler;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.commands.*;

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

  public final Shooter m_shooter;
  private final Intake m_intake;
  private Drivetrain m_drivetrain;
  private final Indexer m_indexer;
  public final Vision m_vision;
  public VisionLookup m_visionLookup;
  private Conveyor m_conveyor;
  public Hood m_hood;

  //Creating instances of commands
  private final ShootCommand m_shootHubCommand;
  private final ShootCommand m_shootTapeCommand;
  private final ShootCommand m_shootSafeZoneCommand;
  private final IntakeCommand m_intakeCommand;
  private final IntakeReverseCommand m_intakeReverseCommand;
  private final IntakeForwardCommand m_intakeForwardCommand;
  private final DefaultDriveCommand m_defaultDriveCommand;
  private final DefaultIndexCommand m_defaultIndexCommand;
  private final LimelightDriveCommand m_limelightDriveCommand;
  private final TurnToTarget m_turnToTarget;
  private final AimDriveToTarget m_aimDriveToTarget;
  private final ShootUsingLimelightCommand m_shootUsingLimelightCommand;
  private final ConveyorCommand m_conveyorCommand;
  private final HoodForwardCommand m_hoodForwardCommand;
  private final HoodBackwardCommand m_hoodBackwardCommand;
  private final HoodPositionCommand m_hoodPositionCommand;



  /** The container for the robot. Contains subsystems, OI devices, and commands. */
  public RobotContainer() {

    // Configure the button bindings
    configureButtonBindings();
    //Create instances of subsystems
    m_sensorControl = new SensorBoard();
    m_humanControl = new OI();
    m_intake = new Intake(m_sensorControl);
    m_drivetrain = new Drivetrain(m_sensorControl);
    m_indexer = new Indexer(m_sensorControl);
    m_vision = new Vision(m_sensorControl, m_visionLookup, m_drivetrain, m_humanControl);
    m_shooter = new Shooter(m_sensorControl, m_vision, m_conveyor, m_visionLookup, m_hood);
    m_hood = new Hood(m_sensorControl, m_visionLookup, m_vision);
    m_visionLookup = new VisionLookup();
    m_conveyor = new Conveyor(m_sensorControl);

    //Commands
    m_defaultDriveCommand = new DefaultDriveCommand(m_sensorControl, m_humanControl, m_drivetrain);
    m_defaultIndexCommand = new DefaultIndexCommand(m_sensorControl, m_humanControl, m_indexer);

    m_drivetrain.setDefaultCommand(m_defaultDriveCommand);
    m_indexer.setDefaultCommand(m_defaultIndexCommand);

    m_shootHubCommand = new ShootCommand(m_sensorControl, m_shooter, m_indexer, m_conveyor, m_visionLookup, m_hood, 1.2446, m_humanControl.getDesiredButton(kControllerID_XBOX, kButtonID_XboxX));
    m_shootTapeCommand = new ShootCommand(m_sensorControl, m_shooter, m_indexer, m_conveyor, m_visionLookup, m_hood, 4.65, m_humanControl.getDesiredButton(kControllerID_XBOX, kButtonID_XboxRB));
    m_shootSafeZoneCommand = new ShootCommand(m_sensorControl, m_shooter, m_indexer, m_conveyor, m_visionLookup, m_hood, 6, m_humanControl.getDesiredButton(kControllerID_XBOX, kButtonID_XboxB));
    m_intakeCommand = new IntakeCommand(m_sensorControl, m_humanControl, m_intake);
    m_intakeReverseCommand = new IntakeReverseCommand(m_sensorControl, m_humanControl, m_intake);
    m_limelightDriveCommand = new LimelightDriveCommand(m_sensorControl, m_humanControl, m_drivetrain, m_vision);
    m_turnToTarget = new TurnToTarget(m_sensorControl, m_humanControl, m_drivetrain, m_vision);
    m_aimDriveToTarget = new AimDriveToTarget(m_sensorControl, m_humanControl, m_drivetrain, m_vision);
    m_shootUsingLimelightCommand = new ShootUsingLimelightCommand(m_sensorControl, m_humanControl, m_vision, m_shooter);
    m_intakeForwardCommand = new IntakeForwardCommand(m_sensorControl, m_humanControl, m_intake);
    m_conveyorCommand = new ConveyorCommand(m_sensorControl, m_humanControl, m_conveyor);
    m_hoodForwardCommand = new HoodForwardCommand(m_sensorControl, m_humanControl, m_hood, m_indexer);
    m_hoodBackwardCommand = new HoodBackwardCommand(m_sensorControl, m_humanControl, m_hood, m_indexer);
    m_hoodPositionCommand = new HoodPositionCommand(m_sensorControl, m_humanControl, m_hood, m_indexer);

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
      CommandScheduler.getInstance().schedule(m_shootHubCommand);
    }

    if (m_humanControl.isDown(m_humanControl.getDesiredButton(kControllerID_XBOX, kButtonID_XboxRB))) { //kControllerID_XBOX, kButtonID_XboxB
      CommandScheduler.getInstance().schedule(m_shootTapeCommand);
    }

    if (m_humanControl.isDown(m_humanControl.getDesiredButton(kControllerID_XBOX, kButtonID_XboxB))) { //kControllerID_XBOX, kButtonID_XboxB
      CommandScheduler.getInstance().schedule(m_shootSafeZoneCommand);
    }

    if(m_humanControl.isDown(m_humanControl.getDesiredButton(kControllerID_XBOX, kButtonID_XboxA)) || m_humanControl.isDown(m_humanControl.getDesiredButton(kControllerID_LeftJoy, kButtonID_Drive2))) {
      CommandScheduler.getInstance().schedule(m_intakeCommand);
      m_sensorControl.setIsIntaking(true);
    }
    else {
      m_sensorControl.setIsIntaking(false);
    }

    if(m_humanControl.isDown(m_humanControl.getDesiredButton(kControllerID_XBOX, kButtonID_XboxStart)) || m_humanControl.isDown(m_humanControl.getDesiredButton(kControllerID_LeftJoy, kButtonID_Drive4))) {
      CommandScheduler.getInstance().schedule(m_intakeReverseCommand);
    }

    // if (m_humanControl.isDown(m_humanControl.getDesiredButton(kControllerID_XBOX, kButtonID_XboxBack))){
    //   CommandScheduler.getInstance().schedule(m_limelightDriveCommand);
    // }

    // if (m_humanControl.isDown(m_humanControl.getDesiredButton(kControllerID_XBOX, kButtonID_XboxRB))){
    //   CommandScheduler.getInstance().schedule(m_shootUsingLimelightCommand);
    // }

    // if (m_humanControl.isDown(m_humanControl.getDesiredButton(kControllerID_XBOX, kButtonID_XboxRB))){
    //     CommandScheduler.getInstance().schedule(m_shootCommand);
    // }

    if (m_humanControl.isDown(m_humanControl.getDesiredButton(kControllerID_XBOX, kButtonID_XboxBack))){
      CommandScheduler.getInstance().schedule(m_hoodPositionCommand);
    }

    if (m_humanControl.isDown(m_humanControl.getDesiredButton(kControllerID_XBOX, kButtonID_XboxLB))){
      CommandScheduler.getInstance().schedule(m_conveyorCommand);
    }

    // if (m_humanControl.isDown(m_humanControl.getDesiredButton(kControllerID_XBOX, kButtonID_XboxLB))){
    //   CommandScheduler.getInstance().schedule(m_aimDriveToTarget);
    // }

    if(m_humanControl.isDown(m_humanControl.getDesiredButton(kControllerID_XBOX, kButtonID_XboxY)) || m_humanControl.isDown(m_humanControl.getDesiredButton(kControllerID_LeftJoy, kButtonID_Drive4))) {
      CommandScheduler.getInstance().schedule(m_intakeForwardCommand);
    }

    // if(m_humanControl.isDown(m_humanControl.getDesiredButton(kControllerID_XBOX, kButtonID_XboxLB)) || m_humanControl.isDown(m_humanControl.getDesiredButton(kControllerID_LeftJoy, kButtonID_Drive5))) {
    //   CommandScheduler.getInstance().schedule(m_conveyorCommand);
    // }

    // if(m_humanControl.isDown(m_humanControl.getDesiredButton(kControllerID_XBOX, kButtonID_XboxRB)) || m_humanControl.isDown(m_humanControl.getDesiredButton(kControllerID_LeftJoy, kButtonID_Drive5))) {
    //   CommandScheduler.getInstance().schedule(m_hoodForwardCommand);
    // }

    // if(m_humanControl.isDown(m_humanControl.getDesiredButton(kControllerID_XBOX, kButtonID_XboxBack)) || m_humanControl.isDown(m_humanControl.getDesiredButton(kControllerID_LeftJoy, kButtonID_Drive5))) {
    //   CommandScheduler.getInstance().schedule(m_hoodBackwardCommand);
    // }
  }

    //does nothing
    public SequentialCommandGroup m_justTaxi = new SequentialCommandGroup(
      new  DriveStraightCommand(1.0, m_drivetrain)
    );


    public Command getAutonomousCommand() {
      //Poll Sendable Chooser
      return m_chooser.getSelected();
    }
}
