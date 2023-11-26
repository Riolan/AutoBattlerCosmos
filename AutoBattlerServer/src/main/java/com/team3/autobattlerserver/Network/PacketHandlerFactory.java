/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.team3.autobattlerserver.Network;

import com.team3.autobattlerserver.Network.Packets.Handle.*;

/**
 *
 * @author Rio
 */
public class PacketHandlerFactory {
    public PacketHandler make(int packetId) {
        return switch (PacketBuilder.valueOf(packetId)) {
            case TEST -> new TestPacket();
            case STATECHANGE -> new GameStateChangePacket();
            case SHOP -> new ShopEntitiesPacket();
            case SEARCHFORGAME -> new SearchForGamePacket();
            case STARTGAME -> new StartGamePacket();
            case LOGIN -> new LoginPacket();
            case BUYUNITS -> new BuyUnitsPacket();         
            case STARTBATTLE -> new StartBattlePacket();
            case ROUNDCHECK ->  new RoundCheckPacket();
            case SIGNUP -> new SignUpPacket();
            default -> new TestPacket();
        }; 
    }    
}
