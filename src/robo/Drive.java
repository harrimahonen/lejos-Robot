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
	   mB.forward();
	   mA.forward();
	   mA.endSynchronization();
	}	
	public void driveBackward() {
	   mA.startSynchronization();
	   mA.backward();
	   mB.backward();
	   mA.endSynchronization();
	   }
	public void turnLeft(){
		mA.forward();
	}
	public void turnRight() {
		mB.forward();		
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
