/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.team3.autobattler.Network.Packet.Handle;

import com.team3.autobattler.AutoBattler;
import com.team3.autobattler.Game.GameStates;
import com.team3.autobattler.Network.Packet.PacketHandler;
import com.team3.autobattler.SceneManagement.SceneManager;
import org.json.JSONObject;

/**
 *
 * @author Rio
 */
public class ShopEntitiesPacket implements PacketHandler {
    @Override
    public void execute(JSONObject response) {
        // response as the passed variable makes so much more sense
        System.out.println("ShopEntitiesPacket, Recieved: " + response);
        
        /*
        JSONArray unitOne = new JSONArray();
        String name, int hp, int dmg, Ability ability = getRandomUnit(roundLevel);
        unit1.put(name);
        unit1.put(...);
        unit1.put(ability);
        
        JSONObject response = new JSONObject();
        response.put(unitOne);
        response.put(unitN);
        
        */
        
        //Unit unitOne = response.getJSONArray(key)
        
        // Place unit into shop board
        // 
        if (AutoBattler.socketHandler.getClient().getGameState() == GameStates.SHOP) {
            SceneManager sceneManager = SceneManager.getInstance();
            
            javax.swing.JPanel shopScene = sceneManager.getScene(GameStates.SHOP);
            // shopScene.setUnits();
            
            // Update Panel with new information, not sure if this is the correct way to do it yet
            // .validate
            // .repaint
            // ^^ is more likely the solution
            sceneManager.changeScene(GameStates.SHOP);
        }

    }
}
