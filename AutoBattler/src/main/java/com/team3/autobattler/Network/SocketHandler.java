/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.team3.autobattler.Network;


import com.team3.autobattler.Network.PacketHandlerFactory;
import com.team3.autobattler.Network.PacketHandler;
import java.net.*;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

import org.json.JSONObject;


/**
 *
 * @author Rio
 */

// @@ TODO REFACTOR INTO SINGLETON
// Rename for clarity (?)
public class SocketHandler {
    
    // Socket to connect to another computer (Server)
    Socket socket;
    final int TIMEOUT = 4000; // 4 Second timeout
    
    
    // Stream
    DataInputStream dataInputStream;
    DataOutputStream dataOutputStream;
    
    // Thread
    Thread listener = null;
    
    // Associated information
    String username;
    
    public boolean connect(String ipAddress, int port) {
    
        try {
            socket = new Socket();
            
            // Establish a connection.
            socket.connect(new InetSocketAddress(ipAddress, port), TIMEOUT);
            System.out.println("Connect to: " + ipAddress + ":" + port + " On local port: " + socket.getLocalPort());
            
            
            // Set up input and output streams
            dataInputStream = new DataInputStream(socket.getInputStream());
            dataOutputStream = new DataOutputStream(socket.getOutputStream());
            
            // Close listener (old)
            if (listener != null && listener.isAlive()) {
                listener.interrupt();
            }
            // Create new listener 
            listener = new Thread(this::listen);
            listener.start();
            
            System.out.println("Socket Handler Connect Succeeded.");
            return true;
        } catch (IOException e) {
            System.out.println("Socket Handler Connect Error: " + e.getMessage());
            return false;
        }
    }
    
    private void listen() {
        boolean isRunning = true;
        // https://stackoverflow.com/questions/60259280/how-to-replace-switch-case-to-oop
        PacketHandlerFactory packetHandlerFactory = new PacketHandlerFactory();
        
        System.out.println("Entering into listener thread.");
        
        // listener e.g. Server
        while (isRunning) {
            try {
                //String message = (String) dataInputStream.readUTF();
                //System.out.println("Message Received: " + message);
                
                //Byte input = dataInputStream.readByte();
                
                //System.out.println("Recieved: " + dataInputStream.readUTF());
                // Analyze packet through packet handler
                // https://stackoverflow.com/questions/5542367/cast-byte-to-enum
                
                //byte[] inputBuffer = dataInputStream.readUTF();
                //System.out.println("Recieved: " + inputBuffer);
                String lines = dataInputStream.readUTF();
                
                
                JSONObject obj = new JSONObject(lines);
                int id = obj.getInt("id");
                
                System.out.println(lines);          
                PacketHandler packet = packetHandlerFactory.make(id);
                packet.execute(obj);
                
                
            } catch (IOException e) {
                // Disable loop
                isRunning = false;
                System.out.println("Error has occured in SocketHandler listen: " + e.getMessage());
            }
        }
        
        // if socket/or data stream is not running then
        closeResources();
        System.out.println("Exiting listener thread.");
        
    }
    
    public void closeResources() {
        try {
            socket.close();
            dataInputStream.close();
            dataOutputStream.close();
        } catch (IOException e) {
            System.out.println("Error has occured in SocketHandler closeResources: " + e.getMessage());
        }
    }
    
    public void sendData(String data) {
        try {
            dataOutputStream.writeUTF(data);
        } catch (IOException e) {
            System.out.println("***********\n\t" + e.getMessage() + "\n***********");
        }
    
    }
    
    
}
