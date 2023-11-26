/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Enum.java to edit this template
 */
package com.team3.autobattler.Network.Packet;


import java.util.HashMap;
import java.util.Map;

/**
 *  Used as a set way for keep track of discrete packets
 * @author Rio
 */
public enum PacketBuilder {
    TEST(0), STATECHANGE(1), SHOP(2), SEARCHFORGAME(3), STARTGAME(4), LOGIN(5), BUYUNITS(6), OPPONENT(7), STARTBATTLE(8), ROUNDCHECK(9), SIGNUP(10), BATTLEOUTCOME(11), GAMERESULTS(12);
    
    private final int id;
    private static Map<Integer, PacketBuilder> map = new HashMap<Integer, PacketBuilder>();

     static {
        for (PacketBuilder packetEnum : PacketBuilder.values()) {
            map.put(packetEnum.id, packetEnum);
        }
    }
    
    PacketBuilder(int id) {
        this.id = id;
    }
    
    public int getId() {
        return id;
    }
    
    public static PacketBuilder valueOf(int val) {
        return map.get(val);
    }
    
}
