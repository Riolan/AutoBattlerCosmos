package com.team3.autobattler.Game.Base;

// Unit.java
public class Unit 
{
    private UnitType type; // Use UnitType to store shared characteristics
    private int health;
    private int attack;


    public Unit(int health, int attack, UnitType type)
    {
        this.health = health;
        this.attack = attack;
        this.type = type;
    }

    // Getter methods for health, attack, and type
    public int getHealth() {
        return health;
    }

    public int getAttack() {
        return attack;
    }

    public UnitType getType() {
        return type;
    }
    public String getName() {
        return type.getName();
    }
    public String getAbility() {
        return type.getAbility();
    }
    // ... other class members and methods ...
}