package com.team3.autobattler.Game.Base;

// Unit.java

import java.util.List;

public abstract class Unit 
{
    private UnitType type; // Use UnitType to store shared characteristics
    private int health;
    public int attack;
    private List<? extends Unit> opponentTeam; // Reference to the opposing team

    
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
    
    public void setOpponentTeam(List<? extends Unit> opponentTeam) {
        this.opponentTeam = opponentTeam;
    }

    // Method to get the opposing team
    public List<? extends Unit> getOpponentTeam() {
        return opponentTeam;
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

}
