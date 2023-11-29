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
 * The Player class represents a player in the game, extending the Battler class.
 * It is implemented as a singleton to ensure a single instance.
 * 
 * @author Rio, Collin
 */
public class Player extends Battler {
    public static Player INSTANCE;
    
    private int gold;// = 10;

    /** 
     * An array of items associated with the player.
     * Note: This component may not be necessary and will be revisited later.
     */
    public Item[] items;
    
    /**
     * Private constructor to enforce the singleton pattern.
     * Throws an IllegalAccessException if an attempt is made to construct another instance.
     *
     * @throws IllegalAccessException If an attempt is made to construct another instance.
     */    
    public Player() throws IllegalAccessException {
        if (INSTANCE != null) {
            throw new IllegalAccessException("You cannot construct an instance of the Player class. Please use the getInstance() function.");
        }
        gold = 10;
    }
    
    /**
     * Retrieves the singleton instance of the Player class.
     *
     * @return The singleton instance of the Player class.
     */
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
    /**
     * Adds a unit to the list of units associated with the player.
     *
     * @param unit The unit to be added.
     */
    public void addUnit(Unit unit) {
        units.add(unit);
    }
    
    /**
     * Retrieves the list of units associated with the player.
     *
     * @return The list of units, or null if the list is empty.
     */
    public List<Unit> getUnits() {
        
        return units;
    }
    
     /**
     * Sets the list of units associated with the player.
     *
     * @param units The list of units.
     */
    public void setUnits(List<Unit> units) {
        this.units = units;
    }
    
    /**
     * Retrieves a unit from the list based on the specified index.
     *
     * @param index The index of the unit in the list.
     * @return The unit at the specified index, or null if the list is empty.
     */
    public Unit getUnit(int index) {
        if (units.size() <= 0) return null;
        return units.get(index);
    }
    
    /**
     * Sets a unit in the list at the specified index.
     *
     * @param index The index at which to set the unit.
     * @param unit  The unit to be set at the specified index.
     */
    public void setUnits(int index, Unit unit) {
        this.units.set(index, unit);
        
    }
    
    /**
     * Removes a unit from the list of units associated with the player.
     *
     * @param unit The unit to be removed.
     */
    public void removeUnit(Unit unit) {
        this.units.remove(unit);
    }
    
    /**
     * Retrieves the amount of gold the player possesses.
     *
     * @return The amount of gold.
     */
    public int getGold() {
        return gold;
    }
    
    /**
     * Subtracts the specified amount of gold from the player's total.
     * Updates the UI if successful.
     *
     * @param cost The amount of gold to subtract.
     * @return True if the gold subtraction is successful, false otherwise.
     */
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
    
    /**
     * Adds the specified amount of gold to the player's total.
     * Updates the UI.
     *
     * @param cost The amount of gold to add.
     * @return True if the gold addition is successful, false otherwise.
     */
    public boolean addGold(int cost) {
        gold = gold + cost;
        SceneManager sceneManager = SceneManager.getInstance();
        MainMenuScene mainMenu = (MainMenuScene) sceneManager.getScene(GameStates.MAINMENU);
        mainMenu.currency.setText("Currency: " + this.getGold() + " Gold");
        return true;
    }
}
