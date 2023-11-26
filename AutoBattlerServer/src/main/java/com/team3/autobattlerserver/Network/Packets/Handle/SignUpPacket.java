/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.team3.autobattlerserver.Network.Packets.Handle;

import com.team3.autobattlerserver.AutoBattlerServer;
import com.team3.autobattlerserver.Client.Client;
import com.team3.autobattlerserver.Client.User;
import com.team3.autobattlerserver.Client.ClientHandler;
import com.team3.autobattlerserver.Network.PacketElement;
import com.team3.autobattlerserver.Network.PacketHandler;

import org.json.JSONObject;

/**
 * checks if user already exists, otherwise adds user to user data file
 * @author Emily
 */
public class SignUpPacket implements PacketHandler {


    @Override
    public void execute(ClientHandler handler, JSONObject response) {
        System.out.println("SignUpPacket, Recieved: " + response);
        
        
        Client client = handler.getClient();
        
        String username = response.getString("username");
        String password = response.getString("password");
        
        // Validate that a user exists with that username and password.
        if (AutoBattlerServer.userDataAccess.getUser(username) != null) {
            PacketElement packet = new com.team3.autobattlerserver.Network.Packets.Create.SignUpPacket(false, username);
            handler.sendData(packet);
            return;
        }
        
        User user = new User(username, password);
        user.setId(AutoBattlerServer.userDataAccess.getUserAmt());
        client.setUser(user);
        
        AutoBattlerServer.userDataAccess.save(user);
        
        PacketElement packet = new com.team3.autobattlerserver.Network.Packets.Create.SignUpPacket(true, username);
        handler.sendData(packet);
        //PacketElement statePacket = new com.team3.autobattlerserver.Network.Packets.Create.GameStateChangePacket(GameStates.MAINMENU);
        //clientHandler.sendData(statePacket);
    }
}
