/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.team3.autobattler.Network.Packet;

import com.team3.autobattler.Network.Packet.PacketVisitor;
import com.team3.autobattler.Network.Packet.Create.*;
import org.json.JSONObject;

/**
 * This feels like it could be further reduced with reflection
 * but due to time constraints and having not worked with reflection much
 * this should be sufficient.
 * @author Rio
 */
public class PacketVisitorImpl implements PacketVisitor {
    
    @Override
    public JSONObject visit(TestPacket packet) {
        JSONObject data = packet.getJsonObject();
        return data;
    }
    
    @Override
    public JSONObject visit(GameStateChangePacket packet) {
        JSONObject data = packet.getJsonObject();
        return data;
    }
    
    
    @Override
    public JSONObject visit(ShopEntitiesPacket packet) {
        JSONObject data = packet.getJsonObject();
        return data;
    }
    
        
    @Override
    public JSONObject visit(SearchForGamePacket packet) {
        JSONObject data = packet.getJsonObject();
        return data;
    }
    
    
    @Override
    public JSONObject visit(StartGamePacket packet) {
        JSONObject data = packet.getJsonObject();
        return data;
    }
}
