package com.team2502.robot2015.commands.autonomous;

import com.team2502.robot2015.commands.drive.MoveTime;
import com.team2502.robot2015.commands.drive.TurnDegrees;
import com.team2502.robot2015.commands.drive.TurnTime;

import edu.wpi.first.wpilibj.Preferences;
import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class Move extends CommandGroup {
    
    public  Move() {
        // Add Commands here:
        // e.g. addSequential(new Command1());
        //      addSequential(new Command2());
        // these will run in order.

        // To run multiple commands at the same time,
        // use addParallel()
        // e.g. addParallel(new Command1());
        //      addSequential(new Command2());
        // Command1 and Command2 will run in parallel.

        // A command group will require all of the subsystems that each member
        // would require.
        // e.g. if Command1 requires chassis, and Command2 requires arm,
        // a CommandGroup containing them would require both the chassis and the
        // arm.
    	addSequential(new MoveTime(Preferences.getInstance().getDouble("MoveTime", 2.5), -1));
//    	addSequential(new TurnDegrees(Preferences.getInstance().getDouble("TurnDegrees", -45), 1));
    	addSequential(new TurnTime(Preferences.getInstance().getDouble("TurnTime", 2.65), -1));
    }
}
