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
import com.team3.autobattler.SceneManagement.Scenes.MainMenuScene;
import com.team3.autobattler.SceneManagement.Scenes.PlayOutRoundScene;
import com.team3.autobattler.SceneManagement.Scenes.StartRoundScene;
import org.json.JSONArray;
import org.json.JSONObject;

/**
 * The OpponentPacket class implements the PacketHandler interface and is responsible
 * for processing and executing opponent information received from the server.
 *
 * @author rio
 */
public class OpponentPacket implements PacketHandler {
 
    /**
     * Executes the handling of the opponent packet by processing the information
     * received from the server and updating relevant scenes and UI elements.
     *
     * @param inputBuffer A JSONObject containing the opponent information.
     */
    @Override
    public void execute(JSONObject inputBuffer) {
        
        // validate(inputBuffer) apart of PacketHandler, general
        // validation, then implement proper, more extansive
        System.out.println("OpponentPacket, Recieved: " + inputBuffer);
        
        String opponentName = inputBuffer.getString("name");
        JSONArray opponentUnits = inputBuffer.getJSONArray("units");
        
        SceneManager sceneManager = SceneManager.getInstance();
            
        StartRoundScene startRoundScene = (StartRoundScene) sceneManager.getScene(GameStates.STARTROUND);
        PlayOutRoundScene playOutRoundScene = (PlayOutRoundScene) sceneManager.getScene(GameStates.PLAYOUTROUND);
        // Update Panel with new information, not sure if this is the correct way to do it yet
        //sceneManager.changeScene(GameStates.SHOP);
        Client client = AutoBattler.socketHandler.getClient();
        if (opponentName != null) {
            startRoundScene.opponentName.setText(opponentName);
            playOutRoundScene.opponentName.setText(opponentName);
            startRoundScene.playerName.setText(client.getUser().getUsername());
            playOutRoundScene.playerName.setText(client.getUser().getUsername());
        } else {
            startRoundScene.opponentName.setText("SOMETHING WENT WRONG");
        }
        startRoundScene.receiveData(opponentName, opponentUnits);
        startRoundScene.validate();
    }
            

    
}
