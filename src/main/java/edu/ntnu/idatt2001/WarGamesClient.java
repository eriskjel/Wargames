package edu.ntnu.idatt2001;

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

        //specifies the path to the csv file containing the army
        String pathToArmy = "C:\\Users\\eriks\\OneDrive - NTNU\\NTNU\\2. Semester\\IDATT2001\\Wargames\\csv\\army.csv";
        Army armyFromFile = readFromFile(pathToArmy);

        //simulates battle (hardcode army vs. file army)
        Battle battle = new Battle(armyOne, armyFromFile);

        //simulates battle (both are hardcoded armies)
        //Battle battle = new Battle(armyOne, armyTwo);
        battle.simulate();
    }

    /**
     * Method for reading an army from a file. Reads line by line, and splits by comma.
     * Method then creates units and adds them to an army before returning the whole army
     * @param path the path of the file containing the army
     * @return Returns the army as an object containing the information specified in the csv file
     */
    public static Army readFromFile(String path){
        try (BufferedReader bufferedReader = Files.newBufferedReader(Path.of(path))) {
            String line;
            String armyName = bufferedReader.readLine(); // consume first line and set it to the army name
            //line = bufferedReader.readLine();
            Army army = new Army(armyName);
            while ((line = bufferedReader.readLine()) != null) {
                String[] words = line.split(",");
                if (Objects.equals(words[0], "InfantryUnit")) {
                    army.add(new InfantryUnit(words[1], Integer.parseInt(words[2])));
                }
                else if (Objects.equals(words[0], "RangedUnit")) {
                    army.add(new RangedUnit(words[1], Integer.parseInt(words[2])));
                }
                else if (Objects.equals(words[0], "CavalryUnit")) {
                    army.add(new CavalryUnit(words[1], Integer.parseInt(words[2])));
                }
                else if (Objects.equals(words[0], "CommanderUnit")) {
                    army.add(new CommanderUnit(words[1], Integer.parseInt(words[2])));
                }
                else{
                    System.err.println("Something went wrong");
                }
            }
            return army;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}

