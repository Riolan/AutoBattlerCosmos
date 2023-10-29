/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Enum.java to edit this template
 */
package com.team3.autobattler.Network.Packet;

/**
 *  I do not like this as a name ;/
 * @author riola
 */
public enum PacketBuilder {
    TESTPACKET(0);
    
    private final int id;
    
    PacketBuilder(int id) {
        this.id = id;
    }
    
    public int getId() {
        return id;
    }
    
}
