package com.team2502.robot2015.commands;

import com.team2502.robot2015.Robot;
import com.team2502.robot2015.subsystems.DriveTrain;

import edu.wpi.first.wpilibj.command.Command;

public class SlideDrive extends Command {

	private static DriveTrain dt = Robot.driveTrain;

	public SlideDrive() {
		requires(Robot.driveTrain);
	}

	protected void initialize() {
	}

	protected void execute() {
		dt.slideDrive();
	}

	protected boolean isFinished() {
		return false;
	}

	protected void end() {
	}

	protected void interrupted() {
	}
}
