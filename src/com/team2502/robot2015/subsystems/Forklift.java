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

	private final CANTalon forkliftWinch = new CANTalon(RobotMap.FORKLIFT_WINCH);
	private final Talon activeIntake = new Talon(RobotMap.ACTIVE_INTAKE);
//	private final CANTalon activeIntakeLeft = new CANTalon(RobotMap.ACTIVE_INTAKE_LEFT);
//	private final CANTalon activeIntakeRight = new CANTalon(RobotMap.ACTIVE_INTAKE_RIGHT);
	private final Solenoid leftArm = new Solenoid(
			RobotMap.FORKLIFT_SOLENOID_LEFT);
	private final Solenoid rightArm = new Solenoid(
			RobotMap.FORKLIFT_SOLENOID_RIGHT);
	private final Solenoid actuator = new Solenoid(
			RobotMap.FORKLIFT_SOLENOID_ARM);
	private final Solenoid binHolder = new Solenoid(
			RobotMap.FORKLIFT_TOP_ARMS);
	private final AnalogInput sensor = new AnalogInput(RobotMap.FORKLIFT_SENSOR);
	private static int forkliftDirection = -1;

	
	private boolean isLeftForced = false;
	private boolean isRightForced = false;
	private boolean enabled = true;
	
	public boolean isEnable() {
		return enabled;
	}

	public void initDefaultCommand() {
		setDefaultCommand(new MoveForklift());
	}

	public void stop() {
		forkliftWinch.set(0);
	}

	public void setActiveIntakeState(boolean open) {

		if (!isLeftForced) {
			setLeft(open);
		}
		if (!isRightForced) {
			setRight(open);
		}

	}
	
	public void setAutoOpenEnabled(boolean enabled) {
		this.enabled = enabled;
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
	
	public void setActuator(boolean open) {
		actuator.set(open);
	}
	
	public void setBinHolder(boolean open) {
		binHolder.set(open);
	}

	public boolean isOpenLeft() {

		return leftArm.get();
	}

	public boolean isOpenRight() {
		return rightArm.get();
	}
	
	public boolean isActuatorOpen() {
		return actuator.get();
	}
	
	public boolean isBinHolderOpen() {
		return binHolder.get();
	}

	public void move(double speed) {
		forkliftWinch.set(speed);
	}

	public void moveLift() {

		forkliftWinch.set(OI.getLiftStick().getY() * forkliftDirection);

	}

	public double getHeight() {

		return 60 - (getSensorVoltage() * 10.1);
	}

	public double getSensorVoltage() {
		return sensor.getVoltage();
//		return sensor.getAverageVoltage();
	}

	public void toggleDirection() {

		forkliftDirection *= -1;
	}
	
	public void spinActiveIntake(boolean in) {
		
		double speed = OI.getDriveStick().getThrottle() * ((in) ? 1 : -1);
		if (Math.abs(speed) <= .03) speed = 0;
//		activeIntakeLeft.set(speed);
//		activeIntakeRight.set(speed);
		activeIntake.set(speed);
	}
	
	public void stopActiveIntake() {
//		activeIntakeLeft.set(0);
//		activeIntakeRight.set(0);
		activeIntake.set(0);
	}

	public void updateForkliftDashboard() {

		SmartDashboard.putNumber("Forklift Voltage", getSensorVoltage());
		SmartDashboard.putData("IR Sensor", sensor);
		SmartDashboard.putNumber("Forklift Height", getHeight());
		SmartDashboard.putNumber("Throttle", OI.getLiftStick().getThrottle());
		SmartDashboard.putBoolean("Left Forklift Open", isOpenLeft());
		SmartDashboard.putBoolean("Right Forklift Open", isOpenRight());
		SmartDashboard.putBoolean("Forklift Actuator Open", isActuatorOpen());
		SmartDashboard.putBoolean("Forced Left", isLeftForced());
		SmartDashboard.putBoolean("Forced Right", isRightForced());
		SmartDashboard.putNumber("Throttle Value", OI.getDriveStick().getThrottle());

	}

}
