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
 * The Troop class represents a collection of units used in battle.
 * It provides methods to create, retrieve, remove, and swap units within the troop.
 * 
 * @author Rio
 */
public class Troop {
    //private List<Unit> aggregate = new ArrayList<>();
    public static Map<Integer, List<Unit>> aggregate = new HashMap();
    
    
    
    /**
     * Creates a new unit and adds it to the troop associated with the given client UUID.
     *
     * @param client_uuid The client UUID associated with the troop.
     * @param newUnit     The new unit to be added.
     */
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

    
    /**
     * Creates a new unit with specified characteristics and adds it to the troop associated with the given client UUID.
     *
     * @param client_uuid The client UUID associated with the troop.
     * @param health      The health of the new unit.
     * @param attack      The attack value of the new unit.
     * @param name        The name of the new unit.
     * @param ability     The ability of the new unit.
     * @param cost        The cost of the new unit.
     */
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
    
    /**
     * Retrieves the list of units associated with the given client UUID.
     *
     * @param key The client UUID associated with the troop.
     * @return The list of units, or null if the troop is empty.
     */
    public List<Unit> getUnits(int key) {
        if (aggregate.containsKey(key)) {
            return aggregate.get(key);
        }
        return null;
    }
    
    
    /**
     * Removes a unit from the troop associated with the given client UUID at the specified position.
     *
     * @param client_uuid The client UUID associated with the troop.
     * @param pos         The position of the unit to be removed.
     * @return True if the removal is successful, false otherwise.
     */
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
    
    
    
    /**
     * Swaps two units within the troop associated with the given client UUID.
     *
     * @param client_uuid The client UUID associated with the troop.
     * @param A           The position of the first unit.
     * @param B           The position of the second unit.
     * @return True if the swap is successful, false otherwise.
     */
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
