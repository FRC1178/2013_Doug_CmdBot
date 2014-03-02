/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jfsRobotics.jfsCmdBot.commands;

import com.jfsRobotics.jfsCmdBot.Components;
import com.jfsRobotics.jfsCmdBot.subsystems.Drivetrain;
import edu.wpi.first.wpilibj.command.Command;
/**
 *
 * @author John
 */
public class SetDrivetrainCommand extends Command {
    private double moveArcade,rotateArcade
                   , leftTank,rightTank
                   //, magnitudeMecanum,directionMecanum,rotateMecanum
                   ;
    
    private Drivetrain drivetrain;
    

    public SetDrivetrainCommand(double moveArcade, double rotateArcade
                               , double leftTank,double rightTank
                               //, double magnitudeMecanum,double directionMecanum, double rotateMecanum
                               )
    {
        super("SetDrivetrain");

        this.moveArcade = moveArcade;
        this.rotateArcade = rotateArcade;
        this.leftTank = leftTank;
        this.rightTank = rightTank;
        //this.magnitudeMecanum = magnitudeMecanum;
       // this.directionMecanum = directionMecanum;
        //this.rotateMecanum = rotateMecanum;
        
        drivetrain = Components.getInstance().drivetrain;
        requires(drivetrain);
    }

    protected void initialize() {
        drivetrain.updateStuff(moveArcade,rotateArcade
                               , leftTank,rightTank
                             //  , magnitudeMecanum,directionMecanum,rotateMecanum
                               );
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