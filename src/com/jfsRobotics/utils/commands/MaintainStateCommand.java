/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jfsRobotics.utils.commands;

import com.jfsRobotics.utils.subsystems.SubsystemJFS;
import edu.wpi.first.wpilibj.command.Command;

/**
 * This {@link Command} maintains the current state of a
 * subsystem. It ensures that the subsystem does not time out and is
 * normally used as the default command of a subsystem.
 * @author John
 */
public class MaintainStateCommand extends Command {
    private SubsystemJFS subsystem;
    
    public MaintainStateCommand(SubsystemJFS subsystem) {
        super("MaintainState");
        requires(subsystem);
        this.subsystem = subsystem;
        setInterruptible(true);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
        subsystem.update();
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return false;
    }

    // Called once after isFinished returns true
    protected void end() {
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}