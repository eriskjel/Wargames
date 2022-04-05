package edu.ntnu.idatt2001;

import edu.ntnu.idatt2001.file.FileHandler;
import edu.ntnu.idatt2001.units.CavalryUnit;
import edu.ntnu.idatt2001.units.CommanderUnit;
import edu.ntnu.idatt2001.units.InfantryUnit;
import edu.ntnu.idatt2001.units.RangedUnit;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Objects;

public class WarGamesClient {


    public static void main(String[] args) {

        //test data for if project wants to hardcoded armies to fight
        //at the moment, one army is created by code down below, and one by file
        /*
        //creates to armies
        Army armyOne = new Army("Human Army");
        Army armyTwo = new Army("Orcish Horde");

        //adds hundreds of different units to both armies
        for (int i = 0; i < 500; i++) {
            armyOne.add(new InfantryUnit("Footman", 100));
            armyTwo.add(new InfantryUnit("Grunt", 100));
        }
        for (int i = 0; i < 500; i++) {
            armyOne.add(new RangedUnit("Archer", 100));
            armyTwo.add(new RangedUnit("Ranged",100));
        }

        for (int i = 0; i < 100; i++) {
            armyOne.add(new CavalryUnit("Knight", 100));
            armyTwo.add(new CavalryUnit("Raider", 100));
        }
        for (int i = 0; i < 200; i++) {
            armyOne.add(new RangedUnit("Archer", 100));
            armyTwo.add(new RangedUnit("Spearman", 100));
        }
        for (int i = 0; i < 1; i++) {
            armyOne.add(new CommanderUnit("Mountain King", 180));
            armyTwo.add(new CommanderUnit("Gul´dan", 180));
        }

         */

        //creates hardcoded army one, to fight army from file
        Army armyOne = new Army("Human Army");

        //adds hundreds of different units to both armies
        for (int i = 0; i < 7; i++) {
            armyOne.add(new InfantryUnit("Footman", 100));
        }
        for (int i = 0; i < 1; i++) {
            armyOne.add(new RangedUnit("Archer", 100));
        }

        for (int i = 0; i < 2; i++) {
            armyOne.add(new CavalryUnit("Knight", 100));
        }
        for (int i = 0; i < 1; i++) {
            armyOne.add(new CommanderUnit("Mountain King", 180));
        }

        Terrain terrain = Terrain.PLAINS;

        //specifies the path to the csv file containing the army
        String pathToArmy = "C:\\Users\\eriks\\OneDrive - NTNU\\NTNU\\2. Semester\\IDATT2001\\Wargames\\csv\\army.csv";

        //creates fileHandler object to read army from file
        FileHandler fileHandler = new FileHandler();
        Army armyFromFile = fileHandler.readFromFile(pathToArmy);

        //simulates battle (hardcode army vs. file army)
        Battle battle = new Battle(armyOne, armyFromFile, terrain);

        //simulates battle (both are hardcoded armies)
        //Battle battle = new Battle(armyOne, armyTwo);
        battle.simulate();
    }
}

