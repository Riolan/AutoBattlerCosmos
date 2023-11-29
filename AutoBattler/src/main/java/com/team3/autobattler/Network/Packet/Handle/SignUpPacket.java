/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.team3.autobattler.Network.Packet.Handle;

import com.team3.autobattler.AutoBattler;
import com.team3.autobattler.Game.Base.Player;
import com.team3.autobattler.Game.GameStates;
import com.team3.autobattler.Network.Client;
import com.team3.autobattler.Network.Packet.PacketElement;
import com.team3.autobattler.Network.Packet.PacketHandler;
import com.team3.autobattler.SceneManagement.SceneManager;
import com.team3.autobattler.SceneManagement.Scenes.LoginScene;
import com.team3.autobattler.SceneManagement.Scenes.MainMenuScene;
import com.team3.autobattler.SceneManagement.Scenes.SignUpScene;

import org.json.JSONArray;
import org.json.JSONObject;

/**
 * The SignUpPacket class implements the PacketHandler interface and is responsible
 * for processing and executing sign-up information received from the server.
 *
 * @author Emily
 */
public class SignUpPacket implements PacketHandler {
    /**
     * Executes the handling of the sign-up packet by processing the information
     * received from the server and updating the relevant scenes based on success or failure.
     *
     * @param response A JSONObject containing the sign-up information.
     */
    @Override
    public void execute(JSONObject response) {
        // response as the passed variable makes so much more sense
        System.out.println("SignUpPacket, Recieved: " + response);
        if (response.getBoolean("success")) {
            // SignUp was successful
            
            // Go to mainmenu
           
            
            String username = response.getString("username");
            Client client = AutoBattler.socketHandler.getClient();
            client.getUser().setUsername(username);
            
            MainMenuScene mainMenu = (MainMenuScene)SceneManager.getInstance().getScene(GameStates.MAINMENU);
            
            if (username != null) {
                mainMenu.welcomeUser.setText("Welcome, " + username);
                mainMenu.currency.setText("Currency: " + Player.getPlayer().getGold() + " Gold");
            } 
            else {
                mainMenu.welcomeUser.setText("SOMETHING WENT WRONG");

            }
            PacketElement statePacket = new com.team3.autobattler.Network.Packet.Create.GameStateChangePacket(GameStates.MAINMENU);
            AutoBattler.socketHandler.sendData(statePacket);
            
        } else {
            SignUpScene signUpScene = (SignUpScene)SceneManager.getInstance().getScene(GameStates.SIGNUP);
            signUpScene.usernameErrorMsg.setText("Username taken");
            
        }
        

    }
}
