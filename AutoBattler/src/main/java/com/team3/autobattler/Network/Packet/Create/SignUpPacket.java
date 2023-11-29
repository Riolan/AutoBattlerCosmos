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
 * The SignUpPacket class implements the PacketElement interface and is used for
 * sending signup information. It contains details such as the username and password
 * for user registration.
 *
 * @author Emily
 */
public class SignUpPacket implements PacketElement {
    // Required ID for each packet
    private int id = PacketBuilder.SIGNUP.getId();
    
    private String username = "";
    private String password = "";
    
    
    Field fld[] = this.getClass().getDeclaredFields();
    
    /**
     * Constructs a SignUpPacket with the specified username and password.
     *
     * @param username The username for user registration.
     * @param password The password for user registration.
     */
    public SignUpPacket(String username, String password) {
        init();

        this.username = username;
        this.password = password;
        
        

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
     * @return A JSONObject containing the data of the SignUpPacket.
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
     * @return A JSONObject containing the data of the SignUpPacket.
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
