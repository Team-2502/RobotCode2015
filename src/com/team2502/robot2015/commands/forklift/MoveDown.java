package com.team2502.robot2015.commands.forklift;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class MoveDown extends Command {

	private double distance;
	private double speed;
	private double startDistance;
	private double Forklift fl = Robot.forklift;
    public MoveDown() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	requires(Robot.forklift);
    	this.distance = distance;
    	this.speed = speed;
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	this.startDistance = fl.getHeight();
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	fl.move(speed);
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return fl.getHeight() - distance > startDistance;
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
