/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.team3.autobattlerserver.Network;

import com.team3.autobattlerserver.Network.Packets.Handle.*;
import com.team3.autobattlerserver.Network.PacketBuilder;

/**
 *
 * @author Rio
 */
public class PacketHandlerFactory {
    public PacketHandler make(int packetId) {
        switch (PacketBuilder.values()[packetId]) {
            case TEST: return new TestPacket();
            case SCENECHANGE: return new GameStateChangePacket();
            case SHOP: return new ShopEntitiesPacket();
            case SEARCHFORGAME: return new SearchForGamePacket();
            case STARTGAME: return new StartGamePacket();
            default: return new TestPacket();
        }
    }    
}
