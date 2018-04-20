package robo;

import java.io.File;

import lejos.hardware.Button;
import lejos.hardware.Sound;
import lejos.hardware.port.SensorPort;
import lejos.hardware.sensor.EV3ColorSensor;
import lejos.robotics.Color;

public class Colorsense extends Thread {
	EV3ColorSensor cs;

	public Colorsense() {
		cs = new EV3ColorSensor(SensorPort.S3);
	}

	public void run() {

		while (!Button.ESCAPE.isDown()) {
			switch (cs.getColorID()) {
			case Color.BLUE:
				
				File music = new File("blue.wav");
				Sound.playSample(music, 100);
				Sound.setVolume(100);
				break;
				
			}
		}
	}
}
