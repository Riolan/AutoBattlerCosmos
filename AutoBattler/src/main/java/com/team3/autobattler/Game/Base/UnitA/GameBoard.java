/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.team3.autobattler.Game.Base.UnitA;

/**
 * The GameBoard class represents the game board, managing game elements and state.
 * It is implemented as a singleton to ensure a single instance.
 * 
 * @author Rio
 */
public class GameBoard {
    public static GameBoard INSTANCE;
    Troop troop;
    
    /**
     * Private constructor to enforce the singleton pattern.
     * Throws an IllegalAccessException if an attempt is made to construct another instance.
     *
     * @throws IllegalAccessException If an attempt is made to construct another instance.
     */
    public GameBoard() throws IllegalAccessException {
        if (INSTANCE != null) {
            throw new IllegalAccessException("You cannot construct an instance of the GameBoard class. Please use the getInstance() function.");
        }
        this.troop = new Troop();
        
        // Add units to the troop
        //troop.createUnit(5, 5, "Earth", "Earth Ability", 5);
        
    }
    
    /**
     * Retrieves the singleton instance of the GameBoard class.
     *
     * @return The singleton instance of the GameBoard class.
     */
    public static GameBoard getInstance() {
        if (INSTANCE == null) {
            try {
                INSTANCE = new GameBoard();
            } catch (IllegalAccessException e) {}
        }
        return INSTANCE;
    }
    
}
