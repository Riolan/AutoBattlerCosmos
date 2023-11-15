/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.team3.autobattlerserver.Game;

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
    private static Map<Integer, List<Unit>> aggregate = new HashMap();
    
    public void createUnit(int client_uuid, int health, int attack, String name, String ability) {
        
        System.out.println("New unit!");
        
        // Shared information from a UnitType
        UnitType type = UnitFactory.getUnitType(name, ability);
        // Specific information for each unit
        Unit unit = new Unit(health, attack, type);
        // add unit to troop aggregate (collection of units)
        //if (client_uuid == -1) return;
        if (!aggregate.containsKey(client_uuid)) {
            aggregate.put(client_uuid, null);
        }
        
        List<Unit> units = aggregate.get(client_uuid);
        if (units != null) {
            if (units.add(unit)) {
                System.out.println("3New unit!" + aggregate.get(client_uuid));
                aggregate.put(client_uuid, units);
            }
        } else {
            System.out.println("2New unit!" + aggregate.get(client_uuid));
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
    
}
