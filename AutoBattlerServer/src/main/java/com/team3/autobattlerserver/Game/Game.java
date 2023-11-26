/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.team3.autobattlerserver.Game;

import java.util.ArrayList;
import java.util.List;

/**
 * tracks game information for each client when they enter a game sequence
 * @author Emily
 */
public class Game {
    private boolean inBattle = false;
    private int battleId = -1;
    private int wins = 0;
    private int losses = 0;
    private int draws = 0;
    
    public Game() {
        System.out.println("game entered");
        this.inBattle = false;
        this.battleId = -1;
        this.wins = 0;
        this.losses = 0;
    }
    
    public boolean isInBattle() {
        return inBattle;
    }
    
    public void setInBattle(boolean inBattle) {
        this.inBattle = inBattle;
        System.out.println("inBattle" + this.inBattle);
    }
    
    public int getBattleId() {
        return battleId;
    }
    
    public void setBattleId(int battleId) {
        this.battleId = battleId;
        System.out.println("battleId" + this.battleId);
    }
    
    public int getWins() {
        return wins;
    }
    
    public void incrementWins() {
        this.wins++;
    }
    
    public int getLosses() {
        return losses;
    }
    
    public void incrementLosses() {
        this.losses++;
    }
    
    public int getDraws() {
        return draws;
    }
    
    public void incrementDraws() {
        this.draws++;
    }
}
