package edu.ntnu.idatt2001;

import edu.ntnu.idatt2001.units.*;
import org.junit.jupiter.api.Test;


import static org.junit.jupiter.api.Assertions.*;

class BattleTest {



    @Test
    public void simulate() {
        Army armyOne = new Army("Human Army");
        Army armyTwo = new Army("Orcish Horde");

        for (int i = 0; i < 100; i++) {
            armyOne.add(new InfantryUnit("Footman", 100));
            armyTwo.add(new InfantryUnit("Grunt", 100));
        }
        for (int i = 0; i < 100; i++) {
            armyOne.add(new RangedUnit("Archer", 100));
            armyTwo.add(new RangedUnit("Ranged",100));
        }

        for (int i = 0; i < 100; i++) {
            armyOne.add(new CavalryUnit("Knight", 100));
            armyTwo.add(new CavalryUnit("Raider", 100));
        }
        for (int i = 0; i < 100; i++) {
            armyOne.add(new RangedUnit("Archer", 100));
            armyTwo.add(new RangedUnit("Spearman", 100));
        }
        for (int i = 0; i < 1; i++) {
            armyOne.add(new CommanderUnit("Mountain King", 180));
            armyTwo.add(new CommanderUnit("Gul´dan", 180));
        }
        Battle battle = new Battle(armyOne, armyTwo);
        battle.simulate();
    }

    @Test
    public void checkWin() {
        Army winningArmy = new Army("Winning army");
        Army losingArmy = new Army("Losing army");

        winningArmy.add(new CommanderUnit("winner", 1000));

        assertEquals("Winning army has won! Remaining units: " + winningArmy, Battle.checkWin(winningArmy,losingArmy));
    }


    @Test
    public void checkIfUnitIsAlive() {
        assertFalse(Battle.isDead(new InfantryUnit("erik", 100)));
    }

    /**
     * check if unit is dead. Need to manually set its health to a negative int, after creating due to exception handling.
     */
    @Test
    public void checkIfUnitIsDead() {
        Unit test = new InfantryUnit("erik", 100);
        test.setHealth(-100);
        assertTrue(Battle.isDead(test));

    }

}