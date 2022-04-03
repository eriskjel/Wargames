package edu.ntnu.idatt2001.units;

import edu.ntnu.idatt2001.Terrain;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import static org.junit.jupiter.api.Assertions.*;

/**
 * test class for ranged unit
 */
public class RangedUnitTest {

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
            Unit rangedUnit = new RangedUnit(name, health, attack, armour);
        });
    }

    /**
     * inner test class for when ranged unit is fighting in forest terrain
     */
    @Nested
    public class BonusesInForest{

        //sets terrain as forrest
        Terrain terrain = Terrain.FOREST;

        /**
         * test for checking if the attack and armour stat are as they should be
         */
        @Test
        public void BonusAddsArmourStat(){
            Unit testunit = new RangedUnit("Archer", 25);
            assertEquals(6, testunit.getResistBonus(terrain));
            assertEquals(1, testunit.getAttackBonus(terrain));
        }

        /**
         * test for checking if the atttack and armour stat are as they should be after an attack
         */
        @Test
        public void bonusAttackDoesExtraDamageAfterOnce(){
            Unit testunit = new RangedUnit("Archer", 25);
            Unit testunit2 = new RangedUnit("Spearman", 25);
            testunit.attack(testunit2, terrain);
            assertEquals(4, testunit2.getResistBonus(terrain));
            assertEquals(1, testunit2.getAttackBonus(terrain));
        }

        /**
         * test for checking if the attack and armour stat are as they should be after two attacks
         */
        @Test
        public void bonusAttackDoesExtraDamageAfterTwice(){
            Unit testunit = new RangedUnit("Archer", 25);
            Unit testunit2 = new RangedUnit("Spearman", 25);
            testunit.attack(testunit2, terrain);
            testunit.attack(testunit2, terrain);
            assertEquals(2, testunit2.getResistBonus(terrain));
            assertEquals(1, testunit2.getAttackBonus(terrain));
        }
    }

    /**
     * inner test class for when ranged unit is fighting in hill terrain
     */
    @Nested
    public class BonusesInHill{

        //sets terrain as hills
        Terrain terrain = Terrain.HILL;

        /**
         * test for checking if the attack and armour stat are as they should be
         */
        @Test
        public void BonusAddsArmourStat(){
            Unit testunit = new RangedUnit("Archer", 25);
            assertEquals(6, testunit.getResistBonus(terrain));
            assertEquals(5, testunit.getAttackBonus(terrain));
        }

        /**
         * test for checking if the atttack and armour stat are as they should be after an attack
         */
        @Test
        public void bonusAttackDoesExtraDamageAfterOnce(){
            Unit testunit = new RangedUnit("Archer", 25);
            Unit testunit2 = new RangedUnit("Spearman", 25);
            testunit.attack(testunit2, terrain);
            assertEquals(4, testunit2.getResistBonus(terrain));
            assertEquals(5, testunit2.getAttackBonus(terrain));
        }

        /**
         * test for checking if the attack and armour stat are as they should be after two attacks
         */
        @Test
        public void bonusAttackDoesExtraDamageAfterTwice(){
            Unit testunit = new RangedUnit("Archer", 25);
            Unit testunit2 = new RangedUnit("Spearman", 25);
            testunit.attack(testunit2, terrain);
            testunit.attack(testunit2, terrain);
            assertEquals(2, testunit2.getResistBonus(terrain));
            assertEquals(5, testunit2.getAttackBonus(terrain));
        }
    }

    /**
     * inner test class for when ranged unit is fighting in plains terrain
     */
    @Nested
    public class BonusesInPlains{

        //sets terrain as plains
        Terrain terrain = Terrain.PLAINS;

        /**
         * test for checking if the attack and armour stat are as they should be
         */
        @Test
        public void BonusAddsArmourStat(){
            Unit testunit = new RangedUnit("Archer", 25);
            assertEquals(6, testunit.getResistBonus(terrain));
            assertEquals(3, testunit.getAttackBonus(terrain));
        }

        /**
         * test for checking if the atttack and armour stat are as they should be after an attack
         */
        @Test
        public void bonusAttackDoesExtraDamageAfterOnce(){
            Unit testunit = new RangedUnit("Archer", 25);
            Unit testunit2 = new RangedUnit("Spearman", 25);
            testunit.attack(testunit2, terrain);
            assertEquals(4, testunit2.getResistBonus(terrain));
            assertEquals(3, testunit2.getAttackBonus(terrain));
        }

        /**
         * test for checking if the attack and armour stat are as they should be after two attacks
         */
        @Test
        public void bonusAttackDoesExtraDamageAfterTwice(){
            Unit testunit = new RangedUnit("Archer", 25);
            Unit testunit2 = new RangedUnit("Spearman", 25);
            testunit.attack(testunit2, terrain);
            testunit.attack(testunit2, terrain);
            assertEquals(2, testunit2.getResistBonus(terrain));
            assertEquals(3, testunit2.getAttackBonus(terrain));
        }
    }
}