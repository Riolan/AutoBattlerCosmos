/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.team3.autobattler.Game.Base;

import java.util.ArrayList;
import java.util.List;


/**
 * Player singleton
 * @author colli
 */
public class Player {
    
    private static Player player = null;

    private int gold;
    private int health;
    private int turnNum;
            
    private List<Item> items;
    private List<Unit> units;
    
    public Player(int gold, int health, int turn,  List<Unit> units) {
        this.gold = gold;
        this.health = health;
    }
   
   public void setUnits(List<Unit> units) {
       this.units = units;
   }
   
   /**
    * Player instance
    * @return 
    */
   public static Player getPlayer() {
        if (player == null) {
            List<Unit> emptyList = new ArrayList<Unit>();
            player = new Player(10, 5, 1, emptyList); 
        }
  
        return player;
    }
    
}
