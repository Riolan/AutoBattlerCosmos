/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.team3.autobattler.Network.Packet.Handle;

import com.team3.autobattler.AutoBattler;
import com.team3.autobattler.Network.Packet.PacketHandler;
import com.team3.autobattler.Network.Packet.PacketHandler;
import com.team3.autobattler.SceneManagement.SceneManager;
import org.json.JSONObject;

/**
 *
 * @author riola
 * Todo Rename to HandleTestPacket or TestPacketHandler or rename execute to handle()
 */
public class TestPacket implements PacketHandler {
    
    @Override
    public void execute(JSONObject inputBuffer) {
        if (inputBuffer.has("pong")) System.out.println(inputBuffer.get("pong"));
    }    
}
