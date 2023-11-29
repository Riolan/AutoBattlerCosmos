/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.team3.autobattler.Game;
import com.team3.autobattler.SceneManagement.SceneManager;

/**
 * The MyGameState class represents a concrete implementation of the GameStateObserver interface.
 * It maintains and observes the game state, allowing updates when the game state changes.
 *
 * @author Rio
 */
public class MyGameState implements GameStateObserver {
    private GameStates gameState = GameStates.UNCONNECTED;
    /**
     * Constructs a new MyGameState instance with an initial game state.
     *
     * @param gameState The initial game state.
     */
    public MyGameState(GameStates gameState) {
        this.gameState = gameState;
    }
    
    /**
     * Updates the game state based on the observed changes.
     *
     * @param o The object representing the new game state.
     */
    @Override
    public void update(Object o) {
        this.setState((GameStates) o);
    }
    
    /**
     * Sets the current game state.
     *
     * @param newState The new game state.
     */
    public void setState(GameStates newState) {
        this.gameState = newState;
    }
    
    /**
     * Gets the current game state.
     *
     * @return The current game state.
     */
    public GameStates getState() {
        return gameState;
    }
}
