/*----------------------------------------------------------------------------*/
/* Copyright (c) FIRST 2008. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package com.jfsRobotics.jfsCmdBot;

import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;


import com.jfsRobotics.jfsCmdBot.subsystems.Drivetrain;
import com.jfsRobotics.jfsCmdBot.subsystems.Shooter;

import com.jfsRobotics.utils.commands.MaintainStateCommand;
import com.jfsRobotics.utils.gamepad.Gamepad_XBox3;
import com.jfsRobotics.jfsCmdBot.commands.GamepadDriveCommand;
import com.jfsRobotics.jfsCmdBot.commands.SetDrivetrainCommand;


/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the IterativeRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the manifest file in the resource
 * directory.
 */
public class CmdBot extends IterativeRobot {
    private Command autonomousCommand;

    /**
     * This function is called when the robot switches between modes (eg. Autonomous,
     * Teleop) to reset running subsystems.
     */
    public void betweenModes() {
        Drivetrain drivetrain = Components.getInstance().drivetrain;
        drivetrain.setDefaultCommand(new MaintainStateCommand(drivetrain));
                
        Shooter shooter = Components.getInstance().shooter;
        shooter.setDefaultCommand(new MaintainStateCommand(shooter));


        stop();
        if(autonomousCommand != null) {
            autonomousCommand.cancel();
        }
    }
    
    /**
     * This function is run when the robot is first started up and should be
     * used for any initialization code.
     */
    public void robotInit() {
        Components.getInstance(); //Create all robot subsystems.
        Controls.getInstance(); //Create all robot controls.
        System.out.println("Robot initialized.");
    }
    
    public void disabledInit() {
        betweenModes();
    }
    
    public void disabledPeriodic() {
        stop();
    }
    
    /**
     * This function is called once at the start of autonomous mode.
     */
    public void autonomousInit(){
        betweenModes();



    }

    /**
     * This function is called periodically during autonomous.
     */
    public void autonomousPeriodic() {
        Scheduler.getInstance().run();
    }
    
    /**
     * This function is called once at the start of teleop mode.
     */
    public void teleopInit(){
        betweenModes();
        Gamepad_XBox3 driveGamepad = Controls.getInstance().gamepad1;
        Components.getInstance().drivetrain.setDefaultCommand(new GamepadDriveCommand(driveGamepad));
    }

    /**
     * This function is called periodically during operator control.
     */
    public void teleopPeriodic() {
        Scheduler.getInstance().run();        
    }
    
    /**
     * This function is called once at the start of test mode.
     */
    public void testInit() {
        betweenModes();
        LiveWindow.setEnabled(false);
        teleopInit();
    }
    
    /**
     * This function is called periodically during test mode.
     */
    public void testPeriodic() {
        teleopPeriodic();

    }
    
    /**
     * This function is called to stop <i>each</i> subsystem.
     */
    public void stop() {
        Components.getInstance().drivetrain.updateStuff(0,0,0,0);
        Components.getInstance().shooter.setShooterVelocity(Shooter.SHOOTER_OFF);
    }
}
