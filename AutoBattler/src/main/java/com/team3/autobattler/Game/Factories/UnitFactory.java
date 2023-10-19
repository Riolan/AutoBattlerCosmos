package com.team3.autobattler.Game.Factories;


import com.team3.autobattler.Game.Base.Unit;
import com.team3.autobattler.Game.Base.UnitType;

// Unit factory

import java.util.HashMap;
import java.util.Map;

public class UnitFactory 
{
    
    static Map<String, UnitType> unitTypes = new HashMap<>();

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