/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.team3.autobattler.Network;

import com.team3.autobattler.Network.Packet.TestPacket;
import com.team3.autobattler.Network.PacketHandler;

/**
 *
 * @author riola
 */
public class PacketHandlerFactory {
    public PacketHandler make(int packetId) {
        switch (packetId) {
            case 0: return new TestPacket();
            default: return new TestPacket();
        }
    }    
}
