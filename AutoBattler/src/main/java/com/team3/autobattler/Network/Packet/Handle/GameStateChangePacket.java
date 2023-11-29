/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.team3.autobattler.Network.Packet.Handle;

import com.team3.autobattler.AutoBattler;
import com.team3.autobattler.Game.GameStates;
import com.team3.autobattler.Network.Packet.PacketHandler;
import com.team3.autobattler.SceneManagement.SceneManager;
import com.team3.autobattler.SceneManagement.Scenes.MainMenuScene;
import org.json.JSONObject;

/**
 * The GameStateChangePacket class implements the PacketHandler interface and is
 * responsible for processing and executing changes in the game state received from the server.
 *
 * @author  rio
 */
public class GameStateChangePacket implements PacketHandler {
 
    /**
     * Executes the handling of the game state change packet by extracting the new
     * game state information from the inputBuffer and updating the client's game state.
     *
     * @param inputBuffer A JSONObject containing the new game state information.
     */
    @Override
    public void execute(JSONObject inputBuffer) {
        
        // validate(inputBuffer) apart of PacketHandler, general
        // validation, then implement proper, more extansive
        System.out.println("GameStateChangePacket, Recieved: " + inputBuffer);
        
        GameStates newState = inputBuffer.getEnum(GameStates.class, "gameState");

        AutoBattler.socketHandler.getClient().setGameState(newState);
    }
            

    
}
