package robo;

import lejos.robotics.RegulatedMotor;

public class Drive {
	
	private RegulatedMotor mA;
	private RegulatedMotor mB;
	final float maxSpeed;
	
	public Drive(RegulatedMotor mA, RegulatedMotor mB) {
	maxSpeed = mA.getMaxSpeed();
	this.mA = mA;
	this.mB = mB;
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
}
