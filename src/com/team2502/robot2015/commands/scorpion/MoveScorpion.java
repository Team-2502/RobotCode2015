package com.team2502.robot2015.commands.scorpion;

import com.team2502.robot2015.OI;
import com.team2502.robot2015.Robot;
import com.team2502.robot2015.subsystems.ScorpionTail;

import edu.wpi.first.wpilibj.command.Command;

public class MoveScorpion extends Command {

	private ScorpionTail s = Robot.scorpion;
	
	protected void initialize() {
		
	}

	
	protected void execute() {
		if(OI.getLiftStick().getRawButton(3)) {
			
			s.move(OI.getLiftStick().getY() * OI.getLiftStick().getY());
			
			
		}
		
		
	}


	protected boolean isFinished() {
		
		return false;
	}

	
	protected void end() {
	
		
	}

	@Override
	protected void interrupted() {
		// TODO Auto-generated method stub
		
	}

}
