package com.jfsRobotics.jfsCmdBot.commands;

import com.jfsRobotics.jfsCmdBot.Components;
import edu.wpi.first.wpilibj.command.Command;
import com.jfsRobotics.jfsCmdBot.subsystems.Drivetrain;
import edu.wpi.first.wpilibj.Timer;

/**
 * This command will run the robot forward for the set drive time.
 * 
 * @author John
 */
public class DriveStraightCommand extends Command {
    
    private Drivetrain drivetrain;
        
    //the state machine variable
    private int state;
    
     //time variable used to time events
    private double startTime;
    
    public static final int DRIVE_FWD = 1;
    public static final int DRIVE_REV = -1;
    
    //the time that the motor should run forward and back
    private double driveTime = 2; //seconds
    private double driveSpeed = 0.5;
    private int direction = DRIVE_FWD;
    
    //static state variables, used in state machine
    private static final int BOOTING = 0,
                        DRIVING_STRAIGHT = 1,
                        FINISHED = 2;
    
    public DriveStraightCommand(double drivetime) {
        this.driveTime = drivetime;
        drivetrain = Components.getInstance().drivetrain;
        requires(drivetrain);
    }
    
    public DriveStraightCommand(int direction, double speed, double drivetime) {
        this.driveTime = drivetime;
        this.direction = direction;
        this.driveSpeed = speed;
        drivetrain = Components.getInstance().drivetrain;
        requires(drivetrain);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
        this.setInterruptible(false);
        state = BOOTING;
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
        switch (state)
        {
            //initializing, grabs start time
            case BOOTING:
                startTime = Timer.getFPGATimestamp();
                state = DRIVING_STRAIGHT;
                break;
            
            case DRIVING_STRAIGHT:
                if (Timer.getFPGATimestamp() >= startTime + driveTime)
                {
                    state = FINISHED;
                    drivetrain.updateTank(0, 0);
                    break;
                }
                drivetrain.updateTank(-driveSpeed*direction, driveSpeed*direction);
                break;
        }
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        //done when we are finished with our state machining
        return (state == FINISHED);
    }

    // Called once after isFinished returns true
    protected void end() {
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}