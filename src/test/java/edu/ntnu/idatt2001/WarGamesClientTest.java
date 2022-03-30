package edu.ntnu.idatt2001;

import edu.ntnu.idatt2001.units.CavalryUnit;
import edu.ntnu.idatt2001.units.CommanderUnit;
import edu.ntnu.idatt2001.units.InfantryUnit;
import edu.ntnu.idatt2001.units.RangedUnit;
import org.junit.jupiter.api.Assertions;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Objects;

class WarGamesClientTest {

    @Test
    void readFromFile() {
        Army armyFromFile = null;
        try (BufferedReader bufferedReader = Files.newBufferedReader(Path.of("C:\\Users\\eriks\\OneDrive - NTNU\\NTNU\\2. Semester\\IDATT2001\\Wargames\\csv\\army.csv"))) {
            String line;
            String armyName = bufferedReader.readLine(); // consume first line and set it to the army name
            //line = bufferedReader.readLine();
            armyFromFile = new Army(armyName);
            //Army army = new Army(armyName);
            while ((line = bufferedReader.readLine()) != null) {
                String[] words = line.split(",");
                if (Objects.equals(words[0], "InfantryUnit")) {
                    armyFromFile.add(new InfantryUnit(words[1], Integer.parseInt(words[2])));
                }
                else if (Objects.equals(words[0], "RangedUnit")) {
                    armyFromFile.add(new RangedUnit(words[1], Integer.parseInt(words[2])));
                }
                else if (Objects.equals(words[0], "CavalryUnit")) {
                    armyFromFile.add(new CavalryUnit(words[1], Integer.parseInt(words[2])));
                }
                else if (Objects.equals(words[0], "CommanderUnit")) {
                    armyFromFile.add(new CommanderUnit(words[1], Integer.parseInt(words[2])));
                }
                else{
                    System.err.println("Something went wrong");
                }
            }

            //System.out.println(armyFromFile.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }

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