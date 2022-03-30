package edu.ntnu.idatt2001;

import edu.ntnu.idatt2001.units.*;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class ArmyTest {


    @Test
    public void addUnit(){
        Army armyOne = new Army("army one");
        armyOne.add(new InfantryUnit("test", 100));
        assertEquals(1, armyOne.getUnits().size());
    }
    @Test
    public void addAllUnits(){
        ArrayList<Unit> units = new ArrayList<>();
        units.add( new InfantryUnit("Gunman", 100));
        units.add( new InfantryUnit("Gunman2", 100));

        Army armyOne = new Army("army one");

        armyOne.addAll(units);
        assertEquals(2, armyOne.getUnits().size());

    }
    @Test
    public void removeUnit(){
        Army armyOne = new Army("army one");
        InfantryUnit unit = new InfantryUnit("test", 100);
        armyOne.add(unit);

        armyOne.remove(unit);
        assertEquals(0, armyOne.getUnits().size());
    }

    @Test
    public void hasUnits(){
        Army armyOne = new Army("army one");
        assertFalse(armyOne.hasUnits());
        armyOne.add(new InfantryUnit("test", 100));
        assertTrue(armyOne.hasUnits());
    }

    @Test
    public void getRandomUnit(){
        Army armyOne = new Army("army one");
        armyOne.add(new InfantryUnit("test", 100));
        armyOne.add(new InfantryUnit("test2", 100));
        armyOne.add(new InfantryUnit("test3", 100));
        System.out.println(armyOne.getRandom().toString());
    }

    @Test
    void getUnits() {
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
        for (int i = 0; i < 1; i++) {
            armyOne.add(new CommanderUnit("Mountain King", 180));
        }


        assertEquals(500, armyOne.getInfantryUnits().size());
        assertEquals(300, armyOne.getRangedUnits().size());
        assertEquals(100, armyOne.getCavalryUnits().size());
        assertEquals(1, armyOne.getCommanderUnits().size());
    }

    @Test
    void writeToFile(){

        Army armyOne = new Army("Human Army");

        //adds hundreds of different units to both armies
        for (int i = 0; i < 10; i++) {
            armyOne.add(new InfantryUnit("Footman", 100));
            armyOne.add(new RangedUnit("Footman", 100));
        }


        //writes to file
        armyOne.writeToFile(armyOne.getUnits());

        //checks if file exists and if the file has content
        File file = new File("csv/test.csv");
        assertTrue(file.exists());
        assertNotEquals(0, file.length());
    }
}