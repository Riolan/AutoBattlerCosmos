/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.team3.autobattlerserver.Client;

import java.io.Serializable;
import com.team3.autobattlerserver.Game.GameStates;
/**
 *
 * @author Rio
 */
public class Client implements Serializable {
    User user;
    GameStates gameState; 
    int currency;

    
    public int getCurrency() {
        return currency;
    }
    
    void setCurrency(int currency) {
        this.currency = currency;
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
    

    // If able to change the gamestate change it and return true.
    public boolean setGameState(GameStates gameState) {
        if (this.gameState.canChangeGameState(this.gameState, gameState)) {
            this.gameState = gameState;
            return true;
        }
        return false;
    }
    
    
}
