/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Enum.java to edit this template
 */
package com.team3.autobattlerserver.Network;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

/**
 *  I do not like this as a name ;/
 *  Later to be used as a set way for keep track of discrete packets
 * @author Rio
 */
public enum PacketBuilder {
    TEST(0), SCENECHANGE(1), SHOP(2), SEARCHFORGAME(3), LOGIN(5);
    
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
