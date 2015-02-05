package com.team2502.robot2015.commands.drive;

import java.util.ArrayList;
import java.util.Arrays;

import com.team2502.robot2015.Robot;
import com.team2502.robot2015.subsystems.DriveTrain;
import com.team2502.robot2015.subsystems.DriveTrain.Motors;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class MoveDistance extends Command {

	private double distance;
	private double speed;
	private DriveTrain dt = Robot.driveTrain;
	private ArrayList<Double> lastEncoderValues = new ArrayList<Double>();
	private double movedDistance = 0;
	
    public MoveDistance(double distance, double speed) {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	this.speed = speed;
    	this.distance = distance;
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	
    	for (Motors m : Motors.values()) {
    		lastEncoderValues.add(dt.getEncoderValue(m));
    	}
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    
    	dt.moveMainDrive(speed);
    	
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
       
    	ArrayList<Double> encoderValues = new ArrayList<Double>();
    	for (Motors m : Motors.values()) {
    		encoderValues.add(dt.getEncoderValue(m));
    	}
    	
    	double diff = encoderValues.get(0) - lastEncoderValues.get(0);
    	double smallestDiff = encoderValues.get(0) - lastEncoderValues.get(0);
    	for (int i = 1; i < encoderValues.size(); i++) {
    		
    		diff = encoderValues.get(i) - lastEncoderValues.get(i);
    		if (Math.abs(diff) < Math.abs(smallestDiff)) smallestDiff = diff;
    	}
    	movedDistance += smallestDiff;
    	lastEncoderValues = encoderValues;
    	
    	return ((DriveTrain.WHEEL_DIAMETER * Math.PI) * movedDistance) - distance > 0;
        
    }

    // Called once after isFinished returns true
    protected void end() {
    	dt.stopAll();
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	end();
    }
}