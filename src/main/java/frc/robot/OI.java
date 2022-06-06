package frc.robot;
import static frc.robot.Constants.*;

import edu.wpi.first.wpilibj.Joystick;

import edu.wpi.first.wpilibj.XboxController;

//import frc.robot.subsystems.*;
//import static edu.wpi.first.wpilibj.DoubleSolenoid.Value.*;


public class OI {
    // Create the joysticks and XBOX controller
    Joystick XBOX1 = new Joystick(XBOX_CONTROLLER_PORT);
    public Joystick leftJoy = new Joystick(LEFT_JOY_PORT);
    public Joystick rightJoy = new Joystick(RIGHT_JOY_PORT);
    //creates solenoids
    //DoubleSolenoid exampleDoublePCM = new DoubleSolenoid(PneumaticsModuleType.CTREPCM, 1, 2);
    //DoubleSolenoid exampleDoublePH = new DoubleSolenoid(9, PneumaticsModuleType.REVPH, 4, 5);


    //creating buttons for the joysticks
    ButtonReader  triggerLeft = new ButtonReader(leftJoy, kButtonID_Drive1);
    ButtonReader  button2Left = new ButtonReader(leftJoy, INTAKE_BUTTON_DRIVER_ID);
    ButtonReader  button3Left = new ButtonReader(leftJoy, kButtonID_Drive3);
    ButtonReader  button4Left = new ButtonReader(leftJoy, INTAKE_REVERSE_BUTTON_DRIVER_ID);
    ButtonReader  button5Left = new ButtonReader(leftJoy, kButtonID_Drive5);
    ButtonReader  button6Left = new ButtonReader(leftJoy, kButtonID_Drive6);
    ButtonReader  button7Left = new ButtonReader(leftJoy, kButtonID_Drive7);
    ButtonReader  button8Left = new ButtonReader(leftJoy, kButtonID_Drive8);
    ButtonReader  button9Left = new ButtonReader(leftJoy, kButtonID_Drive9);
    ButtonReader  button10Left = new ButtonReader(leftJoy, kButtonID_Drive10);
    ButtonReader  button11Left = new ButtonReader(leftJoy, kButtonID_Drive11);
    ButtonReader  button12Left = new ButtonReader(leftJoy, kButtonID_Drive12);
    ButtonReader  triggerRight = new ButtonReader(rightJoy, kButtonID_DriveRight1);

    //creating buttons for the XBOX
    ButtonReader  intakeButton = new ButtonReader(XBOX1, INTAKE_BUTTON_ID);
    ButtonReader  shootButton = new ButtonReader(XBOX1, SHOOT_BUTTON_ID);
    ButtonReader  Xbutton = new ButtonReader(XBOX1, kButtonID_XboxX);
    ButtonReader  Ybutton = new ButtonReader(XBOX1, kButtonID_XboxY);
    ButtonReader  LBbutton = new ButtonReader(XBOX1, kButtonID_XboxLB);
    ButtonReader  Backbutton = new ButtonReader(XBOX1, kButtonID_XboxBack);
    ButtonReader  outtakeButton = new ButtonReader(XBOX1, INTAKE_REVERSE_BUTTON_ID);
    ButtonReader  RBbutton = new ButtonReader(XBOX1, kButtonID_XboxRB);

    //xbox joysticks
    public XboxController xboxcontroller = new XboxController(0);

    // SensorBoard m_sensorControl;

    // ShootCommand m_shootCommand;
    // Shooter m_shooter;



    public OI(){

        
      
    }

    public boolean wasJustPressed(ButtonReader button) {
        return button.wasJustPressed();
    }

    public boolean wasJustReleased(ButtonReader button) {
        return button.wasJustReleased();
    }

    public boolean stateJustChanged(ButtonReader button) {
        return button.stateJustChanged();
    }

    public boolean isDown(ButtonReader button){
        return button.isDown();
    }

    public ButtonReader getDesiredButton(int joystickID, int buttonID) {
        if (joystickID == LEFT_JOY_PORT || joystickID == RIGHT_JOY_PORT) {

            if (buttonID == kButtonID_Drive1) {
                return triggerLeft;
            }
            else if (buttonID == INTAKE_BUTTON_DRIVER_ID) {
                return button2Left;
            }
            else if (buttonID == kButtonID_Drive3) {
                return button3Left;
            }
            else if (buttonID == INTAKE_REVERSE_BUTTON_DRIVER_ID) {
                return button4Left;
            }
            else if (buttonID == kButtonID_Drive5) {
                return button5Left;
            }
            else if (buttonID == kButtonID_Drive6) {
                return button6Left;
            }
            else if (buttonID == kButtonID_Drive7) {
                return button7Left;
            }
            else if (buttonID == kButtonID_Drive8) {
                return button8Left;
            }
            else if (buttonID == kButtonID_Drive9) {
                return button9Left;
            }
            else if (buttonID == kButtonID_Drive10) {
                return button10Left;
            }
            else if (buttonID == kButtonID_Drive11) {
                return button11Left;
            }
            else if (buttonID == kButtonID_Drive12) {
                return button12Left;
            }
            else if (buttonID == kButtonID_DriveRight1) {
                return triggerRight;
            }
        }

        //xbox
        else {
            if (buttonID == INTAKE_BUTTON_ID) {
                return intakeButton;
            }
            else if (buttonID == SHOOT_BUTTON_ID) {
                return shootButton;
            }
            else if (buttonID == kButtonID_XboxX) {
                return Xbutton;
            }
            else if (buttonID == kButtonID_XboxY) {
                return Ybutton;
            }
            else if (buttonID == kButtonID_XboxLB) {
                return LBbutton;
            }
            else if (buttonID == kButtonID_XboxBack) {
                return Backbutton;
            }
            else if (buttonID == INTAKE_REVERSE_BUTTON_ID) {
                return outtakeButton;
            }
            else if (buttonID == kButtonID_XboxRB) {
                return RBbutton;
            }
        }
        
        return null;

    }


}
 