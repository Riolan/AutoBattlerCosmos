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
    public void execute(long aId, JSONObject response) {
        System.out.println("execute: Client Id: " + aId);
        System.out.println("StartBattlePacket: " + response);
        //if (response.getBoolean("refreshShop") && checkIfCanRefreshShop) 
        boolean x = response.getBoolean("startBattle");
        System.out.println("Outpiut - " + x);
        if (response.getBoolean("startBattle")) {
            // put into matc
            ClientHandler clientHandler = ClientHandler.clientHandlers.get(aId);
            Client client = clientHandler.getClient();
            Battle battle = ClientMatchmaker.matches.get(client.getBattleId());
            battle.doBattle();
        }
        

    }
    
}
