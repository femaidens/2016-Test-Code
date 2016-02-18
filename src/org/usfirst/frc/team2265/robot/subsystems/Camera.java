package org.usfirst.frc.team2265.robot.subsystems;
import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.Sendable;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.networktables.NetworkTable;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import java.util.ArrayList;

import org.usfirst.frc.team2265.robot.Robot;

/**
 *
 */
public class Camera extends Subsystem {
	NetworkTable table;
	double defaultVals = 0.0; 
	double[] defVal = {0.0};
	double goalArea = 22.65; 
	RobotDrive testDrive; 
	Joystick testLeft, testRight;
	public static CANTalon fl, fr, rl, rr;  

    // Put methods for controlling this subsystem
    // here. Call these from Commands.

    public Camera() {
    	table = NetworkTable.getTable("");
    	fl = new CANTalon(1);
    	fr = new CANTalon (13);
    	rl = new CANTalon(2);
    	rr = new CANTalon(12);
    	testDrive = new RobotDrive(fl,rl,fr,rr);
    	testLeft = new Joystick(0);
    	testRight = new Joystick(1);
    	
    }
	public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    	//key is a String--I think it's the name of the table
    }
    public void printOffCenter(){
    	double imgWidth= (table.getNumber("IMAGE_WIDTH", defaultVals) / 2); 
    	double cogX = table.getNumber("COG_X", defaultVals); 
    	double difference = cogX - imgWidth;
    	SmartDashboard.putNumber("Off Center By: ", difference );
    }
    public boolean centering() {
    //	double imgHeight = table.getNumber("IMAGE_HEIGHT", defaultVals) / 2;
    	double imgWidth= (table.getNumber("IMAGE_WIDTH", defaultVals) / 2); 
    	double cogX = table.getNumber("COG_X", defaultVals); 
    	double difference = cogX - imgWidth;
    	/*double largest= 0.0;
    	for (int i = 0 ; i < areas.length; i++){
    		if (areas[i] > areas[i-1] && areas.length >1)
    			largest = areas[i];
    		else if (areas.length == 1)
    			largest = areas[i]; 
    	}
    	SmartDashboard.putNumber("Area: ", largest);*/
    	SmartDashboard.putNumber("Off Center By: ", difference );
    	
    	if(difference >= -5.0 && difference <= 5.0) { 
    		testDrive(0.0, 0.0);  
    		return true;
    	}
    	if(difference <-5.0){
    		testDrive(0.2,0.05);
    	}
    	if(difference > 5.0)
    		testDrive(0.05, 0.2);
    	return false;
    }
	public int shake(){
    	double[] area = table.getNumberArray("FirstReport/area", defVal); 
    	double originalArea = area[0];
    	if (originalArea >= goalArea*.95 && originalArea <= goalArea*1.05){
    		return 0;
    	}
    	if(originalArea <= goalArea*0.95 && originalArea >= goalArea* 1.05) {
    		return 1; 
    	}
    	return -1; 
    }
    
    public void teleopCamera(){
    }
    
    public void testDrive() {
    	testDrive.tankDrive(-testLeft.getY(), -testRight.getY());
    }
    
    public void testDrive(double x, double y){
    	testDrive.tankDrive(x , y);
    }
}

    
  //in another method(s):
	//table.getValue("some parameter--to be determined", values);
	
