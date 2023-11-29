/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.team3.autobattler.Game.Base;

import com.team3.autobattler.Game.Base.UnitA.Unit;

import java.util.List;
/**
 * The Battle class represents a battle between two teams of units.
 * It tracks the state of the battle, including the teams involved and the battle's unique identifier.
 * 
 * @author curbow
 */
public class Battle {
    
    /** The unique identifier for the battle. */
    private int id;
    
    /** The list of units in the player's team. */
    protected List<Unit> player;
    
    /** The list of units in the opponent's team. */
    protected List<Unit> opponent;
    
    /**
     * Constructs a new Battle instance with the specified teams and identifier.
     *
     * @param team1 The list of units in the player's team.
     * @param team2 The list of units in the opponent's team.
     * @param id    The unique identifier for the battle.
     */
    public Battle(List<Unit> team1, List<Unit> team2, int id) {
        this.player = team1;
        this.opponent = team2;
        this.id = id;
    }

    /**
     * Starts the battle, printing a message indicating the beginning of the battle.
     */
    public void startBattle() {
        System.out.println("Starting battle.");
    };
    
    /**
     * Gets the unique identifier for the battle.
     *
     * @return The unique identifier for the battle.
     */
    public int getId() {
        return this.id;
    }

    /**
     * Checks if any unit in team 1 is still alive.
     *
     * @return True if at least one unit in team 1 is alive, false otherwise.
     */
    public boolean isTeam1Alive() {
        return player.stream().anyMatch(Unit::isAlive);
    }

    /**
     * Checks if any unit in team 2 is still alive.
     *
     * @return True if at least one unit in team 2 is alive, false otherwise.
     */
    public boolean isTeam2Alive() {
        return opponent.stream().anyMatch(Unit::isAlive);
    }
    
    /**
     * Checks if there is a winner in the battle.
     *
     * @return True if there is a winner, false otherwise.
     */
    public boolean winner() {
        return isTeam1Alive();
    }
    
    /**
     * Checks if team 1 is the winner of the battle.
     *
     * @return True if team 1 is the winner, false otherwise.
     */
    public boolean isTeam1Winner() {
        return !isTeam1Alive() && isTeam2Alive();
    }

    /**
     * Checks if team 2 is the winner of the battle.
     *
     * @return True if team 2 is the winner, false otherwise.
     */
    public boolean isTeam2Winner() {
        return isTeam1Alive() && !isTeam2Alive();
    }
}







