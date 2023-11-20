/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.team3.autobattler.Network.Packet.Create;
import com.team3.autobattler.Network.Packet.PacketBuilder;
import com.team3.autobattler.Network.Packet.PacketElement;
import com.team3.autobattler.Network.Packet.PacketVisitor;
import com.team3.autobattler.Game.GameStates;
import static com.team3.autobattler.Network.Packet.PacketElement.jsonObject;
import java.lang.reflect.Field;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Packet used to change the game state, primarily for changing scene and 
 * ensuring that the Client/Server are on the correct state.
 * @author Rio
 */
public class GameStateChangePacket implements PacketElement {
    // Required ID for each packet
    private int id = PacketBuilder.SCENECHANGE.getId();
    private GameStates gameState;

    Field fld[] = this.getClass().getDeclaredFields();

    public GameStateChangePacket(GameStates gameState) {
        //
        init();
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
    
        @Override
    public void init() {
        jsonObject.clear();
    }
}
