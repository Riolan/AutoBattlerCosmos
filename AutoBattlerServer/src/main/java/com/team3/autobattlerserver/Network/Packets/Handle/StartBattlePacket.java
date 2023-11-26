/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.team3.autobattlerserver.Network.Packets.Handle;
import com.team3.autobattlerserver.Client.Client;
import com.team3.autobattlerserver.Client.ClientHandler;
import com.team3.autobattlerserver.Game.Battle;
import com.team3.autobattlerserver.Network.PacketHandler;
import com.team3.autobattlerserver.Game.Matchmaker.ClientMatchmaker;

import org.json.JSONObject;

/**
 *
 * @author Rio
 */
public class StartBattlePacket implements PacketHandler {
    
    @Override
    public void execute(ClientHandler handler, JSONObject response) {
        System.out.println("StartBattlePacket: " + response);
        
        // Allow player to dequeue from the match        
        if (response.getBoolean("startBattle")) {
            // Put player into match
            Client client = handler.getClient();
            Battle battle = ClientMatchmaker.matches.get(client.getGame().getBattleId());
            battle.doBattle(client);
        }
        

    }
    
}
