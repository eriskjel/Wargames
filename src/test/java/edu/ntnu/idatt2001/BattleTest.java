package edu.ntnu.idatt2001;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BattleTest {

    @Nested
    class CommanderUnitTest{
        @Test
        void checkBonusCalculations(){
            Unit testunit = new CommanderUnit("Mountain", 25);
            Unit testunit2 = new CommanderUnit("Wave", 25);
            assertEquals(1, testunit.getResistBonus());
            assertEquals(6, testunit.getAttackBonus());

        }
        @Test
        void checkBonusCalculationsOnce(){
            Unit testunit = new CommanderUnit("Mountain", 25);
            Unit testunit2 = new CommanderUnit("Wave", 25);
            testunit.attack(testunit2);
            assertEquals(1, testunit.getResistBonus());
            assertEquals(2, testunit.getAttackBonus());
        }

    }

    @Nested
    class BonusDamage{
        @Test
        void bonusAttackDoesExtraDamage(){
            Unit testunit = new CavalryUnit("Orc", 25);
            Unit testunit2 = new CavalryUnit("Orc2", 25);
            assertEquals(1, testunit.getResistBonus());
            assertEquals(6, testunit.getAttackBonus());

        }
        @Test
        void bonusAttackDoesExtraDamageAfter(){
            Unit testunit = new CavalryUnit("Orc", 25);
            Unit testunit2 = new CavalryUnit("Orc2", 25);
            testunit.attack(testunit2);
            assertEquals(1, testunit.getResistBonus());
            assertEquals(2, testunit.getAttackBonus());

        }
    }

    @Nested
    class BonusArmor{
        @Test
        void BonusAddsArmourStat(){
            Unit testunit = new RangedUnit("Archer", 25);
            Unit testunit2 = new RangedUnit("Spearman", 25);
            assertEquals(6, testunit2.getResistBonus());
            assertEquals(3, testunit2.getAttackBonus());

        }
        @Test void bonusAttackDoesExtraDamageAfterOnce(){
            Unit testunit = new RangedUnit("Archer", 25);
            Unit testunit2 = new RangedUnit("Spearman", 25);
            testunit.attack(testunit2);
            assertEquals(4, testunit2.getResistBonus());
            assertEquals(3, testunit2.getAttackBonus());
        }
        @Test void bonusAttackDoesExtraDamageAfterTwice(){
            Unit testunit = new RangedUnit("Archer", 25);
            Unit testunit2 = new RangedUnit("Spearman", 25);
            testunit.attack(testunit2);
            testunit.attack(testunit2);
            assertEquals(2, testunit2.getResistBonus());
            assertEquals(3, testunit2.getAttackBonus());
        }
    }


    @Test
    void simulate() {

    }

    @Test
    void checkWin() {
    }

    @Test
    void battleInfo() {
    }

    @Test
    void checkIfUnitIsDead() {
        assertFalse(Battle.isDead(new InfantryUnit("erik", 100)));
    }
    @Test
    void checkIfUnitIsAlive() {
        assertTrue(Battle.isDead(new InfantryUnit("erik", -100)));
    }

    @Nested
    class HealthIsNotSupported{
        @Test
        @DisplayName("Health is not supported.")
        public void healthIsNegative(){
            try{
                Unit unit = new InfantryUnit("Footman", -100);
            } catch (IllegalArgumentException ex){
                assertEquals(ex.getMessage(), "Health must be larger than zero.");
            }
        }
    }

}