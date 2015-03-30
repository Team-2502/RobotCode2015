package com.team2502.robot2015;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;

import com.team2502.robot2015.commands.autonomous.Move;
import com.team2502.robot2015.commands.autonomous.Pickup;
import com.team2502.robot2015.subsystems.DriveTrain;
import com.team2502.robot2015.subsystems.Forklift;
import com.team2502.robot2015.subsystems.ScorpionTail;

import edu.wpi.first.wpilibj.CameraServer;
import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.PowerDistributionPanel;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the IterativeRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the manifest file in the resource
 * directory.
 */
public class Robot extends IterativeRobot {

	// Modes for the Autonomous period
	// NOTHING : Do nothing for this part of autonomous
	// MOVE : Move for a certain period of time around the field
	// PICKUP_MOVE : Pickup an 'Item' and move for a certain period of time
	// around the field
	enum AutoModes {
		NOTHING, MOVE, PICKUP_MOVE;
	}

	// public static final ExampleSubsystem exampleSubsystem = new
	// ExampleSubsystem();
	public static final DriveTrain driveTrain = DriveTrain.getInstance();
	public static OI oi;
	public static final Forklift forklift = new Forklift();
	public static final ScorpionTail scorpion = new ScorpionTail();
	public static final PowerDistributionPanel PDP = new PowerDistributionPanel();

	SendableChooser autoChooser = new SendableChooser();
	Command autonomousCommand;

	/**
	 * This function is run when the robot is first started up and should be
	 * used for any initialization code.
	 */
	public void robotInit() {
		oi = new OI();
//		CameraServer.getInstance().startAutomaticCapture("cam0");
		// instantiate the command used for the autonomous period
		// autonomousCommand = new ExampleCommand();
		// autonomousCommand = new RecycleBinPickup();
		autoChooser.addObject("Pickup and Move Backwards",
				AutoModes.PICKUP_MOVE);
		autoChooser.addObject("Move Backwards", AutoModes.MOVE);
		autoChooser.addDefault("Do Nothing", AutoModes.NOTHING);
		SmartDashboard.putData("Auto Mode", autoChooser);
	}

	public void disabledPeriodic() {
		// Gets the next instance in the Scheduler (should be the disabled
		// command)
		Scheduler.getInstance().run();
	}

	public void autonomousInit() {
		switch ((AutoModes) autoChooser.getSelected()) {
		case PICKUP_MOVE:
			autonomousCommand = new Pickup();
			break;
		case MOVE:
			autonomousCommand = new Move();
			break;
		case NOTHING:
			autonomousCommand = null;
			break;
		}
		// schedule the autonomous command (example)
		if (autonomousCommand != null)
			autonomousCommand.start();
	}

	/**
	 * This function is called periodically during autonomous
	 */
	public void autonomousPeriodic() {
		// Gets the next instance in the Scheduler (should be the next command
		// in autonomous)
		Scheduler.getInstance().run();
	}

	public void teleopInit() {
		// This makes sure that the autonomous stops running when
		// teleop starts running. If you want the autonomous to
		// continue until interrupted by another command, remove
		// this line or comment it out.
		if (autonomousCommand != null)
			autonomousCommand.cancel();
		try {
			PrintWriter writer = new PrintWriter("currentLog.log", "UTF-8");
		} catch (FileNotFoundException | UnsupportedEncodingException e) {
			e.printStackTrace();
		}
	}

	/**
	 * This function is called when the disabled button is hit. You can use it
	 * to reset subsystems before shutting down.
	 */
	public void disabledInit() {
		// Nothing is here since nothing needs to be reset.
	}

	/**
	 * This function is called periodically during operator control
	 */
	public void teleopPeriodic() {
		Scheduler.getInstance().run();
		forklift.updateForkliftDashboard();
		driveTrain.updateDriveDashboard();
		// writer.println(System.currentTimeMillis() + " FrontRight: " +
		// PDP.getCurrent(12));
		// writer.println(System.currentTimeMillis() + " FrontLeft: " +
		// PDP.getCurrent(14));
		SmartDashboard.putData("PDP", PDP);
	}

	/**
	 * This function is called periodically during test mode
	 */
	// Makes sure everything is running properly
	public void testPeriodic() {
		LiveWindow.run();
	}
}
