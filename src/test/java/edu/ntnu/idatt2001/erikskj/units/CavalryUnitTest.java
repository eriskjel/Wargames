package edu.ntnu.idatt2001.erikskj.units;

import edu.ntnu.idatt2001.erikskj.enums.Terrain;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;


import static org.junit.jupiter.api.Assertions.*;

/**
 * junit tests of Cavalry Unit
 */
public class CavalryUnitTest {


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
            Unit cavalryUnit = new CavalryUnit(name, health, attack, armour);
        });
    }


    /**
     * inner test class for when cavalry unit is fighting in forest terrain
     */
    @Nested
    public class BonusesInForest{

        final Terrain terrain = Terrain.FOREST;

        /**
         * checks if the attack and resist bonus stats are as they should be.
         */
        @Test
        public void bonusAttackDoesExtraDamage(){
            Unit testunit = new CavalryUnit("Orc", 25);
            assertEquals(0, testunit.getResistBonus(terrain));
            assertEquals(6, testunit.getAttackBonus(terrain));
        }

        /**
         * checks if the attack and resist bonus stats are as they should be. However after a unit has attacked someone else, meaning attack bonus should be decreased
         */
        @Test
        public void bonusAttackDoesExtraDamageAfterAttack(){
            Unit testunit = new CavalryUnit("Orc", 25);
            Unit testunit2 = new CavalryUnit("Orc2", 25);
            testunit.attack(testunit2, terrain);
            assertEquals(0, testunit.getResistBonus(terrain));
            assertEquals(2, testunit.getAttackBonus(terrain));

        }
    }

    /**
     * inner test class for when cavalry unit is fighting in plains terrain
     */
    @Nested
    public class BonusesInPlains{

        final Terrain terrain = Terrain.PLAINS;

        /**
         * checks if the attack and resist bonus stats are as they should be.
         */
        @Test
        public void bonusAttackDoesExtraDamage(){
            Unit testunit = new CavalryUnit("Orc", 25);
            assertEquals(1, testunit.getResistBonus(terrain));
            assertEquals(8, testunit.getAttackBonus(terrain));
        }

        /**
         * checks if the attack and resist bonus stats are as they should be. However after a unit has attacked someone else, meaning attack bonus should be decreased
         */
        @Test
        public void bonusAttackDoesExtraDamageAfterAttack(){
            Unit testunit = new CavalryUnit("Orc", 25);
            Unit testunit2 = new CavalryUnit("Orc2", 25);
            testunit.attack(testunit2, terrain);
            assertEquals(1, testunit.getResistBonus(terrain));
            assertEquals(4, testunit.getAttackBonus(terrain));

        }
    }

    /**
     * inner test class for when cavalry unit is fighting in hill terrain
     */
    @Nested
    public class BonusesInHill{

        final Terrain terrain = Terrain.HILL;

        /**
         * checks if the attack and resist bonus stats are as they should be.
         */
        @Test
        public void bonusAttackDoesExtraDamage(){
            Unit testunit = new CavalryUnit("Orc", 25);
            assertEquals(1, testunit.getResistBonus(terrain));
            assertEquals(6, testunit.getAttackBonus(terrain));
        }

        /**
         * checks if the attack and resist bonus stats are as they should be. However after a unit has attacked someone else, meaning attack bonus should be decreased
         */
        @Test
        public void bonusAttackDoesExtraDamageAfterAttack(){
            Unit testunit = new CavalryUnit("Orc", 25);
            Unit testunit2 = new CavalryUnit("Orc2", 25);
            testunit.attack(testunit2, terrain);
            assertEquals(1, testunit.getResistBonus(terrain));
            assertEquals(2, testunit.getAttackBonus(terrain));

        }
    }
}