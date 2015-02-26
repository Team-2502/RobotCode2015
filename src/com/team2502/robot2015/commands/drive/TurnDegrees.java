package com.team2502.robot2015.commands.drive;

import java.util.ArrayList;

import com.team2502.robot2015.Robot;
import com.team2502.robot2015.subsystems.DriveTrain;
import com.team2502.robot2015.subsystems.DriveTrain.Motors;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class TurnDegrees extends Command {

	private double degrees;
	private double speed;
	private double distance;
	private double left;
	private double right;
	private DriveTrain dt = Robot.driveTrain;
	private ArrayList<Double> lastEncoderValues = new ArrayList<Double>();
	private double movedDistance = 0;
//	private double startTime;
	
    public TurnDegrees(double degrees, double speed) {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	requires(Robot.driveTrain);
    	this.speed = speed;
    	this.degrees = degrees;
    	this.distance = (degrees / 90) * 30 / (DriveTrain.WHEEL_DIAMETER * Math.PI) * 360 * 4;
    	left = (degrees > 0) ? 1 : -1;
    	right = -left;
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	for (Motors m : Motors.values()) {
    		lastEncoderValues.add(dt.getEncoderValue(m));
    	}
//    	startTime = System.currentTimeMillis();
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	dt.moveMainDrive(left, right);
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
    	ArrayList<Double> encoderValues = new ArrayList<Double>();
    	for (Motors m : Motors.values()) {
    		encoderValues.add(dt.getEncoderValue(m));
    	}
    	
    	double diff = encoderValues.get(0) - lastEncoderValues.get(0);
    	double smallestDiff = encoderValues.get(0) - lastEncoderValues.get(0);
    	for (int i = 0; i < encoderValues.size(); i++) {
    		if (i != 1 && i != 3) {
    			diff = encoderValues.get(i) - lastEncoderValues.get(i);
    			if (Math.abs(diff) < Math.abs(smallestDiff)) smallestDiff = diff;
    		}
    	}
    	movedDistance += smallestDiff;
    	lastEncoderValues = encoderValues;
    	
    	return movedDistance - distance > 0;
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
