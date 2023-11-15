package com.team3.autobattler.Game.Tests;


import com.team3.autobattler.Game.Base.Unit;
import com.team3.autobattler.Game.Base.UnitType;
import com.team3.autobattler.Game.Base.Battle;

import java.util.List;
import java.util.Random;

public class Earth extends Unit {
    public Earth(int health, int attack, UnitType type) {
        super(health, attack, type);
    }

    // Ability: Deal 0.5 of attack damage to a random enemy
    public void useAbility() {
        List<? extends Unit> enemies = getOpponentTeam();
        if (!enemies.isEmpty()) {
            Unit randomEnemy = enemies.get(new Random().nextInt(enemies.size()));
            int damage = (int) (0.5 * getAttack()); // Calculate 0.5 of attack damage
            randomEnemy.takeDamage(damage);
        }
    }
}

