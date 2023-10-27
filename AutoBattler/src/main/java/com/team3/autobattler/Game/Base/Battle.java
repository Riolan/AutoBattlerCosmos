/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.team3.autobattler.Game.Base;

/**
 *
 * @author curbo
 */


import java.util.List;

public abstract class Battle {
    protected List<? extends Unit> team1;
    protected List<? extends Unit> team2;

    public Battle(List<? extends Unit> team1, List<? extends Unit> team2) {
        this.team1 = team1;
        this.team2 = team2;
    }

    public abstract void startBattle();

    public boolean isTeam1Alive() {
        return team1.stream().anyMatch(Unit::isAlive);
    }

    public boolean isTeam2Alive() {
        return team2.stream().anyMatch(Unit::isAlive);
    }
    
    public boolean isTeam1Winner() {
        return !isTeam1Alive() && isTeam2Alive();
    }

    public boolean isTeam2Winner() {
        return isTeam1Alive() && !isTeam2Alive();
    }
}







