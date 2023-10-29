/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.team3.autobattler.Network.Packet;

import com.team3.autobattler.Network.PacketCreator;
import java.util.Objects;
import org.json.JSONObject;

/**
 *
 * @author riola
 */
public class TestPacket2 extends PacketCreator {
    
    // Ensure creation has id, a requirement for every packet.
    public TestPacket2(int id) {
        this.id = id;
    }
    
    public JSONObject create(String age) {
        // First call is to always init an id for the packet.
        jsonObject = super.create();
        jsonObject.append("age", age);
        return jsonObject;
    }
}
