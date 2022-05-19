package edu.ntnu.idatt2001.erikskj.war;

import edu.ntnu.idatt2001.erikskj.factory.UnitFactory;
import edu.ntnu.idatt2001.erikskj.units.*;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

/**
 * test class for army class
 */
public class ArmyTest {


    /**
     * test to check if adding unit to army works
     */
    @Test
    public void addUnit(){
        Army armyOne = new Army("army one");
        armyOne.add(new InfantryUnit("test", 100));
        assertEquals(1, armyOne.getUnits().size());
    }

    /**
     * test to check if its possible to add an arraylist of units to army
     */
    @Test
    public void addAllUnits(){
        ArrayList<Unit> units = new ArrayList<>();
        units.add( new InfantryUnit("Gunman", 100));
        units.add( new InfantryUnit("Gunman2", 100));

        Army armyOne = new Army("army one");

        armyOne.addAll(units);
        assertEquals(2, armyOne.getUnits().size());

    }

    /**
     * test to check if removing a unit from an army works
     */
    @Test
    public void removeUnit(){
        Army armyOne = new Army("army one");
        InfantryUnit unit = new InfantryUnit("test", 100);
        armyOne.add(unit);

        armyOne.remove(unit);
        assertEquals(0, armyOne.getUnits().size());
    }

    /**
     * test to see if hasUnits method works. method should return false if army empty and vice versa
     */
    @Test
    public void hasUnits(){
        Army armyOne = new Army("army one");
        assertFalse(armyOne.hasUnits());
        armyOne.add(new InfantryUnit("test", 100));
        assertTrue(armyOne.hasUnits());
    }

    /**
     * test for pulling random unit from army.
     * Tricky test to write, because JUnit test are meant to be handled with for instance expected vs actual output
     * however, the point of randomness is that there are no specified expected output
     * to solve this, you can run the test multiple times and make sure by just visually checking that there are no apparent patterns when pulling units
     */
    @Test
    public void getRandomUnit(){
        Army armyOne = new Army("army one");
        armyOne.add(new InfantryUnit("test", 100));
        armyOne.add(new InfantryUnit("test2", 100));
        armyOne.add(new InfantryUnit("test3", 100));
        System.out.println(armyOne.getRandom().toString());
    }

    /**
     * test for getting all units by units type
     * checks if the size of arraylist returned by getUnit method is the same as the number of units created in the test
     */
    @Test
    public void getUnits() {
        //creates to armies
        Army armyOne = new Army("Human Army");

        //adds hundreds of different units to both armies
        for (int i = 0; i < 500; i++) {
            armyOne.add(new InfantryUnit("Footman", 10));
        }
        for (int i = 0; i < 300; i++) {
            armyOne.add(new RangedUnit("Archer", 10));
        }
        for (int i = 0; i < 100; i++) {
            armyOne.add(new CavalryUnit("Knight", 5));
        }
        for (int i = 0; i < 3; i++) {
            armyOne.add(new CommanderUnit("Mountain King", 180));
        }


        assertEquals(500, armyOne.getInfantryUnits().size());
        assertEquals(300, armyOne.getRangedUnits().size());
        assertEquals(100, armyOne.getCavalryUnits().size());
        assertEquals(3, armyOne.getCommanderUnits().size());
    }


    @Test
    public void getArrayWithUnitNames() {
        Army armyOne = new Army("Human Army");

        //adds hundreds of different units to both armies
        for (int i = 0; i < 10; i++) {
            armyOne.add(new InfantryUnit("Footman", 10));
        }
        for (int i = 0; i < 5; i++) {
            armyOne.add(new RangedUnit("Archer", 10));
        }
        for (int i = 0; i < 2; i++) {
            armyOne.add(new CavalryUnit("Knight", 5));
        }

        ArrayList<String> correctArraylistWithNames = new ArrayList<>();
        correctArraylistWithNames.add("Infantry");
        correctArraylistWithNames.add("Ranged");
        correctArraylistWithNames.add("Cavalry");

        assertEquals(correctArraylistWithNames, armyOne.getArrayWithUnitNames());
    }

    @Test
    public void getNumUnitsByType() {
        Army armyOne = new Army("Human Army");

        //adds hundreds of different units to both armies
        for (int i = 0; i < 10; i++) {
            armyOne.add(new InfantryUnit("Footman", 10));
        }
        for (int i = 0; i < 5; i++) {
            armyOne.add(new RangedUnit("Archer", 10));
        }
        for (int i = 0; i < 2; i++) {
            armyOne.add(new CavalryUnit("Knight", 5));
        }
        for (int i = 0; i < 50; i++) {
            armyOne.add(new RangedUnit("Archer", 10));
        }

        assertEquals(10, armyOne.getNumUnitsByType("Infantry"));
        assertEquals(55, armyOne.getNumUnitsByType("Ranged"));
        assertEquals(2, armyOne.getNumUnitsByType("Cavalry"));
    }

    @Test
    public void getSumHealth(){
        Army armyOne = new Army("Human Army");

        //adds hundreds of different units to both armies
        for (int i = 0; i < 10; i++) {
            armyOne.add(new InfantryUnit("Footman", 10));
        }
        for (int i = 0; i < 10; i++) {
            armyOne.add(new RangedUnit("Archer", 10));
        }
        for (int i = 0; i < 2; i++) {
            armyOne.add(new CavalryUnit("Knight", 5));
        }

        assertEquals(210, armyOne.getSumHealth());
    }

