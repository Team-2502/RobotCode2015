package com.team2502.robot2015.commands.forklift;

import com.team2502.robot2015.Robot;
import com.team2502.robot2015.subsystems.Forklift;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class ToggleActiveIntakeArms extends Command {

	private Forklift fl = Robot.forklift;
	
    public ToggleActiveIntakeArms() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	requires(Robot.forklift);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	
    	fl.setState(!(fl.isOpenLeft() || fl.isOpenRight()));
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return true;
    }

    // Called once after isFinished returns true
    protected void end() {
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
