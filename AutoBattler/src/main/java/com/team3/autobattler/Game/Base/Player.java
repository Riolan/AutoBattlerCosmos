/*
 
Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
*/
package com.team3.autobattler.Game.Base;

import com.team3.autobattler.Game.Base.UnitA.Unit;
import com.team3.autobattler.Game.Factories.ItemFactory;
import java.util.ArrayList;
import java.util.List;


/**
 * Abstract out Player to Battler further
 * divide Battler into Player and Opponent
 * @author Rio, Collin
 */
public class Player extends Battler {
    public static Player INSTANCE;
    private List<Unit> units = new ArrayList<Unit>();

    private int gold = 10;

    // Not really a neccessary component
    // will return to later.
    public Item[] items;
    
        
    public Player() throws IllegalAccessException {
        if (INSTANCE != null) {
            throw new IllegalAccessException("You cannot construct an instance of the Player class. Please use the getInstance() function.");
        }

        
    }

    public static Player getPlayer() {
        if (INSTANCE == null) 
        {
            try {
                INSTANCE = new Player();
            } catch (IllegalAccessException e) {
                
            }
        }

        return INSTANCE;
    }
          /* Could be nullable */
    public void addUnit(Unit unit) {
        units.add(unit);
    }
    
    /* Could be nullable */
    public List<Unit> getUnits() {
        if (units.size() <= 0) return null;
        return units;
    }
    
    /*  */
    public void setUnits(List<Unit> units) {
        this.units = units;
    }
    
    /* Could be nullable */
    public Unit getUnit(int index) {
        if (units.size() < index+1) return null;
        return units.get(index);
    }
    
    public void setUnits(int index, Unit unit) {
        this.units.set(index, unit);
        
    }
    public int getGold() {
        return gold;
    }
    public boolean subtractGold(int cost) {
        if (gold - cost < 0) {
        return false;
        }
        gold = gold-cost;
        return true;
    }
}
