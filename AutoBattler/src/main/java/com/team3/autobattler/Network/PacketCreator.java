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
public interface PacketCreator {
    
    JSONObject jsonObject = new JSONObject();

    int packetId = -1;
    
    public JSONObject create(String stringJsonObject);
    
}
