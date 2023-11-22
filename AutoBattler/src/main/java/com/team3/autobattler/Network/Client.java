/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.team3.autobattler.Network;

import com.team3.autobattler.Game.GameStateObservable;
import com.team3.autobattler.Game.GameStates;
import com.team3.autobattler.Game.MyGameState;
import java.io.Serializable;

/**
 *
 * @author Rio
 */
public class Client implements Serializable {
    public static Client INSTANCE;
    static User user;
    

    // Client's Game State
    MyGameState gameState;
    // Observer changes with Client's Game State
    GameStateObservable observable; 
            
    public Client() {
        observable = new GameStateObservable();
        gameState = new MyGameState();
        observable.addObserver(gameState);
        
    }
    
    public User getUser() {
        return user;
    }
    
    public void setUser(User user) {
        this.user = user;
    }
    
    public GameStates getGameState() {
        return gameState.getState();
    }
    

    /**
     * Returns true if successfully changed state.
     * @param gameState
     * @return 
     */
    public boolean setGameState(GameStates gameState) {
        if (getGameState().canChangeGameState(this.getGameState(), gameState)) {
            this.gameState.setState(gameState);
            return true;
        }
        return false;
    }
    
    public static Client getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new Client();
        }
        return INSTANCE;
    }
    
    
}
