package com.team2502.robot2015.commands.drive;

import com.team2502.robot2015.Robot;
import com.team2502.robot2015.subsystems.DriveTrain;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class SlideTime extends Command {

	private static DriveTrain dt = Robot.driveTrain;
	private static double time;
	private static double startTime;
	private static double speed;
	
    public SlideTime(double time, double speed) {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	requires(Robot.driveTrain);
    	this.speed = speed;
    	this.time = time;
    	
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	startTime = System.currentTimeMillis();
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	dt.moveSlide(speed);
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
    	 return System.currentTimeMillis() - (time * 1000) > startTime;
    }

    // Called once after isFinished returns true
    protected void end() {
    	dt.stopSlide();
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	end();
    }
}
