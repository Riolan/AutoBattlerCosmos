/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.team3.autobattler.Game.Base.UnitA;

import java.util.ArrayList;
import java.util.List;

/**
 * Unit which is used for battle.
 * @author Rio
 */
public class Troop {
    public List<Unit> aggregate = new ArrayList<>();
    
    public void createUnit(int health, int attack, String name, String ability) {
        // Shared information from a UnitType
        UnitType type = UnitFactory.getUnitType(name, ability);
        // Specific information for each unit
        Unit unit = new Unit(health, attack, type);
        // add unit to troop aggregate (collection of units)
        aggregate.add(unit);
    }
    
}
