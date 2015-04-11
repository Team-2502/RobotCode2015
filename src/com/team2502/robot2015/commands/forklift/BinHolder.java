package com.team2502.robot2015.commands.forklift;

import com.team2502.robot2015.Robot;
import com.team2502.robot2015.subsystems.Forklift;

import edu.wpi.first.wpilibj.command.Command;

public class BinHolder extends Command {

	private Forklift fl = Robot.forklift;
	
	public BinHolder() {
		requires(Robot.forklift);
	}
	
	protected void initialize() {
		// TODO Auto-generated method stub		
	}

	protected void execute() {
    	fl.setBinHolder(!fl.isBinHolderOpen());
	}

	protected boolean isFinished() {
		// TODO Auto-generated method stub
		return true;
	}

	protected void end() {
		// TODO Auto-generated method stub		
	}

	protected void interrupted() {
		// TODO Auto-generated method stub		
	}
}