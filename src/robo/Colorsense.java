package robo;

import lejos.hardware.Button;
import lejos.hardware.port.SensorPort;
import lejos.hardware.sensor.EV3ColorSensor;
import lejos.robotics.Color;

public class Colorsense extends Thread {
	EV3ColorSensor cs;
	String color = "";
	
	public Colorsense() {
		cs = new EV3ColorSensor(SensorPort.S3);
	}
	
	public String getColor() {
		return this.color;
	}

	public void run() {

		while (!Button.ESCAPE.isDown()) {
			switch (cs.getColorID()) {
			case Color.BLUE:
				this.color = "BLUE";
				break;
			case Color.GREEN:
				this.color = "GREEN";
				break;
			case Color.RED:
				this.color = "RED";
				break;
				
				
			}
		}
	}
}
