/*
 
Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
*/
package com.team3.autobattler.Game.Base;

import com.team3.autobattler.Game.Factories.ItemFactory;


/**
 *
 
@author colli*/
public class Player {
        private static Player player = null;
        private static Player computer = null;
    private Player() {
        gold = 10;
        health = 5;
        turnNum = 1;
        items = new Item[4];
        units = new Unit[4];
    }

    public static Player getPlayer() {
        if (player == null) 
        {
            player = new Player(); 
        }

        return player;
    }
    public static Player getComputer() {
        if (computer == null) 
        {
            computer = new Player(); 
        }

        return computer;
    }


    private int gold;
    private int health;
    private int turnNum;

    public Item[] items;
    public Unit[] units;

}
