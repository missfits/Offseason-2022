// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.Constants;

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
    public static int kCANID_MotorIntake = 5;
    public static int kCANID_MotorConveyor = 6;
    public static int kCANID_MotorShooter = 7;
    public static int kCANID_MotorHood = 0; //change
    public static int kCANID_MotorRoller = 0; //change
    public static int kCANID_MotorClimber1 = 8;
    public static int kCANID_MotorIndex = 0; //change
    //public static int kCANID_MotorClimber2 = 0; 

    public static int kMXP = 13;

    // IDs for the XBOX port, joystick IDs, joystick IDs on the XBOX
    public static int kControllerID_XBOX = 0;
    public static int kControllerID_LeftJoy = 1;
    public static int kControllerID_RightJoy = 2;
    public static int kControllerID_DriveLeftXBOX = 1;
    public static int kControllerID_DriveRightXBOX = 2;

    //XBOX button IDs
    public static int kButtonID_XboxA = 1;  // INTAKE
    public static int kButtonID_XboxB = 2;  // SHOOTER
    public static int kButtonID_XboxX = 3;  // PREP
    public static int kButtonID_XboxY = 4; 
    public static int kButtonID_XboxLB = 5;
    public static int kButtonID_XboxRB = 6;
    public static int kButtonID_XboxBack = 7;
    public static int kButtonID_XboxStart = 8; // INTAKE REVERSE

    // Driving Joystick Button IDs
    public static int kButtonID_DriveLeft1 = 1;     // left trigger
    public static int kButtonID_DriveRight1 = 1;    // right trigger
    public static int kButtonID_Drive2 = 2;         // intake
    public static int kButtonID_Drive3 = 3;
    public static int kButtonID_Drive4 = 4;         // intake reverse
    public static int kButtonID_Drive5 = 5;
    public static int kButtonID_Drive6 = 6;
    public static int kButtonID_Drive7 = 7;
    public static int kButtonID_Drive8 = 8;
    public static int kButtonID_Drive9 = 9;
    public static int kButtonID_Drive10 = 10;
    public static int kButtonID_Drive11 = 11;
    public static int kButtonID_Drive12 = 12;

    //intake pneumatics
    public static int kSolenoidID_ForwardChannel = 7;
    public static int kSolenoidID_ReverseChannel = 6;

    //sensors
    public static int kSensorID_TopLight = 0;
    public static int kSensorID_BottomLight = 1;

    //motor powers
    public static double INTAKE_ROLLERS_FORWARD_POWER = 1.0;
    public static double INTAKE_ROLLERS_REVERSE_POWER = -1.0;
    public static double INTAKE_ROLLERS_RESET_POWER = 0.0;

    public static double FLYWHEEL_RESET_POWER = 0.0;

    public static double INDEX_ROLLERS_FORWARD_POWER = 1.0;
    public static double INDEX_ROLLERS_REVERSE_POWER = -1.0;
    public static double INDEX_RESET_POWER = 0.0;

    public static double FLYWHEEL_F_FAC = 0.00019;
    public static double FLYWHEEL_P_FAC = 0.0005;
    public static double FLYWHEEL_I_FAC = 0.0;
    public static double FLYWHEEL_D_FAC = 0.008;

    public static double DRIVETRAIN_P_FAC = 0.01;
    public static double DRIVETRAIN_I_FAC = 0.0;
    public static double DRIVETRAIN_D_FAC = 0.0;

    //ifdef
    public static boolean EOS = true;

    //Shooter Hood Positions
    public static double hoodabsOut = 0; //Example: 0.058
    public static double hoodabsIn = 0; //Example: 0.956
    public static double hoodrevOut = 0; //Example: -38
    public static double hoodrevIn = 0; //Example: 0
    public static double hoodAngleOut = 0; //Example: 43
    public static double hoodAngleIn = 0; //Example: 21
    public static double forwardVelOffset = 0; //Example: 60
    public static double forwardHoodOffsetFar = 0; //Example: 1
    public static double forwardHoodOffsetClose = 0; //Example: 4.5
    public static double change = 0; //Example: 7.5

    public static boolean isShooting;
}
