package com.team3.autobattler.Game.Base.UnitA;


//import com.team3.autobattlerserver.Game.Units.*;
import java.util.HashMap;
import java.util.Map;


/**
 * The UnitFactory class encapsulates the complexity of flyweight creation for unit types.
 * It manages the creation and reuse of UnitType objects based on their names.
 * 
 * @author Rio
 */
public class UnitFactory {

    static Map<String, UnitType> unitsTypes = new HashMap<>();

    /* Empty Constructor */
    private UnitFactory() {} 
    
    /**
     * Retrieves or creates a UnitType object based on the specified name, ability, and cost.
     * If a UnitType with the same name already exists, it is reused; otherwise, a new one is created.
     *
     * @param name    The name of the unit type.
     * @param ability The ability of the unit type.
     * @param cost    The cost of the unit type.
     * @return The UnitType object associated with the specified name.
     */
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
