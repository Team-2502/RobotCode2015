package com.team2502.robot2015.subsystems;

import com.team2502.robot2015.RobotMap;

import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class ScorpionTail extends Subsystem {
    
	private final Talon winch = new Talon(RobotMap.SCORPION_WINCH);
    // Put methods for controlling this subsystem
    // here. Call these from Commands.

    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    
    
    }
    
    public void move(double speed) {
    	
    	winch.set(speed);
    }
    
    public void stop() {
    	
    	winch.stopMotor();
    }
    
    public double getEncoderValue() {
    	
    	return winch.getPosition();
    }
    
    
}

