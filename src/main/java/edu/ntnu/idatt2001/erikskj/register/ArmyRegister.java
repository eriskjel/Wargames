package edu.ntnu.idatt2001.erikskj.register;

import edu.ntnu.idatt2001.erikskj.file.FileHandler;
import edu.ntnu.idatt2001.erikskj.war.Army;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Class that holds a register over all armies in application
 */
public class ArmyRegister {

    private final ArrayList<Army> armies;

    /**
     * constructor for class, which creates arraylist instance
     */
    public ArmyRegister(){
        this.armies = new ArrayList<>();
    }

    public void add(Army army){
        army.setArmyID(armies.size());
        armies.add(army);
        if (!army.isUploaded()){
            writeArmyToFile(army);
        }
    }

    public void setArmyIDs(){
        for (int i = 0; i < this.getArmies().size(); i++) {
            this.getArmies().get(i).setArmyID(i);
        }
    }

    public void writeArmyToFile(Army army){
        FileHandler fileHandler = new FileHandler();
        //fileHandler.writeToFile(army, army.getFilePathAndName(), armyIsCreated);
        fileHandler.writeToFile(army);
    }


    /**
     * resets directory with every army, and creates a new one with correct army ids
     * this is needed because when army ID are reset, because register size is changing, the file names
     * does not automatically change.
     * therefore, this method makes sures that the file names have the same id as the corresponding army
     * @throws IOException exception
     */
    public void resetAndWriteArmyToFile() throws IOException {
        //deletes all files in army directory
        File file = new File("./ArmiesDir/Armies/");
        FileUtils.cleanDirectory(file);


        /*
        File gitkeep = new File("./Armies/.gitkeep");
        gitkeep.createNewFile();

         */

        FileHandler fileHandler = new FileHandler();


        for (int i = 0; i < this.getArmies().size(); i++) {
            Army army = this.getArmies().get(i);
            if (!army.isUploaded()){
                fileHandler.writeToFile(army);
            }
        }
    }

    /**
     * removes army from register, and deleted corresponding army file
     * @param army army
     */
    public void remove(Army army){
        //removes army from arraylist register
        armies.remove(army);

        //gets file path
        if (!army.isUploaded()){
            File file = new File(army.getArmyFile().getPath());
            //tries to delete file
            try{
                file.delete();
            }
            catch (Exception e){
                System.err.println(e);
            }
        }
        /*


         */
    }

    /**
     * returns register
     * @return army register
     */
    public ArrayList<Army> getArmies(){
        return this.armies;
    }

    /**
     * removes every army from register, and deletes all files from army register folder
     * @throws IOException exception
     */
    public void removeAll() throws IOException {
        //clear arraylist
        armies.clear();
        //deletes directory
        File file = new File("src/main/resources/armyRegister");
        FileUtils.cleanDirectory(file);
    }

    public Army getArmyByID(int id){
        for (int i = 0; i < this.getArmies().size(); i++) {
            if (this.getArmies().get(i).getArmyID() == id){
                return this.getArmies().get(i);
            }
        }
        return null;
    }

    public void readArmiesFromDir() throws IOException {
        FileHandler fileHandler = new FileHandler();
        if (!fileHandler.isDirEmpty()){
            ArrayList<Army> armies = fileHandler.readArmyFromRegister();
            for(Army army : armies){
                this.add(army);
            }
        }
    }
}
