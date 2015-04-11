package com.team2502.robot2015.commands.autonomous;

import com.team2502.robot2015.commands.drive.MoveTime;
import com.team2502.robot2015.commands.drive.TurnTime;
import com.team2502.robot2015.commands.forklift.MoveLiftTime;
import com.team2502.robot2015.commands.forklift.ToggleActiveIntakeArms;

import edu.wpi.first.wpilibj.Preferences;
import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.command.WaitCommand;

/**
 *
 */
public class Pickup extends CommandGroup {

	public Pickup() {
		// Add Commands here:
		// e.g. addSequential(new Command1());
		// addSequential(new Command2());
		// these will run in order.

		// addParallel(new SetLeftForced(true));x
		// addSequential(new SetRightForced(true));
		// addSequential(new ToggleForklift());
		// addSequential(new WaitCommand(.5));
		// addSequential(new MoveLiftTime(2, -1));
		// // addSequential(new TurnDegrees(180, 1));
		// addSequential(new TurnTime(3.5, -0.9));
		// addSequential(new MoveLiftTime(2, 1));
		// addSequential(new MoveTime(2, 1));
		// addSequential(new MoveLiftTime(.3, 1));
		// addSequential(new WaitCommand(.25));
		// addParallel(new SetLeftForced(false));
		// addSequential(new SetRightForced(false));

		// To run multiple commands at the same time,
		// use addParallel()
		// e.g. addParallel(new Command1());
		// addSequential(new Command2());
		// Command1 and Command2 will run in parallel.

		// A command group will require all of the subsystems that each member
		// would require.
		// e.g. if Command1 requires chassis, and Command2 requires arm,
		// a CommandGroup containing them would require both the chassis and the
		// arm.

		// addSequential(new ToggleForklift());
		// addSequential(new WaitCommand(0.25));
		// addSequential(new MoveLiftTime(2, -1));
		// addSequential(new SlideTime(1, -0.7));
		// addSequential(new MoveTime(1, 0.5));
		// addSequential(new MoveLiftTime(2, 1));
		// addSequential(new ToggleForklift());
		// addSequential(new MoveTime(1, -0.5));
		// addParallel(new MoveLiftTime(2, -1));
		// addSequential(new SlideTime(1, 0.7));

		// Toggles the forklift from its previous state -> Will be deprecated
		// soon probably
		addSequential(new ToggleActiveIntakeArms());
		// Waits for 0.5s
		addSequential(new WaitCommand(0.5));
		// Moves the forklift for 1.5s with a speed of -1 (- being reverse
		// direction)
		addSequential(new MoveLiftTime(1.5, -1));
		// Moves the robot for a certain period of time at a certain speed
		// Preferences.getInstance() -> Gets the instance for the class |
		// .getDouble("MoveTime", 2.5) -> Gets a double with id of "MoveTime"
		// and sets the time to 2.5 | , -.8 sets the speed of movement to -.8
		// (-being reverse direction)
		addSequential(new MoveTime(Preferences.getInstance().getDouble(
				"MoveTime", 2.5), -.8));
		// Moves the robot for a certain period of time at a certain speed
		// Preferences.getInstance() -> Gets the instance for the class |
		// .getDouble("MoveTime", 2.5) -> Gets a double with id of "TurnTime"
		// and sets the time to 2.65 | , -1 sets the speed of movement to -1
		// (- being reverse direction)
		addSequential(new TurnTime(Preferences.getInstance().getDouble(
				"TurnTime", 2.65), -1));
		// Moves the forklift for 1.5s with a speed of 1
		addSequential(new MoveLiftTime(1.5, 1));
		// Toggles the forklift from its previous state -> Will be deprecated
		// soon probably
		addSequential(new ToggleActiveIntakeArms());
	}
}