    @Test
    public void hasUnitsAlive() {
        Army armyOne = new Army("Human Army");

        //adds hundreds of different units to both armies
        for (int i = 0; i < 10; i++) {
            armyOne.add(new InfantryUnit("Footman", 10));
        }
        for (int i = 0; i < 10; i++) {
            armyOne.add(new RangedUnit("Archer", 10));
        }
        for (int i = 0; i < 2; i++) {
            armyOne.add(new CavalryUnit("Knight", 5));
        }

        assertTrue(armyOne.hasUnitsAlive());
    }

    @Test
    public void hasOneUnitAlive() {
        Army armyOne = new Army("Human Army");

        UnitFactory factory = new UnitFactory();
        armyOne.addAll(factory.getListOfUnits("InfantryUnit", 10, "Erik", 10));
        armyOne.addAll(factory.getListOfUnits("RangedUnit", 10, "Erik", 10));
        armyOne.addAll(factory.getListOfUnits("CavalryUnit", 10, "Erik", 10));
        armyOne.addAll(factory.getListOfUnits("CommanderUnit", 10, "Erik", 10));

        //sets health of all units to zero except one unit, meaning they are all killed in combat
        for (int i = 0; i < armyOne.getUnits().size() - 1; i++) {
            armyOne.getUnits().get(i).setHealth(0);
        }

        System.out.println(armyOne);
        assertTrue(armyOne.hasUnitsAlive());
    }

    @Test
    public void hasNotUnitsAlive() {
        Army armyOne = new Army("Human Army");

        UnitFactory factory = new UnitFactory();
        armyOne.addAll(factory.getListOfUnits("InfantryUnit", 10, "Erik", 10));
        armyOne.addAll(factory.getListOfUnits("RangedUnit", 10, "Erik", 10));
        armyOne.addAll(factory.getListOfUnits("CavalryUnit", 10, "Erik", 10));
        armyOne.addAll(factory.getListOfUnits("CommanderUnit", 10, "Erik", 10));

        //sets health of all units to zero, meaning they are all killed in combat
        for (int i = 0; i < armyOne.getUnits().size(); i++) {
            armyOne.getUnits().get(i).setHealth(0);
        }

        assertFalse(armyOne.hasUnitsAlive());
    }

    @Test
    public void getRandomAliveUnit() {

        Army armyOne = new Army("Human Army");

        UnitFactory factory = new UnitFactory();
        armyOne.addAll(factory.getListOfUnits("InfantryUnit", 10, "Kenneth", 10));
        armyOne.addAll(factory.getListOfUnits("RangedUnit", 10, "Paul", 10));
        //sets health of all units to zero, meaning they are all killed in combat
        for (int i = 0; i < armyOne.getUnits().size(); i++) {
            armyOne.getUnits().get(i).setHealth(0);
        }
        armyOne.addAll(factory.getListOfUnits("CavalryUnit", 1, "Sam", 10));
        Unit unit = new CommanderUnit("Lord", 180);
        unit.setHealth(0);
        armyOne.add(unit);




        System.out.println(armyOne);
        assertEquals("Sam", armyOne.getRandomAliveUnit().getName());
    }

    @Test
    public void getNumAliveUnitsByType() {
        Army armyOne = new Army("Human Army");

        //adds hundreds of different units to both armies
        for (int i = 0; i < 10; i++) {
            armyOne.add(new InfantryUnit("Footman", 10));
        }


        for (int i = 0; i < 10; i++) {
            armyOne.add(new RangedUnit("Archer", 10));
        }
        Unit unit1 = new RangedUnit("Archer", 100);
        unit1.setHealth(0);
        armyOne.add(unit1);

        for (int i = 0; i < 2; i++) {
            armyOne.add(new CavalryUnit("Knight", 5));
        }

        for (int i = 0; i < 10; i++) {
            armyOne.add(new CommanderUnit("Footman", 10));
        }
        Unit unit2 = new CommanderUnit("Lord", 180);
        unit2.setHealth(0);
        armyOne.add(unit2);

        assertEquals(10, armyOne.getNumAliveUnitsByType("Infantry"));
        assertEquals(10, armyOne.getNumAliveUnitsByType("Ranged"));
        assertEquals(2, armyOne.getNumAliveUnitsByType("Cavalry"));
        assertEquals(10, armyOne.getNumAliveUnitsByType("Commander"));
    }

    @Test
    public void resetArmy() {
        Army armyOne = new Army("Human Army");

        int sumHealth = 0;

        for (int i = 0; i < 4; i++) {
            Unit unit = new InfantryUnit("Erik", 100);
            sumHealth += unit.getHealth();
            unit.setHealth(0);
            armyOne.add(unit);
        }

        for (int i = 0; i < 5; i++) {
            Unit unit = new RangedUnit("Archer", 63);
            sumHealth += unit.getHealth();
            unit.setHealth(0);
            armyOne.add(unit);
        }

        for (int i = 0; i < 6; i++) {
            Unit unit = new CavalryUnit("Knight", 72);
            sumHealth += unit.getHealth();
            unit.setHealth(0);
            armyOne.add(unit);
        }

        for (int i = 0; i < 3; i++) {
            Unit unit = new CommanderUnit("Lord", 180);
            sumHealth += unit.getHealth();
            unit.setHealth(0);
            armyOne.add(unit);
        }

        armyOne.resetArmy();
        assertEquals(sumHealth, armyOne.getSumHealth());
    }
}