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
 * The ShopEntitiesPacket class implements the PacketElement interface and represents
 * a packet used to request shop entities. It contains information about whether the
 * client intends to refresh the shop and receive an updated list of available items.
 * 
 * @author Rio
 */
public class ShopEntitiesPacket implements PacketElement {
    // Required ID for each packet
    private int id = PacketBuilder.SHOP.getId();
    private boolean refreshShop;

    Field fld[] = this.getClass().getDeclaredFields();
    
    /**
     * Constructs a ShopEntitiesPacket with the specified refresh status.
     *
     * @param refreshShop True if the client intends to refresh the shop, false otherwise.
     */
    public ShopEntitiesPacket(boolean refreshShop) {  
        init();
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
    
    /**
     * Gets the JSON representation of the packet.
     *
     * @return A JSONObject containing the data of the ShopEntitiesPacket.
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
     * @return A JSONObject containing the data of the ShopEntitiesPacket.
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
