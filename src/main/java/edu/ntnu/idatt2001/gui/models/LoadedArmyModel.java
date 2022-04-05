package edu.ntnu.idatt2001.gui.models;


import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

/**
 * Class that represents an Army. This is solely used to add data to the table, used in "view-armies.fxml".
 * This should not be confused with the actual Army object, which represent an actual army in a battle.
 *
 */
public class LoadedArmyModel {

    /**
     * uses SimpleString and SimpleInt properties. this is to use the correct format in order to add data to the javafx tableview
     */
    private SimpleStringProperty armyName;
    private SimpleIntegerProperty totalUnits;
    private SimpleIntegerProperty totalHealth;
    private SimpleStringProperty fileName;


    public LoadedArmyModel(String armyName, int totalUnits, int totalHealth, String fileName){
        this.armyName = new SimpleStringProperty(armyName);
        this.totalUnits = new SimpleIntegerProperty(totalUnits);
        this.totalHealth = new SimpleIntegerProperty(totalHealth);
        this.fileName = new SimpleStringProperty(fileName);
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
    public String getFileName() {
        return fileName.get();
    }

    /**
     * setter for fileName
     * @param fileName new quantity
     */
    public void setTotalHealth(String fileName) {
        this.fileName = new SimpleStringProperty(fileName);
    }
}
