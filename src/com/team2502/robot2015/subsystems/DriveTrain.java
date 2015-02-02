package com.team2502.robot2015.subsystems;

import com.team2502.robot2015.OI;
import com.team2502.robot2015.RobotMap;
import com.team2502.robot2015.commands.SlideDrive;

import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.command.Subsystem;

public class DriveTrain extends Subsystem {

	private static DriveTrain instance;
	private final RobotDrive drive;

	private DriveTrain() {
		drive = new RobotDrive(RobotMap.LEFT_FRONT_DRIVE, RobotMap.LEFT_BACK_DRIVE, RobotMap.RIGHT_FRONT_DRIVE, RobotMap.RIGHT_BACK_DRIVE);
	}

	public static DriveTrain getInstance() {
		if (instance == null) {
			instance = new DriveTrain();
		}
		return instance;
	}

	protected void initDefaultCommand() {
		setDefaultCommand(new SlideDrive());
	}

	public void slideDrive() {
		drive.arcadeDrive(OI.getDriveStick());
	}
}
