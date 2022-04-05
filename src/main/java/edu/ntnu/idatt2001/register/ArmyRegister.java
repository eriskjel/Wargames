package edu.ntnu.idatt2001.register;

import edu.ntnu.idatt2001.file.FileHandler;
import edu.ntnu.idatt2001.war.Army;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class ArmyRegister {

    private ArrayList<Army> armies;

    public ArmyRegister(){
        this.armies = new ArrayList<>();
    }

    public void add(Army army){
        army.setArmyID(armies.size());
        armies.add(army);
        FileHandler fileHandler = new FileHandler();
        String pathName = army.getFilePath();
        fileHandler.writeToFile(army, pathName);
    }

    public void remove(Army army){
        armies.remove(army);
        File file = new File(army.getFilePath());
        file.delete();
    }

    public int getArmiesSize(){
        return this.armies.size();
    }

    public ArrayList<Army> getArmies(){
        return this.armies;
    }

    public void removeAll() throws IOException {
        armies.clear();
        File file = new File("src/main/resources/armyRegister");
        FileUtils.cleanDirectory(file);

    }
}
