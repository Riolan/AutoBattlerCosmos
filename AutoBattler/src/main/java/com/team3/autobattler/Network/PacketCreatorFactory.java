/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.team3.autobattler.Network;

import com.team3.autobattler.Network.Packet.TestPacket2;
import com.team3.autobattler.Network.PacketCreator;

/**
 *
 * @author riola
 */
public class PacketCreatorFactory {
    public PacketCreator make(int packetId) {
        switch (packetId) {
            case 0: return new TestPacket2(packetId);
            default: return new TestPacket2(packetId);
        }
    }
}
