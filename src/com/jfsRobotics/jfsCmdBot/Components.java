package com.jfsRobotics.jfsCmdBot;

import com.jfsRobotics.jfsCmdBot.subsystems.Drivetrain;
import com.jfsRobotics.jfsCmdBot.subsystems.Shooter;


/**
 *
 * @author John
 */
public class Components {
    private static Components instance;

    
    //Analog Input Constants

    //PWM constants
    //Drivetrain constants
    // home bot  LF 1  LR 3  RF 2  RR 4
    // phoenix   LF 3  LR 7  RF 1 RR 5  no shooter 10
    // 2013 DURT LF 1  LR 4  RF 2  RR 3   shooter 5
    // 2014 kit bot "Yoshi"   Left 1  Right 2

    private static final int FRONT_LEFT_DRIVE = 1;
    private static final int BACK_LEFT_DRIVE = 4;
    private static final int FRONT_RIGHT_DRIVE = 2;
    private static final int BACK_RIGHT_DRIVE = 3;
    
    private static final int SHOOTER = 5;
    


    
    
    //Subsystem objects
    public final Drivetrain drivetrain;
    public final Shooter shooter;

    
    /**
     * Private constructor for the Components singleton. This constructor
     * is only called once and handles creating all the robot subsystems.
     */
    private Components(){
        drivetrain = new Drivetrain(FRONT_LEFT_DRIVE,
                                    FRONT_RIGHT_DRIVE,
                                    BACK_LEFT_DRIVE, 
                                    BACK_RIGHT_DRIVE);
        
        shooter = new Shooter(SHOOTER);
        
    }
    
    /**
     * Returns a new instance of {@link Components}, creating one if null.
     * @return {@link Components}
     */
    public static Components getInstance() {
        if(instance == null){
            instance = new Components();
        }
        return instance;
    }
}
