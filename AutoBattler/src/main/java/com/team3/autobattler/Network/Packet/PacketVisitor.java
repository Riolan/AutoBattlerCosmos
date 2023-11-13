/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.team3.autobattler.Network.Packet;

import com.team3.autobattler.Network.Packet.Create.*;
import org.json.JSONObject;

/**
 *
 * @author Rio
 */
public interface PacketVisitor {
    JSONObject visit(TestPacket testingPacket);
    JSONObject visit(GameStateChangePacket testingPacket);
    JSONObject visit(ShopEntitiesPacket testingPacket);
}
