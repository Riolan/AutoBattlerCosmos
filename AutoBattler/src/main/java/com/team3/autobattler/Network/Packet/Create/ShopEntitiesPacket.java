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
 * Packet used to request Shop Entities.
 * @author Rio
 */
public class ShopEntitiesPacket implements PacketElement {
    // Required ID for each packet
    private int id = PacketBuilder.SCENECHANGE.getId();
    private boolean refreshShop;

    Field fld[] = this.getClass().getDeclaredFields();

    public ShopEntitiesPacket(boolean refreshShop) {        
        // Request the server to send a refreshed list of the shop
        // will return a new shop if it can, must first check the
        // players cash to see if they can.
        this.refreshShop = refreshShop;
        
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
