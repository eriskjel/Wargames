package edu.ntnu.idatt2001.erikskj.file;

import edu.ntnu.idatt2001.erikskj.units.InfantryUnit;
import edu.ntnu.idatt2001.erikskj.units.RangedUnit;
import edu.ntnu.idatt2001.erikskj.war.Army;
import edu.ntnu.idatt2001.erikskj.units.CavalryUnit;
import edu.ntnu.idatt2001.erikskj.units.CommanderUnit;
import javafx.stage.FileChooser;
import org.junit.jupiter.api.Test;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

public class FileHandlerTest {

    /**
     * junit test for reading an army from a file.
     * reads army and creates an army object instance. Afterwards that army is compared with the expected, hardcoded, army and if the two are equal
     * then the file reading is done successfully
     */
    /*
    @Test
    public void readFromFile() {
        //create army from file
        String filePath = "src/main/resources/csv/army.csv";
        FileHandler fileHandler = new FileHandler();
        Army armyFromFile = fileHandler.readFromFile(filePath);
        //Army armyFromFile = WarGamesClient.readFromFile(filePath);


        //creating equal army to army from file, to check if file reading method works
        Army testFileArmy = new Army("File Army");
        for (int i = 0; i < 6; i++) {
            testFileArmy.add(new InfantryUnit("Footman", 100));
        }
        testFileArmy.add(new RangedUnit("Archer", 100));
        for (int i = 0; i < 2; i++) {
            testFileArmy.add(new CavalryUnit("The Rock", 100));
        }
        testFileArmy.add(new CommanderUnit("Erik", 180));


        assertEquals(armyFromFile, testFileArmy);
    }

     */

    @Test
    public void UploadArmy(){
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Open Resource File");
        File file = new File("src/main/resources/csv/army.csv");
        FileHandler fileHandler = new FileHandler();
        Army army = fileHandler.readFromFile(file);
        System.out.println(army);
    }

    /**
     * tests if file filehandler class throws exception if file to be read is not csv
     * @throws IOException exception
     */
    @Test
    public void readFromFileNotCSVThrowsException() throws IOException {
        //create new file
        String filePath = "src/main/resources/testFiles/NOTCSV.notCSV";
        File file = new File(filePath);
        file.createNewFile();

        FileHandler fileHandler = new FileHandler();
        assertThrows(IllegalArgumentException.class, () ->{
            fileHandler.readFromFile(filePath);
        });

        //deletes file after test
        file.delete();
    }

    /**
     * test to check if filehandler throws exception when there are commas in the first line of csv file containing army
     * @throws IOException exception
     */
    @Test
    public void readArmyFromFileArmyNameNotFound() throws IOException {
        String path = "src/main/resources/testFiles/armyWithOutName.csv";
        BufferedWriter writer = new BufferedWriter(new FileWriter(path));

        //writing to file, imitating a scenario where the army name is not specified, and the
        //file only contains the units
        writer.write("InfantryUnit,The man,1000");
        writer.close();

        FileHandler fileHandler = new FileHandler();
        assertThrows(IllegalArgumentException.class, () ->{
            fileHandler.readFromFile(path);
        });

        //deletes file after test
        File file = new File(path);
        file.delete();
    }

    /**
     * test for method writing to file
     * runs writeToFile method from FileHandler class, and afterwards checks if there is a file with the desired name written in the method
     */
    /*
    @Test
    public void writeToFile(){

        Army armyOne = new Army("Human Army");

        //adds hundreds of different units to both armies
        for (int i = 0; i < 10; i++) {
            armyOne.add(new InfantryUnit("Footman", 100));
            armyOne.add(new RangedUnit("Archer", 100));
        }


        //writes to file
        FileHandler fileHandler = new FileHandler();
        String path = "src/main/resources/testFiles/testnew.csv";
        fileHandler.writeToFile(armyOne);

        //checks if file exists and if the file has content
        File file = new File(path);
        assertTrue(file.exists());
        assertNotEquals(0, file.length());

        //deletes file after test
        file.delete();
    }

     */
}