package com.team2502.robot2015.subsystems;

import com.team2502.robot2015.OI;
import com.team2502.robot2015.RobotMap;
import com.team2502.robot2015.commands.drive.SlideDrive;

import edu.wpi.first.wpilibj.BuiltInAccelerometer;
import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class DriveTrain extends Subsystem {

	public static final double WHEEL_DIAMETER = 5;

	public enum Motors {
		LEFT_FRONT, LEFT_SLIDE, RIGHT_FRONT, RIGHT_SLIDE
	}

	private static DriveTrain instance;
	private final RobotDrive drive;
	private final CANTalon leftFront;
//	private final CANTalon leftBack;
	private final CANTalon leftSlide;

	private final CANTalon rightFront;
//	private final CANTalon rightBack;
	private final CANTalon rightSlide;
	
	private double speedX = 0;
	private double speedY = 0;
	private double xCoord = 0;
	private double yCoord = 0;
	private double initialXAccel = 0;
	private double initialYAccel = 0;
	private double prevTime;

	private BuiltInAccelerometer accel = new BuiltInAccelerometer();

	private DriveTrain() {
		leftFront = new CANTalon(RobotMap.LEFT_FRONT_DRIVE);
//		leftBack = new CANTalon(RobotMap.LEFT_BACK_DRIVE);
		leftSlide = new CANTalon(RobotMap.LEFT_SLIDE_DRIVE);

		rightFront = new CANTalon(RobotMap.RIGHT_FRONT_DRIVE);
//		rightBack = new CANTalon(RobotMap.RIGHT_BACK_DRIVE);
		rightSlide = new CANTalon(RobotMap.RIGHT_SLIDE_DRIVE);
		drive = new RobotDrive(leftFront, rightFront);
//		accel = new BuiltInAccelerometer();

		leftFront.setPosition(0);
//		leftBack.setPosition(0);
		leftSlide.setPosition(0);

		rightFront.setPosition(0);
//		rightBack.setPosition(0);
		rightSlide.setPosition(0);
		accel.startLiveWindowMode();
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
		stopMain();
		stopSlide();
	}

	public void stopSlide() {
		leftSlide.set(0);
		rightSlide.set(0);
	}

	public void stopMain() {
		drive.stopMotor();
	}

	public void slideDrive() {
		drive.arcadeDrive(OI.getDriveStick().getY(), OI.getDriveStick().getZ(), true);
		leftSlide.set(-OI.getDriveStick().getX() * Math.abs(OI.getDriveStick().getX()));
		rightSlide.set(-OI.getDriveStick().getX() * Math.abs(OI.getDriveStick().getX()));
		adjustMovement();
	}
	
	private void adjustMovement() {
		
		if (Math.abs(OI.getDriveStick().getX()) < .05 && Math.abs(OI.getDriveStick().getY()) < .05) return;
		
		if (prevTime == 0) {
			prevTime = System.currentTimeMillis();
			initialXAccel = accel.getX();
			initialYAccel = accel.getY();
		}
		
		
		double changedTime = (System.currentTimeMillis() - prevTime) / 1000;
		speedX += (accel.getX() - initialXAccel) * changedTime;
		speedY += (accel.getY() - initialYAccel) * changedTime;
		xCoord += speedX * changedTime;
		yCoord += speedY * changedTime;
		prevTime = System.currentTimeMillis();
	}

	public void moveMainDrive(double speed) {
		moveMainDrive(speed, speed);
	}

	public void moveMainDrive(double leftSpeed, double rightSpeed) {
		drive.tankDrive(leftSpeed, rightSpeed);
	}

	public void moveSlide(double speed) {
		leftSlide.set(speed);
		rightSlide.set(speed);
	}

	public double getEncoderValue(Motors motor) {
		switch (motor) {
		case LEFT_FRONT:
			return leftFront.getPosition();
//		case LEFT_BACK:
//			return leftBack.getPosition();
		case LEFT_SLIDE:
			return leftSlide.getPosition();
		case RIGHT_FRONT:
			return rightFront.getPosition() * 100;
//		case RIGHT_BACK:
//			return rightBack.getPosition();
		case RIGHT_SLIDE:
			return rightSlide.getPosition();
		default:
			return Double.POSITIVE_INFINITY;
		}
	}

	public void updateDriveDashboard() {

		for (Motors m : Motors.values()) {

			double en = getEncoderValue(m);
//			double dist = ((WHEEL_DIAMETER * Math.PI) * (en / 1440));
//
//			SmartDashboard.putNumber(m.toString() + " Distance", dist);
			SmartDashboard.putNumber(m.toString() + " Encoder", en);

		}

		
		accel.updateTable();
		
		
		SmartDashboard.putData("Accelerometer", accel);
		SmartDashboard.putNumber("X", accel.getX() - initialXAccel);
		SmartDashboard.putNumber("Y", accel.getY() - initialYAccel);
		SmartDashboard.putNumber("Z", accel.getZ());
		
		SmartDashboard.putNumber("X Coord", xCoord);
		SmartDashboard.putNumber("Y Coord", yCoord);
		SmartDashboard.putNumber("X Speed", speedX);
		SmartDashboard.putNumber("Y Speed", speedY);

	}

	public double rampUpTo(double speed, double startTime, double rampMultiplier) {
		double absSpeed = Math.abs(speed);
		double changeInTime = (System.currentTimeMillis() - startTime) / 1000;
		if (changeInTime * rampMultiplier < absSpeed) {
			return changeInTime * rampMultiplier * (speed < 0 ? -1 : 1);
		} else {
			return speed;
		}
	}

	public double rampDownFrom(double speed, double targetTime, double rampMultiplier) {
		double absSpeed = Math.abs(speed);
		double currentTime = System.currentTimeMillis() / 1000d;
		if (currentTime < targetTime) {
			if ((targetTime - currentTime) * rampMultiplier < absSpeed) {
				return (targetTime - currentTime) * rampMultiplier  * (speed < 0 ? -1 : 1);
			} else {
				return speed;
			}
		} else {
			return 0;
		}
	}
	
	public BuiltInAccelerometer getAccelerometer() {
		return accel;
	}
}
