package com.team3.autobattler.Game.Base;

// UnitType.java
public class UnitType {
    private String name;
    private String ability;

    public UnitType(String name, String ability) {
        this.name = name;
        this.ability = ability;
    }
    
     public String getName() {
        return name;
    }

    public String getAbility() {
        return ability;
    }

    
}