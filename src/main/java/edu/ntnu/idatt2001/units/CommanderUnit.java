package edu.ntnu.idatt2001.units;

import java.util.ArrayList;

public class CommanderUnit extends Unit implements UnitInterface{


    /**
     * constructor
     * @param name name of unit
     * @param health health of unit
     * @param attack attack of unit
     * @param armour armour of unit
     */
    public CommanderUnit(String name, int health, int attack, int armour){
        super(name, health, attack, armour);
        numAttacksGiven = 0;
    }

    /**
     * simplified constructor
     * @param name name of unit
     * @param health health of unit
     */
    public CommanderUnit(String name, int health){
        super(name, health, 25, 15);
        numAttacksGiven = 0;
    }

    /**
     *
     * @return attack stat of this unit
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
     * @return armour of this unit
     */
    @Override
    public int getResistBonus() {
        return 1;
    }

    @Override
    public void draw() {
        System.out.println("Inside CommanderUnit::draw method.");
    }
}
