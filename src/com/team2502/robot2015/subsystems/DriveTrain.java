package com.team2502.robot2015.subsystems;

import com.team2502.robot2015.OI;
import com.team2502.robot2015.RobotMap;
import com.team2502.robot2015.commands.SlideDrive;

import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.command.Subsystem;

public class DriveTrain extends Subsystem {

	private static DriveTrain instance;
	private final RobotDrive drive;
	private final CANTalon leftFront;
	private final CANTalon leftBack;
	private final CANTalon leftSlide;
	
	private final CANTalon rightFront;
	private final CANTalon rightBack;
	private final CANTalon rightSlide;

	private DriveTrain() {
		leftFront = new CANTalon(RobotMap.LEFT_FRONT_DRIVE);
		leftBack = new CANTalon(RobotMap.LEFT_BACK_DRIVE);
		leftSlide = new CANTalon(RobotMap.LEFT_SLIDE_DRIVE);
		
		rightFront = new CANTalon(RobotMap.RIGHT_FRONT_DRIVE);
		rightBack = new CANTalon(RobotMap.RIGHT_BACK_DRIVE);
		rightSlide = new CANTalon(RobotMap.RIGHT_SLIDE_DRIVE);
		drive = new RobotDrive(leftFront, leftBack, rightFront, rightBack);
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
	
	public void stopAll() {
		drive.stopMotor();
		leftSlide.set(0);
		rightSlide.set(0);
	}

	public void slideDrive() {
		drive.arcadeDrive(OI.getDriveStick());
		leftSlide.set(Math.pow(OI.getDriveStick().getX(), 2));
		rightSlide.set(Math.pow(OI.getDriveStick().getX(), 2));
	}
}
