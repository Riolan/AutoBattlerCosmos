/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.team3.autobattler.Network.Packet;

import com.team3.autobattler.Network.PacketCreator;
import org.json.JSONObject;

/**
 *
 * @author riola
 */
public class TestPacket2 implements PacketCreator {
    
    TestPacket2(int packetId) {
    
    }
    
    @Override
    public JSONObject create(String stringJsonObject) {
        jsonObject.put("id", 1);
        jsonObject.put("age", 19);
        return jsonObject;
    }
}
