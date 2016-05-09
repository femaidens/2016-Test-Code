package org.usfirst.frc.team2265.robot.subsystems;

import org.usfirst.frc.team2265.robot.RobotMap;
import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Subsystem;
import org.usfirst.frc.team2265.robot.subsystems.Piston;

/**
 *
 */
public class Cannon extends Subsystem {

    public CANTalon cannonFL, cannonFR, roller, camTalon, rollerPos;
    public Piston cannonPiston;
    public boolean isShooting = false;

    private double shootTicks, acquireTicks, gateTicks, camSecs;
    //public DigitalInput limitSwitch; 
    /*
     * shootTicks: ticks to go from acquire to shoot
     * gateTicks; ticks to go from acquire to gate
     * acqTicks: ticks it takes to go from shoot to acquire (=shootSecs)
     * camSecs: time it takes to turn the cam once
     * */
    
    private Timer timer; 
    public boolean isHigh, isLow, isShoot, isAcq, isGate;
    private double Kp = 0.05, Ki = 0.01, Kd=0.0; 
    private double Kpwm = 1/585;
    
    private double leftVError, leftVErrorSum, leftVErrorChange, rightVError, rightVErrorSum, rightVErrorChange, leftOutput, rightOutput; 

    public Cannon() {
        cannonFL = new CANTalon(RobotMap.cannonFLPort);
        cannonFR = new CANTalon(RobotMap.cannonFRPort);
        roller = new CANTalon(RobotMap.rollerPort);
        rollerPos = new CANTalon(RobotMap.acquirer);
        camTalon = new CANTalon(RobotMap.camTalonPort);
        //limitSwitch = new DigitalInput(RobotMap.)
        
        camSecs = .78;
        shootTicks = 1500;
        acquireTicks = 1800;
        gateTicks = 2100; 
        
        isAcq= true;
        isLow = false;
        isHigh = true; 
        
        cannonPiston = new Piston(RobotMap.cannonSolPort1, RobotMap.cannonSolPort2, RobotMap.cannonSolPort3, RobotMap.cannonSolPort4);
        
        camTalon.setFeedbackDevice(CANTalon.FeedbackDevice.QuadEncoder);
        rollerPos.setFeedbackDevice(CANTalon.FeedbackDevice.QuadEncoder);
        
        System.out.println("Cannon created.");
    }

    // Put methods for controlling this subsystem
    // here. Call these from Commands.

    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }

    public void spinWheelsIntake(double velocity) {
        //positive velocity is to shoot
        //negative velocity is to acquire
    	cannonFL.set(-velocity);
        cannonFR.set(velocity);
    }
    
    public void spinWheelsShoot(double velocity) {
        //positive velocity is to shoot
        //negative velocity is to acquire
    	
    	cannonFL.set(-0.70);
        cannonFR.set(0.70);
    }

    public void spinWheels(double leftVelocity, double rightVelocity) {
        //positive velocity is to shoot
        //negative velocity is to acquire
        cannonFL.set(leftVelocity);
        cannonFR.set(-rightVelocity);
    }

    public void spinRoller(double velocity) {
        roller.set(-velocity);
    }

    public void stop() {
        cannonFL.set(0.0);
        cannonFR.set(0.0);
        roller.set(0.0);
        isShooting = false;
    }
    
    public void turnCam() { 
        //while (timer.get() < camSecs) 
        camTalon.set(-.87);
        Timer.delay(camSecs);
        camTalon.set(.87);
        Timer.delay(camSecs-.076);
        camTalon.set(0.0); 
        isShooting = true;
        /*if(limitSwitch.get() == 0){
        	camTalon.set(0.57);
        }
        else{
        	camTalon.set(0.0);
        } */
    }
    
    public void reverseCam() {
    	camTalon.set(-.83); 
    	Timer.delay(camSecs);
    	camTalon.set(0.0);
    	isShooting= false; 
    }
    
    public void liftCannon() {
    	cannonPiston.extend();
    	isHigh = true; 
    	isLow = false; 
    	System.out.println("Cannon lifted");
    }
    
    public void lowerCannon() {
    	cannonPiston.retract();
    	isLow = true;
    	isHigh = false; 
    	System.out.println("Cannon lowered");
    }

    public void rollerShootPos() {
    	rollerPos.set(RobotMap.up); 
    	Timer.delay(1.0);
    	rollerPos.set(0.0);
    }
    
    public void rollerAcquirePos() {
    	rollerPos.set(RobotMap.down);
    	Timer.delay(0.5);
    	rollerPos.set(0.0);
    }
    
 
}