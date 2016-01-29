package org.usfirst.frc.team2265.robot.commands;

import org.usfirst.frc.team2265.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class Shoot extends Command {
	double v = 0.5;
	
    public Shoot(double velocity) {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	requires (Robot.cannon);
    	v = velocity;
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	//turn off compressor
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	if (Robot.cannon.isHigh)
    		Robot.cannon.spinWheels(v*1.25);
    	if (Robot.cannon.isLow)
    		Robot.cannon.spinWheels(v*0.75);
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return false;
    }

    // Called once after isFinished returns true
    protected void end() {
    	Robot.cannon.stop();
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}