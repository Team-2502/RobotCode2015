package com.team2502.robot2015.subsystems;

import com.team2502.robot2015.OI;
import com.team2502.robot2015.RobotMap;
import com.team2502.robot2015.commands.MoveForklift;
import com.team2502.robot2015.commands.SlideDrive;

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
	private final Solenoid leftArm = new Solenoid(RobotMap.FORKLIFT_SOLENOID_LEFT);
	private final Solenoid rightArm = new Solenoid(RobotMap.FORKLIFT_SOLENOID_RIGHT);
	private final AnalogInput sensor = new AnalogInput(RobotMap.FORKLIFT_SENSOR);

    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    	setDefaultCommand(new MoveForklift());
    }
    
    public void stop() {
    	forkliftWinch.set(0);
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
//    	updateForkliftDashboard();
    }
    
    public void moveLift() {
    	
    	forkliftWinch.set(OI.getLiftStick().getY());
    	
    }
    
    public double getHeight() {
    	
    	return 6 - ((getSensorVoltage() / 5) * 5 + 1);
//    	return Double.POSITIVE_INFINITY;
    }
    
    public double getSensorVoltage() {
    	
    	return sensor.getAverageVoltage();
    }
    
    public void updateForkliftDashboard() {
    	
    	SmartDashboard.putNumber("Forlift Voltage", getSensorVoltage());
    	SmartDashboard.putNumber("Forlift Height", getHeight());
    	
    	
    	
    }
    
    
}
