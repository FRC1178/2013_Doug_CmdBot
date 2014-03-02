/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jfsRobotics.jfsCmdBot.commands;

import edu.wpi.first.wpilibj.command.Command;
import com.jfsRobotics.utils.gamepad.Gamepad_XBox3;
import com.jfsRobotics.utils.gamepad.FilterSet;
import com.jfsRobotics.utils.gamepad.GamepadResult;
import com.jfsRobotics.jfsCmdBot.subsystems.Drivetrain;
import com.jfsRobotics.jfsCmdBot.Components;
import com.jfsRobotics.utils.gamepad.filters.DeadzoneFilter;
import com.jfsRobotics.utils.gamepad.filters.ScalingPowerFilter;
/**
 *
 * @author John
 */
public class GamepadDriveCommand extends Command{
    private static final String COMMAND_NAME = "GamepadDrive";
    private static final double SPEED_MULTIPLIER = 1;
    
    Gamepad_XBox3 gamepad;
    FilterSet filters;
    Drivetrain drivetrain;
    
    /**
     * Construct a new GamepadDriveCommand using the given gamepad, filters
     * and drivetrain.
     * @param gamepad The Gamepad_XBox3 object to read for control values.
     * @param filters The set of filters to use when filtering gamepad output.
     * @param drivetrain The drivetrain object to control.
     */
    public GamepadDriveCommand(Gamepad_XBox3 gamepad, FilterSet filters){
        super(COMMAND_NAME);
        this.gamepad = gamepad;
        this.filters = filters;
        this.drivetrain = Components.getInstance().drivetrain;
        requires(drivetrain);
    }
    
    /**
     * Constructs a new GamepadDriveCommand using the given gamepad and
     * drivetrain. This command will use a default set of filters.
     * @param gamepad The Gamepad_XBox3 object to read for control values.
     * @param drivetrain The drivetrain object to control.
     */
    public GamepadDriveCommand(Gamepad_XBox3 gamepad){
        super(COMMAND_NAME);
        filters = new FilterSet();
        filters.addFilter(new DeadzoneFilter(0.15));
        filters.addFilter(new ScalingPowerFilter(2));
        this.gamepad = gamepad;
        this.drivetrain = Components.getInstance().drivetrain;
        requires(drivetrain);
    }

    protected void initialize() {
    }

    /**
     * Submits values from the given {@code gamepad} to the given
     * {@code drivetrain}.
     */
    protected void execute() {
        //**********************************************************
        // The Drivetrain subsystem expects inputs to allow it to
        // drive in one of three modes: Arcade, Tank or Mecanum.
        // It is the job of this class to send the correct parameters
        // for all cases, so that which ever case has been selected,
        // the correct Driver controls will be available.
        //**********************************************************
        GamepadResult gamepadState = filters.filter(gamepad.getJoysticks());
        drivetrain.updateStuff( gamepadState.getLeftX(), // arcadeMove
                               -gamepadState.getLeftY(), // arcadeRotate
                                gamepadState.getLeftY(), // leftTank
                               -gamepadState.getRightY()// rightTank
                            //    , gamepadState.getLeftMagnitude() //magMec
                            //    , gamepadState.getLeftDirection() //dirMec
                            //    , gamepadState.getRightX()  //rotMec
                               ); 
    }

    protected boolean isFinished() {
        return false;
    }

    protected void end() {
        
    }

    protected void interrupted() {
        end();
    }
}
