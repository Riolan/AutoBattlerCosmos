/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.team3.autobattler.Network.Packet;

import com.team3.autobattler.Network.PacketHandler;
import org.json.JSONObject;

/**
 *
 * @author riola
 */
public class HandleSceneChangePacket implements PacketHandler {
    @Override
    public void execute(JSONObject inputBuffer) {
        
        // validate(inputBuffer) apart of PacketHandler, general
        // validation, then implement proper, more extansive
        System.out.println("HandleSceneChangePacket, Recieved: " + inputBuffer);
        
        
        
    
    }
    
    
    
}
