package com.team3.autobattler.Game.Base.UnitA;


//import com.team3.autobattlerserver.Game.Units.*;
import java.util.HashMap;
import java.util.Map;


/**
 * Encapsulates complexity of flyweight creation.
 * @author Rio
 */
public class UnitFactory {

    static Map<String, UnitType> unitsTypes = new HashMap<>();

    /* Empty Constructor */
    private UnitFactory() {} 
    
    
    public static UnitType getUnitType(String name, String ability, int cost) {
        UnitType result = unitsTypes.get(name);
        if (result == null) {
            System.out.println("---- a new unittype is added");
            result = new UnitType(name, ability, cost);
        }
        System.out.println("---- REUSING!!!");
        return result;
    }

}
