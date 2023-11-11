/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.team3.autobattler.Network.Packet;

import com.team3.autobattler.Network.Packet.PacketVisitor;
import com.team3.autobattler.Network.Packet.Create.*;
import org.json.JSONObject;

/**
 *
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
}
