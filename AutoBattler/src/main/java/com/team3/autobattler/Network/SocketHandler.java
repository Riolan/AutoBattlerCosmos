/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.team3.autobattler.Network;


import com.team3.autobattler.Network.Packet.PacketVisitorImpl;
import com.team3.autobattler.Network.Packet.PacketVisitor;
import com.team3.autobattler.Network.Packet.PacketElement;
import com.team3.autobattler.AutoBattler;
import com.team3.autobattler.Game.GameStates;
import com.team3.autobattler.Network.PacketErrors.MalformedPacketException;
import com.team3.autobattler.Network.Packet.PacketHandlerFactory;
import com.team3.autobattler.Network.Packet.PacketHandler;
import java.net.*;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

import org.json.JSONObject;


/**
 * Deals with general connection.
 * Would be good to refactor into a singleton later to ensure only one instance.
 * @author Rio
 */
public class SocketHandler {
    
    // Socket to connect to another computer (Server)
    Socket socket;
    final int TIMEOUT = 4000; // 4 Second timeout
    
    
    
    public SocketHandler() {
        client = new Client();
    }
    
    // Stream
    DataInputStream dataInputStream;
    DataOutputStream dataOutputStream;
    
    // Thread
    Thread listener = null;
    
    // Associated information
    static Client client;
    
    public Client getClient() {
        return this.client;
    }
    
    
    /**
     * Connect to server by setting up socket.
     * Spawning new thread.
     * Changing game state.
     * Some of the above can be moved to a different method.
     * @param ipAddress
     * @param port
     * @return 
     */
    public boolean connect(String ipAddress, int port, StringBuilder output) {
        output.setLength(0);
        try {
            socket = new Socket();
            
            // Establish a connection.
            socket.connect(new InetSocketAddress(ipAddress, port), TIMEOUT);
            System.out.println("Connect to: " + ipAddress + ":" + port + " On local port: " + socket.getLocalPort());
            
            // Set up input and output streams
            dataInputStream = new DataInputStream(socket.getInputStream());
            dataOutputStream = new DataOutputStream(socket.getOutputStream());
            
            // if able to connect
            client.setGameState(GameStates.CONNECTED);
            client.setGameState(GameStates.LOGIN);
            
            // Close listener (old)
            if (listener != null && listener.isAlive()) {
                listener.interrupt();
            }
            // Create new listener 
            listener = new Thread(this::listen);
            listener.start();
            
        
            return true;
        } catch (IOException e) {
            output.append(e.getMessage());
            System.out.println("Socket Handler Connect Error: " + e.getMessage());
            return false;
        }
    }
    
    /**
     * Begin listening to the socket.
     */
    private void listen() {
        boolean isRunning = true;
        // https://stackoverflow.com/questions/60259280/how-to-replace-switch-case-to-oop
        PacketHandlerFactory packetHandlerFactory = new PacketHandlerFactory();
        
        System.out.println("Entering into listener thread.");
        
        // listener e.g. Server
        while (isRunning) {
            try {
                // Recieved some data:
                String buffer = dataInputStream.readUTF();

                // Convert data expected packet format
                JSONObject obj = new JSONObject(buffer);
                // May want to throw a Malformed Packet Exception, and log user who
                // has sent it.
                if (!obj.has("id")) continue;
                
                
                // Analyze packet through packet handler by id
                int id = obj.getInt("id");
                
                System.out.println("Socket Handler input: " + buffer);
                // Using Packet Handler Factory make respective handler
                PacketHandler packet = packetHandlerFactory.make(id);
                // excute the respetive handler once it has been properly assigned
                packet.execute(obj);
                
                
            } catch (IOException e) {
                // Disable loop
                isRunning = false;
                System.out.println("Error has occured in SocketHandler listen: " + e.getMessage());
                AutoBattler.connect();
                client.setGameState(GameStates.UNCONNECTED);
            }
        }
        
        // if socket/or data stream is not running then
        closeResources();
        System.out.println("Exiting listener thread.");
        
    }
    
    /**
     * Close out necessary resources, data streams, and socket.
     */
    public void closeResources() {
        try {
            socket.close();
            dataInputStream.close();
            dataOutputStream.close();
        } catch (IOException e) {
            System.out.println("Error has occured in SocketHandler closeResources: " + e.getMessage());
        }
    }
    
    ///////
    // Current attempt at packet sending.
    ///////
    private PacketVisitor visitor = new PacketVisitorImpl();
    /**
     * 
     * @param packet 
     */
    public void sendData(PacketElement packet) { //throws MalformedPacketException {
            // Do not send data if not connected to server.

            
            if (client.getGameState().getState().equals(GameStates.UNCONNECTED)) return;
            JSONObject data = packet.accept(visitor);
            
            // Not a reasonable check atm, but example for later
            // packets should have a set id rather than passing it
            //if (data.get("id") == null) throw new MalformedPacketException("Packet id is null. Supply the packet with valid id.");
            
            try {
                dataOutputStream.writeUTF(data.toString());
            } catch (IOException e) {
                System.out.println("***********\n\t" + e.getMessage() + "\n***********");
            }

    }
    
    
}
