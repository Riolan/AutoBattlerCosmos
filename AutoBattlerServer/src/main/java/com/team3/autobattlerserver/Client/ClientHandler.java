/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.team3.autobattlerserver.Client;

import com.team3.autobattlerserver.Game.GameStates;
import com.team3.autobattlerserver.Network.PacketElement;
import com.team3.autobattlerserver.Network.PacketHandler;
import com.team3.autobattlerserver.Network.PacketHandlerFactory;
import com.team3.autobattlerserver.Network.PacketVisitor;
import com.team3.autobattlerserver.Network.PacketVisitorImpl;
import java.io.*;
import java.net.Socket;
import java.util.ArrayList;

import org.json.JSONObject;

/**
 *
 * @author Rio
 */
public class ClientHandler implements Runnable {

    // public to be accessible
    public static ArrayList<ClientHandler> clientHandlers = new ArrayList<>();
    public static int clientAmt = 0;
    private Socket socket;

    private DataInputStream inputstream;
    private DataOutputStream outputstream;

    private Client client;

    ClientHandlerGUI clientGUI;

    private int clientId;
    
    public ClientHandler(Socket socket) {
        try {
            this.socket = socket;
            this.inputstream = new DataInputStream(socket.getInputStream());
            this.outputstream = new DataOutputStream(socket.getOutputStream());

            // Create a new Client associated with this socket.
            this.client = new Client();
            
            // Set client gamestate to CONNECTED
            client.gameState = client.gameState.UNCONNECTED;
            System.out.println("Current Client Game State: " + client.gameState);

            if (client.gameState.canChangeGameState(client.gameState, GameStates.CONNECTED)) {
                client.gameState = GameStates.CONNECTED;
            }
            System.out.println("Updated Client Game State: " + client.gameState);


            // Client Handler List 
            clientHandlers.add(this);
            
            this.clientId = clientAmt;
            clientAmt++;
            
            System.out.println("Added new ClientHandler to clientHandlers.");

            // Client GUI
            clientGUI = new ClientHandlerGUI(this.clientId);
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

                clientGUI.jTextPane1.setText(clientGUI.jTextPane1.getText() + jsonObject.toString(2) + '\n');

                int packetId = jsonObject.getInt("id");

                // Analyze packet through packet handler
                // Strategy pattern for different algorithm based on input.
                PacketHandler packet = packetHandlerFactory.make(packetId);
                // Excute the packet (closer to handle)
                packet.execute(this.clientId, jsonObject);

            } catch (IOException e) {
                
                System.out.println("Client " + socket.toString() + " has disconnected: " + e.getMessage());
                closeEverything(socket, inputstream, outputstream);
                break;
            }
        }
    }

    private PacketVisitor visitor = new PacketVisitorImpl();
    /**
     * 
     * @param packet 
     */
    public void sendData(PacketElement packet) { //throws MalformedPacketException {
            // Do not send data if not connected to server.
            if (client.getGameState().equals(GameStates.UNCONNECTED)) return;
            JSONObject data = packet.accept(visitor);
            
            // Not a reasonable check atm, but example for later
            // packets should have a set id rather than passing it
            //if (data.get("id") == null) throw new MalformedPacketException("Packet id is null. Supply the packet with valid id.");
            
            try {
                outputstream.writeUTF(data.toString());
            } catch (IOException e) {
                System.out.println("***********\n\t" + e.getMessage() + "\n***********");
            }

    }
    
    public void removeClientHandler() {
        clientHandlers.remove(this);
    }

    
    
    /**
     * 
     * @param socket
     * @param bufferedReader
     * @param bufferedWriter 
     */
    public void closeEverything(Socket socket, DataInputStream bufferedReader, DataOutputStream bufferedWriter) {
        removeClientHandler();

        clientGUI.setVisible(false);

        try {
            if (bufferedReader != null) {
                bufferedReader.close();
            }
            if (bufferedWriter != null) {
                bufferedWriter.close();
            }
            if (socket != null) {
                socket.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
