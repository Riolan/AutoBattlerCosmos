/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.team3.autobattler.Game.Base.UnitA;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Unit which is used for battle.
 * @author Rio
 */
public class Troop {
    //private List<Unit> aggregate = new ArrayList<>();
    public static Map<Integer, List<Unit>> aggregate = new HashMap();
    
    
    
    // More so add unit
    public void createUnit(int client_uuid, Unit newUnit) {
        
        System.out.println("New unit associated with: " + client_uuid);
        
        // Shared information from a UnitType
        UnitType type = UnitFactory.getUnitType(newUnit.getName(),
                newUnit.getName(), newUnit.getCost());
        // Specific information for each unit
        Unit unit = new Unit(newUnit.getHealth(),
                newUnit.getAttack(), 
                newUnit.getType());
       
        // add unit to troop aggregate (collection of units)
        if (!aggregate.containsKey(client_uuid)) {
            aggregate.put(client_uuid, null);
        }
        
        List<Unit> units = aggregate.get(client_uuid);
        if (units != null) {
            if (units.add(unit)) {
                aggregate.put(client_uuid, units);
            }
        } else {
            
            List<Unit> list_ = new ArrayList();
            list_.add(unit);
            aggregate.put(client_uuid, list_);
        }
    }

    
    
    public void createUnit(int client_uuid, int health, int attack, String name, String ability, int cost) {

        // Shared information from a UnitType
        UnitType type = UnitFactory.getUnitType(name, ability, cost);
        // Specific information for each unit
        Unit unit = new Unit(health, attack, type);
       
        // add unit to troop aggregate (collection of units)
        if (!aggregate.containsKey(client_uuid)) {
            aggregate.put(client_uuid, null);
        }
        
        List<Unit> units = aggregate.get(client_uuid);
        if (units != null) {
            if (units.add(unit)) {
                aggregate.put(client_uuid, units);
            }
        } else {
            
            List<Unit> list_ = new ArrayList();
            list_.add(unit);
            aggregate.put(client_uuid, list_);
        }
    }
    
    /* Nullable */
    public List<Unit> getUnits(int key) {
        if (aggregate.containsKey(key)) {
            return aggregate.get(key);
        }
        return null;
    }
    
    
    // remove a unit at x location.
    public boolean removeUnit(int client_uuid, int pos) {
        
        System.out.println("Removing unit associated with: " + client_uuid);
        
        if (!aggregate.containsKey(client_uuid)) return false;
        
        List<Unit> units = aggregate.get(client_uuid);
        if (units.size() - 1 < pos) {
            return false;
        }

        units.remove(pos);
        return true;
    }
    
    
    
    // Swap unit at A location to B, and B to A.
    public boolean swapUnits(int client_uuid, int A, int B) {
       
        if (!aggregate.containsKey(client_uuid)) return false;
        
        List<Unit> units = aggregate.get(client_uuid);
        
        if (units.size() < 2) return false;
        
        if (A < (units.size() - 1) || B < (units.size() - 1)) {
            return false;
        }
        
        Unit holdA = units.get(A);
        Unit holdB = units.get(B);
        units.set(A, holdB);
        units.set(B, holdA);        
        return true;
    }
    
    
}
