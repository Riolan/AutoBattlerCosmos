/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.team3.autobattlerserver.Game;

/**
 * Hi 
 * @author Rio
 */
public class GameStateObservable {
    private GameStates gameState;
    private GameStateObserver observer;
    
    /**
     * 
     * @param observer 
     */
    public void addObserver(GameStateObserver observer) {
        this.observer = observer;
    }
    
    /**
     * 
     * @param observer 
     */
    public void removeObserver(GameStateObserver observer) {
        this.observer = null;
    }
    
    /**
     * 
     * @param gameState 
     */
    public void setGameState(GameStates gameState) {
        // validation might be placed here
        if (observer.equals(null)) {
            // Throw error, no observer is defined.
            return;
        }
        this.gameState = gameState;
    }
    
    
}
