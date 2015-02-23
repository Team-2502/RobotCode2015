package com.team2502.robot2015.commands.scorpion;

import com.team2502.robot2015.Robot;
import com.team2502.robot2015.subsystems.ScorpionTail;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class MoveTailTime extends Command {

	private double speed;
	private double time;
	private double startTime;
	private ScorpionTail st = Robot.scorpion;
	
    public MoveTailTime(double time, double speed) {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	requires(Robot.scorpion);
    	this.speed = speed;
    	this.time = time;
    	
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	startTime = System.currentTimeMillis();
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	st.move(speed);
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
    	return System.currentTimeMillis() - (time * 1000) > startTime;
    }

    // Called once after isFinished returns true
    protected void end() {
    	st.stop();
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	end();
    }
}
