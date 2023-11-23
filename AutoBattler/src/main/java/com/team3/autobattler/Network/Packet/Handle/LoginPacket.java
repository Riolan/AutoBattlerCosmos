/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.team3.autobattler.Network.Packet.Handle;

import com.team3.autobattler.AutoBattler;
import com.team3.autobattler.Game.GameStates;
import com.team3.autobattler.Network.Client;
import com.team3.autobattler.Network.Packet.PacketElement;
import com.team3.autobattler.Network.Packet.PacketHandler;
import com.team3.autobattler.SceneManagement.SceneManager;
import com.team3.autobattler.SceneManagement.Scenes.LoginScene;

import org.json.JSONArray;
import org.json.JSONObject;

/**
 *
 * @author Rio
 */
public class LoginPacket implements PacketHandler {
    @Override
    public void execute(JSONObject response) {
        // response as the passed variable makes so much more sense
        System.out.println("LoginPacket, Recieved: " + response);
        if (response.getBoolean("success")) {
            // Login was successful
            
            // Go to mainmenu
           
            
            String username = response.getString("username");
            Client client = AutoBattler.socketHandler.getClient();
            client.getUser().setUsername(username);
            
            PacketElement statePacket = new com.team3.autobattler.Network.Packet.Create.GameStateChangePacket(GameStates.MAINMENU);
            AutoBattler.socketHandler.sendData(statePacket);
            
        } else {
            LoginScene loginScene = (LoginScene)SceneManager.getInstance().getScene(GameStates.LOGIN);
            loginScene.loginSuccess.setText("Failed to login");
            
            
        }
        

    }
}
