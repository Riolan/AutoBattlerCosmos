/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.team3.autobattlerserver.Network;

import com.team3.autobattlerserver.Network.Packets.Handle.*;

/**
 *
 * @author riola
 */
public class PacketHandlerFactory {
    public PacketHandler make(int packetId) {
        switch (packetId) {
            case 0: return new TestPacket();
            case 1: return new GameStateChangePacket();
            case 2: return new ShopEntitiesPacket();
            case 3: return new SearchForGamePacket();
            default: return new TestPacket();
        }
    }    
}
