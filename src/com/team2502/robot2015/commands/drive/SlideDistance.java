package com.team2502.robot2015.commands.drive;

import java.util.ArrayList;

import com.team2502.robot2015.Robot;
import com.team2502.robot2015.subsystems.DriveTrain;
import com.team2502.robot2015.subsystems.DriveTrain.Motors;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class SlideDistance extends Command {

	private double distance;
	private double speed;
	private DriveTrain dt = Robot.driveTrain;
	private ArrayList<Double> lastEncoderValues = new ArrayList<Double>();
	private double movedDistance = 0;
	
	
    public SlideDistance(double distance, double speed) {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	requires(Robot.driveTrain);
    	this.speed = speed;
    	this.distance = distance;
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	
    		lastEncoderValues.add(dt.getEncoderValue(Motors.LEFT_SLIDE));
    		lastEncoderValues.add(dt.getEncoderValue(Motors.RIGHT_SLIDE));
    	
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	dt.moveSlide(speed);
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
    	ArrayList<Double> encoderValues = new ArrayList<Double>();
    	encoderValues.add(dt.getEncoderValue(Motors.LEFT_SLIDE));
		encoderValues.add(dt.getEncoderValue(Motors.RIGHT_SLIDE));
    	
    	double diff = encoderValues.get(0) - lastEncoderValues.get(0);
    	double diff1 = encoderValues.get(1) - lastEncoderValues.get(1);
    	
    	double smallestDiff = (Math.abs(diff) < Math.abs(diff1)) ? diff : diff1;
    	
    	movedDistance += smallestDiff;
    	lastEncoderValues = encoderValues;
    	
    	return ((DriveTrain.WHEEL_DIAMETER * Math.PI) * movedDistance) - distance > 0;
    }

    // Called once after isFinished returns true
    protected void end() {
    	dt.stopSlide();
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	end();
    }
}
