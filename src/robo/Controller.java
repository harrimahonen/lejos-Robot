package robo;

import lejos.hardware.motor.EV3LargeRegulatedMotor;
import lejos.hardware.port.MotorPort;
import lejos.hardware.port.SensorPort;
import lejos.hardware.sensor.EV3IRSensor;
import lejos.robotics.RegulatedMotor;
/*
 * This is the wrapper class to combine all the other classes
 */
public class Controller extends Thread {
	IR IRthread;
	EV3IRSensor irSensor;
	RegulatedMotor motorA;
	RegulatedMotor motorB;
	RegulatedMotor motorC;
	Drive drive;
	
	public Controller() {
		motorB = new EV3LargeRegulatedMotor(MotorPort.A);
		motorA = new EV3LargeRegulatedMotor(MotorPort.D);
		motorC = new EV3LargeRegulatedMotor(MotorPort.C);
		irSensor = new EV3IRSensor(SensorPort.S2);
		IRthread = new IR(irSensor);
		drive = new Drive(motorA, motorB, motorC);
		}
	}

