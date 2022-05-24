package edu.ntnu.idatt2001.erikskj.client;

import edu.ntnu.idatt2001.erikskj.enums.Terrain;
import edu.ntnu.idatt2001.erikskj.units.CavalryUnit;
import edu.ntnu.idatt2001.erikskj.units.CommanderUnit;
import edu.ntnu.idatt2001.erikskj.units.InfantryUnit;
import edu.ntnu.idatt2001.erikskj.units.RangedUnit;
import edu.ntnu.idatt2001.erikskj.war.Army;
import edu.ntnu.idatt2001.erikskj.war.Battle;

/**
 * Client class for simulating war. This was used before the GUI was implemented, and has no value to the rest of the program.
 * More specifically this java class was not mandatory, but described as task 6 in the first iteration of the project
 */
public class WarGamesClient {


    public static void main(String[] args) {

        //test data for if project wants to hardcoded armies to fight
        //at the moment, one army is created by code down below, and one by file

        //creates two armies
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

        Terrain terrain = Terrain.PLAINS;

        //simulates battle
        Battle battle = new Battle(armyOne, armyTwo, terrain);
        battle.simulate();
    }
}

