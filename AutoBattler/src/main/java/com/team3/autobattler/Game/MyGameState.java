/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.team3.autobattler.Game;

import com.team3.autobattler.AutoBattler;
import com.team3.autobattler.Network.Packet.Create.GameStateChangePacket;
import com.team3.autobattler.Network.Packet.PacketElement;
import com.team3.autobattler.SceneManagement.SceneManager;

/**
 *
 * @author Rio
 */
public class MyGameState implements GameStateObserver {
    private GameStates gameState = GameStates.UNCONNECTED;
    
    @Override
    public void update(Object o) {
        this.setState((GameStates) o);
    }
    
    public void setState(GameStates newState) {
        // May need validation later. :)
        if (newState == this.gameState) return;
        this.gameState = newState;
        // change scene
        SceneManager.getInstance().changeScene(newState);
        if (newState == GameStates.UNCONNECTED) return;
        PacketElement packet = new GameStateChangePacket(newState);
        AutoBattler.socketHandler.sendData(packet);
    }
    
    
    public GameStates getState() {
        return gameState;
    }
    
    
}
