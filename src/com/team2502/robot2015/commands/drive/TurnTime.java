package com.team2502.robot2015.commands.drive;

import com.team2502.robot2015.Robot;
import com.team2502.robot2015.subsystems.DriveTrain;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class TurnTime extends Command {

	private DriveTrain dt = Robot.driveTrain;
	private double time;
	private double startTime;
	private double speed;
	private double rampMulti = 1;
	
    public TurnTime(double time, double speed) {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	requires(Robot.driveTrain);
    	this.speed = speed;
    	this.time = time;
    }
    
    public TurnTime(double time, double speed, double rampMulti) {
    	this(time, speed);
    	this.rampMulti = rampMulti;
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	startTime = System.currentTimeMillis();
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	if (System.currentTimeMillis() - startTime < time * 500) {
    		dt.moveMainDrive(dt.rampUpTo(speed, startTime, rampMulti), -dt.rampUpTo(speed, startTime, rampMulti));
    	} else {
    		dt.moveMainDrive(dt.rampDownFrom(speed, startTime / 1000d + time, rampMulti), -dt.rampDownFrom(speed, startTime / 1000d + time, rampMulti));
    	}
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return System.currentTimeMillis() - (time * 1000d) >= startTime;
    }

    // Called once after isFinished returns true
    protected void end() {
    	dt.stopMain();
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	end();
    }
}
