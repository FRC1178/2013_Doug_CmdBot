package com.jfsRobotics.jfsCmdBot.subsystems;

import com.jfsRobotics.utils.subsystems.SubsystemJFS;
import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.DriverStationLCD;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.Victor;

/**
 *
 * @author John
 */
public class Drivetrain  extends SubsystemJFS{
    private RobotDrive robotDrive;
    private Victor leftFront;
    private Victor rightFront;
    private Victor leftBack;
    private Victor rightBack;

    //arcade drive parameters
    private double moveArcade;
    private double rotateArcade;
    //tank drive parameters
    private double leftTank;
    private double rightTank;
    
    private static final double leftTankCompensate = 0.0; //-0.15;
    private static final double rightTankCompensate = 0.0;
    
    //Drive Type constants
    public final int ARCADE_DRIVE = 1;
    public final int TANK_DRIVE = 2;

    private int driveType = ARCADE_DRIVE;

    private static final DriverStationLCD.Line OUTPUT_LINE1 = DriverStationLCD.Line.kUser1;
    private DriverStationLCD textOutput;

    public Drivetrain(int leftFrt, int rightFrt, int leftBck, int rightBck) {
        leftFront = new Victor(leftFrt);
        rightFront = new Victor(rightFrt);
        leftBack = new Victor(leftBck);
        rightBack = new Victor(rightBck);
        this.robotDrive = new RobotDrive(leftFront,leftBack,rightFront,rightBack);
        robotDrive.setInvertedMotor(RobotDrive.MotorType.kFrontLeft, false);
        robotDrive.setInvertedMotor(RobotDrive.MotorType.kRearLeft, false);
        robotDrive.setInvertedMotor(RobotDrive.MotorType.kFrontRight, true);
        robotDrive.setInvertedMotor(RobotDrive.MotorType.kRearRight, true);

        textOutput = DriverStationLCD.getInstance();
    }

    public void changeDriveType()
    {
        driveType++;
        if (driveType > TANK_DRIVE) driveType = ARCADE_DRIVE;
    }

    public void setDriveType(int daDrivetype)
    {
        this.driveType = daDrivetype;
        if (driveType > TANK_DRIVE) driveType = ARCADE_DRIVE;
    }

    public int getDriveType()
    {
        return driveType;
    }

    public void updateStuff(double moveArcade
                            ,double rotateArcade
                            ,double leftTank
                            ,double rightTank
                            )
    {
        this.moveArcade = moveArcade;
        this.rotateArcade = rotateArcade;
        this.leftTank = leftTank + leftTankCompensate;
        this.rightTank = rightTank + rightTankCompensate;
        update();
    }

    public void updateTank( double leftTank
                           ,double rightTank
                           )
    {
        setDriveType(TANK_DRIVE);
        this.leftTank = leftTank + leftTankCompensate;
        this.rightTank = rightTank + rightTankCompensate;
        update();
    }

    public void updateArcade(double moveArcade
                            ,double rotateArcade
                            )
    {
        setDriveType(ARCADE_DRIVE);
        this.moveArcade = moveArcade;
        this.rotateArcade = rotateArcade;
        update();
    }
    
    public void update(){
        //****************************************************
        // Based the selected DriveType, run the proper code
        //
        //****************************************************

        if(driveType == TANK_DRIVE){
            robotDrive.tankDrive(leftTank,rightTank);
            textOutput.println(OUTPUT_LINE1, 1, "JFS_TANK_DRIVE   ");
            textOutput.updateLCD();
        }else
        {
            if(driveType == ARCADE_DRIVE){
                // This works well even when using the Mecanum wheels
                // Forward and back. Sideways motion turns robot.
                robotDrive.arcadeDrive(moveArcade, rotateArcade);
                textOutput.println(OUTPUT_LINE1, 1, "JFS_ARCADE_DRIVE   ");
                textOutput.updateLCD();
            }
        }
    }

    public void setDefaultCommand(Command command){
        if(getDefaultCommand() != null){
            super.getDefaultCommand().cancel();
        }
        super.setDefaultCommand(command);
    }
}
