package robo;

import lejos.hardware.Button;
import lejos.hardware.lcd.LCD;
import lejos.hardware.motor.EV3LargeRegulatedMotor;
import lejos.hardware.port.MotorPort;
import lejos.hardware.port.SensorPort;
//import lejos.hardware.sensor.EV3ColorSensor;
import lejos.hardware.sensor.EV3IRSensor;
import lejos.robotics.RegulatedMotor;

public class Rattopojat {
	// TODO:
	/*
	 * Drive - drive according to remote signal IR - detect remote signal Color
	 * sense - detect color when near object main - Robot will drive when using
	 * remote and will get color information when close enough to an object
	 */

	public static void main(String[] args) {
		// initialize
		EV3IRSensor irSensor = new EV3IRSensor(SensorPort.S2);
		RegulatedMotor motorA = new EV3LargeRegulatedMotor(MotorPort.A);
		RegulatedMotor motorB = new EV3LargeRegulatedMotor(MotorPort.D);
		Drive drive = new Drive(motorA, motorB);
		//float distance = 0.0f;
		IR IRthread = new IR(irSensor);
		// EV3ColorSensor cs = new EV3ColorSensor(SensorPort.S3);
		IRthread.start();
		LCD.drawInt((int)drive.maxSpeed,0,2);
		while (!Button.ESCAPE.isDown()) {
			switch (IRthread.getRemoteCmd()) {
			case 0:
				drive.free();
				break;
			case 1:
				LCD.drawString("Driving", 0, 4);
				drive.driveForward();
				break;
			case 2:
				LCD.drawString("Driving backward", 0, 4);
				drive.driveBackward();
				break;
			case 3:
				drive.turnLeft();
				break;
			case 4:
				drive.rotateMotor();
				break;
			}
		}
		motorA.close();
		motorB.close();
		irSensor.close();
		IRthread.interrupt();
	}

}
