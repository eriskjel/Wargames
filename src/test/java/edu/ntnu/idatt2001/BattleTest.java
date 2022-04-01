package edu.ntnu.idatt2001;

import edu.ntnu.idatt2001.units.*;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * unit test class for Battle class
 */
public class BattleTest {


    /**
     * unit test for simulate method. Tricky method to test, however it's possible to just overlook that armies fight and that there
     * is a winner picked, implying that the other army has no units left
     */
    @Test
    public void simulate() {
        Army armyOne = new Army("Human Army");
        Army armyTwo = new Army("Orcish Horde");

        for (int i = 0; i < 75; i++) {
            armyOne.add(new InfantryUnit("Footman", 100));
            armyTwo.add(new InfantryUnit("Grunt", 100));
        }
        for (int i = 0; i < 50; i++) {
            armyOne.add(new RangedUnit("Archer", 100));
            armyTwo.add(new RangedUnit("Ranged",100));
        }

        for (int i = 0; i < 25; i++) {
            armyOne.add(new CavalryUnit("Knight", 100));
            armyTwo.add(new CavalryUnit("Raider", 100));
        }
        for (int i = 0; i < 10; i++) {
            armyOne.add(new RangedUnit("Archer", 100));
            armyTwo.add(new RangedUnit("Spearman", 100));
        }
        for (int i = 0; i < 1; i++) {
            armyOne.add(new CommanderUnit("Mountain King", 180));
            armyTwo.add(new CommanderUnit("Gul´dan", 180));
        }

        Battle battle = new Battle(armyOne, armyTwo);
        battle.simulate();

        assertTrue(!armyOne.hasUnits() || !armyTwo.hasUnits());
    }

    /**
     * test to check if a winner is chosen properly
     */
    @Test
    public void checkWin() {
        Army winningArmy = new Army("Winning army");
        Army losingArmy = new Army("Losing army");

        winningArmy.add(new CommanderUnit("winner", 1000));

        assertEquals("Winning army has won! Remaining units: " + winningArmy, Battle.checkWin(winningArmy,losingArmy));
    }


    /**
     * tests if unit with positive health is alive or dead
     */
    @Test
    public void checkIfUnitIsAlive() {
        assertFalse(Battle.isDead(new InfantryUnit("erik", 100)));
    }

    /**
     * check if unit with negative health is dead. Need to manually set its health to a negative int, after creating due to exception handling.
     */
    @Test
    public void checkIfUnitIsDead() {
        Unit test = new InfantryUnit("erik", 100);
        test.setHealth(-100);
        assertTrue(Battle.isDead(test));

    }
}