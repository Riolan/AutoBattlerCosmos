/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.team3.autobattlerserver.Network.Packets.Handle;

import com.team3.autobattlerserver.AutoBattlerServer;
import com.team3.autobattlerserver.Game.GameStates;
import com.team3.autobattlerserver.Game.MyGameState;
import com.team3.autobattlerserver.Client.ClientHandler;
import com.team3.autobattlerserver.Client.Client;
import com.team3.autobattlerserver.Network.PacketElement;
import com.team3.autobattlerserver.Network.PacketHandler;
import org.json.JSONObject;



/**
 *
 * @author riola
 */
public class GameStateChangePacket implements PacketHandler {
 
    
    
    
    @Override
    public void execute(ClientHandler handler, JSONObject inputBuffer) {
        
        System.out.println("GameStateChangePacket, Recieved: " + inputBuffer);
        GameStates newState = inputBuffer.getEnum(GameStates.class, "gameState");
        Client client = handler.getClient();
        
        System.out.println("B4 client Game state:" + client.getGameState());
        // validation to ensure state changes to a legal state
        client.setGameState(newState);
        System.out.println("A4 client Game state:" + client.getGameState());
        handler.clientGUI.gameStateLabel.setText(
                client.getGameState().toString());
        
        PacketElement packet = new com.team3.autobattlerserver.Network.Packets.Create.GameStateChangePacket(client.getGameState());
        handler.sendData(packet);
    }
    
    
    
}
