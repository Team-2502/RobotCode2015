package com.team2502.robot2015.commands.scorpion;

import com.team2502.robot2015.OI;
import com.team2502.robot2015.Robot;
import com.team2502.robot2015.subsystems.ScorpionTail;

import edu.wpi.first.wpilibj.command.Command;

public class MoveScorpion extends Command {

	private ScorpionTail s = Robot.scorpion;
	
	public MoveScorpion() {
		requires(Robot.scorpion);
	}

	protected void initialize() {

	}

	protected void execute() {
		if (OI.getLiftStick().getRawButton(2)) {
			s.move(OI.getLiftStick().getY() * Math.abs(OI.getLiftStick().getY()));
		} else {
			s.stop();
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
