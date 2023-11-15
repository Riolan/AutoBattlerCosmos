package com.team3.autobattlerserver.Game;


import com.team3.autobattlerserver.Game.Unit;
import com.team3.autobattlerserver.Game.UnitType;
//import com.team3.autobattlerserver.Game.Units.*;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;


/**
 * Encapsulates complexity of flyweight creation
 * @author Rio
 */
public class UnitFactory {

    static Map<String, UnitType> unitsTypes = new HashMap<>();

    /* Empty Constructor */
    private UnitFactory() {} 
    
    
    public static UnitType getUnitType(String name, String ability) {
        UnitType result = unitsTypes.get(name);
        if (result == null) {
            
            result = new UnitType(name, ability);
            unitsTypes.put(name, result);
        }
        System.out.println(unitsTypes);
        return result;
    }

}
