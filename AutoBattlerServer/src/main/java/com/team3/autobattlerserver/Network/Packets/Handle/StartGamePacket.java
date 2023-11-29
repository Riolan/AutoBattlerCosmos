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
import com.team3.autobattlerserver.Game.GameBoard;
import com.team3.autobattlerserver.Game.Troop;
import com.team3.autobattlerserver.Game.Unit;
import com.team3.autobattlerserver.Network.PacketElement;
import com.team3.autobattlerserver.Network.PacketHandler;
import org.json.JSONObject;

import com.team3.autobattlerserver.Network.Packets.Create.GameStateChangePacket;
import com.team3.autobattlerserver.Network.Packets.Create.ShopEntitiesPacket;
import java.util.Collections;
import java.util.List;


/**
 *
 * @author Rio
 */
public class StartGamePacket implements PacketHandler {

    
    @Override
    public void execute(ClientHandler handler, JSONObject inputBuffer) {
        
        
        System.out.println("Start Game Packet, Recieved: " + inputBuffer);
        
        // Derefrence client

        Client client = handler.getClient();
        
        // Client requests to start a game.
        // check if client is already in game
        client.setInGame(true);
        
        // Send changestate to client (SHOP)
        client.setGameState(GameStates.SHOP);
        PacketElement packet = new GameStateChangePacket(GameStates.SHOP);
        handler.sendData(packet);
        
        // Generate random units to send to Player.

        // Take a look back at this later for generating random units.
        Troop troop = GameBoard.getInstance().getTroop();
        
        List<Unit> availableUnits = troop.getUnits(-1);
        Collections.shuffle(availableUnits);
        List <Unit> shopUnits = availableUnits.subList(0, 4);
        
        
        // Store random shop units here.
        client.setUnits(shopUnits);
        // send random shop units
        PacketElement shopDataPacket = new ShopEntitiesPacket(shopUnits);
        handler.sendData(shopDataPacket);
        
    }
    
    
    
}
