/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.team3.autobattlerserver.Network.Packets.Handle;

import com.team3.autobattlerserver.Client.ClientHandler;
import com.team3.autobattlerserver.Client.Client;
import com.team3.autobattlerserver.Game.GameBoard;
import com.team3.autobattlerserver.Game.GameStates;
import com.team3.autobattlerserver.Game.Troop;
import com.team3.autobattlerserver.Game.Unit;
import com.team3.autobattlerserver.Network.PacketElement;
import com.team3.autobattlerserver.Network.Packets.Create.GameStateChangePacket;

import com.team3.autobattlerserver.Network.PacketHandler;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONObject;


/**
 *
 * @author Rio
 */
public class BuyUnitsPacket implements PacketHandler {

    /**
     * 
     * @param aId
     * @param inputBuffer 
     */
    @Override
    public void execute(ClientHandler handler, JSONObject inputBuffer) {
        
        System.out.println("BuyUnitsPacket Packet, Recieved: " + inputBuffer);
        
        // Derefrence client
        Client client = handler.getClient();

        // index for unit bought
        int index = (int) inputBuffer.getInt("bought");
        
        
        // Logic for buying units.
        List<Unit> units = client.getStoredShopUnits();
        System.out.println("Bought unit:" + units.get(index));
        
        // validate we can buy these units 
        Troop troop = GameBoard.getInstance().getTroop();
        
        
        // Associate new unit to player using uuid
        troop.createUnit((int)handler.getClient().getUser().getId(), units.get(index));
        

        
        // End shop button was pressed
        // Therefore progress to next state.
        PacketElement packet = new GameStateChangePacket(GameStates.GAMESEARCH);
        handler.getClient().setGameState(GameStates.GAMESEARCH);
        handler.sendData(packet);
        
        
    }
    
    
    
}
