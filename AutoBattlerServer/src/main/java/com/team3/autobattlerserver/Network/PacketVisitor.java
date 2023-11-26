/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.team3.autobattlerserver.Network;

import com.team3.autobattlerserver.Network.Packets.Create.*;
import org.json.JSONObject;

/**
 *
 * @author Rio
 */
public interface PacketVisitor {
    JSONObject visit(TestPacket packet);
    JSONObject visit(GameStateChangePacket packet);
    JSONObject visit(ShopEntitiesPacket packet);
    JSONObject visit(OpponentPacket packet);
    JSONObject visit(LoginPacket packet);
    JSONObject visit(BattleOutcomePacket packet);
    JSONObject visit(GameResultsPacket packet);
    JSONObject visit(SignUpPacket packet);
}
