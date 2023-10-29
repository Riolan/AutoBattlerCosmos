/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.team3.autobattler.Network.Packet;
import com.team3.autobattler.Network.PacketCreator;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * https://refactoring.guru/design-patterns/builder/java/example
 * @author riola
 */
public class CreateSceneChangePacket extends PacketCreator {
    
    public CreateSceneChangePacket(int id) {
        this.id = id;
    }
    
    @Override
    public JSONObject create() {

        return jsonObject;
    }
    
    
    
}
