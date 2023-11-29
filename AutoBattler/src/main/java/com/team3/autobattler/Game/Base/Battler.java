/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.team3.autobattler.Game.Base;
import com.team3.autobattler.Game.Base.UnitA.Unit;

import java.util.List;

/**
 * The Battler abstract class represents entities capable of participating in battles.
 * It defines common attributes such as health, turn number, and a list of units.
 * 
 * Author: Rio
 */
public abstract class Battler {
    private int health;
    private int turnNumber;
    
    private List<Unit> units;
    
    
     /**
     * Retrieves the list of units associated with the battler.
     *
     * @return The list of units, or null if the list is empty.
     */
    public List<Unit> getUnits() {
        if (units.size() <= 0) return null;
        return units;
    }
    
    /**
     * Sets the list of units associated with the battler.
     *
     * @param units The list of units.
     */
    public void setUnits(List<Unit> units) {
        this.units = units;
    }
    
    /**
     * Retrieves a unit from the list based on the specified index.
     *
     * @param index The index of the unit in the list.
     * @return The unit at the specified index, or null if the list is empty.
     */
    public Unit getUnit(int index) {
        if (units.size() <= 0) return null;
        return units.get(index);
    }
    
    /**
     * Sets a unit in the list at the specified index.
     *
     * @param index The index at which to set the unit.
     * @param unit  The unit to be set at the specified index.
     */
    public void setUnit(int index, Unit unit) {
        this.units.set(index, unit);
        
    }
}
