/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.team3.autobattler.Game.Base;


/**
 *
 * @author colli
 */
public class Player {
    
   public Player() {
        gold = 10;
        health = 5;
        turnNum = 1;
        items = new Item[4];
        units = new Unit[4];
    }
   
    
    public int gold;
    public int health;
    public int turnNum;
            
    public Item[] items;
    public Unit[] units;
    
}
