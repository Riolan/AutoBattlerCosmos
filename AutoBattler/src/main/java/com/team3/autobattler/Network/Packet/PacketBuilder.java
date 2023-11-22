/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Enum.java to edit this template
 */
package com.team3.autobattler.Network.Packet;

/**
 * Used as a set way for keep track of discrete packets (Should be shared.)
 * @author Rio
 */
public enum PacketBuilder {
    TEST(0), SCENECHANGE(1), SHOP(2), SEARCHFORGAME(3), STARTGAME(4);
    
    private final int id;
    
    PacketBuilder(int id) {
        this.id = id;
    }
    
    public int getId() {
        return id;
    }
    
}
