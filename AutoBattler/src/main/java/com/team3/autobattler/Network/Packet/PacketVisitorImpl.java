/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.team3.autobattler.Network.Packet;

import com.team3.autobattler.Network.Packet.PacketVisitor;
import com.team3.autobattler.Network.Packet.Create.*;
import org.json.JSONObject;

/**
 * The PacketVisitorImpl class implements the PacketVisitor interface and provides
 * concrete implementations for visiting different types of packets. It serves as a
 * visitor to extract data from various packet types in a unified manner.
 * 
 * @author Rio
 */
public class PacketVisitorImpl implements PacketVisitor {
    
    /**
     * Visits a TestPacket and extracts data in the form of a JSONObject.
     *
     * @param packet The TestPacket to visit.
     * @return A JSONObject containing data from the TestPacket.
     */
    @Override
    public JSONObject visit(TestPacket packet) {
        JSONObject data = packet.getJsonObject();
        return data;
    }
    
    /**
     * Visits a GameStateChangePacket and extracts data in the form of a JSONObject.
     *
     * @param packet The GameStateChangePacket to visit.
     * @return A JSONObject containing data from the GameStateChangePacket.
     */
    @Override
    public JSONObject visit(GameStateChangePacket packet) {
        JSONObject data = packet.getJsonObject();
        return data;
    }
    
    /**
     * Visits a ShopEntitiesPacket and extracts data in the form of a JSONObject.
     *
     * @param packet The ShopEntitiesPacket to visit.
     * @return A JSONObject containing data from the ShopEntitiesPacket.
     */
    @Override
    public JSONObject visit(ShopEntitiesPacket packet) {
        JSONObject data = packet.getJsonObject();
        return data;
    }
    
     /**
     * Visits a SearchForGamePacket and extracts data in the form of a JSONObject.
     *
     * @param packet The SearchForGamePacket to visit.
     * @return A JSONObject containing data from the SearchForGamePacket.
     */   
    @Override
    public JSONObject visit(SearchForGamePacket packet) {
        JSONObject data = packet.getJsonObject();
        return data;
    }
    
    /**
     * Visits a LoginPacket and extracts data in the form of a JSONObject.
     *
     * @param packet The LoginPacket to visit.
     * @return A JSONObject containing data from the LoginPacket.
     */
    @Override
    public JSONObject visit(LoginPacket packet) {
        JSONObject data = packet.getJsonObject();
        return data;
    }
    
    /**
     * Visits a StartGamePacket and extracts data in the form of a JSONObject.
     *
     * @param packet The StartGamePacket to visit.
     * @return A JSONObject containing data from the StartGamePacket.
     */
    @Override
    public JSONObject visit(StartGamePacket packet) {
        JSONObject data = packet.getJsonObject();
        return data;
    }
    
    /**
     * Visits a ShopInteractionPacket and extracts data in the form of a JSONObject.
     *
     * @param packet The ShopInteractionPacket to visit.
     * @return A JSONObject containing data from the ShopInteractionPacket.
     */
    @Override
    public JSONObject visit(ShopInteractionPacket packet) {
        JSONObject data = packet.getJsonObject();
        return data;
    }
    
    /**
     * Visits a StartBattlePacket and extracts data in the form of a JSONObject.
     *
     * @param packet The StartBattlePacket to visit.
     * @return A JSONObject containing data from the StartBattlePacket.
     */
    @Override
    public JSONObject visit(StartBattlePacket packet) {
        JSONObject data = packet.getJsonObject();
        return data;
    }
    
    /**
     * Visits a RoundCheckPacket and extracts data in the form of a JSONObject.
     *
     * @param packet The RoundCheckPacket to visit.
     * @return A JSONObject containing data from the RoundCheckPacket.
     */
    @Override
    public JSONObject visit(RoundCheckPacket packet) {
        JSONObject data = packet.getJsonObject();
        return data;
    }
    
    /**
     * Visits a SignUpPacket and extracts data in the form of a JSONObject.
     *
     * @param packet The SignUpPacket to visit.
     * @return A JSONObject containing data from the SignUpPacket.
     */
    @Override
    public JSONObject visit(SignUpPacket packet) {
        JSONObject data = packet.getJsonObject();
        return data;
    }
}
