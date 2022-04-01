package edu.ntnu.idatt2001;

import edu.ntnu.idatt2001.units.CavalryUnit;
import edu.ntnu.idatt2001.units.CommanderUnit;
import edu.ntnu.idatt2001.units.InfantryUnit;
import edu.ntnu.idatt2001.units.RangedUnit;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;


/**
 * Test class for WarGamesClient
 */
public class WarGamesClientTest {

    /**
     * junit test for reading an army from a file.
     * reads army and creates an army object instance. Afterwards that army is compared with the expected, hardcoded, army and if the two are equal
     * then the file reading is done successfully
     */
    @Test
    public void readFromFile() {
        //create army from file
        String filePath = "C:\\Users\\eriks\\OneDrive - NTNU\\NTNU\\2. Semester\\IDATT2001\\Wargames\\csv\\army.csv";
        Army armyFromFile = WarGamesClient.readFromFile(filePath);


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
}