package com.team3.autobattler.Game.Factories;


import com.team3.autobattler.Game.Base.Item;
import com.team3.autobattler.Game.Base.Unit;
import com.team3.autobattler.Game.Base.UnitType;

// Unit factory

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class UnitFactory 
{

    public Map<Integer, Unit> units = new HashMap<>();
    private static UnitFactory unitfactory = null;

    public Unit getUnit() {
        Random rand = new Random();
        int key = rand.nextInt(units.size());
        return units.get(key);
    }

    public int getNumUnits() {
        return units.size();
    }
    private void initializeUnits()
    {
        units.put(units.size(), new Unit(1,2,new UnitType("testOne","testOne")));
        units.put(units.size(), new Unit(1,2,new UnitType("testTwo","testTwo")));
        units.put(units.size(), new Unit(1,2,new UnitType("testThree","testThree")));
        units.put(units.size(), new Unit(1,2,new UnitType("testFour","testFour")));
    }
    private UnitFactory() 
    {
        initializeUnits();
    } 

    public static UnitFactory getInstance() 
    { 
        if (unitfactory == null) 
        {
            unitfactory = new UnitFactory(); 

        }

        return unitfactory; 
    } 
}
/*
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
*/