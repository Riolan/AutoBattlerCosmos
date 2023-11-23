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
        return switch (PacketBuilder.valueOf(packetId)) {
            case TEST -> new TestPacket();
            case SCENECHANGE -> new GameStateChangePacket();
            case SHOP -> new ShopEntitiesPacket();
            case LOGIN -> new LoginPacket();
            default -> new TestPacket();
        }; //case : return new SearchForGamePacket();
    }    
}
