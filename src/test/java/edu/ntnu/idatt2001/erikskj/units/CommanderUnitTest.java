package edu.ntnu.idatt2001.erikskj.units;

import edu.ntnu.idatt2001.erikskj.enums.Terrain;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.*;

/**
 * junit tests for Commander Unit
 * a commander units attack and resist stats are not changed based on terrain, therefore the terrain is arbitrary in this class
 */
public class CommanderUnitTest {

    final Terrain terrain = Terrain.HILL;

    /**
     * test that checks every object variable of unit superclass, and for each csv line, with different illegal values, checks
     * if the method throws.
     * There is one illegal value per line, and per object variable
     * meaning every value and exception will be tested
     * @param name name
     * @param health health
     * @param attack attack
     * @param armour armour
     */
    @ParameterizedTest
    @CsvSource({
            "'', 1, 1, 1",
            "invalid health, -1, 1, 1",
            "invalid attack, 1, -1, 1",
            "invalid armour, 1, 1, -1"
    })
    public void constructorThrowsExceptionForNegativeValues(String name, int health, int attack, int armour){
        assertThrows(java.lang.IllegalArgumentException.class, () -> {
            Unit commanderUnit = new CommanderUnit(name, health, attack, armour);
        });
    }

    /**
     *checks if the attack and resist bonus stats are as they should be. This is without any attacks beforehand
     * commander unit has no changes by terrain, so it is irrelevant what it is
     */
    @Test
    public void checkBonusCalculations() {
        Unit testunit = new CommanderUnit("Mountain", 25);
        assertEquals(1, testunit.getResistBonus(terrain));
        assertEquals(6, testunit.getAttackBonus(terrain));

    }

    /**
     * checks if the attack and resist bonus stats are as they should be. However after a unit has attacked someone else, implying attack bonus should be decreased
     * commander unit has no changes by terrain, so it is irrelevant what it is
     */
    @Test
    public void checkBonusCalculationsOnce() {
        Unit testunit = new CommanderUnit("Mountain", 25);
        Unit testunit2 = new CommanderUnit("Wave", 25);
        testunit.attack(testunit2, terrain);
        assertEquals(1, testunit.getResistBonus(terrain));
        assertEquals(2, testunit.getAttackBonus(terrain));
    }
}