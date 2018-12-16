/*----------------------------------------------------------------------------*/
/* Copyright (c) 2016-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package org.usfirst.frc.team4567.robot;
import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.hal.FRCNetComm.tResourceType;
import edu.wpi.first.wpilibj.hal.HAL;

/**
 * Handle input from Xbox 360 or Xbox One controllers connected to the Driver Station.
 *
 * <p>This class handles Xbox input that comes from the Driver Station. Each time a value is
 * requested the most recent value is returned. There is a single class instance for each controller
 * and the mapping of ports to hardware buttons depends on the code in the Driver Station.
 */
//NOTICE: This code is a straight up copy and edit of class name and the values to match DS from XboxController, after learning classes in processing most makes sense to me now.
// Wii remote is a specific type of GenericHID, so use it's fuctions but with our numbers
// GenericHID Doesn't have any fancy code-based fuctions, just makes the getX(), getBumper(), getRawAxis(), etc accessable from DS
public class Wiimote extends GenericHID {

	//Looked a bit into Enum, bit confused but get its uses, can be used to refrence multiple objects in a group, or in this case a group of temrs to a variable
	//https://www.geeksforgeeks.org/enum-in-java/
  // Represents each button to its "port" on the xbox controller according to DS
	private enum Button {
    kOne(1),
    kTwo(2),
    kA(3),
    kB(4),
    kBack(6),
    kStart(5);

//     @SuppressWarnings("MemberName")
    private int value;

    Button(int value) {
      this.value = value;
    }
  }

  /**
   * Construct an instance of a joystick. The joystick index is the USB port on the drivers
   * station.
   *
   * @param port The port on the Driver Station that the joystick is plugged into.
   */
	// What is super?
  public Wiimote(final int port) {
    super(port);

    HAL.report(tResourceType.kResourceType_Joystick, port);
  }

 // I renamed the doubles and some booleans to make sense for a wii controller. X and Y in context to holding wii remote sideways with D-pad on left
 // DS Detected the D Pad as 2 axis, X and Y, that go from 0 to 1 or -1 when pressed 
  public double getPadX(){
	  return getRawAxis(0);
  }
  // Ignore these getY getX, they were some things called (Abstracts) according to the errors and my friends at FRC Discord, I don't know about abstracts
  // but appearantly these 2 were of the GenericHID Class. 
  public double getY(Hand hand){
    return 5;
  }
  public double getX(Hand hand){
    return 5;
  }
  public double getPadY(){
	  return getRawAxis(1);
  }
  // Mario-Kart Turning Axis
  public double getTurn(){
	  return getRawAxis(3);
  }
  // I CANNOT CONFIRM IF THIS PORT IS ACCURATE. The wiimote wouldn't connect when I made this one (12/11/18), assuming 2 based on others
  public double getTilt(){
	  return getRawAxis(2);
  }
  // All these comments are auto generated \/  \/
  /**
   * Read the value of the A button on the controller.
   *
   * @return The state of the button.
   */
  // All of these I understand how it works after working with the processing classes, assuming it needs public so the xboxcontroller/wiimote can be used in any file in the project.
  public boolean getAButton() {
    return getRawButton(Button.kA.value);
  }

  /**
   * Whether the A button was pressed since the last check.
   *
   * @return Whether the button was pressed since the last check.
   */
  public boolean getAButtonPressed() {
    return getRawButtonPressed(Button.kA.value);
  }

  /**
   * Whether the A button was released since the last check.
   *
   * @return Whether the button was released since the last check.
   */
  public boolean getAButtonReleased() {
    return getRawButtonReleased(Button.kA.value);
  }

  /**
   * Read the value of the B button on the controller.
   *
   * @return The state of the button.
   */
  public boolean getBButton() {
    return getRawButton(Button.kB.value);
  }

  /**
   * Whether the B button was pressed since the last check.
   *
   * @return Whether the button was pressed since the last check.
   */
  public boolean getBButtonPressed() {
    return getRawButtonPressed(Button.kB.value);
  }

  /**
   * Whether the B button was released since the last check.
   *
   * @return Whether the button was released since the last check.
   */
  public boolean getBButtonReleased() {
    return getRawButtonReleased(Button.kB.value);
  }

  /**
   * Read the value of the X button on the controller.
   *
   * @return The state of the button.
   */
  public boolean getOneButton() {
    return getRawButton(Button.kOne.value);
  }

  /**
   * Whether the X button was pressed since the last check.
   *
   * @return Whether the button was pressed since the last check.
   */
  public boolean getOneButtonPressed() {
    return getRawButtonPressed(Button.kOne.value);
  }

  /**
   * Whether the X button was released since the last check.
   *
   * @return Whether the button was released since the last check.
   */
  public boolean getOneButtonReleased() {
    return getRawButtonReleased(Button.kOne.value);
  }

  /**
   * Read the value of the Y button on the controller.
   *
   * @return The state of the button.
   */
  public boolean getTwoButton() {
    return getRawButton(Button.kTwo.value);
  }

  /**
   * Whether the Y button was pressed since the last check.
   *
   * @return Whether the button was pressed since the last check.
   */
  public boolean getTwoButtonPressed() {
    return getRawButtonPressed(Button.kTwo.value);
  }

  /**
   * Whether the Y button was released since the last check.
   *
   * @return Whether the button was released since the last check.
   */
  public boolean getTwoButtonReleased() {
    return getRawButtonReleased(Button.kTwo.value);
  }

  /**
   * Read the value of the back button on the controller.
   *
   * @return The state of the button.
   */
  public boolean getBackButton() {
    return getRawButton(Button.kBack.value);
  }

  /**
   * Whether the back button was pressed since the last check.
   *
   * @return Whether the button was pressed since the last check.
   */
  public boolean getBackButtonPressed() {
    return getRawButtonPressed(Button.kBack.value);
  }

  /**
   * Whether the back button was released since the last check.
   *
   * @return Whether the button was released since the last check.
   */
  public boolean getBackButtonReleased() {
    return getRawButtonReleased(Button.kBack.value);
  }

  /**
   * Read the value of the start button on the controller.
   *
   * @return The state of the button.
   */
  public boolean getStartButton() {
    return getRawButton(Button.kStart.value);
  }

  /**
   * Whether the start button was pressed since the last check.
   *
   * @return Whether the button was pressed since the last check.
   */
  public boolean getStartButtonPressed() {
    return getRawButtonPressed(Button.kStart.value);
  }

  /**
   * Whether the start button was released since the last check.
   *
   * @return Whether the button was released since the last check.
   */
  public boolean getStartButtonReleased() {
    return getRawButtonReleased(Button.kStart.value);
  }
}
