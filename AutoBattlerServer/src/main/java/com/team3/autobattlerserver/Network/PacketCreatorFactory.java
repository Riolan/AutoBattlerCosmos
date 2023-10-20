/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.team3.autobattlerserver.Network;

import com.team3.autobattlerserver.Network.Packets.TestPacket2;

/**
 *
 * @author Rio
 */
public class PacketCreatorFactory {
    public PacketCreator make(int packetId) {
        switch (packetId) {
            case 0: return new TestPacket2();
            default: return new TestPacket2();
        }
    }
}
