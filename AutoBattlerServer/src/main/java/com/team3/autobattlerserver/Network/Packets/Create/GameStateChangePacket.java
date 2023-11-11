/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.team3.autobattlerserver.Network.Packets.Create;
import com.team3.autobattlerserver.Network.PacketBuilder;
import com.team3.autobattlerserver.Network.PacketElement;
import com.team3.autobattlerserver.Network.PacketVisitor;
import com.team3.autobattlerserver.Game.GameStates;

import static com.team3.autobattlerserver.Network.PacketElement.jsonObject;

import java.lang.reflect.Field;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Packet used to change scene.
 * @author riola
 */
public class GameStateChangePacket implements PacketElement {
    // Required ID for each packet
    private int id = PacketBuilder.SCENECHANGE.getId();
    private GameStates gameState;

    Field fld[] = this.getClass().getDeclaredFields();

    public GameStateChangePacket(GameStates gameState) {
        //
        this.gameState = gameState;
        
        
        //
        for(Field x : fld) {
            if (x.getName().equals("fld")) continue;
            try {
                jsonObject.put(x.getName(), x.get(this));
            } catch(IllegalAccessException | IllegalArgumentException | JSONException e) {
                 System.out.println("-------\n" + e + "\n-------");
            } 
        }
    }
    
    @Override
    public JSONObject getJsonObject() {
        return jsonObject;
    }
    
    
    @Override
    public JSONObject accept(PacketVisitor visitor) {
        return visitor.visit(this);
    }
}
