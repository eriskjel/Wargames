package edu.ntnu.idatt2001.erikskj.client;

import edu.ntnu.idatt2001.erikskj.factory.UnitFactory;
import edu.ntnu.idatt2001.erikskj.file.FileHandler;
import edu.ntnu.idatt2001.erikskj.war.Army;
import edu.ntnu.idatt2001.erikskj.war.Battle;
import edu.ntnu.idatt2001.erikskj.enums.Terrain;
import edu.ntnu.idatt2001.erikskj.units.CavalryUnit;
import edu.ntnu.idatt2001.erikskj.units.CommanderUnit;
import edu.ntnu.idatt2001.erikskj.units.InfantryUnit;
import edu.ntnu.idatt2001.erikskj.units.RangedUnit;

public class WarGamesClient {


    public static void main(String[] args) {

        //test data for if project wants to hardcoded armies to fight
        //at the moment, one army is created by code down below, and one by file

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


        /*
        //creates hardcoded army one, to fight army from file
        Army armyTwo = new Army("Human Army");

        UnitFactory factory = new UnitFactory();

        //adds hundreds of different units to both armies
        for (int i = 0; i < 7; i++) {
            //armyOne.add(new InfantryUnit("Footman", 100));
            armyTwo.add(factory.createUnit("Infantry", "Footman", 100));
        }
        for (int i = 0; i < 1; i++) {
            //armyOne.add(new RangedUnit("Archer", 100));
            armyTwo.add(factory.createUnit("Ranged", "Archer", 100));
        }

        for (int i = 0; i < 2; i++) {
            //armyOne.add(new CavalryUnit("Knight", 100));
            armyTwo.add(factory.createUnit("Cavalry", "Knight", 100));
        }
        for (int i = 0; i < 1; i++) {
            //armyOne.add(new CommanderUnit("Mountain King", 180));
            armyTwo.add(factory.createUnit("Commander", "Footman", 100));
        }

         */


        Terrain terrain = Terrain.PLAINS;

        //specifies the path to the csv file containing the army
        String pathToArmy = "src/main/resources/csv/army.csv";

        //creates fileHandler object to read army from file
        FileHandler fileHandler = new FileHandler();
        //Army armyFromFile = fileHandler.readFromFile(pathToArmy);

        //simulates battle (hardcode army vs. file army)
        Battle battle = new Battle(armyOne, armyTwo, terrain);

        //simulates battle (both are hardcoded armies)
        //Battle battle = new Battle(armyOne, armyTwo);
        battle.simulate();
    }
}

