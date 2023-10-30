/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.team3.autobattlerserver.Network.Packets.Create;

import com.team3.autobattlerserver.Game.GameStates;
import com.team3.autobattlerserver.Network.PacketCreator;
import static com.team3.autobattlerserver.Network.PacketCreator.jsonObject;
import org.json.JSONObject;

/**
 *
 * @author Rio
 */
public class GameStateChangePacket implements PacketCreator {
    @Override
    public JSONObject create() {
        jsonObject.put("id", 1);
        jsonObject.put("gameState", GameStates.CONNECTED);
        return jsonObject;
    }
}
