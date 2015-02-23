package com.team2502.robot2015.commands.autonomous;

import com.team2502.robot2015.Robot;
import com.team2502.robot2015.commands.drive.MoveDistance;
import com.team2502.robot2015.commands.drive.MoveTime;
import com.team2502.robot2015.commands.scorpion.MoveTailTime;
import com.team2502.robot2015.subsystems.DriveTrain;
import com.team2502.robot2015.subsystems.ScorpionTail;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class RecycleBinPickup extends CommandGroup {
    
	private DriveTrain dt = Robot.driveTrain;
	private ScorpionTail st = Robot.scorpion;
	
	
    public  RecycleBinPickup() {
    	requires(Robot.driveTrain);
    	requires(Robot.scorpion);
    	
    	addSequential(new MoveTime(1, 1));
    	addSequential(new MoveDistance(-72, 1));
    	addSequential(new MoveTailTime(1, 4));
    	addSequential(new MoveDistance(24, 1));
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
    }
}
