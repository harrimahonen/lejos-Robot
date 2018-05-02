package robo;

import lejos.robotics.RegulatedMotor;
import lejos.utility.Delay;

public class Drive {
	
	private RegulatedMotor mA;
	private RegulatedMotor mB;
	private RegulatedMotor mC;
	final float maxSpeed;
	
	public Drive(RegulatedMotor mA, RegulatedMotor mB, RegulatedMotor mC) {
	maxSpeed = mA.getMaxSpeed();
	this.mA = mA;
	this.mB = mB;
	this.mC = mC;
	this.mA.setSpeed((int)maxSpeed);
	this.mB.setSpeed((int)maxSpeed);
	mA.synchronizeWith(new RegulatedMotor[] {mB});
	}
	
	public void driveForward() { 
	   mA.startSynchronization();
	   this.mA.setSpeed(1000);
	   this.mB.setSpeed(1000);
	   mB.forward();
	   mA.forward();
	   mA.endSynchronization();
	}	
	public void driveBackward() {
	   mA.startSynchronization();
	   this.mA.setSpeed(1000);
	   this.mB.setSpeed(1000);
	   mA.backward();
	   mB.backward();
	   mA.endSynchronization();
	   }
	public void turnLeft(){
		this.mA.setSpeed(1000);
		this.mB.setSpeed(1000);
		mA.forward();
		mB.backward();
	}
	public void turnRight() {
		this.mA.setSpeed(1000);
		this.mB.setSpeed(1000);
		mB.forward();
		mA.backward();
	}
	public void turnUpleft() {
		this.mA.setSpeed(1000);
		this.mB.setSpeed(500);
		mA.forward();
		mB.forward();
	}
	public void turnUpright() {
		this.mA.setSpeed(500);
		this.mB.setSpeed(1000);
		mA.forward();
		mB.forward();
	}
	public void turnDownleft() {
		this.mA.setSpeed(1000);
		this.mB.setSpeed(500);
		mA.backward();
		mB.backward();
	}
	public void turnDownright() {
		this.mA.setSpeed(500);
		this.mB.setSpeed(1000);
		mA.backward();
		mB.backward();
	}
	public void free() {
		mA.flt();
		mB.flt();
	}
	public void stop() {
		mA.startSynchronization();
		mA.stop();
		mB.stop();
		mA.endSynchronization();

	}
	public void sabotage() {
		mC.rotate(10);
		mC.rotate(-10);
	}
}
