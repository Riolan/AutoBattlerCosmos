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
import java.util.Collections;
import java.util.List;
import org.json.JSONObject;


/**
 *
 * @author Rio
 */
public class ShopInteractionPacket implements PacketHandler {

    /**
     * More than buy units packet now. Handles buying, selling, and ending buy 
     * phase. ShopInteractionPacket (?)
     * @param aId
     * @param inputBuffer 
     */
    @Override
    public void execute(ClientHandler handler, JSONObject response) {
        
        System.out.println("BuyUnitsPacket Packet, Recieved: " + response);
        
        // Derefrence client
        Client client = handler.getClient();
        
        // type action (buy, sell, move, done)
        int action = response.getInt("action");
        // index for unit bought or sold
        int index = response.getInt("bought");
        
        
        Troop troop = GameBoard.getInstance().getTroop();

        // guard clause
        if (client.getGameState() != GameStates.SHOP) return;
        
        
        int key = (int)handler.getClient().getUser().getId();
        
//        
//        System.out.println("Starting units: ");
//        if (troop.getUnits(key) == null) {
//            System.out.println("MT");
//        } else {
//            for (Unit tst :  troop.getUnits(key)) {
//                System.out.println(tst.getName());
//            }
//        }
        
        if (action == 0) {
            // Logic for buying units.
            List<Unit> units = client.getStoredShopUnits();
            System.out.println("Bought unit:" + units.get(index).getName());

            // validate we can buy these units 


            // Associate new unit to player using uuid
            troop.createUnit(key, units.get(index));
        } else if (action == 1) {
            
           
            // Logic for selling units
            Unit soldUnit = troop.getUnits(key).get(index);
            
            System.out.println("Sold unit:" + soldUnit.getName());
            int sellCost = soldUnit.getCost();
            troop.removeUnit(key, index);
            
            /// validate we can sell
            // update cash
        
        } else if ( action == 2 ) {
            // Logic for moving units
            if (index == 0) {
                // Rotate Left
                Collections.rotate(troop.getUnits(key),
                        -1);  
                System.out.println("Rotate Left");
            }
            
            if (index == 1) {
                // Rotate Left
                Collections.rotate(troop.getUnits(key),
                        1);  
                System.out.println("Rotate Right");

            }
            
        } else if (action == 3) {
            System.out.println("Next State");
            // Logic for progressing to next state.
            PacketElement packet = new GameStateChangePacket(GameStates.GAMESEARCH);
            handler.getClient().setGameState(GameStates.GAMESEARCH);
            handler.sendData(packet);
        }
        
//
//        System.out.println("Ending units: ");
//        if (troop.getUnits(key) == null) {
//            System.out.println("MT");
//        } else {
//            for (Unit tst :  troop.getUnits(key)) {
//                System.out.println(tst.getName());
//            }
//        }
    }
    
    
    
}
