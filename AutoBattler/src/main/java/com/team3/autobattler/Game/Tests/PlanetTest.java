package com.team3.autobattler.Game.Tests;

import org.junit.Test;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotSame;

import org.junit.Before;



import com.team3.autobattler.Game.Base.Item;
import com.team3.autobattler.Game.Base.ItemType;

import com.team3.autobattler.Game.Base.Unit;
import com.team3.autobattler.Game.Base.UnitType;
import com.team3.autobattler.Game.Factories.UnitFactory;

public class PlanetTest {
    private UnitType earthType;
    private UnitType marsType;

    @Before
    public void setUp() {
        // Create some planet types for testing
//        earthType = UnitFactory.getUnitType("Earth", "Terrestrial");
//        marsType = UnitFactory.getUnitType("Mars", "Martian");
    }

    @Test
    public void testPlanetCreation() {
        Unit earth1 = new Earth(100, 20, earthType);
        Unit earth2 = new Earth(100, 20, earthType);
        Unit mars = new Mars(120, 30, marsType);

        // Verify that the planets were created with the correct properties
        assertEquals(100, earth1.getHealth());
        assertEquals(20, earth1.getAttack());
        assertEquals(earthType, earth1.getType());

        assertEquals(120, mars.getHealth());
        assertEquals(30, mars.getAttack());
        assertEquals(marsType, mars.getType());

        // Verify that the planets are not the same instance (flyweight pattern)
        assertNotSame(earth1, earth2);
    }
}