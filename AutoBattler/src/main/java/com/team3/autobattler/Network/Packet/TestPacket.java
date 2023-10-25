/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.team3.autobattler.Network.Packet;

import com.team3.autobattler.AutoBattler;
import com.team3.autobattler.Network.PacketHandler;
import com.team3.autobattler.Network.PacketHandler;
import com.team3.autobattler.SceneManagement.SceneManager;
import org.json.JSONObject;

/**
 *
 * @author riola
 * @Todo Rename to HandleTestPacket or TestPacketHandler or rename execute to handle()
 */
public class TestPacket implements PacketHandler {
    
    @Override
    public void execute(JSONObject inputBuffer) {
        
        // validate(inputBuffer) apart of PacketHandler, general
        // validation, then implement proper, more extansive
        System.out.println("Test Packet Handler, Recieved: " + inputBuffer);
    
        newScreen();    
    }
    
    private void newScreen() {
        // Make a GUI class that handles every page frame swing etc.
        System.out.println("Changing screen to TEST");
        SceneManager scene = SceneManager.getInstance();
        scene.changeScene(SceneManager.GameStates.TEST2);
        
    }
    
    
}
