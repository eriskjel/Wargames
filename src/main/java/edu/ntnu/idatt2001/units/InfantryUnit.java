package edu.ntnu.idatt2001.units;

import java.util.ArrayList;

public class InfantryUnit extends Unit implements UnitInterface{

    /**
     * constructor
     * @param name name of this unit
     * @param health health of this unit
     * @param attack attack of this unit
     * @param armour armour of this unit
     */
    public InfantryUnit(String name, int health, int attack, int armour){
        super(name, health, attack, armour);
    }

    /**
     * simplified constructor
     * @param name name of this unit
     * @param health name of this unit
     */
    public InfantryUnit(String name, int health){
        super(name, health, 15, 10);
    }

    /**
     *
     * @return attack bonus of this unit
     */
    @Override
    public int getAttackBonus() {
        return 2;
    }

    @Override
    public int incrementAttackBonus(int increment) {
        return increment;
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
    public int incrementResistBonus(int increment) {
        return increment;
    }

    @Override
    public void draw() {
        System.out.println("Inside InfantryUnit::draw method.");
    }
}
