package edu.ntnu.idatt2001.register;

import edu.ntnu.idatt2001.units.CavalryUnit;
import edu.ntnu.idatt2001.units.InfantryUnit;
import edu.ntnu.idatt2001.units.RangedUnit;
import edu.ntnu.idatt2001.war.Army;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class RegistryClientTest {

    @Test
    public void registryClientTest(){
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

        RegistryClient.armyRegister.add(armyOne);
        System.out.println(RegistryClient.armyRegister.getArmies());
    }

}