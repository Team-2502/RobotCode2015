package com.team2502.robot2015.commands.autonomous;

import com.team2502.robot2015.commands.forklift.MoveLiftTime;
import com.team2502.robot2015.commands.forklift.MoveUp;
import com.team2502.robot2015.commands.forklift.SetLeftForced;
import com.team2502.robot2015.commands.forklift.SetRightForced;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class Pickup extends CommandGroup {
    
    public  Pickup() {
        // Add Commands here:
        // e.g. addSequential(new Command1());
        //      addSequential(new Command2());
        // these will run in order.
    	
    	addParallel(new SetLeftForced(true));
    	addParallel(new SetRightForced(true));
    	addSequential(new MoveLiftTime(.5, 1));
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
    }
}
