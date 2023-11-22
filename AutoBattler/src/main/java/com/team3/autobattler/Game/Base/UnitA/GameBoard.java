/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.team3.autobattler.Game.Base.UnitA;

/**
 * Will eventually generate based off a JSON input file.
 * @author Rio
 */
public class GameBoard {
    public static GameBoard INSTANCE;
    Troop troop;
    
    
    public GameBoard() throws IllegalAccessException {
        if (INSTANCE != null) {
            throw new IllegalAccessException("You cannot construct an instance of the GameBoard class. Please use the getInstance() function.");
        }
        this.troop = new Troop();
        
        // Add units to the troop
        //troop.createUnit(5, 5, "Earth", "Earth Ability", 5);
        
    }
    
    public static GameBoard getInstance() {
        if (INSTANCE == null) {
            try {
                INSTANCE = new GameBoard();
            } catch (IllegalAccessException e) {}
        }
        return INSTANCE;
    }
    
}
