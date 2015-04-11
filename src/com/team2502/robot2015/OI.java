package com.team2502.robot2015;

import com.team2502.robot2015.commands.forklift.BinHolder;
import com.team2502.robot2015.commands.forklift.SetLeftForced;
import com.team2502.robot2015.commands.forklift.SetRightForced;
import com.team2502.robot2015.commands.forklift.SpinIntake;
import com.team2502.robot2015.commands.forklift.ForkliftArmActuator;
import com.team2502.robot2015.commands.forklift.ToggleActiveIntakeArms;
import com.team2502.robot2015.commands.forklift.ToggleDirection;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.Button;
import edu.wpi.first.wpilibj.buttons.JoystickButton;

/**
 * This class is the glue that binds the controls on the physical operator
 * interface to the commands and command groups that allow control of the robot.
 */
public class OI {
	// // CREATING BUTTONS
	// One type of button is a joystick button which is any button on a
	// joystick.
	// You create one by telling it which joystick it's on and which button
	// number it is.
	// Joystick stick = new Joystick(port);
	// Button button = new JoystickButton(stick, buttonNumber);

	// There are a few additional built in buttons you can use. Additionally,
	// by subclassing Button you can create custom triggers and bind those to
	// commands the same as any other Button.

	// // TRIGGERING COMMANDS WITH BUTTONS
	// Once you have a button, it's trivial to bind it to a button in one of
	// three ways:

	// Start the command when the button is pressed and let it run the command
	// until it is finished as determined by it's isFinished method.
	// button.whenPressed(new ExampleCommand());

	// Run the command while the button is being held down and interrupt it once
	// the button is released.
	// button.whileHeld(new ExampleCommand());

	// Start the command when the button is released and let it run the command
	// until it is finished as determined by it's isFinished method.
	// button.whenReleased(new ExampleCommand());

	private static Joystick driveStick;
	private static Joystick liftStick;
	private static Button forkliftButton;
	//private static Button forkliftDirection;
	private static Button toggleCameraButton;
	private static Button forceLeft;
	private static Button forceRight;
	private static Button scorpion;
	private static Button activeIntakeButtonIn;
	private static Button activeIntakeButtonOut;
	private static Button openForkliftButton;
	private static Button binHolder;

	public OI() {
		driveStick = new Joystick(RobotMap.RIGHT_JOYSTICK);
		toggleCameraButton = new JoystickButton(driveStick, 2);
		// toggleCameraButton.whenPressed(new ToggleCamera());

		liftStick = new Joystick(RobotMap.LEFT_JOYSTICK);
		forkliftButton = new JoystickButton(liftStick, 1);
		forkliftButton.whenPressed(new ToggleActiveIntakeArms());

		activeIntakeButtonIn = new JoystickButton(driveStick, 1);
		activeIntakeButtonIn.whileHeld(new SpinIntake(true));

		activeIntakeButtonOut = new JoystickButton(driveStick, 2);
		activeIntakeButtonOut.whileHeld(new SpinIntake(false));
		
		//forkliftDirection = new JoystickButton(liftStick, 3);
		//forkliftDirection.whenPressed(new ToggleDirection());
		
		openForkliftButton = new JoystickButton(liftStick, 2);
		openForkliftButton.whenPressed(new ForkliftArmActuator());

		binHolder = new JoystickButton(driveStick, 3);
		binHolder.whenPressed(new BinHolder());
		
		forceLeft = new JoystickButton(liftStick, 4);
		forceLeft.whenPressed(new SetLeftForced(true));
		forceLeft.whenReleased(new SetLeftForced(false));

		forceRight = new JoystickButton(liftStick, 5);
		forceRight.whenPressed(new SetRightForced(true));
		forceRight.whenReleased(new SetRightForced(false));
	}

	public static Joystick getDriveStick() {
		return driveStick;
	}

	public static Joystick getLiftStick() {

		return liftStick;

	}
}
