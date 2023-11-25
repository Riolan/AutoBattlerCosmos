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
    public void execute(long aId, JSONObject inputBuffer) {
        
        System.out.println("execute: Client Id: " + aId);
        System.out.println("BuyUnitsPacket Packet, Recieved: " + inputBuffer);
        
        // Derefrence client
        ClientHandler clientHandler = ClientHandler.clientHandlers.get(aId);
        Client client = clientHandler.getClient();

        int input = (int) inputBuffer.getNumber("bought");
        
        
        // Logic for buying units.
        List<Unit> units = client.getUnits();
        
        List<Unit> new_units = new ArrayList<Unit>();
        
        if ((input & 1) == 1) {
            new_units.add(units.get(0));
        } 
        if ((input & 2) == 2) {
             new_units.add(units.get(1));
        } 
        if ((input & 4) == 4) {
             new_units.add(units.get(2));
        } 
        if ((input & 8) == 8) {
             new_units.add(units.get(3));       
        }
        
        // validate we can buy these units 
        
        Troop troop = GameBoard.getInstance().getTroop();
        
        
        for (Unit unit : new_units) {
            // add unit
            troop.createUnit((int)aId, unit);
        }
     
        
        for (Unit unit : troop.getUnits((int)aId)) {
            System.out.println("Troops units: " + unit);
        }
        
        System.out.println("Senbding out a gamestate change packe for end shop");
        
        
        // End shop button was pressed
        PacketElement packet = new GameStateChangePacket(GameStates.GAMESEARCH);
        clientHandler.getClient().setGameState(GameStates.GAMESEARCH);
        clientHandler.sendData(packet);
        
        
    }
    
    
    
}
