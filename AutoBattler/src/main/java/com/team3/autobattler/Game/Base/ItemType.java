package com.team3.autobattler.Game.Base;

// ItemType class to represent shared characteristics of items
public class ItemType {
    private String name;
    private String ability;

    public ItemType(String name, String ability) {
        this.name = name;
        this.ability = ability;
    }

    // Getter methods for name and ability
    public String getName() {
        return name;
    }

    public String getAbility() {
        return ability;
    }
}