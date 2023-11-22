/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.team3.autobattlerserver.Client;

import java.io.Serializable;
import com.team3.autobattlerserver.Game.GameStates;
import com.team3.autobattlerserver.Game.GameStateObserver;
import com.team3.autobattlerserver.Game.GameStateObservable;
import com.team3.autobattlerserver.Game.Unit;
import java.util.List;
import java.util.ArrayList;
/**
 *
 * @author Rio
 */
public class Client implements Serializable, GameStateObservable {
    User user;
    GameStates gameState; 
    int currency;
    private List<Unit> units;
    private List<GameStateObserver> observers = new ArrayList<>();
    
    
    public Client() {
        this.user = new User();
    }
    
    
    public List<Unit> getUnits() {
        return units;
    }
    
    void setUnits(List<Unit> units) {
        this.units = units;
    }
    
    
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
            notifyObservers();
            return true;
        }
        return false;
    }
    
    @Override
    public void addObserver(GameStateObserver observer) {
        observers.add(observer);
    }
    
    @Override
    public void removeObserver(GameStateObserver observer) {
        observers.remove(observer);
    }
    
    @Override
    public void notifyObservers() {
        for (GameStateObserver observer : observers) {
            observer.update(gameState);
        }
    }
}
