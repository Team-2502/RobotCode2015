package com.team2502.robot2015.commands.forklift;

import com.team2502.robot2015.OI;
import com.team2502.robot2015.Robot;
import com.team2502.robot2015.subsystems.Forklift;

import edu.wpi.first.wpilibj.Preferences;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class MoveForklift extends Command {

	private Forklift fl = Robot.forklift;
	private boolean settingTargetHeight = true;
	private double targetHeight;
	private Preferences prefs;
//	Preferences prefs;

	public MoveForklift() {
		// Use requires() here to declare subsystem dependencies
		// eg. requires(chassis);
		requires(Robot.forklift);
	}

	// Called just before this Command runs the first time
	protected void initialize() {
	}

	// Called repeatedly when this Command is scheduled to run
	protected void execute() {
		if (!OI.getLiftStick().getRawButton(2)) {
//			if (Math.abs(OI.getLiftStick().getY()) < 0.01) {
//				if (settingTargetHeight) {
//					targetHeight = fl.getHeight();
//					settingTargetHeight = false;
//				} else {
//					if (fl.getHeight() < targetHeight
//							&& targetHeight - fl.getHeight() < 12) {
//						fl.move(0 - (targetHeight - fl.getHeight()) / 12);
//					} else {
//						fl.stop();
//					}
//				}
//			} else {
//				settingTargetHeight = true;
				fl.moveLift();

				////////////////////////////////////////////////////////////////////
				////////////////////////////////////////////////////////////////////
				////////////////////////////////////////////////////////////////////
				////////////////////////////////////////////////////////////////////
				//Change the 5.0 Value if you can't get data from smartdashbaord
//				if (fl.getSensorVoltage() > prefs.getDouble("Forklift Voltage", 5.0) && fl.isEnable())
				SmartDashboard.putString("Stuff", "SPAM");
//					if (fl.getSensorVoltage() > .27 && fl.isEnable()){
				if (fl.getSensorVoltage() > SmartDashboard.getNumber("Volts", .26) && fl.isEnable()){
//					new OpenSpinIntake();
//					new ToggleActiveIntakeArms();
						fl.setActiveIntakeState(false);
					SmartDashboard.putString("Stuff", "HI");
					}
					SmartDashboard.putBoolean("StuffOpen", fl.isEnable());
					
				
//			}
		} else {
			fl.stop();
		}
	}

	// Make this return true when this Command no longer needs to run execute()
	protected boolean isFinished() {
		return false;
	}

	// Called once after isFinished returns true
	protected void end() {
	}

	// Called when another command which requires one or more of the same
	// subsystems is scheduled to run
	protected void interrupted() {
	}
}
