/*
 
Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
*/
package com.team3.autobattler.Game.Base;

import com.team3.autobattler.Game.Factories.ItemFactory;
import java.util.List;


/**
 * The Opponent class represents an opponent in the game, extending the Battler class.
 * It is implemented as a singleton to ensure a single instance.
 * 
 * @author Rio, Collin
 */
public class Opponent extends Battler {
    public static Opponent INSTANCE;

    
    /** 
     * An array of items associated with the opponent.
     * Note: This component may not be necessary and will be revisited later.
     */
    public Item[] items;
    
    /**
     * Private constructor to enforce the singleton pattern.
     * Throws an IllegalAccessException if an attempt is made to construct another instance.
     *
     * @throws IllegalAccessException If an attempt is made to construct another instance.
     */    
    public Opponent() throws IllegalAccessException {
        if (INSTANCE != null) {
            throw new IllegalAccessException("You cannot construct an instance of the Opponent class. Please use the getInstance() function.");
        }
        
    }
    
    /**
     * Retrieves the singleton instance of the Opponent class.
     *
     * @return The singleton instance of the Opponent class.
     */
    public static Opponent getOpponent() {
        if (INSTANCE == null) 
        {
            try {
                INSTANCE = new Opponent();
            } catch (IllegalAccessException e) {
                
            }
        }

        return INSTANCE;
    }

}
