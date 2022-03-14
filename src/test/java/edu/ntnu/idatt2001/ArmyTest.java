package edu.ntnu.idatt2001;

import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class ArmyTest {

    @Test
    void getInfantryUnits() {
        //creates to armies
        Army armyOne = new Army("Human Army");

        //adds hundreds of different units to both armies
        for (int i = 0; i < 500; i++) {
            armyOne.add(new InfantryUnit("Footman", 10));
        }
        for (int i = 0; i < 500; i++) {
            armyOne.add(new RangedUnit("Archer", 10));
        }

        for (int i = 0; i < 100; i++) {
            armyOne.add(new CavalryUnit("Knight", 5));
        }
        for (int i = 0; i < 200; i++) {
            armyOne.add(new RangedUnit("Archer", 4));
        }
        for (int i = 0; i < 1; i++) {
            armyOne.add(new CommanderUnit("Mountain King", 180));
        }

        System.out.println(armyOne.getInfantryUnits());
        System.out.println(armyOne.getRangedUnits());
        System.out.println(armyOne.getCavalryUnits());
        System.out.println(armyOne.getCommanderUnits());
    }

    @Test
    void writeToFile(){
        Army armyOne = new Army("Human Army");

        //adds hundreds of different units to both armies
        for (int i = 0; i < 10; i++) {
            armyOne.add(new InfantryUnit("Footman", 100));
        }

        armyOne.writeToFile(armyOne.getUnits());
    }
}