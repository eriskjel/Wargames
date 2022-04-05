package edu.ntnu.idatt2001.register;

import edu.ntnu.idatt2001.file.FileHandler;
import edu.ntnu.idatt2001.war.Army;

import java.io.File;
import java.util.ArrayList;

public class ArmyRegister {

    ArrayList<Army> armies = new ArrayList<>();

    public void add(Army army){
        armies.add(army);

        FileHandler fileHandler = new FileHandler();
        String pathName = "src/main/resources/armyRegister/" + army.getName() + army.getArmyID();
        fileHandler.writeToFile(army, pathName);
    }

    public void remove(Army army){
        armies.remove(army);

        String pathName = "src/main/resources/armyRegister/" + army.getName() + army.getArmyID();

        File file = new File(pathName);
        file.delete();
    }
}
