package robo;

import java.io.File;

import lejos.hardware.Button;
import lejos.hardware.Sound;
import lejos.hardware.lcd.LCD;
import lejos.hardware.motor.EV3LargeRegulatedMotor;
import lejos.hardware.port.MotorPort;
import lejos.hardware.port.SensorPort;
import lejos.hardware.sensor.EV3ColorSensor;
import lejos.hardware.sensor.EV3IRSensor;
import lejos.robotics.Color;
import lejos.robotics.RegulatedMotor;
import lejos.utility.Delay;

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
		RegulatedMotor motorA = new EV3LargeRegulatedMotor(MotorPort.D);
		RegulatedMotor motorB = new EV3LargeRegulatedMotor(MotorPort.A);
		Drive drive = new Drive(motorA, motorB);
		//float distance = 0.0f;
		IR IRthread = new IR(irSensor);
		EV3ColorSensor cs = new EV3ColorSensor(SensorPort.S3);
		IRthread.start();
		LCD.drawInt((int)drive.maxSpeed,0,2);
		
		
		while (!Button.ESCAPE.isDown()) {
			
			//Color sensing feature
			if (cs.getColorID() == Color.BLUE) {
				File music = new File("blue.wav");
				Sound.playSample(music, 100);
				Sound.setVolume(100);
			}
			
			//Remote controller reader
			switch (IRthread.getRemoteCmd()) {
			case 0:
				drive.stop();
				break;
			case 1:
				drive.driveForward();
				break;
			case 2:
				drive.driveBackward();
				break;
			case 3:
				drive.turnLeft();
				break;
			case 4:
				drive.turnRight();
				break;
			}
		}
		motorA.close();
		motorB.close();
		IRthread.interrupt();

	
	}

}
