/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.team3.autobattler.Network.Packet.Handle;

import com.team3.autobattler.AutoBattler;
import com.team3.autobattler.Game.GameStates;
import com.team3.autobattler.Network.Client;
import com.team3.autobattler.Network.Packet.PacketHandler;
import com.team3.autobattler.SceneManagement.SceneManager;
import com.team3.autobattler.SceneManagement.Scenes.EndRoundScene;
import com.team3.autobattler.SceneManagement.Scenes.MainMenuScene;
import com.team3.autobattler.SceneManagement.Scenes.StartRoundScene;
import org.json.JSONArray;
import org.json.JSONObject;

/**
 * (not functional)
 * receives battle outcome information from server
 * @author Emily
 */
public class BattleOutcomePacket implements PacketHandler {
 
    
    
    
    @Override
    public void execute(JSONObject inputBuffer) {
        
        // validate(inputBuffer) apart of PacketHandler, general
        // validation, then implement proper, more extansive
        System.out.println("BattleOutcomePacket, Recieved: " + inputBuffer);
        
        String result = inputBuffer.getString("result");
        int goldEarned = inputBuffer.getInt("goldEarned");
        
        SceneManager sceneManager = SceneManager.getInstance();
            
        EndRoundScene endRoundScene = (EndRoundScene)sceneManager.getScene(GameStates.ENDROUND);
        // Update Panel with new information, not sure if this is the correct way to do it yet
        //sceneManager.changeScene(GameStates.SHOP);
        if (result != null) {
                endRoundScene.resultMsg.setText(result);
            } else {
                endRoundScene.resultMsg.setText("SOMETHING WENT WRONG");

            }
        endRoundScene.goldEarnedMsg.setText("You earned " + goldEarned + " gold");
    }
            

    
}
