package edu.ntnu.idatt2001.erikskj.factory;

import edu.ntnu.idatt2001.erikskj.units.*;
import java.util.ArrayList;

/**
 * Unit factory class.
 */
public class UnitFactory {

    /**
     * Method for creating a unit. Takes type of unit, unit name and health points and returns a unit with these values
     * @param unitType type of unit
     * @param name name of unit
     * @param health health of unit
     * @return Unit object
     * @throws IllegalArgumentException Throws exception if unit could not be made due to invalid unit type
     */
    public Unit createUnit(String unitType, String name, int health) throws IllegalArgumentException{
        if (unitType == null){
            throw new IllegalArgumentException("Invalid unit type");
        }
        if (unitType.equalsIgnoreCase("InfantryUnit")){
            return new InfantryUnit(name, health);
        }
        else if(unitType.equalsIgnoreCase("RangedUnit")){
            return new RangedUnit(name, health);
        }
        else if(unitType.equalsIgnoreCase("CavalryUnit")){
            return new CavalryUnit(name, health);
        }
        else if (unitType.equalsIgnoreCase("CommanderUnit")){
            return new CommanderUnit(name, health);
        }
        else{
            throw new IllegalArgumentException("Invalid unit type");
        }
    }

    /**
     * Method for creating and returning a list of units.
     * Takes type of unit, integer n representing number of desired units to be made,
     * unit name and health points and returns a list with units with specified values
     * @param unitType unit type
     * @param n number of units to be made
     * @param name name of unit
     * @param health health of unit
     * @return arraylist with unit objects
     * @throws IllegalArgumentException Throws exception if number n, number of units to be made, is lower than 0.
     */
    public ArrayList<Unit> getListOfUnits(String unitType, int n, String name, int health) throws IllegalArgumentException{
        if (n < 0){
            throw new IllegalArgumentException("Invalid number of n units.");
        }
        ArrayList<Unit> units = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            units.add(createUnit(unitType, name, health));
        }
        return units;
    }
}
