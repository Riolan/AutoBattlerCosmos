/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.team3.autobattlerserver.Network.Packets.Handle;
import com.team3.autobattlerserver.Client.ClientHandler;
import com.team3.autobattlerserver.Game.GameBoard;
import com.team3.autobattlerserver.Game.Troop;
import com.team3.autobattlerserver.Game.Unit;
import com.team3.autobattlerserver.Game.UnitFactory;
import com.team3.autobattlerserver.Game.UnitType;
import com.team3.autobattlerserver.Network.PacketElement;
import com.team3.autobattlerserver.Network.PacketHandler;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;


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
        
        List<Unit> availableUnits = troop.getUnits(-1);
        Collections.shuffle(availableUnits);
        List <Unit> shopUnits = availableUnits.subList(0, 3);
        
        PacketElement packet = null;

        packet = new com.team3.autobattlerserver.Network.Packets.Create.ShopEntitiesPacket(shopUnits);
        handler.sendData(packet);
    }
}
