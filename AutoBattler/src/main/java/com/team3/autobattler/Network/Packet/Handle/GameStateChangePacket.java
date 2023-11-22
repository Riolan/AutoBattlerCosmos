/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.team3.autobattler.Network.Packet.Handle;

import com.team3.autobattler.AutoBattler;
import com.team3.autobattler.Game.GameStates;
import com.team3.autobattler.Network.Packet.PacketHandler;
import org.json.JSONObject;

/**
 *
 * @author riola
 */
public class GameStateChangePacket implements PacketHandler {
 
    
    
    
    @Override
    public void execute(JSONObject inputBuffer) {
        
        // validate(inputBuffer) apart of PacketHandler, general
        // validation, then implement proper, more extansive
        System.out.println("GameStateChangePacket, Recieved: " + inputBuffer);
        
        GameStates newState = inputBuffer.getEnum(GameStates.class, "gameState");
//        
//     
//        if (canChangeState) 
        
        AutoBattler.socketHandler.getClient().setGameState(newState);

    }
    
    
    
}
