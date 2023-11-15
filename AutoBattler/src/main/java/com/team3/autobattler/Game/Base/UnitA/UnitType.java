package com.team3.autobattler.Game.Base.UnitA;

import javax.swing.ImageIcon;


/**
 * Contains state shared by several units
 * @author Curbow & Rio
 */
public class UnitType {
    // A unit shares a name
    private String name;
    // ability (no reason for ability to be a string)
    // derive a class of abilities and implement the abilities
    // based off Unit information.
    private String ability;
    // icon
    private ImageIcon icon;
    // priority
    
    
    public UnitType(String name, String ability) {
        this.name = name;
        this.ability = ability;
    }
    
     public String getName() {
        return name;
    }

    public String getAbility() {
        return ability;
    }

    
}