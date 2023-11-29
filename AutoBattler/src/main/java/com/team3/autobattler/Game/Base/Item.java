package com.team3.autobattler.Game.Base;

/**
 * The Item class represents an item in the game, utilizing an ItemType to store shared characteristics.
 * It provides methods to access properties such as name, ability, and cost.
 * 
 * @author ??? (none listed)
 */
public class Item {
    private ItemType itemType;
    
    /**
     * Constructs a new Item instance with the specified ItemType.
     *
     * @param itemType The type of the item.
     */
    public Item(ItemType itemType) {
        this.itemType = itemType;
    }

    /**
     * Gets the name of the item.
     *
     * @return The name of the item.
     */
    public String getName() {
        return itemType.getName();
    }
    
    /**
     * Gets the ability associated with the item.
     *
     * @return The ability of the item.
     */
    public String getAbility() {
        return itemType.getAbility();
    }

    /**
     * Gets the cost of the item. The cost is always 1 gold for all items.
     *
     * @return The cost of the item.
     */
    public int getCost() {
        return 1;
    }
}