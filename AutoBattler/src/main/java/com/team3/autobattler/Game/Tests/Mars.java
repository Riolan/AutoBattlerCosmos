package com.team3.autobattler.Game.Tests;

import com.team3.autobattler.Game.Base.Unit;
import com.team3.autobattler.Game.Base.UnitType;


public class Mars extends Unit {
    private int attackBonus; // to store the attack bonus gained

    public Mars(int health, int attack, UnitType type) {
        super(health, attack, type);
        this.attackBonus = 0; // initialize the attack bonus to 0
    }

    @Override
    public void takeDamage(int damage) {
        super.takeDamage(damage);

        // Check if Mars is attacked (damage is greater than 0)
        if (damage > 0) {
            // Mars gains +2 attack every time it is attacked
            this.attackBonus += 2;
            // Update the attack value
            this.attack = getBaseAttack() + attackBonus;
        }
    }

    // Add a method to get the base attack without the bonus
    private int getBaseAttack() {
        return super.getAttack();
    }

    // Override the getAttack method to include the bonus
    @Override
    public int getAttack() {
        return this.attack;
    }
}