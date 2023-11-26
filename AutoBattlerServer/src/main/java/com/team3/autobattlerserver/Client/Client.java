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
    
    private boolean inGame = false;
    private boolean inBattle = false;
    private int battleId = -1;

    GameStates gameState; 
    int currency;
    private List<Unit> possibleShopUnits;
    
    
    
    private List<GameStateObserver> observers;
    
    
    public Client() {
        observers = new ArrayList<>(); 
        this.user = new User();
    }
    
    /**
     * Store units transmitted to player's shop
     * Used to validate buy.
     * @return 
     */
    public List<Unit> getStoredShopUnits() {
        return possibleShopUnits;
    }
    
    public void setUnits(List<Unit> units) {
        this.possibleShopUnits = units;
    }
    
    
    public int getCurrency() {
        return currency;
    }
    
    void setCurrency(int currency) {
        this.currency = currency;
    }
    
        
    public boolean getInGame() {
        return inGame;
    }
    
    public void setInGame(boolean inGame) {
        this.inGame = inGame;
    }
    
    public boolean getInBattle() {
        return inBattle;
    }
    
    public void setInBattle(boolean inBattle) {
        this.inBattle = inBattle;
    }
    public int getBattleId() {
        return battleId;
    }
    
    public void setBattleId(int battleId) {
        this.battleId = battleId;
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
