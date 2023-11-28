/*
 
Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
*/
package com.team3.autobattler.Game.Base;

import com.team3.autobattler.Game.Base.UnitA.Unit;
import com.team3.autobattler.Game.Factories.ItemFactory;
import com.team3.autobattler.Game.GameStates;
import com.team3.autobattler.SceneManagement.SceneManager;
import com.team3.autobattler.SceneManagement.Scenes.MainMenuScene;
import com.team3.autobattler.SceneManagement.Scenes.Shop;
import java.util.ArrayList;
import java.util.List;


/**
 * Abstract out Player to Battler further
 * divide Battler into Player and Opponent
 * @author Rio, Collin
 */
public class Player extends Battler {
    public static Player INSTANCE;
    
    private int gold;// = 10;

    // Not really a neccessary component
    // will return to later.
    public Item[] items;
    
        
    public Player() throws IllegalAccessException {
        if (INSTANCE != null) {
            throw new IllegalAccessException("You cannot construct an instance of the Player class. Please use the getInstance() function.");
        }
        gold = 10;
    }

    public static Player getPlayer() {
        if (INSTANCE == null) 
        {
            try {
                INSTANCE = new Player();
            } catch (IllegalAccessException e) {
                
            }
        }

        return INSTANCE;
    }
    private List<Unit> units = new ArrayList<Unit>();
          /* Could be nullable */
    public void addUnit(Unit unit) {
        units.add(unit);
    }
    
    public List<Unit> getUnits() {
        
        return units;
    }
    
    /*  */
    public void setUnits(List<Unit> units) {
        this.units = units;
    }
    
    /* Could be nullable */
    public Unit getUnit(int index) {
        if (units.size() <= 0) return null;
        return units.get(index);
    }
    
    public void setUnits(int index, Unit unit) {
        this.units.set(index, unit);
        
    }
    
    
    public void removeUnit(Unit unit) {
        this.units.remove(unit);
    }
    
    public int getGold() {
        return gold;
    }
    
    public boolean subtractGold(int cost) {
        if (gold - cost < 0) {
        return false;
        }
        gold = gold-cost;
        SceneManager sceneManager = SceneManager.getInstance();
        MainMenuScene mainMenu = (MainMenuScene) sceneManager.getScene(GameStates.MAINMENU);
        mainMenu.currency.setText("Currency: " + this.getGold() + " Gold");
        
        return true;
    }
    
    public boolean addGold(int cost) {
        gold = gold + cost;
        SceneManager sceneManager = SceneManager.getInstance();
        MainMenuScene mainMenu = (MainMenuScene) sceneManager.getScene(GameStates.MAINMENU);
        mainMenu.currency.setText("Currency: " + this.getGold() + " Gold");
        return true;
    }
}
