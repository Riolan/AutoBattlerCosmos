/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.team3.autobattler.Game.Tests;

/**
 *
 * @author curbo
 */

import com.team3.autobattler.Game.Base.Unit;
import com.team3.autobattler.Game.Base.UnitType;

public class Neptune extends Unit {
    private static final double DAMAGE_REDUCTION_PERCENTAGE = 0.1; // 10% damage reduction

    public Neptune(int health, int attack, UnitType type) {
        super(health, attack, type);
    }

    @Override
    public void takeDamage(int damage) {
        // Reduce the damage taken based on the damage reduction percentage
        int reducedDamage = (int) (damage * (1 - DAMAGE_REDUCTION_PERCENTAGE));
        super.takeDamage(reducedDamage);
    }
}