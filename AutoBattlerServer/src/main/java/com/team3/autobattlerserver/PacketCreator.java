/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.team3.autobattlerserver;


import org.json.JSONObject;

/**
 *
 * @author Rio
 */
public interface PacketCreator {
    JSONObject jsonObject = new JSONObject();

    public JSONObject create();
    
}
