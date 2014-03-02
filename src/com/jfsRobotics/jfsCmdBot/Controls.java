package com.jfsRobotics.jfsCmdBot;

import com.jfsRobotics.utils.gamepad.Gamepad_XBox3;
import com.jfsRobotics.jfsCmdBot.commands.ChangeDriveTypeCommand;
import com.jfsRobotics.jfsCmdBot.commands.SetShooterCommand;

import com.jfsRobotics.jfsCmdBot.subsystems.Shooter;



public class Controls {
    private static Controls instance;
    private static final double ONE_JOYSTICK_MAGNITUDE = 1;
    
    public final Gamepad_XBox3 gamepad1;
    
    private Controls(){
        gamepad1 = new Gamepad_XBox3(1);
        //Drivetrain Direction Toggle
        gamepad1.B_BUTTON.whenPressed(new ChangeDriveTypeCommand());
        

        gamepad1.RIGHT_BUMPER.whenPressed(new SetShooterCommand(Shooter.SHOOTER_ON));
        gamepad1.RIGHT_BUMPER.whenReleased(new SetShooterCommand(Shooter.SHOOTER_OFF));
    }
    
    /**
     * Returns the proper instance of Controls.
     * This method creates a new Controls object the first time it is called
     * and returns that object for each subsequent call.
     * @return The current instance of Controls.
     */
    public static Controls getInstance(){
        if(instance == null){
            instance = new Controls();
        }
        return instance;
    }
}
