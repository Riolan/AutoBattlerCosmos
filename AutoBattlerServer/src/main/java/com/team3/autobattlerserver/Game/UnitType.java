package com.team3.autobattlerserver.Game;

import javax.swing.ImageIcon;


/**
 * Contains state shared by several units
 * @author Curbow
 * @author Rio
 */
public class UnitType {
    // A unit shares a name
    private String name;
    // ability (no reason for ability to be a string)
    // derive a class of abilities and implement the abilities
    // based off Unit information.
    private String ability;
    // icon
    private String icon;
    private int cost;
    
    public UnitType(String name, String ability, int cost) {
        this.name = name;
        this.ability = ability;
        this.cost = cost;
        this.icon = "/" + name + ".png";
    }
    
     public String getName() {
        return name;
    }
     
    public String getAbility() {
        return ability;
    }
    public int getCost() {
        return cost;
    }
    
    public String getIcon() {
        return icon;
    }
    
}