/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.team3.autobattlerserver.Network.Packets.Create;
import com.team3.autobattlerserver.Game.Game;
import com.team3.autobattlerserver.Network.PacketBuilder;
import com.team3.autobattlerserver.Network.PacketElement;
import com.team3.autobattlerserver.Network.PacketVisitor;
import com.team3.autobattlerserver.Game.GameStates;

import static com.team3.autobattlerserver.Network.PacketElement.jsonObject;

import java.lang.reflect.Field;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Sends user game results once they finish a game
 * @author Emily
 */
public class GameResultsPacket implements PacketElement {
    // Required ID for each packet
    private int id = PacketBuilder.GAMERESULTS.getId();
    private int wins;
    private int losses;

    Field fld[] = this.getClass().getDeclaredFields();

    public GameResultsPacket(Game game) {
        init();
        this.wins = game.getWins();
        this.losses = game.getLosses();
        
        
        //
        for(Field x : fld) {
            if (x.getName().equals("fld")) continue;
            try {
                jsonObject.put(x.getName(), x.get(this));
            } catch(IllegalAccessException | IllegalArgumentException | JSONException e) {
                 System.out.println("-------\n" + e + "\n-------");
            } 
        }
    }
    
    @Override
    public void init() {
        jsonObject.clear();
    }
    
    
    @Override
    public JSONObject getJsonObject() {
        return jsonObject;
    }
    
    
    @Override
    public JSONObject accept(PacketVisitor visitor) {
        return visitor.visit(this);
    }
}
