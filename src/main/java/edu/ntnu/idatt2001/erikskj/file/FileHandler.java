package edu.ntnu.idatt2001.erikskj.file;

import edu.ntnu.idatt2001.erikskj.factory.UnitFactory;
import edu.ntnu.idatt2001.erikskj.units.CavalryUnit;
import edu.ntnu.idatt2001.erikskj.units.CommanderUnit;
import edu.ntnu.idatt2001.erikskj.units.InfantryUnit;
import edu.ntnu.idatt2001.erikskj.units.RangedUnit;
import edu.ntnu.idatt2001.erikskj.war.Army;
import org.apache.commons.io.FilenameUtils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Objects;

public class FileHandler {


    public FileHandler(){}

    /**
     * Method for reading an army from a file. Reads line by line, and splits by comma.
     * Method then creates units and adds them to an army before returning the whole army
     * @param path the path of the file containing the army
     * @throws IllegalArgumentException if file does not end with .csv
     * @return Returns the army as an object containing the information specified in the csv file
     */
    public Army readFromFile(String path) throws IllegalArgumentException{
        //checks if file is a csv file
        File file = new File(path);
        if (!FilenameUtils.getExtension(path).equals("csv")){
            throw new IllegalArgumentException("The file needs to be a .csv file.");
        }

        //reads file
        try (BufferedReader bufferedReader = Files.newBufferedReader(Path.of(path))) {
            String line;
            String armyName = bufferedReader.readLine(); // consume first line and set it to the army name

            //if first line contains any commas, an illegalArgumentException will be thrown
            //this is because it's the only way to properly check if the file does not have an army name on the first line
            if (armyName.contains(",")){
                throw new IllegalArgumentException("Army name cannot contain commas. Did you remember to add one?");
            }

            Army army = new Army(armyName);
            UnitFactory factory = new UnitFactory();

            //constants
            String infantryUnit = "InfantryUnit";
            String rangedUnit = "RangedUnit";
            String cavalryUnit = "CavalryUnit";
            String commanderUnit = "CommanderUnit";

            while ((line = bufferedReader.readLine()) != null) {
                String[] words = line.split(",");
                String unitName = words[1];
                int unitHealth = Integer.parseInt(words[2]);
                if (Objects.equals(words[0], infantryUnit)) {
                    //army.add(new InfantryUnit(words[1], Integer.parseInt(words[2])));
                    army.add(factory.createUnit(infantryUnit, unitName, unitHealth));
                }
                else if (Objects.equals(words[0], "RangedUnit")) {
                    //army.add(new RangedUnit(words[1], Integer.parseInt(words[2])));
                    army.add(factory.createUnit(rangedUnit, unitName, unitHealth));
                }
                else if (Objects.equals(words[0], "CavalryUnit")) {
                    //army.add(new CavalryUnit(words[1], Integer.parseInt(words[2])));
                    army.add(factory.createUnit(cavalryUnit, unitName, unitHealth));
                }
                else if (Objects.equals(words[0], "CommanderUnit")) {
                    //army.add(new CommanderUnit(words[1], Integer.parseInt(words[2])));
                    army.add(factory.createUnit(commanderUnit, unitName, unitHealth));
                }
                else{
                    System.err.println("Something went wrong when reading file. Have you deleted all empty lines?");
                }
            }
            return army;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    public void writeToFile(Army army, String path) {
        if (!path.endsWith("csv")){
            path += ".csv";
        }
        try (FileWriter fileWriter = new FileWriter(path)) {
            fileWriter.write(army.getName() + "\n");
            for (int i = 0; i < army.getUnits().size(); i++) {
                fileWriter.write(army.getUnits().get(i).toCSVFormat());
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
