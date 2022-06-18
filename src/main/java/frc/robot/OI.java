package frc.robot;
import static frc.robot.Constants.*;

import edu.wpi.first.wpilibj.Joystick;

import edu.wpi.first.wpilibj.XboxController;

//import frc.robot.subsystems.*;
//import static edu.wpi.first.wpilibj.DoubleSolenoid.Value.*;


public class OI {
    // Create the joysticks and XBOX controller
    Joystick XBOX1 = new Joystick(kControllerID_XBOX);
    public Joystick leftJoy = new Joystick(kControllerID_LeftJoy);
    public Joystick rightJoy = new Joystick(kControllerID_RightJoy);
    //creates solenoids
    //DoubleSolenoid exampleDoublePCM = new DoubleSolenoid(PneumaticsModuleType.CTREPCM, 1, 2);
    //DoubleSolenoid exampleDoublePH = new DoubleSolenoid(9, PneumaticsModuleType.REVPH, 4, 5);


    //creating buttons for the joysticks
    ButtonReader  triggerLeft = new ButtonReader(leftJoy, kButtonID_DriveLeft1);
    ButtonReader  button2Left = new ButtonReader(leftJoy, kButtonID_Drive2);
    ButtonReader  button3Left = new ButtonReader(leftJoy, kButtonID_Drive3);
    ButtonReader  button4Left = new ButtonReader(leftJoy, kButtonID_Drive4);
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
    ButtonReader  intakeButton = new ButtonReader(XBOX1, kButtonID_XboxA);
    ButtonReader  shootButton = new ButtonReader(XBOX1, kButtonID_XboxB);
    ButtonReader  prepButton = new ButtonReader(XBOX1, kButtonID_XboxX);
    ButtonReader  Ybutton = new ButtonReader(XBOX1, kButtonID_XboxY);
    ButtonReader  LBbutton = new ButtonReader(XBOX1, kButtonID_XboxLB);
    ButtonReader  Backbutton = new ButtonReader(XBOX1, kButtonID_XboxBack);

    // Button Backbutton = new Button(XBOX1, kButtonID_XboxBack);

    ButtonReader  outtakeButton = new ButtonReader(XBOX1, kButtonID_XboxStart);
    ButtonReader  RBbutton = new ButtonReader(XBOX1, kButtonID_XboxRB);



    //xbox joysticks
    // public XboxController xboxcontroller = new XboxController(0);

    // SensorBoard m_sensorControl;

    // ShootCommand m_shootCommand;
    // Shooter m_shooter;



    public OI(){

      
    }

    public void updateButtons() {
        triggerLeft.readValue();
        button2Left.readValue();
        button3Left.readValue();
        button4Left.readValue();
        button5Left.readValue();
        button6Left.readValue();
        button7Left.readValue();
        button8Left.readValue();
        button9Left.readValue();
        button10Left.readValue();
        button11Left.readValue();
        button12Left.readValue();
        triggerRight.readValue();

    //creating buttons for the XBOX
        intakeButton.readValue();
        shootButton.readValue();
        prepButton.readValue();
        Ybutton.readValue();
        LBbutton.readValue();
        Backbutton.readValue();

    // Button Backbutton = new Button(XBOX1, kButtonID_XboxBack);

        outtakeButton.readValue();
        RBbutton.readValue();
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
        // System.out.println("get desired button");
        if (joystickID == kControllerID_LeftJoy) {

            if (buttonID == kButtonID_DriveLeft1) {
                return triggerLeft;
            }
            else if (buttonID == kButtonID_Drive2) {
                return button2Left;
            }
            else if (buttonID == kButtonID_Drive3) {
                return button3Left;
            }
            else if (buttonID == kButtonID_Drive4) {
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
        }

        else if (joystickID == kControllerID_RightJoy) {
            if (buttonID == kButtonID_DriveRight1) {
                return triggerRight;
            }
        }

        //xbox
        else {
            if (buttonID == kButtonID_XboxA) {
                return intakeButton;
            }
            else if (buttonID == kButtonID_XboxB) {
                return shootButton;
            }
            else if (buttonID == kButtonID_XboxX) {
                return prepButton;
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
            else if (buttonID == kButtonID_XboxStart) {
                return outtakeButton;
            }
            else if (buttonID == kButtonID_XboxRB) {
                return RBbutton;
            }
        }
        
        return null;

    }

    public double getLeftJoyY() {
        return leftJoy.getY();
    }

    public double getLeftJoyX() {
        return leftJoy.getX();
    }

    public double getRightJoyY() {
        return rightJoy.getY();
    }

    public double getRightJoyX() {
        return rightJoy.getX();
    }

}
 