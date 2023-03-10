package edu.ntnu.idatt2001.erikskj.file;

import edu.ntnu.idatt2001.erikskj.factory.UnitFactory;
import edu.ntnu.idatt2001.erikskj.war.Army;
import org.apache.commons.io.FilenameUtils;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Objects;

/**
 * Class that handles all the file reading, writing and other methods regarding file usage
 */
public class FileHandler {

    private final String defaultPath = "./ArmiesDir/";


    /**
     * Method for reading an army from a file. Reads line by line, and splits by comma.
     * Method then creates units and adds them to an army before returning the whole army
     * @param loadedFile file containing army
     * @return Returns the army as an object containing the information specified in the csv file
     * @throws IllegalArgumentException if file does not end with .csv
     */
    public Army readFromFile(File loadedFile) throws IllegalArgumentException{
        //checks if file is a csv file
        if (!FilenameUtils.getExtension(loadedFile.getName()).equals("csv")){
                throw new IllegalArgumentException("The file needs to be a .csv file.");
        }

        //reads file
        try (BufferedReader bufferedReader = Files.newBufferedReader(Path.of(loadedFile.getPath()))) {
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
                String[] elements = line.split(",");

                String unitType = elements[0];
                String unitName = elements[1];
                int unitHealth = Integer.parseInt(elements[2]);

                switch (unitType){
                    case "InfantryUnit" -> army.add(factory.createUnit(infantryUnit, unitName, unitHealth));
                    case "RangedUnit" -> army.add(factory.createUnit(rangedUnit, unitName, unitHealth));
                    case "CavalryUnit" -> army.add(factory.createUnit(cavalryUnit, unitName, unitHealth));
                    case "CommanderUnit" -> army.add(factory.createUnit(commanderUnit, unitName, unitHealth));
                    default -> System.err.println("Something went wrong when reading file and creating units");
                }
            }

            if (army.getArmyFile() == null){
                army.setArmyFile(loadedFile);
            }
            return army;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * Method for reading an army from a file. Reads line by line, and splits by comma.
     * Method then creates units and adds them to an army before returning the whole army
     * @param path path to file containing army
     * @return Returns the army as an object containing the information specified in the csv file
     * @throws IllegalArgumentException if file does not end with .csv
     */
    public Army readFromFile(String path) throws IllegalArgumentException{
        //checks if file is a csv file
        //File file = new File(path);
        File file = new File(path);
        if (!FilenameUtils.getExtension(file.getName()).equals("csv")){
            throw new IllegalArgumentException("The file needs to be a .csv file.");
        }

        //reads file
        try (BufferedReader bufferedReader = Files.newBufferedReader(Path.of(file.getPath()))) {
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
            if (army.getArmyFile() == null){
                army.setArmyFile(file);
            }
            return army;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * method that reads all files from armies directory, except .gitkeep, and calls on the readFromFile method to create
     * army objects from the files. Method stores all read armies in a list and returns this list
     * @return ArrayList with all armies read from armies directory
     */
    public ArrayList<Army> readArmiesFromRegister(){
        ArrayList<Army> armies = new ArrayList<>();
        File dir = new File(defaultPath);
        File[] directoryListing = dir.listFiles();
        if (directoryListing != null) {
            for (File child : directoryListing) {
                if (!child.getName().equals(".gitkeep")){
                    armies.add(readFromFile(child));
                }
            }
        }
        else{
            return null;
        }
        return armies;
    }

    /**
     * used for testing, because test armies is in a separate folder from default path
     *
     * method that reads all files from armies directory, except .gitkeep, and calls on the readFromFile method to create
     * army objects from the files. Method stores all read armies in a list and returns this list
     * @param path path to test armies
     * @return arraylist with armies
     */
    public ArrayList<Army> readArmiesFromRegister(String path){
        ArrayList<Army> armies = new ArrayList<>();
        File dir = new File(path);
        File[] directoryListing = dir.listFiles();
        if (directoryListing != null) {
            for (File child : directoryListing) {
                armies.add(readFromFile(child));
            }
        }
        else{
            return null;
        }
        return armies;
    }

    /**
     * method that takes an army object and writes it to a file in the armies directory
     * @param army army object
     */
    public void writeToFile(Army army) {
        String path = defaultPath + army.generateFileName();
        if (!path.endsWith(".csv")){
            path += ".csv";
        }
        try (FileWriter fileWriter = new FileWriter(path)) {
            fileWriter.write(army.getName() + "\n");
            for (int i = 0; i < army.getUnits().size(); i++) {
                fileWriter.write(army.getUnits().get(i).toCSVFormat());
            }

            army.setArmyFile(new File(path));
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    /**
     * creates armies directory if one does not excist already. Also creates the .gitkeep file if it does not excist.
     * @throws IOException exception
     */
    public void initDirectory() throws IOException {
        File armiesDir = new File("./ArmiesDir");
        if (!armiesDir.exists()){
            new File("./ArmiesDir").mkdirs();
            File gitkeep = new File("./ArmiesDir/.gitkeep");
            gitkeep.createNewFile();
        }
    }


    /**
     * method that checks if armies directory is empty or not
     * @return true if empty and false if not
     * @throws IOException exception
     */
    public boolean isArmiesDirEmpty() throws IOException {
        try(DirectoryStream<Path> dirStream = Files.newDirectoryStream(Path.of(defaultPath))) {
            return !dirStream.iterator().hasNext();
        }
    }


    /**
     * cleans armies directory by deleting all files except gitkeep
     */
    public void cleanDir() {
        File dir = new File(defaultPath);
        for (File file : Objects.requireNonNull(dir.listFiles())) {
            if (!file.getName().equals(".gitkeep")) {
                file.delete();
            }
        }
    }
}
