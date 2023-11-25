/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.team3.autobattler.Game.Base;

import com.team3.autobattler.Game.Base.UnitA.Unit;
/**
 *
 * @author curbo
 */


import java.util.List;

public class Battle {
    private int id;
    protected List<Unit> player;
    protected List<Unit> opponent;

    public Battle(List<Unit> team1, List<Unit> team2, int id) {
        this.player = team1;
        this.opponent = team2;
        this.id = id;
    }


    public void startBattle() {
        System.out.println("Starting battle.");
        
        
        
    };
    
    public int getId() {
        return this.id;
    }

    public boolean isTeam1Alive() {
        return player.stream().anyMatch(Unit::isAlive);
    }

    public boolean isTeam2Alive() {
        return opponent.stream().anyMatch(Unit::isAlive);
    }
    
    public boolean winner() {
        return isTeam1Alive();
    }
    
    public boolean isTeam1Winner() {
        return !isTeam1Alive() && isTeam2Alive();
    }

    public boolean isTeam2Winner() {
        return isTeam1Alive() && !isTeam2Alive();
    }
}







