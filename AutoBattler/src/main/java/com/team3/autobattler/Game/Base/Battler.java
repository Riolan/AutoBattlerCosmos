/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.team3.autobattler.Game.Base;
import com.team3.autobattler.Game.Base.UnitA.Unit;

import java.util.List;

/**
 *
 * @author Rio
 */
public abstract class Battler {
    private int health;
    private int turnNumber;
    
    private List<Unit> units;
    
    
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
        if (units.size() <= 0) return null;
        return units.get(index);
    }
    
    public void setUnits(int index, Unit unit) {
        this.units.set(index, unit);
        
    }
}
