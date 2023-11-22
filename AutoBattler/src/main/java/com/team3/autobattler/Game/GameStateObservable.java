/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.team3.autobattler.Game;

import java.util.ArrayList;
import java.util.List;

/**
 * NewsAgency
 * @author Rio
 */
public class GameStateObservable {
    private GameStates gameState;
    private List<GameStateObserver> observers = new ArrayList<>();
    
    /**
     * 
     * @param observer 
     */
    public void addObserver(GameStateObserver observer) {
        this.observers.add(observer);
    }
    
    /**
     * 
     * @param observer 
     */
    public void removeObserver(GameStateObserver observer) {
        this.observers.add(observer);
    }
    
    /**
     * 
     * @param gameState 
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
