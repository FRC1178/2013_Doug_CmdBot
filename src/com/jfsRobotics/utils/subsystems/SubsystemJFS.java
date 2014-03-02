/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jfsRobotics.utils.subsystems;

import com.jfsRobotics.utils.commands.MaintainStateCommand;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 * @author John
 */
public abstract class SubsystemJFS extends Subsystem {
    // Put methods for controlling this subsystem
    // here. Call these from Commands.

    /**
     * Constructs a new Subsystem1816 with no given name.
     */
    public SubsystemJFS(){
        super();
    }
    
    /**
     * Constructs a new Subsystem1816 with the given {@code name}.
     * @param name The name of the new subsystem.
     */
    public SubsystemJFS(String name){
        super(name);
    }
    
    /**
     * Sets the default command of the subsystem to be a MaintainStateCommand.
     */
    protected void initDefaultCommand() {
        setDefaultCommand(new MaintainStateCommand(this));
    }
    
    /**
     * Updates all subcomponents of this subsystem. Calling this method
     * ensures that the watchdog timers for the subcomponents will not
     * time out.
     */
    public abstract void update();
}