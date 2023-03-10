package edu.ntnu.idatt2001.erikskj.war;

import edu.ntnu.idatt2001.erikskj.enums.Terrain;
import edu.ntnu.idatt2001.erikskj.units.*;
import edu.ntnu.idatt2001.erikskj.war.Army;
import edu.ntnu.idatt2001.erikskj.war.Battle;
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

        for (int i = 0; i < 10; i++) {
            armyOne.add(new InfantryUnit("Footman", 100));
            armyTwo.add(new InfantryUnit("Grunt", 100));
        }
        for (int i = 0; i < 10; i++) {
            armyOne.add(new RangedUnit("Archer", 100));
            armyTwo.add(new RangedUnit("Ranged",100));
        }

        for (int i = 0; i < 5; i++) {
            armyOne.add(new CavalryUnit("Knight", 100));
            armyTwo.add(new CavalryUnit("Raider", 100));
        }
        for (int i = 0; i < 2; i++) {
            armyOne.add(new RangedUnit("Archer", 100));
            armyTwo.add(new RangedUnit("Spearman", 100));
        }
        for (int i = 0; i < 1; i++) {
            armyOne.add(new CommanderUnit("Mountain King", 180));
            armyTwo.add(new CommanderUnit("Gul´dan", 180));
        }

        Battle battle = new Battle(armyOne, armyTwo, Terrain.HILL);
        battle.simulate();

        assertTrue(!armyOne.hasUnitsAlive() || !armyTwo.hasUnitsAlive());
    }

    /**
     * unit test for simulate method. Tricky method to test, however it's possible to just overlook that armies fight and that there
     * is a winner picked, implying that the other army has no units left
     * this unit test also accounts for terrain changes in attack and resists stats
     */
    @Test
    public void simulateWithHillTerrain() {
        Terrain terrain = Terrain.HILL;
        Army armyOne = new Army("archers");
        Army armyTwo = new Army("footmen");

        for (int i = 0; i < 5; i++) {
            armyOne.add(new InfantryUnit("Footman", 100));
            //armyTwo.add(new InfantryUnit("Grunt", 100));
        }
        for (int i = 0; i < 5; i++) {
            //armyOne.add(new RangedUnit("Archer", 100));
            armyTwo.add(new RangedUnit("Ranged",100));
        }

        Battle battle = new Battle(armyOne, armyTwo, terrain);
        battle.simulate();

        assertTrue(!armyOne.hasUnitsAlive() || !armyTwo.hasUnitsAlive());
    }

    /**
     * unit test for simulate method. Tricky method to test, however it's possible to just overlook that armies fight and that there
     * is a winner picked, implying that the other army has no units left
     * this unit test also accounts for terrain changes in attack and resists stats
     */
    @Test
    public void simulateWithForestTerrain() {
        Terrain terrain = Terrain.FOREST;
        Army armyOne = new Army("archers");
        Army armyTwo = new Army("footmen");

        for (int i = 0; i < 5; i++) {
            armyOne.add(new InfantryUnit("Footman", 100));
            //armyTwo.add(new InfantryUnit("Grunt", 100));
        }
        for (int i = 0; i < 1; i++) {
            //armyOne.add(new RangedUnit("Archer", 100));
            armyTwo.add(new RangedUnit("Ranged",10));
        }

        Battle battle = new Battle(armyOne, armyTwo, terrain);
        battle.simulate();

        assertTrue(!armyOne.hasUnitsAlive() || !armyTwo.hasUnitsAlive());
    }

    /**
     * unit test for simulate method. Tricky method to test, however it's possible to just overlook that armies fight and that there
     * is a winner picked, implying that the other army has no units left
     * this unit test also accounts for terrain changes in attack and resists stats
     */
    @Test
    public void simulateWithPlainsTerrain() {
        Terrain terrain = Terrain.PLAINS;
        Army armyOne = new Army("Human Army");
        Army armyTwo = new Army("Orcish Horde");

        for (int i = 0; i < 10; i++) {
            armyOne.add(new InfantryUnit("Footman", 100));
            armyTwo.add(new InfantryUnit("Grunt", 100));
        }
        for (int i = 0; i < 10; i++) {
            armyOne.add(new RangedUnit("Archer", 100));
            armyTwo.add(new RangedUnit("Ranged",100));
        }

        Battle battle = new Battle(armyOne, armyTwo, terrain);
        battle.simulate();

        assertTrue(!armyOne.hasUnitsAlive() || !armyTwo.hasUnitsAlive());
    }

    /**
     * test to check if a winner is chosen properly
     */
    @Test
    public void checkWin() {
        Army winningArmy = new Army("Winning army");
        Army losingArmy = new Army("Losing army");

        winningArmy.add(new CommanderUnit("winner", 1000));

        assertEquals(winningArmy, Battle.checkWinner(winningArmy, losingArmy));
    }
}