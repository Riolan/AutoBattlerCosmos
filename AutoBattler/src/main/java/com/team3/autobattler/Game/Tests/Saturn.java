/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.team3.autobattler.Game.Tests;


import com.team3.autobattler.Game.Base.Unit;
import com.team3.autobattler.Game.Base.UnitType;
import java.util.List;

public class Saturn extends Unit {
    // Assuming the retaliation percentage is 20%
    protected static final double RETALIATION_PERCENTAGE = 0.1;

    public Saturn(int health, int attack, UnitType type) {
        super(health, attack, type);
    }

    @Override
    public void takeDamage(int damage) {
        super.takeDamage(damage);

        // Check if Saturn is attacked (damage is greater than 0)
        if (damage > 0) {
            // Check if the attacking unit is an enemy in the first position
            Unit attacker = getAttacker();
            if (attacker != null && attacker.isAlive() && isEnemyInFirstPosition(attacker)) {
                // Deal damage to the attacking enemy based on the retaliation percentage
                int retaliationDamage = (int) (damage * RETALIATION_PERCENTAGE);
                attacker.takeDamage(retaliationDamage);
            }
        }
    }

    // Check if the attacking unit is an enemy in the first position
    private boolean isEnemyInFirstPosition(Unit attacker) {
        List<? extends Unit> opponents = getOpponentTeam();
        return !opponents.isEmpty() && attacker == opponents.get(0);
    }

    // Modified method to get the attacker
    public Unit getAttacker() {
        // Assuming the attacking unit is the first alive unit in the opponent team
        List<? extends Unit> opponents = getOpponentTeam();
        for (Unit opponent : opponents) {
            if (opponent.isAlive()) {
                return opponent;
            }
        }
        return null; // Return null if no alive attacker is found
    }
    
    
}

