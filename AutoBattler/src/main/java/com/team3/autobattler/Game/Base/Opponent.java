/*
 
Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
*/
package com.team3.autobattler.Game.Base;

import com.team3.autobattler.Game.Factories.ItemFactory;
import java.util.List;


/**
 * Abstract out Player to Battler further
 * divide Battler into Player and Opponent
 * @author Rio, Collin
 */
public class Opponent extends Battler {
    public static Opponent INSTANCE;

    
    // Not really a neccessary component
    // will return to later.
    public Item[] items;
    
        
    public Opponent() throws IllegalAccessException {
        if (INSTANCE != null) {
            throw new IllegalAccessException("You cannot construct an instance of the Opponent class. Please use the getInstance() function.");
        }
        
    }

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
