/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jfsRobotics.jfsCmdBot.commands;

import com.jfsRobotics.jfsCmdBot.Components;
import edu.wpi.first.wpilibj.command.Command;
import com.jfsRobotics.jfsCmdBot.subsystems.Shooter;
/**
 *
 * @author John
 */
public class SetShooterCommand extends Command {
    private Shooter shooter;
    private double velocity;
    
    public SetShooterCommand(double velocity) {
        super("SetShooter");
        this.shooter = Components.getInstance().shooter;
        this.velocity = velocity;
        requires(shooter);
    }

    protected void initialize() {
        shooter.setShooterVelocity(velocity);
    }

    protected void execute() {
        
    }

    protected boolean isFinished() {
        return true;
    }

    protected void end() {
        
    }

    protected void interrupted() {
        
    }
    
}