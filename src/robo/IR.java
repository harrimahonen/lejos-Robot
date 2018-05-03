package robo;

import lejos.hardware.Button;
import lejos.hardware.lcd.LCD;
import lejos.hardware.sensor.EV3IRSensor;
import lejos.robotics.SampleProvider;
/*
 * This class will handle detecting signals from infrared remote controller
 *  
 */
public class IR extends Thread {

	private EV3IRSensor irSensor;
	private int remoteCmd = 0;
	private boolean stopSampling = false;
	private SampleProvider distance;
	float[] distSample;
	private int counter = 0;

	public IR(EV3IRSensor sensor) {
		this.irSensor = sensor;
		this.distance = irSensor.getDistanceMode();
		this.distSample = new float[distance.sampleSize()];

	}

	public int getRemoteCmd() {
		return this.remoteCmd;
	}

	public void stopSampling() {
		this.stopSampling = true;
	}

	public float[] getDistance() {
		return this.distSample;
	}

	public void run() {
			while (!stopSampling) {
				this.remoteCmd = irSensor.getRemoteCommand(0);
				distance.fetchSample(distSample, 0);
				LCD.drawString("Distance: ",0,1);
				if (counter >= 10) {
				LCD.drawString(distSample[0]+"",0,2);
				counter = 0;
				}
				counter++;
				if (Button.ESCAPE.isDown()) {
					this.stopSampling = true;
				}
			}
			return;
	}
}
