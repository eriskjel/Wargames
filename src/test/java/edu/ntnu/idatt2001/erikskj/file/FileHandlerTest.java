package edu.ntnu.idatt2001.erikskj.file;

import edu.ntnu.idatt2001.erikskj.factory.UnitFactory;
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
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

public class FileHandlerTest {

    private String armiesDir = "./ArmiesDir/";

    /**
     * junit test for reading an army from a file.
     * reads army and creates an army object instance. Afterwards that army is compared with the expected, hardcoded, army and if the two are equal
     * then the file reading is done successfully
     */
    @Test
    public void readFromFile() {
        //create army from file
        String filePath = "src/main/resources/csv/TestFiles/testArmy.csv";
        FileHandler fileHandler = new FileHandler();
        Army armyFromFile = fileHandler.readFromFile(filePath);


        //creating equal army to army from file, to check if file reading method works
        Army testFileArmy = new Army("Test Army");
        for (int i = 0; i < 6; i++) {
            testFileArmy.add(new InfantryUnit("Footman", 100));
        }
        testFileArmy.add(new RangedUnit("Archer", 100));
        for (int i = 0; i < 2; i++) {
            testFileArmy.add(new CavalryUnit("The Rock", 100));
        }
        testFileArmy.add(new CommanderUnit("Erik", 180));


        assertEquals(testFileArmy, armyFromFile);
    }

    /**
     * tests if file filehandler class throws exception if file to be read is not csv
     * @throws IOException exception
     */
    @Test
    public void readFromFileNotCSVThrowsException() {
        //get test file
        File file = new File("src/main/resources/csv/TestFiles/armyNOTCSV.txt");

        FileHandler fileHandler = new FileHandler();
        assertThrows(IllegalArgumentException.class, () ->{
            fileHandler.readFromFile(file.getPath());
        });
    }

    /**
     * test to check if filehandler throws exception when there are commas in the first line of csv file containing army
     * @throws IOException exception
     */
    @Test
    public void readArmyFromFileArmyNameNotFound() throws IOException {
        File file = new File("src/main/resources/csv/TestFiles/armyWithoutName.csv");


        FileHandler fileHandler = new FileHandler();
        assertThrows(IllegalArgumentException.class, () ->{
            fileHandler.readFromFile(file.getPath());
        });
    }

    /**
     * test for method writing to file
     * runs writeToFile method from FileHandler class, and afterwards checks if there is a file with the desired name written in the method
     */

    @Test
    public void readArmiesFromRegister(){
        UnitFactory factory = new UnitFactory();
        ArrayList<Army> correctArmyList = new ArrayList<>();

        //create army 2
        Army army2 = new Army("Test Army 2");
        army2.addAll(factory.getListOfUnits("InfantryUnit", 1, "Footman", 100));
        army2.addAll(factory.getListOfUnits("RangedUnit", 1, "Archer", 100));
        army2.addAll(factory.getListOfUnits("CavalryUnit", 3, "The Rock", 100));
        army2.addAll(factory.getListOfUnits("CommanderUnit", 1, "Erik", 200));
        correctArmyList.add(army2);


        //create army 1
        Army army = new Army("Test Army 1");
        army.addAll(factory.getListOfUnits("InfantryUnit", 3, "Footman", 100));
        army.addAll(factory.getListOfUnits("RangedUnit", 1, "Archer", 100));
        army.addAll(factory.getListOfUnits("CavalryUnit", 2, "The Rock", 100));
        army.addAll(factory.getListOfUnits("CommanderUnit", 1, "Erik", 180));
        correctArmyList.add(army);



        ArrayList<Army> armiesFromRegister = new ArrayList<>(new FileHandler().readArmiesFromRegister("src/main/resources/csv/TestFiles/TestRegister/"));
        assertEquals(correctArmyList, armiesFromRegister);

    }

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
        fileHandler.writeToFile(armyOne);


        //checks if file exists and if the file has content
        File file = new File(armiesDir);
        assertTrue(file.exists());
        assertNotEquals(0, file.length());

        //deletes file after test
        armyOne.getArmyFile().delete();
    }


}