/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.team3.autobattlerserver.Game;

import com.team3.autobattlerserver.Game.Troop;
import com.team3.autobattlerserver.Game.Units.UnitReader;
import java.util.Map;

/**
 * Will eventually generate based off a JSON input file.
 * @author Rio
 */
public class GameBoard {
    public static GameBoard INSTANCE;
    Troop troop;

    
    public Troop getTroop() {
        return troop;
    }
    
    public GameBoard() throws IllegalAccessException {
        if (INSTANCE != null) {
            throw new IllegalAccessException("You cannot construct an instance of the GameBoard class. Please use the getInstance() function.");
        }
        this.troop = new Troop();
        
        //System.out.println("Adding units to troop");
  
        /* Custom way to add units
        
        uhhhhhhhhhhhh something may be off 
        the the memlocation is the same so like if called again???
        IT IS NOT YUAYYY
        
        
        */
//        UnitReader unitReader = new UnitReader();
//        //System.out.println(UnitReader.values);
//        //System.out.println("================");
//        troop.createUnit(-1, UnitReader.values.get(0));
//        troop.createUnit(-1, UnitReader.values.get(0));
        //System.out.println("================");
        /* 
           Add units to the troop
           intrinsic - state that naturally belongs to the 'FlyWeight' object and 
           thus should be permanent or immutable (internal) or context free. 
        */
        
        // Units intrisic types are: name and ability
       
        /*
            extrinsic - state that belongs to the context of the object (external) 
            or unique to that instance
        */
        
        // Units extrensic types are: associated client, health, attack
        
        troop.createUnit(-1, 3, 2, "Mercury", "Mercury Ability", 2);
        troop.createUnit(-1, 3, 5, "Venus", "Venus Ability", 3);
        troop.createUnit(-1, 6, 3,  "Earth", "Earth Ability", 5);
        troop.createUnit(-1, 5, 3, "Mars", "Mars Ability", 3);
        troop.createUnit(-1, 15, 8, "Jupiter", "Jupiter Ability", 15);
        troop.createUnit(-1, 9, 12, "Saturn", "Saturn Ability", 12);
        troop.createUnit(-1, 5, 8, "Uranus", "Uranus Ability", 8);
        troop.createUnit(-1, 8, 6, "Neptune", "Neptune Ability", 8);
        troop.createUnit(-1, 2, 1, "Pluto", "Pluto Ability", 1);

        
        
        
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
