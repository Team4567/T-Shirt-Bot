/*----v------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/
//Tells the code where it is, it gets lost easily
package org.usfirst.frc.team4567.robot;

// Anything you want to add to your robot that needs to be controlled MUST be imported!
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.Victor;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.Spark;
import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.VictorSP;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.cscore.UsbCamera;
import edu.wpi.first.cameraserver.CameraServer;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.GenericHID.Hand;
import edu.wpi.first.wpilibj.Compressor;
/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the IterativeRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the build.properties file in the
 * project.
 */
public class Robot extends TimedRobot {
	
	// Defines all motor controllers, pistons, etc.
	
	//Wheels
	/* TalonSRX uses CAN, not PWM. You need to access the web interface when connected to the robot to set the port number (Left=2, Right=1)
	WPI_TalonSRX LeftC= new WPI_TalonSRX(2);
	WPI_TalonSRX RightC= new WPI_TalonSRX(1);
	*/
	// These PWM Motor controllers just use their port number on the RIO
	Talon LeftP= new Talon(Constants.LeftP);
	Talon RightP= new Talon(Constants.RightP);
	// Since differential drive only accepts one parameter per side, we have to combine the left and right motors together in groups
	// DifferentialDrive simplifies joystick control code by using the class FRC gives us.
	DifferentialDrive roboDrive = new DifferentialDrive(LeftP,RightP);
	//Control Devices, set to the order number on the Driver Station. Joysticks are the ones on the xbox controller, left=0 right=1
	XboxController XbC = new XboxController(0);
	Joystick leftStick = new Joystick(0);
	//Other motor controllers, PWM
	Victor horn = new Victor(Constants.horn);
	Talon shootR= new Talon(Constants.RightC);
	Victor shootL= new Victor(Constants.LeftC);

	// Piston- require 2 ports on the PCM. One is for up, the other down. A piston must be put in the code for power to be sent to the compressor.
	DoubleSolenoid e = new DoubleSolenoid(5,0,1);
	Compressor c = new Compressor(5);
	
	//Misc Variables
	boolean kid=false;
	boolean lighter=false;
	double pulseVal=0;
	enum lightMode{up,down};
	lightMode l=lightMode.up;
	
    

	
	/**
	 * This function is run when the robot is first started up and should be
	 * used for any initialization code.
	 */
	@Override
	public void robotInit() {
		// Vision Testing Code
		// Setup Camera Parameters
		
		c.setClosedLoopControl(true);
		c.start();
	}
	


	/**
	 * This autonomous (along with the chooser code above) shows how to select
	 * between different autonomous modes using the dashboard. The sendable
	 * chooser code works with the Java SmartDashboard. If you prefer the
	 * LabVIEW Dashboard, remove all of the chooser code and uncomment the
	 * getString line to get the auto name from the text box below the Gyro
	 *
	 * <p>You can add additional auto modes by adding additional comparisons to
	 * the switch structure below with additional strings. If using the
	 * SendableChooser make sure to add them to the chooser code above as well.
	 */
	
	@Override
	public void autonomousInit() {
	
		
	}

	/**
	 * This function is called periodically during autonomous.
	 */
	@Override
	public void autonomousPeriodic() {
		
		
	
	}
	
		
	
		
	

	/**
	 * This function is called periodically during operator control.
	 * The main controls
	 */
	@Override
	public void teleopPeriodic() {
		
		//-------------------------------------------------
		//Joystick control
		// Rev was in order to have either side of the robot be controlled as the front if we were retrieving or shooting
		if (XbC.getStartButtonPressed()) {
			kid = !kid;
			System.out.println("Kid mode is " + kid);
		}

		if (kid) {
		    roboDrive.arcadeDrive(-0.5*leftStick.getY(),0.5*leftStick.getX());
		} else {
		    roboDrive.arcadeDrive(-leftStick.getY(),leftStick.getX());
		}
		//Functions
		// Cannon Elevation Piston
		if(!kid){
			if(XbC.getAButton()){
				//DOWN
				e.set(DoubleSolenoid.Value.kReverse);
			} else if(XbC.getYButton()){
				//UP
				e.set(DoubleSolenoid.Value.kForward);
			} else {
				e.set(DoubleSolenoid.Value.kOff);
			}
			// Shooting Mechanism
			if(XbC.getTriggerAxis(Hand.kRight)>=0.5) {
				//SHOOT
				shootR.set(1);
			} else {
				shootR.set(0);
			}
			if(XbC.getTriggerAxis(Hand.kLeft)>=0.5) {
				//SHOOT
				shootL.set(1);
			} else {
				shootL.set(0);
			}
			// Best Part of the Robot
			
			if(XbC.getBButtonPressed()){
				lighter=!lighter;
			}
		}
		if(XbC.getXButton()) {
			//HORN
			if(kid){
				horn.set(0.5);
			}else{
				horn.set(1);
			}
		} else {
			horn.set(0);
		}
	}
}
