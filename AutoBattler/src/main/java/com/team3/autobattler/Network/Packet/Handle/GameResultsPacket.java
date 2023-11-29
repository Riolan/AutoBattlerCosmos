/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.team3.autobattler.Network.Packet.Handle;

import com.team3.autobattler.AutoBattler;
import com.team3.autobattler.Game.GameStates;
import com.team3.autobattler.Network.Packet.PacketHandler;
import com.team3.autobattler.SceneManagement.SceneManager;
import com.team3.autobattler.SceneManagement.Scenes.EndGameScene;
import com.team3.autobattler.SceneManagement.Scenes.MainMenuScene;
import org.json.JSONObject;

/**
 * The GameResultsPacket class implements the PacketHandler interface and is
 * responsible for receiving game results from the server and displaying them to the user.
 *
 * @author Emily
 */
public class GameResultsPacket implements PacketHandler {
 
    
    
    /**
     * Executes the handling of the game results packet by extracting the relevant
     * information from the inputBuffer and updating the UI with the results.
     *
     * @param inputBuffer A JSONObject containing game results information.
     */
    @Override
    public void execute(JSONObject inputBuffer) {
        
        // validate(inputBuffer) apart of PacketHandler, general
        // validation, then implement proper, more extansive
        System.out.println("GameStateChangePacket, Recieved: " + inputBuffer);
        
        int wins = inputBuffer.getInt("wins");
        int losses = inputBuffer.getInt("losses");

        EndGameScene endGame = (EndGameScene)SceneManager.getInstance().getScene(GameStates.ENDGAME);
            
            endGame.winsLabel.setText("Wins: " + wins);
            endGame.lossesLabel.setText("Losses: " + losses);
    }
            

    
}
