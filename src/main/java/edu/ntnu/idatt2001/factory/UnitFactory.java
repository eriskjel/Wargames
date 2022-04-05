package edu.ntnu.idatt2001.factory;

import edu.ntnu.idatt2001.units.*;

import java.util.ArrayList;

public class UnitFactory {

    public Unit createUnit(String unitType, String name, int health){
        if (unitType == null){
            return null;
        }
        if (unitType.equalsIgnoreCase("INFANTRY UNIT")){
            return new InfantryUnit(name, health);
        }
        else if(unitType.equalsIgnoreCase("RANGED UNIT")){
            return new RangedUnit(name, health);
        }
        else if(unitType.equalsIgnoreCase("CAVALRY UNIT")){
            return new CavalryUnit(name, health);
        }
        else if (unitType.equalsIgnoreCase("COMMANDER UNIT")){
            return new CommanderUnit(name, health);
        }
        return null;
    }

    public ArrayList<Unit> getListOfUnits(String unitType, int n, String name, int health){
        ArrayList<Unit> units = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            units.add(createUnit(unitType, name, health));
        }
        return units;
    }
}
