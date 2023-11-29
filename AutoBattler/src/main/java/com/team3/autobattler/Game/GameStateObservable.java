/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.team3.autobattler.Game;

import java.util.ArrayList;
import java.util.List;

/**
 * The GameStateObservable class represents an observable subject that holds the current game state.
 * It allows observers to register and receive updates when the game state changes.
 * 
 * NewsAgency
 * 
 * @author Rio
 */
public class GameStateObservable {
    private GameStates gameState;
    private List<GameStateObserver> observers = new ArrayList<>();
    
    /**
     * Adds an observer to the list of subscribers.
     *
     * @param observer The observer to be added.
     */
    public void addObserver(GameStateObserver observer) {
        this.observers.add(observer);
    }
    
    /**
     * Removes an observer from the list of subscribers.
     *
     * @param observer The observer to be removed.
     */
    public void removeObserver(GameStateObserver observer) {
        this.observers.add(observer);
    }
    
   /**
     * Sets the current game state and notifies all registered observers.
     *
     * @param gameState The new game state.
     */
    public void setGameState(GameStates gameState) {
        // validation might be placed here
        this.gameState = gameState;
        
        System.out.println("Observer gamestate change: " + gameState);
        for (GameStateObserver observer : this.observers) {
            observer.update(gameState);
        }
    }
}
