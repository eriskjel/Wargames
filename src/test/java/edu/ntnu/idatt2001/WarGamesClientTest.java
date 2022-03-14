package edu.ntnu.idatt2001;

import org.junit.jupiter.api.Test;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Objects;

import static org.junit.jupiter.api.Assertions.*;

class WarGamesClientTest {

    @Test
    void readFromFile() {
        try (BufferedReader bufferedReader = Files.newBufferedReader(Path.of("C:\\Users\\eriks\\OneDrive - NTNU\\NTNU\\2. Semester\\IDATT2001\\Wargames\\csv\\army.csv"))) {
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
            System.out.println(army.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}