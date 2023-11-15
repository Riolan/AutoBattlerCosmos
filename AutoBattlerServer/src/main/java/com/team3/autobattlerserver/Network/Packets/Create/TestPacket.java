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
 * Another test packet using visitor dp
 * @author Rio
 */
public class TestPacket implements PacketElement {
    // Required ID for each packet
    private int id = PacketBuilder.TEST.getId();
    private String pong;
    
    Field fld[] = this.getClass().getDeclaredFields();
    
    /**
     * This is a test packer which is used to ensure viability as well
     * as to demonstrate how a packet should be set up.
     * @param ping
     */
    public TestPacket(String pong) {
        init();
        if (pong != "Pong") {
            System.out.println("We want to respond with a Pong to the requested Ping o/");
        }
        
        this.pong = pong;
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
