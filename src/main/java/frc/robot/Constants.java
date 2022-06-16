// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

/**
 * The Constants class provides a convenient place for teams to hold robot-wide numerical or boolean
 * constants. This class should not be used for any other purpose. All constants should be declared
 * globally (i.e. public static). Do not put anything functional in this class.
 *
 * <p>It is advised to statically import this class (or one of its inner classes) wherever the
 * constants are needed, to reduce verbosity.
 */
public final class Constants {
    public static int kCANID_MotorLeft1 = 1;
    public static int kCANID_MotorLeft2 = 2;
    public static int kCANID_MotorRight1 = 3;
    public static int kCANID_MotorRight2 = 4;
    public static int INTAKE_MOTOR_PORT = 5;
    public static int kCANID_MotorConveyor = 6;
    public static int SHOOTER_MOTOR_PORT = 7;
    public static int kCANID_MotorClimber1 = 8;
    public static int INDEX_MOTOR_PORT = 0; //change
    //public static int kCANID_MotorClimber2 = 9; 

    public static int kMXP = 13;


    // IDs for the XBOX port, joystick IDs, joystick IDs on the XBOX
    public static int XBOX_CONTROLLER_PORT = 0;
    public static int LEFT_JOY_PORT = 1;
    public static int RIGHT_JOY_PORT = 2;
    public static int kControllerID_DriveLeftXBOX = 1;
    public static int kControllerID_DriveRightXBOX = 2;

    //XBOX button IDs
    public static int INTAKE_BUTTON_ID = 1;
    public static int SHOOT_BUTTON_ID = 2;
    public static int PREP_BUTTON_ID = 3;
    public static int kButtonID_XboxY = 4;
    public static int kButtonID_XboxLB = 5;
    public static int kButtonID_XboxRB = 6;
    public static int kButtonID_XboxBack = 7;
    public static int INTAKE_REVERSE_BUTTON_ID = 8;

    // Driving Joystick Button IDs
    public static int kButtonID_Drive1 = 1; // trigger
    public static int kButtonID_DriveRight1 = 1;
    public static int INTAKE_BUTTON_DRIVER_ID = 2;
    public static int kButtonID_Drive3 = 3;
    public static int INTAKE_REVERSE_BUTTON_DRIVER_ID = 4;
    public static int kButtonID_Drive5 = 5;
    public static int kButtonID_Drive6 = 6;
    public static int kButtonID_Drive7 = 7;
    public static int kButtonID_Drive8 = 8;
    public static int kButtonID_Drive9 = 9;
    public static int kButtonID_Drive10 = 10;
    public static int kButtonID_Drive11 = 11;
    public static int kButtonID_Drive12 = 12;

    //pneumatics
    public static int INTAKE_SOLENOID_FORWARD_PORT = 7;
    public static int INTAKE_SOLENOID_REVERSE_PORT = 6;

    //motor powers
    public static double INTAKE_ROLLERS_FORWARD_POWER = 1.0;
    public static double INTAKE_ROLLERS_REVERSE_POWER = -1.0;
    public static double INTAKE_ROLLERS_RESET_POWER = 0.0;

    public static double FLYWHEEL_RESET_POWER = 0.0;

    public static double INDEX_ROLLERS_FORWARD_POWER = 0.4;
    public static double INDEX_ROLLERS_REVERSE_POWER = -0.4;
    public static double INDEX_RESET_POWER = 0.0;

    //sensors
    public static int TOP_LIGHT_SENSOR_PORT = 0;
    public static int BOTTOM_LIGHT_SENSOR_PORT = 1;

    //ifdef
    public static boolean EOS = true;
}
