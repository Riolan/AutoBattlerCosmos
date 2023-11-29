package com.team3.autobattler.Game.Base.UnitA;

// Unit.java

import java.util.List;
/**
 * The Unit class contains state unique for each unit used in battle.
 * It represents a game entity with health, attack, type, and methods to interact with the unit.
 * 
 * @author Curbow & Rio
 */
public class Unit {
    private UnitType type;
    private int health;
    public int attack;
    
    /**
     * Constructs a new unit with specified health, attack, and type.
     *
     * @param health The initial health of the unit.
     * @param attack The attack value of the unit.
     * @param type   The type of the unit specifying shared characteristics.
     */
    public Unit(int health, int attack, UnitType type)
    {
        this.health = health;
        this.attack = attack;
        this.type = type;
    }

    /**
     * Retrieves the current health of the unit.
     *
     * @return The current health of the unit.
     */
    public int getHealth() {
        return health;
    }
    
    /**
     * Retrieves the attack value of the unit.
     *
     * @return The attack value of the unit.
     */
    public int getAttack() {
        return attack;
    }
    
    /**
     * Retrieves the type of the unit.
     *
     * @return The type of the unit.
     */
    public UnitType getType() {
        return type;
    }
    
    /**
     * Retrieves the name of the unit.
     *
     * @return The name of the unit.
     */
    public String getName() {
        return type.getName();
    }
    
     /**
     * Retrieves the cost of the unit.
     *
     * @return The cost of the unit.
     */
    public int getCost() {
        return type.getCost();
    }

    /**
     * Reduces the unit's health by the specified damage amount.
     * Ensures that health doesn't go below 0.
     *
     * @param damage The amount of damage to be taken by the unit.
     */
    public void takeDamage(int damage) {
        health -= damage;
        if (health < 0) {
            health = 0; // Ensure that health doesn't go negative
        }
    }
    
    /**
     * Checks if the unit is alive based on its current health.
     *
     * @return True if the unit is alive, false otherwise.
     */
    public boolean isAlive() {
        return health > 0;
    }
    
    // ... other class members and methods ...

}
