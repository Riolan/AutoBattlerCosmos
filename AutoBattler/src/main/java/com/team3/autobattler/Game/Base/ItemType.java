package com.team3.autobattler.Game.Base;

/**
 * The ItemType class represents shared characteristics of items in the game.
 * It encapsulates properties such as name and ability.
 * 
 * @author [Author's Name]
 */
public class ItemType {
    private String name;
    private String ability;
    
    /**
     * Constructs a new ItemType instance with the specified name and ability.
     *
     * @param name    The name of the item type.
     * @param ability The ability associated with the item type.
     */
    public ItemType(String name, String ability) {
        this.name = name;
        this.ability = ability;
    }

    /**
     * Gets the name of the item type.
     *
     * @return The name of the item type.
     */
    public String getName() {
        return name;
    }
    
    /**
     * Gets the ability associated with the item type.
     *
     * @return The ability of the item type.
     */
    public String getAbility() {
        return ability;
    }
}