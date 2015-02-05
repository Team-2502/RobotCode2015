package com.team2502.robot2015.commands.forklift;

import com.team2502.robot2015.Robot;
import com.team2502.robot2015.subsystems.Forklift;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class MoveUp extends Command {

	private double distance;
	private double startHeight;
	private Forklift fl = Robot.forklift;
	
    public MoveUp(double distance) {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	requires(Robot.forklift);
    	this.distance = distance;
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	this.startHeight = fl.getHeight();
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	fl.move(1);
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return fl.getHeight() - distance > startHeight;
    }

    // Called once after isFinished returns true
    protected void end() {
    	fl.stop();
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	end();
    }
}
