package com.team2502.robot2015.subsystems;

import com.team2502.robot2015.OI;
import com.team2502.robot2015.RobotMap;
import com.team2502.robot2015.commands.drive.SlideDrive;
import com.team2502.robot2015.commands.forklift.MoveForklift;

import edu.wpi.first.wpilibj.AnalogInput;
import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class Forklift extends Subsystem {

	// Put methods for controlling this subsystem
	// here. Call these from Commands.

	private final CANTalon forkliftWinch = new CANTalon(RobotMap.FORKLIFT_WINCH);
//	private final CANTalon activeIntake = new CANTalon(RobotMap.ACTIVE_INTAKE);
	private final Talon activeIntake = new Talon(RobotMap.ACTIVE_INTAKE);
	private final Solenoid leftArm = new Solenoid(
			RobotMap.FORKLIFT_SOLENOID_LEFT);
	private final Solenoid rightArm = new Solenoid(
			RobotMap.FORKLIFT_SOLENOID_RIGHT);
	private final AnalogInput sensor = new AnalogInput(RobotMap.FORKLIFT_SENSOR);
	private static int forkliftDirection = -1;

	
	private boolean isLeftForced = false;
	private boolean isRightForced = false;

	public void initDefaultCommand() {
		// Set the default command for a subsystem here.
		// setDefaultCommand(new MySpecialCommand());
		setDefaultCommand(new MoveForklift());
	}

	public void stop() {
		forkliftWinch.set(0);
	}

	public void setState(boolean open) {

		if (!isLeftForced) {
			setLeft(open);
		}
		if (!isRightForced) {
			setRight(open);
		}

	}

	//Sets forklift arms to forced closed
	public void setRightForced(boolean forced) {
		//Stores data
		isRightForced = forced;
		//If opposite arm is open then close
		if(!forced) setRight(isOpenLeft());
		else setRight(!forced);
	}

	public void setLeftForced(boolean forced) {
		isLeftForced = forced;
		if(!forced) setLeft(isOpenRight());
		else setLeft(!forced);
	}

	//Getter methods
	public boolean isRightForced() {
		return isRightForced;
	}
	
	public boolean isLeftForced() {
		return isLeftForced;
	}
	
	public void setLeft(boolean open) {
		leftArm.set(open);
	}

	public void setRight(boolean open) {
		rightArm.set(open);
	}

	public boolean isOpenLeft() {

		return leftArm.get();
	}

	public boolean isOpenRight() {
		return rightArm.get();
	}

	public void move(double speed) {
		forkliftWinch.set(speed);
		// updateForkliftDashboard();
	}

	public void moveLift() {

		forkliftWinch.set(OI.getLiftStick().getY() * forkliftDirection);

	}

	public double getHeight() {

		return 60 - (getSensorVoltage() * 10.1);
		// return Double.POSITIVE_INFINITY;
	}

	public double getSensorVoltage() {

		return sensor.getAverageVoltage();
	}

	public void toggleDirection() {

		forkliftDirection *= -1;
	}
	
	public void spinActiveIntake(boolean in) {
		
		double speed = OI.getDriveStick().getThrottle() * ((in) ? 1 : -1);
		if (Math.abs(speed) <= .03) speed = 0;
		activeIntake.set(speed);
	}
	
	public void stopActiveIntake() {
		activeIntake.set(0);
	}

	public void updateForkliftDashboard() {

		SmartDashboard.putNumber("Forklift Voltage", getSensorVoltage());
		SmartDashboard.putNumber("Forklift Height", getHeight());
		SmartDashboard.putNumber("Throttle", OI.getLiftStick().getThrottle());
		SmartDashboard.putBoolean("Left Forklift Open", isOpenLeft());
		SmartDashboard.putBoolean("Right Forklift Open", isOpenRight());
		SmartDashboard.putBoolean("Forced Left", isLeftForced());
		SmartDashboard.putBoolean("Forced Right", isRightForced());
		SmartDashboard.putNumber("Throttle Value", OI.getDriveStick().getThrottle());

	}

}
