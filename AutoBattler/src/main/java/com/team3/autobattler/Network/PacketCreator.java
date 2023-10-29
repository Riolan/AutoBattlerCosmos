/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.team3.autobattler.Network;


import org.json.JSONObject;
/**
 *https://refactoring.guru/design-patterns/builder/java/example
 * @author riola
 */
public abstract class PacketCreator {

    public int id;
    public JSONObject jsonObject = new JSONObject();    
    
    public JSONObject create() {
        return jsonObject.append("id", id);
    }
    
    
    public void addInt(String nm, int in) {
        jsonObject.put(nm, in);
    }
    
    public void addString(String nm, String in) {
        jsonObject.put(nm, in);
    }
    
}
