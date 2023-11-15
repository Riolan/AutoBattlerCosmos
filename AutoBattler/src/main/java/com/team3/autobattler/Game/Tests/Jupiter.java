/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.team3.autobattler.Game.Tests;


import com.team3.autobattler.Game.Base.Unit;
import com.team3.autobattler.Game.Base.UnitType;

public class Jupiter extends Unit {
    private int attacksTaken;

    public Jupiter(int health, int attack, UnitType type) {
        super(health, attack, type);
        this.attacksTaken = 0;
    }

    @Override
    public void takeDamage(int damage) {
        // Check if this is the first attack Jupiter is taking
        if (attacksTaken == 0) {
            // Ignore the first attack
            attacksTaken++;
        } else {
            super.takeDamage(damage);
        }
    }
}
