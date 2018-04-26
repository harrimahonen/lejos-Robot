package robo;

import lejos.hardware.Button;
import lejos.hardware.lcd.LCD;
import lejos.hardware.motor.EV3LargeRegulatedMotor;
import lejos.hardware.port.MotorPort;
import lejos.hardware.port.SensorPort;
import lejos.hardware.sensor.EV3IRSensor;
import lejos.robotics.RegulatedMotor;

// main logic controller
public class Controller extends Thread {
	IR IRthread;
	EV3IRSensor irSensor;
	// initialize
	RegulatedMotor motorA;
	RegulatedMotor motorB;
	Drive drive;
	Colorsense cs;
	
	public Controller() {
		motorB = new EV3LargeRegulatedMotor(MotorPort.A);
		motorA = new EV3LargeRegulatedMotor(MotorPort.D);
		irSensor = new EV3IRSensor(SensorPort.S2);
		IRthread = new IR(irSensor);
		drive = new Drive(motorA, motorB);
		//cs = new Colorsense();
	}

	public void run() {
		//LCD.drawInt((int) drive.maxSpeed, 0, 2);
		//IRthread.start();
		//cs.start();
		while(!Button.DOWN.isDown()) {
			
		}
		
		motorA.close();
		motorB.close();
		//IRthread.interrupt();

	}
}
