package com.team3.autobattler.Game.Base;

// Item class using ItemType
public class Item {
    private ItemType itemType;

    public Item(ItemType itemType) {
        this.itemType = itemType;
    }

    // Access ItemType properties
    public String getName() {
        return itemType.getName();
    }

    public String getAbility() {
        return itemType.getAbility();
    }

    // The cost is always 1 gold for all items
    public int getCost() {
        return 1;
    }
}