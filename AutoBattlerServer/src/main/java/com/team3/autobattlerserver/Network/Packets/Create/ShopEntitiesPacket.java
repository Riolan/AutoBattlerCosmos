/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.team3.autobattlerserver.Network.Packets.Create;

import com.team3.autobattlerserver.Game.GameStates;
import com.team3.autobattlerserver.Game.Unit;
import com.team3.autobattlerserver.Network.PacketBuilder;
import com.team3.autobattlerserver.Network.PacketElement;
import static com.team3.autobattlerserver.Network.PacketElement.jsonObject;
import com.team3.autobattlerserver.Network.PacketVisitor;
import java.lang.reflect.Field;
import java.util.List;
import org.json.JSONException;
import org.json.JSONObject;

/**
 *
 * @author Rio
 */
public class ShopEntitiesPacket implements PacketElement {
     // Required ID for each packet
    private int id = PacketBuilder.SHOP.getId();
    private List<Unit> units;
    
    Field fld[] = this.getClass().getDeclaredFields();
    
    
    public ShopEntitiesPacket(List<Unit> units) {
        init();
        // Store the array of units into there I think this works properly?
        this.units = units;
        
        
        
        
        
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
