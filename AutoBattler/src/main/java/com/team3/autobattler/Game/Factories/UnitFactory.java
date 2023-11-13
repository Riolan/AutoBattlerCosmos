package com.team3.autobattler.Game.Factories;


import com.team3.autobattler.Game.Base.Unit;
import com.team3.autobattler.Game.Base.UnitType;

// Unit factory

import java.util.HashMap;
import java.util.Map;
import java.util.Random;


public class UnitFactory 
{
    
    static Map<String, UnitType> unitTypes = new HashMap<>();

    private static UnitFactory unitfactory = null;
        
    public Unit getUnit() {
        Random rand = new Random();
        int key = rand.nextInt(units.size());
        return units.get(key);
    }
    
    
    public static UnitType getUnitType(String name, String ability)
    {
        UnitType result = unitTypes.get(name);
        if (result == null)
        {
            result = new UnitType(name, ability);
            unitTypes.put(name, result);
        }
        return result;
    }
    

   
}