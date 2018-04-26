package robo;

public class Rattopojat {
	public static void main(String[] args) {
		Controller main = new Controller();
		Networking net = new Networking(8887, main);
		main.start();
		net.start();

	}  
}