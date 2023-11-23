/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.team3.autobattlerserver.Network.Packets.Handle;

import com.team3.autobattlerserver.Client.Client;
import com.team3.autobattlerserver.Client.User;
import com.team3.autobattlerserver.Client.ClientHandler;
import com.team3.autobattlerserver.Network.PacketElement;
import com.team3.autobattlerserver.Network.PacketHandler;

import org.json.JSONArray;
import org.json.JSONObject;

/**
 *
 * @author Rio
 */
public class LoginPacket implements PacketHandler {


    @Override
    public void execute(long aId, JSONObject response) {
        System.out.println("LoginPacket, Recieved: " + response);
        
        
        ClientHandler clientHandler = ClientHandler.clientHandlers.get(aId);
        Client client = clientHandler.getClient();
        
        String username = response.getString("username");
        String password = response.getString("password");
        
        // Validate that a user exists with that username and password.
        if (!username.equals("x")) {
            PacketElement packet = new com.team3.autobattlerserver.Network.Packets.Create.LoginPacket(false, username);
            clientHandler.sendData(packet);
            return;
        }
        
        User user = new User(username, password);
        user.setId(client.getUser().getId());
        client.setUser(user);
        
        PacketElement packet = new com.team3.autobattlerserver.Network.Packets.Create.LoginPacket(true, username);
        clientHandler.sendData(packet);
    }
}
