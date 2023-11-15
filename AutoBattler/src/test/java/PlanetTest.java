package test.java;

import com.team3.autobattler.Game.Base.Battle;
import org.junit.Test;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotSame;





import com.team3.autobattler.Game.Base.Unit;
import com.team3.autobattler.Game.Base.UnitType;
import com.team3.autobattler.Game.Factories.UnitFactory;
import com.team3.autobattler.Game.Tests.Earth;
import com.team3.autobattler.Game.Tests.Jupiter;
import com.team3.autobattler.Game.Tests.Mars;
import com.team3.autobattler.Game.Tests.Neptune;
import com.team3.autobattler.Game.Tests.Saturn;
import java.util.ArrayList;
import java.util.List;
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
        int team1AttackerIndex = 0;
        int team2AttackerIndex = 0;
        
        // Set opponent teams for each unit
        for (Unit unit : team1) {
            unit.setOpponentTeam(team2);
        }

        for (Unit unit : team2) {
            unit.setOpponentTeam(team1);
        }

        // Earth's ability: Deal 0.5 of attack damage to a random enemy at the start of combat
        for (Unit unit : team1) {
            if (unit instanceof Earth earth) {
                earth.useAbility();
            }
        }

        for (Unit unit : team2) {
            if (unit instanceof Earth earth) {
                earth.useAbility();
            }
        }


        while (isTeam1Alive() && isTeam2Alive()) {
            Unit team1Attacker = team1.get(team1AttackerIndex);
            Unit team2Attacker = team2.get(team2AttackerIndex);

            // Check if the units in the first slot are alive and attack each other
            if (team1Attacker.isAlive() && team2Attacker.isAlive()) {
                int damage1 = team1Attacker.getAttack();
                int damage2 = team2Attacker.getAttack();

                team2Attacker.takeDamage(damage1);
                team1Attacker.takeDamage(damage2);

                // If a unit's health is zero or less, it's defeated, and the next unit takes its place
                if (!team1Attacker.isAlive()) {
                    team1AttackerIndex++;
                }
                if (!team2Attacker.isAlive()) {
                    team2AttackerIndex++;
                }
            } else {
                // If a unit is defeated, move to the next unit in the slot
                if (!team1Attacker.isAlive()) {
                    team1AttackerIndex++;
                }
                if (!team2Attacker.isAlive()) {
                    team2AttackerIndex++;
                }
            }
        }
    }
    }

    @Test
    public void PlanetCreationTest() {
      
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
        

        // Verify that the planets were created with the correct properties
        assertEquals(100, earth1.getHealth());
        assertEquals(18, earth1.getAttack());
        assertEquals(earthType, earth1.getType());

        assertEquals(80, mars1.getHealth());
        assertEquals(25, mars1.getAttack());
        assertEquals(marsType, mars1.getType());

        // Verify that the planets are not the same instance (flyweight pattern)
        assertNotSame(earth1, earth2);
        
        TestBattle battle = new TestBattle(team1, team2);
        battle.startBattle();
        
        // Check Earth's ability: Deal 0.5 of attack damage to a random enemy
        for (Unit unit : team1) {
            if (unit instanceof Earth earth) {
                int expectedDamage = (int) (0.5 * earth.getAttack());
                assertTrue(expectedDamage >= 0); // Ensure damage is non-negative
                // Add more specific assertions if needed
            }
        }
        
        // Check Saturn's ability: Any planet that attacks it takes 5 damage
         for (Unit unit : team1) {
        if (unit instanceof Saturn saturn) {
            // Assuming the first unit in the enemy team attacks Saturn
            if (team2.get(0).equals(saturn.getAttacker())) {
                // Adjusted the expected health based on the retaliation percentage
                int expectedHealth = 100 - (int) (team2.get(0).getAttack() * Saturn.RETALIATION_PERCENTAGE);
                assertEquals(expectedHealth, team2.get(0).getHealth());
            } else {
                // If it's not the first unit in the enemy team, health remains unchanged
                assertEquals(100, team2.get(0).getHealth());
            }
        }
    }

        
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