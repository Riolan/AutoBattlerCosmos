/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.team3.autobattlerserver.Network.Packets.Handle;

//import com.team3.autobattlerserver.AutoBattlerServer;
//import com.team3.autobattlerserver.Game.;
//import com.team3.autobattlerserver.Game.GameStates;
//import com.team3.autobattlerserver.Game.MyGameState;
import com.team3.autobattlerserver.Client.ClientHandler;
import com.team3.autobattlerserver.Network.PacketElement;
import com.team3.autobattlerserver.Network.PacketHandler;
import org.json.JSONObject;



/**
 *
 * @author riola
 */
public class GameStateChangePacket implements PacketHandler {
 
    
    
    
    @Override
    public void execute(long aId, JSONObject inputBuffer) {
        
        System.out.println("execute: Client Id: " + aId);

        
        // validate(inputBuffer) apart of PacketHandler, general
        // validation, then implement proper, more extansive
        System.out.println("GameStateChangePacket, Recieved: " + inputBuffer);
        
        //ClientHandler client = ClientHandler.clientHandlers.get(aId);
        
        //client.sendJSON( a json obj);

    }
    
    
    
}
