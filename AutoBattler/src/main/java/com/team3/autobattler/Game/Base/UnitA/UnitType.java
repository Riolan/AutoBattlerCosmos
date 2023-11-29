package com.team3.autobattler.Game.Base.UnitA;

import javax.swing.ImageIcon;


/**
 * The UnitType class contains state shared by several units.
 * It represents the characteristics that are common among units of the same type.
 * 
 * @author Curbow & Rio
 */
public class UnitType {
    // A unit shares a name
    private String name;
    // ability (no reason for ability to be a string)
    // derive a class of abilities and implement the abilities
    // based off Unit information.
    private String ability;
    // icon
    private ImageIcon icon;
    private int cost;
    // priority
    
    /**
     * Constructs a new UnitType with the specified name, ability, and cost.
     *
     * @param name    The name of the unit type.
     * @param ability The ability associated with the unit type.
     * @param cost    The cost of the unit type.
     */
    public UnitType(String name, String ability, int cost) {
        this.name = name;
        this.ability = ability;
        this.cost = cost;
    }
    
    /**
     * Retrieves the name of the unit type.
     *
     * @return The name of the unit type.
     */
     public String getName() {
        return name;
    }
     
     /**
     * Retrieves the ability associated with the unit type.
     *
     * @return The ability of the unit type.
     */
    public String getAbility() {
        return ability;
    }
    
    /**
     * Retrieves the cost of the unit type.
     *
     * @return The cost of the unit type.
     */
    public int getCost() {
        return cost;
    }

    
}