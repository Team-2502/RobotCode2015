package com.team2502.robot2015.subsystems;

import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class Forklift extends Subsystem {
    
    // Put methods for controlling this subsystem
    // here. Call these from Commands.
	
	

    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
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
    	
    }
    
    public void setRight(boolean open) {
    	
    }
    
    public void move(double speed) {
    	
    }
    
    public double getHeight() {
    	return Double.POSITIVE_INFINITY;
    }
}
