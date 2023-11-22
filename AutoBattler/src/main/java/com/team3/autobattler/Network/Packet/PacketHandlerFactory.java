/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.team3.autobattler.Network.Packet;

import com.team3.autobattler.Network.Packet.Handle.*;
import com.team3.autobattler.Network.Packet.PacketHandler;

/**
 *
 * @author riola
 */
public class PacketHandlerFactory {
    public PacketHandler make(int packetId) {
        return switch (PacketBuilder.values()[packetId]) {
            case TEST -> new TestPacket();
            case SCENECHANGE -> new GameStateChangePacket();
            case SHOP -> new ShopEntitiesPacket();
            default -> new TestPacket();
        }; //case SEARCHFORGAME: return new SearchForGamePacket();
        //case STARTGAME: return new StartGamePacket();
    }    
}
