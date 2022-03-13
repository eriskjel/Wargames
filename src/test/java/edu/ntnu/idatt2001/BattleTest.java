package edu.ntnu.idatt2001;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class BattleTest {

    /**
     *
     */
    @Nested
    class CheckObjectInstances{
        /**
         * These 4 tests check if the object can be instanced, as they should, without problems.
         * This nested regards task 3.
         */
        @Test
        void checkUnit1(){
            Unit infantry = new InfantryUnit("Gunman", 100);
        }
        @Test
        void checkUnit2(){
            Unit infantry = new RangedUnit("Archer", 100);
        }
        @Test
        void checkUnit3(){
            Unit infantry = new CavalryUnit("The Rock", 100);
        }
        @Test
        void checkUnit4(){
            Unit infantry = new CommanderUnit("Archer", 180);
        }

        /**
         * These 4 tests, are testing negatively, meaning they're checking for wrong inputs. Solely negative health in this matter
         * because any other inputs the IDE will count as error. However negative health is not an error per say, but an udesired value.
         */
        @Test
        void checkUnit1Invalid(){
            try{
                Unit infantry = new InfantryUnit("Gunman", -100);
            }
            catch (IllegalArgumentException e){
                System.out.println("Health cannot be lower than 0.");
            }
        }
        @Test
        void checkUnit2Invalid(){
            try{
                Unit ranged = new RangedUnit("archer", -100);
            }
            catch (IllegalArgumentException e){
                System.out.println("Health cannot be lower than 0.");
            }
        }
        @Test
        void checkUnit3Invalid(){
            try{
                Unit cavalry = new CavalryUnit("Gunman", -100);
            }
            catch (IllegalArgumentException e){
                System.out.println("Health cannot be lower than 0.");
            }
        }
        @Test
        void checkUnit4Invalid(){
            try{
                Unit commanding = new CommanderUnit("Gunman", -100);
            }
            catch (IllegalArgumentException e){
                System.out.println("Health cannot be lower than 0.");
            }
        }
    }

    /**
     * These tests will test the methods in the army class. Task 4.
     */
    @Nested
    class ArmyTests{
        @Test
        void addUnit(){
            Army armyOne = new Army("army one");
            armyOne.add(new InfantryUnit("test", 100));
        }
        @Test
        void addAllUnits(){
            ArrayList<Unit> units = new ArrayList<>();
            units.add( new InfantryUnit("Gunman", 100));
            units.add( new InfantryUnit("Gunman2", 100));
            Army armyOne = new Army("army one", units
            );
            armyOne.add(new InfantryUnit("test", 100));
        }
        @Test
        void removeUnit(){
            ArrayList<Unit> units = new ArrayList<>();
            Unit unit = new InfantryUnit("test", 100);
            units.add(unit);
            units.remove(unit);
            assertTrue(units.isEmpty());
        }
        @Test
        void hasUnits(){
            Army armyOne = new Army("army one");
            assertFalse(armyOne.hasUnits());
            armyOne.add(new InfantryUnit("test", 100));
            assertTrue(armyOne.hasUnits());
        }
        @Test
        void getRandomUnit(){
            Army armyOne = new Army("army one");
            armyOne.add(new InfantryUnit("test", 100));
            armyOne.add(new InfantryUnit("test2", 100));
            armyOne.add(new InfantryUnit("test3", 100));
            System.out.println(armyOne.getRandom().toString());
        }
    }


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
        battle.simulate();    }

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
        Unit test = new InfantryUnit("erik", 100);
        test.setHealth(-100);
        assertTrue(Battle.isDead(test));

    }


    /*
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
     */

}