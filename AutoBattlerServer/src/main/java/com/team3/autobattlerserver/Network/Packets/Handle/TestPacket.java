/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.team3.autobattlerserver.Network.Packets.Handle;
import com.team3.autobattlerserver.Client.ClientHandler;

import com.team3.autobattlerserver.Network.PacketHandler;
import org.json.JSONObject;


/**
 *
 * @author Rio
 */
public class TestPacket implements PacketHandler {
    
    @Override
    public void execute(ClientHandler handler, JSONObject response) {
                
        System.out.println("SearchForGamePacket: " + response);

        if (response.has("ping")) System.out.println(response.get("ping"));
        
        
        
    }    
    
   
    
}
