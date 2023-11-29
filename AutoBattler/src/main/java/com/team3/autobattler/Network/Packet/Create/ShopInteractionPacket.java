/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.team3.autobattler.Network.Packet.Create;

import com.team3.autobattler.Network.Packet.PacketBuilder;
import com.team3.autobattler.Network.Packet.PacketElement;
import com.team3.autobattler.Network.Packet.PacketVisitor;
import java.lang.reflect.Field;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * The ShopInteractionPacket class implements the PacketElement interface and represents
 * a packet used for shop interactions. It contains information about the action to be
 * performed in the shop and the quantity of items bought.
 *
 * @author Rio
 */
public class ShopInteractionPacket implements PacketElement {
    // Required ID for each packet
    private int id = PacketBuilder.SHOPINTERACTION.getId();
    private int action = 0;
    private int bought = 0;

    
    Field fld[] = this.getClass().getDeclaredFields();
    
    /**
     * Constructs a ShopInteractionPacket with the specified action and quantity bought.
     *
     * @param action The action to be performed in the shop.
     * @param bought The quantity of items bought.
     */
    public ShopInteractionPacket(int action, int bought) {
        init();
        
        this.action = action;
        this.bought = bought;

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
     * @return A JSONObject containing the data of the ShopInteractionPacket.
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
     * @return A JSONObject containing the data of the ShopInteractionPacket.
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
