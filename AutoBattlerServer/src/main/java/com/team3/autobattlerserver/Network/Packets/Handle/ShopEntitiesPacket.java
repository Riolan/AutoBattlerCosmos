/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.team3.autobattlerserver.Network.Packets.Handle;
import com.team3.autobattlerserver.Client.ClientHandler;
import com.team3.autobattlerserver.Game.GameBoard;
import com.team3.autobattlerserver.Game.Troop;
import com.team3.autobattlerserver.Network.PacketElement;
import com.team3.autobattlerserver.Network.PacketHandler;


import org.json.JSONObject;

/**
 *
 * @author Rio
 */
public class ShopEntitiesPacket implements PacketHandler {
    
    @Override
    public void execute(ClientHandler handler, JSONObject response) {
       
        System.out.println("ShopEntitesPacker: " + response);
        //if (response.getBoolean("refreshShop") && checkIfCanRefreshShop) 
        GameBoard.getInstance();
        Troop troop = new Troop();

        PacketElement packet = null;

        packet = new com.team3.autobattlerserver.Network.Packets.Create.ShopEntitiesPacket(troop.getUnits(-1));

        System.out.println("--troop.getUnits(-1):" + troop.getUnits(-1));
        
        handler.sendData(packet);
        System.out.println("current gamestate" + handler.getClient().getGameState());

    }
}
