// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.CommandScheduler;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.networktables.NetworkTableInstance;
//


/**
 * The VM is configured to automatically run this class, and to call the functions corresponding to
 * each mode, as described in the TimedRobot documentation. If you change the name of this class or
 * the package after creating this project, you must also update the build.gradle file in the
 * project.
 */
public class Robot extends TimedRobot {
  private Command m_autonomousCommand;

  private RobotContainer m_robotContainer;

  /**
   * This function is run when the robot is first started up and should be used for any
   * initialization code.
   */
  @Override
  public void robotInit() {

    m_robotContainer = new RobotContainer();
    NetworkTableInstance inst = NetworkTableInstance.getDefault();

    String tables[] = {
      "/",
      "/CameraPublisher",
      "/CameraPublisher/photonvision-output",
      "/photonvision",
      "/photonvision/photonvision",
    };
    for (String table : tables) {
      NetworkTable tt = inst.getTable(table);
      inst.startClientTeam(6418);

     tt.addSubTableListener((parent, name, t) -> {
         System.out.println("Parent: " + parent + " Name: " + name);
      }, false);
    }
    

    
  }


  @Override
  public void robotPeriodic() {
    // System.out.println("hello");
    //System.out.println(m_robotContainer.m_vision.m_limelight.getLatestResult().hasTargets());
    CommandScheduler.getInstance().run();
    m_robotContainer.updateButtons();
    m_robotContainer.updateControls();

    SmartDashboard.putNumber("LimelightX", m_robotContainer.m_vision.x);
    SmartDashboard.putNumber("LimelightY", m_robotContainer.m_vision.y);
    SmartDashboard.putNumber("LimelightArea", m_robotContainer.m_vision.area);
    //System.out.println(m_robotContainer.m_vision.x);
  }

  /** This function is called once each time the robot enters Disabled mode. */
  @Override
  public void disabledInit() {}

  @Override
  public void disabledPeriodic() {}

  /** This autonomous runs the autonomous command selected by your {@link RobotContainer} class. */
  @Override
  public void autonomousInit() {
    // m_autonomousCommand = m_robotContainer.getAutonomousCommand();

    // // schedule the autonomous command (example)
    // if (m_autonomousCommand != null) {
    //   m_autonomousCommand.schedule();
    // }
  }

  /** This function is called periodically during autonomous. */
  @Override
  public void autonomousPeriodic() {}

  @Override
  public void teleopInit() {

    if (m_autonomousCommand != null) {
      m_autonomousCommand.cancel();
    }
  }

  /** This function is called periodically during operator control. */
  @Override
  public void teleopPeriodic() {
    CommandScheduler.getInstance().run();
    m_robotContainer.updateControls();
  }

  @Override
  public void testInit() {
    // Cancels all running commands at the start of test mode.
    CommandScheduler.getInstance().cancelAll();
  }

  /** This function is called periodically during test mode. */
  @Override
  public void testPeriodic() {}

  /** This function is called once when the robot is first started up. */
  @Override
  public void simulationInit() {}

  /** This function is called periodically whilst in simulation. */
  @Override
  public void simulationPeriodic() {
    CommandScheduler.getInstance().run();
    m_robotContainer.updateControls();
  }
}
