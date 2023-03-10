package edu.ntnu.idatt2001.erikskj.register;

import edu.ntnu.idatt2001.erikskj.file.FileHandler;
import edu.ntnu.idatt2001.erikskj.war.Army;
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

    /**
     * adds army to register. checks if the army is uploaded and if the army has file, if not then it calls to write the army to file
     * @param army army
     */
    public void add(Army army){
        army.setArmyID(armies.size());
        armies.add(army);
        if (army.isUploaded() && !army.armyHasFile()){
            new FileHandler().writeToFile(army);
        }
    }

    /**
     * method that sets all the IDs of the armies in the register based on the size of the register
     */
    public void setArmyIDs(){
        for (int i = 0; i < this.getArmies().size(); i++) {
            this.getArmies().get(i).setArmyID(i);
        }
    }

    /**
     * resets directory with every army, and creates a new one with correct army ids
     * this is needed because when army ID are reset, because register size is changing, the file names
     * does not automatically change.
     * therefore, this method makes sures that the file names have the same id as the corresponding army
     */
    public void resetAndWriteArmyToFile() {

        new FileHandler().cleanDir();

        //FileHandler fileHandler = new FileHandler();
        for (int i = 0; i < this.getArmies().size(); i++) {
            Army army = this.getArmies().get(i);
            if (army.isUploaded()){
                //fileHandler.writeToFile(army);
                new FileHandler().writeToFile(army);
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
        if (army.isUploaded()){
            File file = new File(army.getArmyFile().getPath());
            //tries to delete file
            try{
                file.delete();
            }
            catch (Exception e){
                System.err.println(e);
            }
        }
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
     */
    public void removeAll() {
        //clear arraylist
        armies.clear();

        new FileHandler().cleanDir();
    }

    /**
     * finds army in register with desired id and returns this army
     * @param id army id
     * @return army in register with matching id
     */
    public Army getArmyByID(int id){
        for (int i = 0; i < this.getArmies().size(); i++) {
            if (this.getArmies().get(i).getArmyID() == id){
                return this.getArmies().get(i);
            }
        }
        return null;
    }

    /**
     * gets a list from filehandler class with all armies from register, and adds these armies to the register
     * @throws IOException exception
     */
    public void readArmiesFromDir() throws IOException {
        FileHandler fileHandler = new FileHandler();
        if (!fileHandler.isArmiesDirEmpty()){
            ArrayList<Army> armies = fileHandler.readArmiesFromRegister();
            for(Army army : armies){
                this.add(army);
            }
            this.setArmyIDs();
        }
        this.resetAndWriteArmyToFile();
    }

    /**
     * resets all armies to original army
     */
    public void resetAllArmies(){
        for (Army army : armies) {
            army.resetArmy();
        }
    }
}
