package com.team3.autobattler.Game.Tests;

import com.team3.autobattler.Game.Base.Battle;
import org.junit.Test;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotSame;

import org.junit.Before;




import com.team3.autobattler.Game.Base.Unit;
import com.team3.autobattler.Game.Base.UnitType;
import com.team3.autobattler.Game.Factories.UnitFactory;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import static org.junit.Assert.assertTrue;
import org.junit.Before;

public class PlanetTest {
    private UnitType earthType;
    private UnitType marsType;
    private UnitType jupiterType;
    private UnitType neptuneType;
    private UnitType saturnType;

    @Before
    public void setUp() {
        // Create some planet types for testing
        earthType = UnitFactory.getUnitType("Earth", "Terrestrial");
        marsType = UnitFactory.getUnitType("Mars", "Martian");
        jupiterType = UnitFactory.getUnitType("Jupiter", "Giant");
        neptuneType = UnitFactory.getUnitType("Neptune", "Dense");
        saturnType = UnitFactory.getUnitType("Saturn", "Rings");
    }
    
    public class TestBattle extends Battle {
    public TestBattle(List<? extends Unit> team1, List<? extends Unit> team2) {
        super(team1, team2);
    }

    @Override
    public void startBattle() {
        while (isTeam1Alive() && isTeam2Alive()) {
            // Implement your test battle logic here
            // For testing purposes, you can simulate unit attacks or other actions
        }
    }
}

    @Test
    public void testPlanetCreation() {
      
        List<Unit> team1 = new ArrayList<>();
        List<Unit> team2 = new ArrayList<>();
        
        Unit earth1 = new Earth(100, 18, earthType);
        Unit earth2 = new Earth(100, 18, earthType);
        Unit mars1 = new Mars(80, 25, marsType);
        Unit mars2 = new Mars(80, 25, marsType);
        Unit jupiter1 = new Jupiter(150, 10, jupiterType);
        Unit jupiter2 = new Jupiter(150, 10, jupiterType);
        Unit neptune1 = new Neptune(115, 16, neptuneType);
        Unit neptune2 = new Neptune(115, 16, neptuneType);
        Unit saturn1 = new Saturn(100, 20, saturnType);
        Unit saturn2 = new Saturn(100, 20, saturnType);
        
        team1.add(earth1);
        team1.add(mars1);
        team1.add(jupiter1);
        team1.add(neptune1);
        team1.add(saturn1);
        team2.add(saturn2);
        team2.add(neptune2);
        team2.add(jupiter2);
        team2.add(mars2);
        team2.add(earth2);
        
        TestBattle battle = new TestBattle(team1, team2);
        battle.startBattle();

        // Verify that the planets were created with the correct properties
        assertEquals(100, earth1.getHealth());
        assertEquals(20, earth1.getAttack());
        assertEquals(earthType, earth1.getType());

        assertEquals(120, mars1.getHealth());
        assertEquals(30, mars1.getAttack());
        assertEquals(marsType, mars1.getType());

        // Verify that the planets are not the same instance (flyweight pattern)
        assertNotSame(earth1, earth2);
        
        assertTrue(team1.isEmpty() || team2.isEmpty());
        
        // Check the outcome using the isTeam1Winner and isTeam2Winner methods
        if (battle.isTeam1Winner()) {
            System.out.println("Team 1 is the winner!");
        } else if (battle.isTeam2Winner()) {
            System.out.println("Team 2 is the winner!");
        } else {
            System.out.println("The battle ended in a draw.");
        }
            
    }
  
}