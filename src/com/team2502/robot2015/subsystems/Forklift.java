package com.team2502.robot2015.subsystems;

import com.team2502.robot2015.RobotMap;

import edu.wpi.first.wpilibj.AnalogInput;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class Forklift extends Subsystem {
	
	// Put methods for controlling this subsystem
    // here. Call these from Commands.
	
	private final Talon forkliftWinch = new Talon(RobotMap.FORKLIFT_WINCH);
	private final Solenoid leftArm = new Solenoid(RobotMap.FORKLIFT_SOLENOID_LEFT);
	private final Solenoid rightArm = new Solenoid(RobotMap.FORKLIFT_SOLENOID_RIGHT);
	private final AnalogInput sensor = new AnalogInput(RobotMap.FORKLIFT_SENSOR);

    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
    
    public void stop() {
    	forkliftWinch.stopMotor();
    }
    
    public void close() {
    	setLeft(false);
    	setRight(false);
    }
    
    public void open() {
    	setLeft(true);
    	setLeft(true);
    }
    
    public void setLeft(boolean open) {
    	leftArm.set(open);
    }
    
    public void setRight(boolean open) {
    	rightArm.set(open);
    }
    
    public void move(double speed) {
    	forkliftWinch.set(speed);
    }
    
    public double getHeight() {
    	return Double.POSITIVE_INFINITY;
    }
}
