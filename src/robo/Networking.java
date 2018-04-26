package robo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.InetSocketAddress;
import java.net.UnknownHostException;
import java.nio.ByteBuffer;

import org.java_websocket.WebSocket;
import org.java_websocket.WebSocketImpl;
//import org.java_websocket.framing.Framedata;
import org.java_websocket.handshake.ClientHandshake;
import org.java_websocket.server.WebSocketServer;

public class Networking extends WebSocketServer{
	private int cmd = 0;
	Controller ctrl;
	
	public Networking( int port, Controller controller ) {
        super( new InetSocketAddress( port ) );
        ctrl = controller;
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
        //broadcast( conn + " has left the room!" );
        System.out.println( conn + " has left!" );
    }

    @Override
    public void onMessage( WebSocket conn, String message ) {
        //System.out.println( conn + " : "+message );
        if(message.equals("UP")){
            //System.out.println("Move UP!");
           ctrl.drive.driveForward();
        }
        if(message.equals("")) {
        	ctrl.drive.stop();
        }
        if(message.equals("DOWN")) {
        	ctrl.drive.stop();
        }
                
        /*if(message.equals("")) {
            System.out.println("Cmd 0");
        	cmd = 0;
        }*/
    }
    @Override
    public void onMessage( WebSocket conn, ByteBuffer message ) {
        //broadcast( message.array() );
        //System.out.println( conn + ": " + message );

    }


    public void startServer(){
        //WebSocketImpl.DEBUG = true;
        int port = 8887; // 843 flash policy port
        //Networking s = new Networking( port );
        //s.start();
        //System.out.println( "Server started on port: " + s.getPort() );

        /*BufferedReader sysin = new BufferedReader( new InputStreamReader( System.in ) );
        while ( true ) {
            String in = sysin.readLine();
            s.broadcast( in );
            if( in.equals( "exit" ) ) {
                s.stop(1000);
                break;
            }
        }
        */
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
    
    public int getRemoteComm() {
    	return this.cmd;
    }
}
