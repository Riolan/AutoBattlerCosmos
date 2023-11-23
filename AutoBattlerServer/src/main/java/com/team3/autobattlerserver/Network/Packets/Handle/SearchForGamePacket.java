/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.team3.autobattlerserver.Network.Packets.Handle;
import com.team3.autobattlerserver.Client.ClientHandler;
import com.team3.autobattlerserver.Network.PacketHandler;
import com.team3.autobattlerserver.Game.Matchmaker.ClientMatchmaker;

import org.json.JSONObject;

/**
 *
 * @author Rio
 */
public class SearchForGamePacket implements PacketHandler {
    
    @Override
    public void execute(long aId, JSONObject response) {
        System.out.println("check");
       
        System.out.println("execute: Client Id: " + aId);
        System.out.println("SearchForGamePacket: " + response);
        //if (response.getBoolean("refreshShop") && checkIfCanRefreshShop) 
        boolean x = response.getBoolean("doSearchForGame");
        System.out.println("Outpiut - " + x);
        if (response.getBoolean("doSearchForGame")) {
            // put into matc
            ClientMatchmaker matchmaker = ClientMatchmaker.getInstance();
            matchmaker.addClient(ClientHandler.clientHandlers.get(aId));
            System.out.println("client added");
        }
        

    }
    
}
