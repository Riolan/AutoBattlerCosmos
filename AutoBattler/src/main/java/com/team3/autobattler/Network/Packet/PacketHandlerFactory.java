/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.team3.autobattler.Network.Packet;


import com.team3.autobattler.Network.Packet.Handle.*;
import static com.team3.autobattler.Network.Packet.PacketBuilder.*;
import com.team3.autobattler.Network.Packet.PacketHandler;

/**
 *
 * @author Rio
 */
public class PacketHandlerFactory {
    public PacketHandler make(int packetId) {
        return switch (PacketBuilder.values()[packetId]) {
            case TEST -> new TestPacket();
            case STATECHANGE -> new GameStateChangePacket();
            case SHOP -> new ShopEntitiesPacket();
            case LOGIN -> new LoginPacket();
            case OPPONENT -> new OpponentPacket();
            case BATTLEOUTCOME -> new BattleOutcomePacket();
            case GAMERESULTS -> new GameResultsPacket();
            case SIGNUP -> new SignUpPacket();
            default -> new TestPacket();
        }; //case SEARCHFORGAME: return new SearchForGamePacket();
        //case STARTGAME: return new StartGamePacket();
    }    
}
