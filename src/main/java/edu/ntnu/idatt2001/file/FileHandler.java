package edu.ntnu.idatt2001.file;

import edu.ntnu.idatt2001.war.Army;
import edu.ntnu.idatt2001.units.*;

import java.io.BufferedReader;
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
     * @return Returns the army as an object containing the information specified in the csv file
     */
    public Army readFromFile(String path){
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

    public void writeToFile(Army army, String path) {
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
