/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jfsRobotics.utils.gamepad;

/**
 *
 * @author John
 */
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.Button;
import edu.wpi.first.wpilibj.buttons.JoystickButton;

public class Gamepad_XBox3 extends Gamepad{
    private Joystick joystick;
    
    private final double DPAD_THRESHOLD = 0.5;
    
    public final Button A_BUTTON;
    public final Button B_BUTTON;
    public final Button X_BUTTON;
    public final Button Y_BUTTON;
    public final Button LEFT_BUMPER;
    public final Button RIGHT_BUMPER;
    public final Button BACK_BUTTON;
    public final Button START_BUTTON;
    public final Button LEFT_JOYSTICK_BUTTON;
    public final Button RIGHT_JOYSTICK_BUTTON;
    public final Button DPAD_UP;
    public final Button DPAD_DOWN;
    public final Button DPAD_RIGHT;
    public final Button DPAD_LEFT;
    
    public Gamepad_XBox3(int port) {
        joystick = new Joystick(port);
        A_BUTTON = new JoystickButton(joystick, 1);
        B_BUTTON = new JoystickButton(joystick, 2);
        X_BUTTON = new JoystickButton(joystick, 3);
        Y_BUTTON = new JoystickButton(joystick, 4);
        LEFT_BUMPER = new JoystickButton(joystick, 5);
        RIGHT_BUMPER = new JoystickButton(joystick, 6);
        BACK_BUTTON = new JoystickButton(joystick, 7);
        START_BUTTON = new JoystickButton(joystick, 8);;
        LEFT_JOYSTICK_BUTTON = new JoystickButton(joystick, 9);
        RIGHT_JOYSTICK_BUTTON = new JoystickButton(joystick, 10);

        DPAD_UP = new DPadButton(this, DPadButton.DPAD_UP);
        DPAD_DOWN = new DPadButton(this, DPadButton.DPAD_DOWN);
        DPAD_LEFT = new DPadButton(this, DPadButton.DPAD_LEFT);
        DPAD_RIGHT = new DPadButton(this, DPadButton.DPAD_RIGHT);
    }
    
    public double getLeftX() {
        return joystick.getRawAxis(1);
    }
    
    public double getLeftY() {
        return -joystick.getRawAxis(2);
    }
    
    public double getTriggerAxis() {
        return joystick.getRawAxis(3);
    }
    
    public double getRightX() {
        return joystick.getRawAxis(4);
    }
    
    public double getRightY() {
        return -joystick.getRawAxis(5);
    }
    
    public byte getDPadX() {
        return dPadToByte(joystick.getRawAxis(6));
    }
    
    public byte getDPadY() {
        return dPadToByte(joystick.getRawAxis(6));
    }
    
    public GamepadResult getJoysticks(){
        return new GamepadResult(getLeftX(),getLeftY(),getRightX(),getRightY());
    }

    protected byte dPadToByte(double value) {
        if(value >= DPAD_THRESHOLD){
            return 1;
        }
        else if(value <= -DPAD_THRESHOLD){
            return -1;
        }
        return 0;
    }
}
