package robo;

import java.net.InetSocketAddress;
import java.nio.ByteBuffer;

import org.java_websocket.WebSocket;
import org.java_websocket.handshake.ClientHandshake;
import org.java_websocket.server.WebSocketServer;

public class Networking extends WebSocketServer{
	Controller ctrl;
	Colorsense cs;
	String msg;
	
	public Networking( int port, Controller controller ) {
        super( new InetSocketAddress( port ) );
        ctrl = controller;
        cs = new Colorsense();
        cs.start();
    }

    public Networking( InetSocketAddress address ) {
        super( address );
    }

    @Override
    public void onOpen( WebSocket conn, ClientHandshake handshake ) {
        conn.send("Welcome to the server!"); //This method sends a message to the new client
        //broadcast( "new connection: " + handshake.getResourceDescriptor() ); //This method sends a message to all clients connected
        //System.out.println( conn.getRemoteSocketAddress().getAddress().getHostAddress() + " entered" );
    }

    @Override
    public void onClose( WebSocket conn, int code, String reason, boolean remote ) {
        System.out.println( conn + " has left!" );
    }

    @Override
    public void onMessage( WebSocket conn, String message ) {
        if(message.equals("UP")){    
        	conn.send(cs.getColor());
           ctrl.drive.driveForward();
        }
        if(message.equals("STOP")) {
        	ctrl.drive.stop();
        }
        if(message.equals("DOWN")) {
        	ctrl.drive.driveBackward();
        }
        if(message.equals("RIGHT")) {
        	ctrl.drive.turnRight();
        }
        if(message.equals("LEFT")) {
        	ctrl.drive.turnLeft();
        }
        if(message.equals("UPLEFT")) {
        	ctrl.drive.turnUpleft();
        }
        if(message.equals("UPRIGHT")) {
        	ctrl.drive.turnUpright();
        }
        if(message.equals("DOWNLEFT")) {
        	ctrl.drive.turnDownleft();
        }
        if(message.equals("DOWNRIGHT")) {
        	ctrl.drive.turnDownright();
        }
        if(message.equals("SABOTAGE")) {
        	ctrl.drive.sabotage();
        }
    }
    @Override
    public void onMessage( WebSocket conn, ByteBuffer message ) {
        
    }


    @Override
    public void onError( WebSocket conn, Exception ex ) {
        ex.printStackTrace();
        if( conn != null ) {
            System.out.println("Error!");        }
    }

    @Override
    public void onStart() {
        System.out.println("Server started!");
    }
}
