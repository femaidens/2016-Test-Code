package org.usfirst.frc.team2265.robot;
/**
 * The RobotMap is a mapping from the ports sensors and actuators are wired into
 * to a variable name. This provides flexibility changing wiring, makes checking
 * the wiring easier and significantly reduces the number of magic numbers
 * floating around.
 */
public class RobotMap {
   
	//Joystick Ports
			public static int leftJoyPort = 0;
			public static int rightJoyPort = 1;
			public static int atkJoyPort = 2;
				
			//Talon Ports
			public static int cannonFLPort = 5;
			public static int cannonFRPort = 6;
			public static int rollerPort = 4; 
			public static int acquirer = 1;
			public static int camTalonPort = 11;
			
			public static int rearLeftPort = 7;
			public static int frontLeftPort = 8;
			
			public static int rearRightPort = 9;
			public static int frontRightPort = 10;
			
			//Solenoid Ports
			public static int cannonSolPort1 = 2;
			public static int cannonSolPort2 = 3;
			public static int cannonSolPort3 = 6;
			public static int cannonSolPort4 = 7; 
			
			//add other sol ports
			public static int transPort1= 0; 
			public static int transPort2= 1; 
			public static int transPort3 = 4; 
			public static int transPort4 = 5; 
}
