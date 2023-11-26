/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.team3.autobattlerserver.Network.Packets.Create;
import com.team3.autobattlerserver.Client.Client;
import com.team3.autobattlerserver.Client.User;
import com.team3.autobattlerserver.Network.PacketBuilder;
import com.team3.autobattlerserver.Network.PacketElement;
import com.team3.autobattlerserver.Network.PacketVisitor;
import com.team3.autobattlerserver.Game.GameStates;
import com.team3.autobattlerserver.Game.Troop;
import com.team3.autobattlerserver.Game.Unit;

import static com.team3.autobattlerserver.Network.PacketElement.jsonObject;

import java.lang.reflect.Field;
import java.util.List;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Packet used for sending data about the respective opponent.
 * @author Rio
 * @author Emily
 */
public class OpponentPacket implements PacketElement {
    // Required ID for each packet
    private int id = PacketBuilder.OPPONENT.getId();
    private String name;
    private List<Unit> units;

    Field fld[] = this.getClass().getDeclaredFields();

    public OpponentPacket(Client opponent) {
        init();
        
        
        Troop troop = new Troop();
        System.out.println("Creating Opponent Packet");
        
        
        User user = opponent.getUser();
        // Information for client
        this.name = user.getUsername();
        this.units = troop.getUnits((int)user.getId());
        
        
        System.out.println(units);
        System.out.println(name);

        for(Field x : fld) {
            if (x.getName().equals("fld")) continue;
            try {
                jsonObject.put(x.getName(), x.get(this));
            } catch(IllegalAccessException | IllegalArgumentException | JSONException e) {
                 System.out.println("-------\n" + e + "\n-------");
            } 
        }
        System.out.println("create opponent packet done");
    }
    
    @Override
    public void init() {
        jsonObject.clear();
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
