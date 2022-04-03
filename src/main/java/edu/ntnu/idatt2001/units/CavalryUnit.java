package edu.ntnu.idatt2001.units;

import java.util.ArrayList;

public class CavalryUnit extends Unit implements UnitInterface{

    /**
     * Constructor of unit
     * @param name name of unit
     * @param health health of unit
     * @param attack attack stat
     * @param armour armour stat
     */
    public CavalryUnit(String name, int health, int attack, int armour){
        super(name, health, attack, armour);
    }

    /**
     * simplified constructor
     * @param name name of unit
     * @param health health of unit
     */
    public CavalryUnit(String name, int health){
        super(name, health, 20, 12);
        numAttacksGiven = 0;
    }

    /**
     *
     * @return attack bonus of this unit
     */
    @Override
    public int getAttackBonus() {
        if (this.numAttacksGiven == 0){
            return 6;
        }
        else{
            return 2;
        }
    }

    /**
     *
     * @return armour bonus of this unit
     */
    @Override
    public int getResistBonus() {
        return 1;
    }

    @Override
    public Unit createUnit(String name, int health) {
        return new InfantryUnit(name, health);
    }

    @Override
    public ArrayList<Unit> getListOfUnits(int n, String name, int health) {
        ArrayList<Unit> units = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            units.add(new InfantryUnit(name, health));
        }
        return units;
    }
}
