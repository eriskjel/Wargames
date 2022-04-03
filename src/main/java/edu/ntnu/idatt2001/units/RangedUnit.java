package edu.ntnu.idatt2001.units;

import edu.ntnu.idatt2001.Terrain;

public class RangedUnit extends Unit implements UnitInterface{

    /**
     * Constructor of Unit class.
     * @param name Name of unit
     * @param health Health of unit
     * @param attack Attack stat
     * @param armour Armour stat
     */
    public RangedUnit(String name, int health, int attack, int armour){
        super(name, health, attack, armour);
        numAttacksReceived = 0;
    }

    /**
     * Simplified constructor
     * @param name name of unit
     * @param health health of unit
     */
    public RangedUnit(String name, int health){
        super(name, health, 15, 8);
        numAttacksReceived = 0;
    }

    /**
     *
     * @return attack bonus of this unit
     */
    @Override
    public int getAttackBonus(Terrain terrain) {
        if (terrain == Terrain.HILL){
            return 5;
        }
        else if(terrain == Terrain.FOREST){
            return 1;
        }
        else{
            return 3;
        }
    }

    /**
     *
     * @return armour bonus of this unit
     * @param terrain battle terrain
     */
    @Override
    public int getResistBonus(Terrain terrain) {
        if (numAttacksReceived == 0){
            return 6;
        }
        else if (numAttacksReceived == 1){
            return 4;
        }
        else{
            return 2;
        }
    }

    @Override
    public void draw() {
        System.out.println("Inside RangedUnit::draw method.");
    }
}
