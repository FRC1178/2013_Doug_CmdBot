/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jfsRobotics.jfsCmdBot.subsystems;

import com.jfsRobotics.utils.subsystems.SubsystemJFS;
import edu.wpi.first.wpilibj.Victor;
import edu.wpi.first.wpilibj.command.Command;

/**
 *
 * @author John
 */

public class Shooter extends SubsystemJFS {
    public static final double SHOOTER_ON = -1;
    public static final double SHOOTER_OFF = 0;
    
    private Victor myShooter;
    private double myVelocity;

    
    public Shooter(int myVictor){
        super("Shooter");
        this.myShooter = new Victor(myVictor);
    }
    
    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
    
    public void setShooterVelocity(double daSpeed){
       myVelocity = daSpeed;
       update();
    }
    
    
    public void update(){
        myShooter.set(myVelocity);
    }
    
    public void setDefaultCommand(Command command){
        if(getDefaultCommand() != null){
            super.getDefaultCommand().cancel();
        }
        super.setDefaultCommand(command);
    }    
}