package com.team2502.robot2015.commands.forklift;

import com.team2502.robot2015.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class MoveToHeight extends Command {

	private Forklift fl = Robot.forklift;
	private double height;
	private double currentHeight;
	
    public MoveToHeight(double height) {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	requires(Robot.forklift);
    	this.height = height;
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	currentHeight = fl.getHeight();
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	
    	fl.move(currentHeight - height);
    	
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
       
    	return currentHeight == fl.getHeight();
    }

    // Called once after isFinished returns true
    protected void end() {
    fl.stopAll();
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    end();
    }
}
