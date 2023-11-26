/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.team3.autobattlerserver.Network.Packets.Handle;

import com.team3.autobattlerserver.AutoBattlerServer;
import com.team3.autobattlerserver.Client.Client;
import com.team3.autobattlerserver.Client.UserDataAccess;
import com.team3.autobattlerserver.Client.User;
import com.team3.autobattlerserver.Client.ClientHandler;
import com.team3.autobattlerserver.Network.PacketElement;
import com.team3.autobattlerserver.Network.PacketHandler;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;

/**
 *
 * @author Rio
 */
public class LoginPacket implements PacketHandler {


    @Override
    public void execute(ClientHandler handler, JSONObject response) {
        System.out.println("LoginPacket, Recieved: " + response);
        
        
        Client client = handler.getClient();
        
        String username = response.getString("username");
        String password = response.getString("password");
        
        User user = AutoBattlerServer.userDataAccess.getUser(username);
        // Validate that a user exists with that username and password.
        if (user == null || !password.equals(user.getPassword())) {
            PacketElement packet = new com.team3.autobattlerserver.Network.Packets.Create.LoginPacket(false, username);
            handler.sendData(packet);
            return;
        }

        client.setUser(user);
                
        PacketElement packet = new com.team3.autobattlerserver.Network.Packets.Create.LoginPacket(true, username);
        handler.sendData(packet);
    }
}
