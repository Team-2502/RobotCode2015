package com.team2502.robot2015;
/**
 * The RobotMap is a mapping from the ports sensors and actuators are wired into
 * to a variable name. This provides flexibility changing wiring, makes checking
 * the wiring easier and significantly reduces the number of magic numbers
 * floating around.
 */
public class RobotMap {
	// For example to map the left and right motors, you could define the
	// following variables to use with your drivetrain subsystem.
	// public static int leftMotor = 1;
	// public static int rightMotor = 2;

	// If you are using multiple modules, make sure to define both the port
	// number and the module. For example you with a rangefinder:
	// public static int rangefinderPort = 1;
	// public static int rangefinderModule = 1;

	public final static int LEFT_JOYSTICK = 0;
	public final static int RIGHT_JOYSTICK = 1;
	
	public final static int LEFT_FRONT_DRIVE = 1;
	public final static int LEFT_BACK_DRIVE = 5;
	public final static int LEFT_SLIDE_DRIVE = 6;
	
	public final static int RIGHT_FRONT_DRIVE = 2;
	public final static int RIGHT_BACK_DRIVE = 4;
	public final static int RIGHT_SLIDE_DRIVE = 3;
	
	public final static int SCORPION_WINCH = -1;
	
	
}
