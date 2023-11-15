/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.team3.autobattlerserver.Game;

import com.team3.autobattlerserver.Game.Troop;
import java.util.Map;

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
        
        System.out.println("Adding units to troop");
        // Add units to the troop
        troop.createUnit(-1, 4, 4, "Earth", "Earth Ability");
        troop.createUnit(-1, 7, 9, "Earth", "Earth Ability");
        troop.createUnit(-1, 4, 4, "Mars", "Mars Ability");
        troop.createUnit(-1, 4, 4, "Mars", "Mars Ability");
        troop.createUnit(-1, 4, 4, "Jupiter", "Jupiter Ability");
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
