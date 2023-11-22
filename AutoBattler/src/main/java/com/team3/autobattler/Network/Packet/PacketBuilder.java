/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Enum.java to edit this template
 */
package com.team3.autobattler.Network.Packet;

/**
 *  This should be under a shared repo for both the server and client.
 *  Later to be used as a set way for keep track of discrete packets
 * @author riola
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
