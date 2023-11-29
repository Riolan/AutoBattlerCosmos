/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.team3.autobattler.Network.Packet.Create;

import com.team3.autobattler.Network.Packet.PacketBuilder;
import com.team3.autobattler.Network.Packet.PacketElement;
import static com.team3.autobattler.Network.Packet.PacketElement.jsonObject;
import com.team3.autobattler.Network.Packet.PacketVisitor;
import java.lang.reflect.Field;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * The StartBattlePacket class implements the PacketElement interface and is used
 * to signal the start of a battle. It contains information about whether the battle
 * should start or not.
 *
 * @author Rio
 */
public class StartBattlePacket implements PacketElement {
    // Required ID for each packet
    private int id = PacketBuilder.STARTBATTLE.getId();
    private boolean startBattle;
    
    Field fld[] = this.getClass().getDeclaredFields();
    
    /**
     * Constructs a StartBattlePacket with the specified startBattle flag.
     *
     * @param startBattle A Boolean flag indicating whether the battle should start.
     */
    public StartBattlePacket(boolean startBattle) {  
        init();
        this.startBattle = startBattle;
        
        for(Field x : fld) {
            if (x.getName().equals("fld")) continue;
            try {
                jsonObject.put(x.getName(), x.get(this));
            } catch(IllegalAccessException | IllegalArgumentException | JSONException e) {
                 System.out.println("-------\n" + e + "\n-------");
            } 
        }
    }
    
    /**
     * Gets the JSON representation of the packet.
     *
     * @return A JSONObject containing the data of the StartBattlePacket.
     */
    @Override
    public JSONObject getJsonObject() {
        return jsonObject;
    }
    
    /**
     * Accepts a PacketVisitor and returns the JSON representation of the packet
     * based on the visitor pattern.
     *
     * @param visitor The PacketVisitor to accept.
     * @return A JSONObject containing the data of the StartBattlePacket.
     */
    @Override
    public JSONObject accept(PacketVisitor visitor) {
        return visitor.visit(this);
    }
    
    /**
     * Initializes the JSON object.
     */
    @Override
    public void init() {
        jsonObject.clear();
    }
}