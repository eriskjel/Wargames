package edu.ntnu.idatt2001.erikskj.gui.models;


import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.scene.image.ImageView;

import java.io.File;

/**
 * Class that represents an Army. This is solely used to add data to the table, used in "view-armies.fxml".
 * This should not be confused with the actual Army object, which represent an actual army in a battle.
 *
 */
public class LoadedArmyModel {

    /**
     * uses SimpleString and SimpleInt properties. this is to use the correct format in order to add data to the javafx tableview
     */
    //private final ImageView icon;
    private SimpleStringProperty armyName;
    private SimpleIntegerProperty totalUnits;
    private SimpleIntegerProperty totalHealth;
    //private SimpleStringProperty filePath;
    private final int armyID;
    //private SimpleStringProperty fileName;
    private File armyFile;



    public LoadedArmyModel(String armyName, int totalUnits, int totalHealth, File armyFile, int armyID){
        this.armyName = new SimpleStringProperty(armyName);
        this.totalUnits = new SimpleIntegerProperty(totalUnits);
        this.totalHealth = new SimpleIntegerProperty(totalHealth);
        //this.filePath = new SimpleStringProperty(filePath);
        this.armyFile = armyFile;
        this.armyID = armyID;
    }



    /**
     * getter for army name
     * @return army name
     */
    public String getArmyName() {
        return armyName.get();
    }

    /**
     * setter for army name
     * @param armyName new unit of competitor
     */
    public void setUnit(String armyName) {
        this.armyName = new SimpleStringProperty(armyName);
    }

    /**
     * getter for total units
     * @return total units of army
     */
    public int getTotalUnits() {
        return totalUnits.get();
    }

    /**
     * setter for total units
     * @param totalUnits new quantity
     */
    public void setTotalUnits(int totalUnits) {
        this.totalUnits = new SimpleIntegerProperty(totalUnits);
    }

    /**
     * getter for total units
     * @return total units of army
     */
    public int getTotalHealth() {
        return totalHealth.get();
    }

    /**
     * setter for total units
     * @param totalHealth new quantity
     */
    public void setTotalHealth(int totalHealth) {
        this.totalHealth = new SimpleIntegerProperty(totalHealth);
    }

    /**
     * getter for fileName
     * @return fileName
     */
    public String getFilePath() {
        return armyFile.getPath();
    }

    /**
     * setter for fileName
     * @param file new quantity
     */
    public void setFilePath(File file) {
        this.armyFile = file;
    }

    public int getArmyID(){
        return this.armyID;
    }

    public String getFileName(){
        return armyFile.getName();
    }

    /*
    public void setFileName(String fileName){
        this.fileName = new SimpleStringProperty(fileName);
    }

     */

}
