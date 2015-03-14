package com.team2502.robot2015.commands.autonomous;

import com.team2502.robot2015.commands.drive.MoveTime;
import com.team2502.robot2015.commands.drive.SlideTime;
import com.team2502.robot2015.commands.drive.TurnDegrees;
import com.team2502.robot2015.commands.drive.TurnTime;
import com.team2502.robot2015.commands.forklift.MoveLiftTime;
import com.team2502.robot2015.commands.forklift.MoveUp;
import com.team2502.robot2015.commands.forklift.SetLeftForced;
import com.team2502.robot2015.commands.forklift.SetRightForced;
import com.team2502.robot2015.commands.forklift.ToggleForklift;

import edu.wpi.first.wpilibj.Preferences;
import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.command.WaitCommand;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class Pickup extends CommandGroup {
    
    public  Pickup() {
        // Add Commands here:
        // e.g. addSequential(new Command1());
        //      addSequential(new Command2());
        // these will run in order.
    	
//    	addParallel(new SetLeftForced(true));x
//    	addSequential(new SetRightForced(true));
//    	addSequential(new ToggleForklift());
//    	addSequential(new WaitCommand(.5));
//    	addSequential(new MoveLiftTime(2, -1));
////    	addSequential(new TurnDegrees(180, 1));
//    	addSequential(new TurnTime(3.5, -0.9));
//    	addSequential(new MoveLiftTime(2, 1));
//    	addSequential(new MoveTime(2, 1));
//    	addSequential(new MoveLiftTime(.3, 1));
//    	addSequential(new WaitCommand(.25));
//    	addParallel(new SetLeftForced(false));
//    	addSequential(new SetRightForced(false));
    	
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
    	
    	
//    	addSequential(new ToggleForklift());
//    	addSequential(new WaitCommand(0.25));
//    	addSequential(new MoveLiftTime(2, -1));
//    	addSequential(new SlideTime(1, -0.7));
//    	addSequential(new MoveTime(1, 0.5));
//    	addSequential(new MoveLiftTime(2, 1));
//    	addSequential(new ToggleForklift());
//    	addSequential(new MoveTime(1, -0.5));
//    	addParallel(new MoveLiftTime(2, -1));
//    	addSequential(new SlideTime(1, 0.7));
    	
    	addSequential(new ToggleForklift());
    	addSequential(new WaitCommand(0.5));
    	addSequential(new MoveLiftTime(1.5, -1));
    	addSequential(new MoveTime(Preferences.getInstance().getDouble("MoveTime", 2.5), -.8));
    	addSequential(new TurnTime(Preferences.getInstance().getDouble("TurnTime", 2.65), -1));
    	addSequential(new MoveLiftTime(1.5, 1));
    	addSequential(new ToggleForklift());
    }
}
