package edu.ntnu.idatt2001.gui.models;


import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

/**
 * Class that represents an Army. This is solely used to add data to the table, used in "load-armies.fxml".
 * This should not be confused with the actual Army object, which represent an actual army in a battle.
 *
 */
public class UnitModel {

    /**
     * uses SimpleString and SimpleInt properties. this is to use the correct format in order to add data to the javafx tableview
     */
    private SimpleStringProperty unit;
    private SimpleIntegerProperty quantity;

    /**
     * constructor for armymodel
     * @param unit
     * @param quantity
     */
    public UnitModel(String unit, int quantity){
        this.unit = new SimpleStringProperty(unit);
        this.quantity = new SimpleIntegerProperty(quantity);
    }



    /**
     * getter for unit
     * @return unit of registered competitor
     */
    public String getUnit() {
        return unit.get();
    }

    /**
     * setter for unit
     * @param unit new unit of competitor
     */
    public void setUnit(String unit) {
        this.unit = new SimpleStringProperty(unit);
    }

    /**
     * getter for quantity
     * @return quantity of competitor
     */
    public int getQuantity() {
        return quantity.get();
    }

    /**
     * setter for quantity
     * @param quantity new quantity
     */
    public void setQuantity(int quantity) {
        this.quantity = new SimpleIntegerProperty(quantity);
    }
}