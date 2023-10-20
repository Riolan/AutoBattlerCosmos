/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.team3.autobattlerserver.Network.Packets;
import com.team3.autobattlerserver.Network.PacketCreator;
import org.json.JSONObject;

/**
 *
 * @author riola
 */
public class TestPacket2 implements PacketCreator {
    @Override
    public JSONObject create() {
        jsonObject.put("id", 1);
        jsonObject.put("age", 19);
        return jsonObject;
    }
}
