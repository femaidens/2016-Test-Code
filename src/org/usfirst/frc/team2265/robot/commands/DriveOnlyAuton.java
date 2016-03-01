package org.usfirst.frc.team2265.robot.commands;

import org.usfirst.frc.team2265.robot.Robot;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class DriveOnlyAuton extends CommandGroup {
    
    public  DriveOnlyAuton() {
    	//requires(Robot.driveTrain);
    	//Timer timer = new Timer();
    	//timer.reset();
    	//timer.start();
    //	while (timer.get() < 0.5) {
    		addSequential(new Jerk()); 
    		addSequential(new AutonDrive (0.3,0.3, 3.0));
    //	}
    	//addSequential(new Drive(0.5, 0.5));
       // addSequential(new Drive(0.0,0.0));
    	//timer.stop();
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
