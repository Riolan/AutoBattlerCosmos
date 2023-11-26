/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.team3.autobattler.Network;

import com.team3.autobattler.AutoBattler;
import com.team3.autobattler.Game.GameStateObservable;
import com.team3.autobattler.Game.GameStateObserver;
import com.team3.autobattler.Game.GameStates;
import com.team3.autobattler.Game.MyGameState;
import com.team3.autobattler.Network.Packet.Create.GameStateChangePacket;
import com.team3.autobattler.Network.Packet.PacketElement;
import com.team3.autobattler.SceneManagement.SceneManager;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Rio
 */
public class Client implements Serializable {
    static User user;
    

    // Client's Game State
    MyGameState gameState = new MyGameState(GameStates.UNCONNECTED);
    // Observer changes with Client's Game State
    GameStateObservable observable; 
    
            
    public Client() {
        user = new User();
        observable = new GameStateObservable();
        observable.addObserver(gameState);
        observable.addObserver(SceneManager.getInstance());
        
        setGameState(GameStates.UNCONNECTED);
        
        
    }
    
    
    public User getUser() {
        return user;
    }
    
    public void setUser(User user) {
        this.user = user;
    }
    
    public MyGameState getGameState() {
        return gameState;
    }
    

    /**
     * Returns true if successfully changed state.
     * @param gameState
     * @return 
     */
    public boolean setGameState(GameStates gameState) {
        System.out.println("Set game state: " + gameState);
        // ew clean this up
        if (getGameState().getState().canChangeGameState(this.getGameState().getState()
                , gameState)) {
            // Update the observerables game state
            // this automatically notifies observers
            this.observable.setGameState(gameState);
            return true;
        }
        return false;
    }
    
    // temporary function to switch to any game state for testing purposes
    public boolean bypassGameState(GameStates gameState) {
        System.out.println("bypassGameState Set game state: " + gameState);
        PacketElement statePacket1 = new GameStateChangePacket(GameStates.CONNECTED);
        AutoBattler.socketHandler.sendData(statePacket1);
        PacketElement statePacket2 = new GameStateChangePacket(gameState);
        AutoBattler.socketHandler.sendData(statePacket2);  

        return true;

    }

    
    
}
