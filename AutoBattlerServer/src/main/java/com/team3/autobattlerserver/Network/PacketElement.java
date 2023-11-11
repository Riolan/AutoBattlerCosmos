/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.team3.autobattlerserver.Network;
import org.json.JSONObject;

/**
 * Part of Visitor implementation
 * @author Rio
 */
public interface PacketElement {
    // Data object of the packet in JSON.
    JSONObject jsonObject = new JSONObject();
    
    // Standard visitor accept.
    public JSONObject accept(PacketVisitor vistor);
    
    // Necessary component of every packet, the output.        
    public JSONObject getJsonObject();
    
}
