package edu.ntnu.idatt2001.register;

import edu.ntnu.idatt2001.units.CavalryUnit;
import edu.ntnu.idatt2001.units.CommanderUnit;
import edu.ntnu.idatt2001.units.InfantryUnit;
import edu.ntnu.idatt2001.units.RangedUnit;
import edu.ntnu.idatt2001.war.Army;
import org.junit.jupiter.api.Test;

import java.io.File;

import static org.junit.jupiter.api.Assertions.*;

public class ArmyRegisterTest {

    @Test
    public void add() {
        //creates army
        Army armyOne = new Army("Human Army");

        //adds hundreds of different units to both armies
        for (int i = 0; i < 10; i++) {
            armyOne.add(new InfantryUnit("Footman", 10));
        }
        for (int i = 0; i < 5; i++) {
            armyOne.add(new RangedUnit("Archer", 10));
        }
        for (int i = 0; i < 3; i++) {
            armyOne.add(new CavalryUnit("Knight", 5));
        }
        for (int i = 0; i < 1; i++) {
            armyOne.add(new CommanderUnit("Mountain King", 180));
        }

        RegistryClient.armyRegister.add(armyOne);
        assertEquals(1, RegistryClient.armyRegister.getArmies().size());

        File file = new File(armyOne.getFilePath());
        assertTrue(file.exists());

        //delete file so that test leaves no trace
        RegistryClient.armyRegister.remove(armyOne);
    }

    @Test
    public void remove() {
        //creates army
        Army armyOne = new Army("Human Army");

        //adds hundreds of different units to both armies
        for (int i = 0; i < 10; i++) {
            armyOne.add(new InfantryUnit("Footman", 10));
        }
        for (int i = 0; i < 5; i++) {
            armyOne.add(new RangedUnit("Archer", 10));
        }
        for (int i = 0; i < 3; i++) {
            armyOne.add(new CavalryUnit("Knight", 5));
        }
        for (int i = 0; i < 1; i++) {
            armyOne.add(new CommanderUnit("Mountain King", 180));
        }

        RegistryClient.armyRegister.add(armyOne);
        RegistryClient.armyRegister.remove(armyOne);
        assertEquals(0, RegistryClient.armyRegister.getArmies().size());

        File file = new File(armyOne.getFilePath());
        assertFalse(file.exists());
    }
}