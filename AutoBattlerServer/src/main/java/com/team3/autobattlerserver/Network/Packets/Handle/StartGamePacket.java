/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.team3.autobattlerserver.Network.Packets.Handle;

import com.team3.autobattlerserver.Client.Client;
import com.team3.autobattlerserver.Client.ClientHandler;
import com.team3.autobattlerserver.Network.PacketHandler;

import org.json.JSONObject;

/**
 *
 * @author Rio
 */
public class StartGamePacket implements PacketHandler {

    @Override
    public void execute(long aId, JSONObject inputBuffer) {

        System.out.println("Recieved StartGame Packed from:" + aId);
        ClientHandler clientHandler = ClientHandler.clientHandlers.get(aId);
        Client client = clientHandler.getClient();
        
        
    }
}
