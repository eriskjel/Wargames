package edu.ntnu.idatt2001.file;

import edu.ntnu.idatt2001.Army;
import edu.ntnu.idatt2001.WarGamesClient;
import edu.ntnu.idatt2001.units.CavalryUnit;
import edu.ntnu.idatt2001.units.CommanderUnit;
import edu.ntnu.idatt2001.units.InfantryUnit;
import edu.ntnu.idatt2001.units.RangedUnit;
import org.junit.jupiter.api.Test;

import java.io.File;

import static org.junit.jupiter.api.Assertions.*;

public class FileHandlerTest {

    /**
     * junit test for reading an army from a file.
     * reads army and creates an army object instance. Afterwards that army is compared with the expected, hardcoded, army and if the two are equal
     * then the file reading is done successfully
     */
    @Test
    public void readFromFile() {
        //create army from file
        String filePath = "C:\\Users\\eriks\\OneDrive - NTNU\\NTNU\\2. Semester\\IDATT2001\\Wargames\\csv\\army.csv";
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

    /**
     * test for method writing to file
     * runs writeToFile method from FileHandler class, and afterwards checks if there is a file with the desired name written in the method
     */
    @Test
    public void writeToFile(){

        Army armyOne = new Army("Human Army");

        //adds hundreds of different units to both armies
        for (int i = 0; i < 10; i++) {
            armyOne.add(new InfantryUnit("Footman", 100));
            armyOne.add(new RangedUnit("Footman", 100));
        }


        //writes to file
        FileHandler fileHandler = new FileHandler();
        fileHandler.writeToFile(armyOne);

        //checks if file exists and if the file has content
        File file = new File("csv/test.csv");
        assertTrue(file.exists());
        assertNotEquals(0, file.length());
    }
}