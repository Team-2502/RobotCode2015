package com.team2502.robot2015.commands.forklift;

import com.team2502.robot2015.Robot;
import com.team2502.robot2015.subsystems.Forklift;

import edu.wpi.first.wpilibj.command.Command;

public class ForkliftArmActuator extends Command {

	private Forklift fl = Robot.forklift;
	
	public ForkliftArmActuator() {
		requires(Robot.forklift);
	}
	
	protected void initialize() {
		// TODO Auto-generated method stub		
	}

	protected void execute() {
    	fl.setActuator(!fl.isActuatorOpen());
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