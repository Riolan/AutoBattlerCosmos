/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.team3.autobattlerserver.Network.Packets.Handle;
import com.team3.autobattlerserver.Network.PacketHandler;


import org.json.JSONObject;

/**
 *
 * @author Rio
 */
public class ShopEntitiesPacket implements PacketHandler {
    
    @Override
    public void execute(int aId, JSONObject response) {
       
        System.out.println("execute: Client Id: " + aId);
        System.out.println("ShopEntitesPacker: " + response);
        //if (response.getBoolean("refreshShop") && checkIfCanRefreshShop) 
        
        
        

    }
}
