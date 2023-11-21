package com.team3.autobattlerserver.Game;

// Unit.java

import java.util.List;
/**
 * Contains state unique for each unit.
 * @author Curbow & Rio
 */
public class Unit implements Comparable<Unit> {
    private UnitType type;
    private int health;
    public int attack;
    
    public int team = -1;
    
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
    public int getCost() {
        return type.getCost();
    }
    

    // Add a method to handle taking damage
    public void takeDamage(int damage) {
        health -= damage;
        if (health < 0) {
            health = 0; // Ensure that health doesn't go negative
        }
    }
    
    public boolean isAlive() {
        return health > 0;
    }
    
    // ... other class members and methods ...
    
    public int compareTo(Unit c) {
        return Integer.compare(getAttack(), c.getAttack());
    }
}
