package frc.robot;

import edu.wpi.first.wpilibj.Joystick;


public class ButtonReader {
    Joystick m_joystick;
    public int m_buttonNum;
    boolean m_currState, m_lastState;

    public ButtonReader(Joystick myJoystick, int myButtonNum) {
        m_joystick = myJoystick;
        m_buttonNum = myButtonNum;
        m_currState = m_joystick.getRawButton(m_buttonNum);
        m_lastState = m_currState;
    }

    public void readValue() {
        m_lastState = m_currState;
        m_currState = m_joystick.getRawButton(m_buttonNum);
    }

    public boolean wasJustPressed() {
        return (m_lastState == false && m_currState == true);
    }

    public boolean wasJustReleased() {
        return (m_lastState == true && m_currState == false);
    }

    public boolean stateJustChanged() {
        return (m_lastState != m_currState);
    }

    public boolean isDown(){
        return m_currState;
    }
}
