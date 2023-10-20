/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.team3.autobattlerserver.Client;

import com.team3.autobattlerserver.Client.Client;
import com.team3.autobattlerserver.Network.PacketHandler;
import com.team3.autobattlerserver.Network.PacketHandlerFactory;
import java.io.*;
import java.net.Socket;
import java.util.ArrayList;

import java.util.concurrent.TimeUnit;

import org.json.JSONObject;

/**
 *
 * @author Rio
 */
public class ClientHandler implements Runnable {
    public static ArrayList<ClientHandler> clientHandlers = new ArrayList<>();
    private Socket socket;
    //private BufferedReader inputstream;
    //private BufferedWriter outputstream;
    private DataInputStream inputstream;
    private DataOutputStream outputstream;
    
    private Client client;
    
    ClientHandlerGUI clientGUI;
    
    public ClientHandler(Socket socket) {
        try {
            this.socket = socket;
            //this.outputstream = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
            //this.inputstream = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            this.inputstream = new DataInputStream(socket.getInputStream());
            this.outputstream = new DataOutputStream(socket.getOutputStream());
            
            clientHandlers.add(this);
            System.out.println("Added new ClientHandler to clientHandlers.");
            
            clientGUI = new ClientHandlerGUI();
            clientGUI.changeLabel(socket.toString());
            clientGUI.setVisible(true);
            
         

            
        } catch (IOException e) {
            closeEverything(socket, inputstream, outputstream);
        }
    }
    
    
    
    @Override
    public void run() {
        // Implement runnable
        System.out.println("Running client input/output handler.");
        // https://stackoverflow.com/questions/60259280/how-to-replace-switch-case-to-oop
        PacketHandlerFactory packetHandlerFactory = new PacketHandlerFactory();
        
        JSONObject jsonObject;
        
        while (socket.isConnected()) {
            try {
                
                
                String message = (String) inputstream.readUTF();
                System.out.println("Message Received: " + message);
                
                
                jsonObject = new JSONObject(message);
                
                
                clientGUI.jTextPane1.setText(clientGUI.jTextPane1.getText() + '\n' + jsonObject.toString(2));
                        
                
                int id = jsonObject.getInt("id");
                
                // Analyze packet through packet handler
                // https://stackoverflow.com/questions/5542367/cast-byte-to-enum
                PacketHandler packet = packetHandlerFactory.make(id);
                
                packet.execute(jsonObject);
                
                
            } catch (IOException e) {
                closeEverything(socket, inputstream, outputstream);
                System.out.println("Error has occured in SocketHandler listen: " + e.getMessage());
                
                break;
            }
        }
    }
    
    public void sendJSON(JSONObject inputbuffer) {
        try {
            outputstream.writeUTF(inputbuffer.toString());
        } catch (IOException e) {
            closeEverything(socket, inputstream, outputstream);
        }
    }
    
    
    
    public void removeClientHandler() {
        clientHandlers.remove(this);
        //broadcastMessage("SERVER: " + username + " has left the chat");
    }
    
     public void closeEverything(Socket socket, DataInputStream bufferedReader, DataOutputStream bufferedWriter) {
        removeClientHandler();
        
        clientGUI.setVisible(false);

        try {
            if (bufferedReader != null) bufferedReader.close();
            if (bufferedWriter != null) bufferedWriter.close();
            if (socket != null) socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
