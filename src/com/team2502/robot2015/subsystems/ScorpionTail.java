package com.team2502.robot2015.subsystems;

import com.team2502.robot2015.RobotMap;
import com.team2502.robot2015.commands.scorpion.MoveScorpion;

import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class ScorpionTail extends Subsystem {

	private final CANTalon winch;

	// Put methods for controlling this subsystem
	// here. Call these from Commands.

	public ScorpionTail() {
		winch = new CANTalon(RobotMap.SCORPION_WINCH);

	}

	public void initDefaultCommand() {
		// Set the default command for a subsystem here.
		setDefaultCommand(new MoveScorpion());

	}

	public void move(double speed) {

		winch.set(speed);
	}

	public void stop() {

		winch.set(0);
	}

	public double getEncoderValue() {

		return winch.getPosition();
	}

}
