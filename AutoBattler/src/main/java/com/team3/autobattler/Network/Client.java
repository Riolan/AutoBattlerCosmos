/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.team3.autobattler.Network;

import com.team3.autobattler.Game.GameStateObservable;
import com.team3.autobattler.Game.GameStateObserver;
import com.team3.autobattler.Game.GameStates;
import com.team3.autobattler.Game.MyGameState;
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
    GameStates gameState = GameStates.UNCONNECTED;
    // Observer changes with Client's Game State
    GameStateObservable observable; 
    
            
    public Client() {
        observable = new GameStateObservable();
        observable.addObserver(new MyGameState(this.gameState));
        observable.addObserver(SceneManager.getInstance());
    }
    
    
    
    
    
    public User getUser() {
        return user;
    }
    
    public void setUser(User user) {
        this.user = user;
    }
    
    public GameStates getGameState() {
        return gameState;
    }
    

    /**
     * Returns true if successfully changed state.
     * @param gameState
     * @return 
     */
    public boolean setGameState(GameStates gameState) {
        System.out.println("Set game state: " + gameState);
        if (getGameState().canChangeGameState(this.getGameState(), gameState)) {
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
        
        // Update the observerables game state
        // this automatically notifies observers
        this.observable.setGameState(gameState);
        return true;

    }

    
    
}
