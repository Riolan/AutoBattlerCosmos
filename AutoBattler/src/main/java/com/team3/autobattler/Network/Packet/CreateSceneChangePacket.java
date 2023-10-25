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
public class CreateSceneChangePacket implements PacketCreator {
    @Override
    public JSONObject create(String stringJsonObject) {
        try {
            JSONObject jsonObject = new JSONObject(stringJsonObject);
        } catch (JSONException err){

        }
        return jsonObject;
    }
}
