/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.team3.autobattlerserver.Network.Packets.Create;

import com.team3.autobattlerserver.Network.PacketBuilder;
import com.team3.autobattlerserver.Network.PacketElement;
import com.team3.autobattlerserver.Network.PacketVisitor;
import java.lang.reflect.Field;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Sends sign up information to user
 * @author Emily
 */
public class SignUpPacket implements PacketElement {
    // Required ID for each packet
    private int id = PacketBuilder.SIGNUP.getId();
    private boolean success;
    private String username;
    
    
    Field fld[] = this.getClass().getDeclaredFields();
    
    /**
     * This is a LoginPacket packet which is used 
     * 
     */
    public SignUpPacket(boolean success, String username) {
        init();

        this.success = success;
        this.username = username;
        
        
        

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
