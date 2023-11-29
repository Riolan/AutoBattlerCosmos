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
 * The Client class represents a user client in the auto-battler game.
 * It manages the user, game state, and serves as an observable for changes in game state.
 * The client can set and retrieve its user, game state, and also change the game state.
 *
 * @author rio
 */
public class Client implements Serializable {
    static User user;
    

    // Client's Game State
    MyGameState gameState = new MyGameState(GameStates.UNCONNECTED);
    // Observer changes with Client's Game State
    GameStateObservable observable; 
    
    /**
     * Constructs a new client, initializes the user, game state, and sets up the observable.
     */
    public Client() {
        user = new User();
        observable = new GameStateObservable();
        observable.addObserver(gameState);
        observable.addObserver(SceneManager.getInstance());
        
        setGameState(GameStates.UNCONNECTED);
        
        
    }
    
    /**
     * Gets the user associated with the client.
     *
     * @return The user associated with the client.
     */
    public User getUser() {
        return user;
    }
    
    /**
     * Sets the user associated with the client.
     *
     * @param user The user to set.
     */
    public void setUser(User user) {
        this.user = user;
    }
    
    /**
     * Gets the game state of the client.
     *
     * @return The game state of the client.
     */
    public MyGameState getGameState() {
        return gameState;
    }
    

     /**
     * Sets the game state of the client. Returns true if the state change is successful.
     *
     * @param gameState The new game state to set.
     * @return True if the state change is successful, false otherwise.
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
    
    /**
     * Temporarily switches to any game state for testing purposes, bypassing the usual state change checks.
     *
     * @param gameState The game state to switch to.
     * @return True if the switch is successful, false otherwise.
     */
    public boolean bypassGameState(GameStates gameState) {
        System.out.println("bypassGameState Set game state: " + gameState);
        PacketElement statePacket1 = new GameStateChangePacket(GameStates.CONNECTED);
        AutoBattler.socketHandler.sendData(statePacket1);
        PacketElement statePacket2 = new GameStateChangePacket(gameState);
        AutoBattler.socketHandler.sendData(statePacket2);  

        return true;

    }

    
    
}
