package edu.ntnu.idatt2001.units;

import edu.ntnu.idatt2001.Terrain;

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
     * @param terrain battle terain
     */
    @Override
    public int getAttackBonus(Terrain terrain) {
        if (terrain == Terrain.PLAINS){
            if (this.numAttacksGiven == 0){
                return 8;
            }
            else{
                return 4;
            }
        }
        else{
            if (this.numAttacksGiven == 0){
                return 6;
            }
            else{
                return 2;
            }
        }
    }

    /**
     *
     * @return armour bonus of this unit
     * @param terrain battle terrain
     */
    @Override
    public int getResistBonus(Terrain terrain) {
        if (terrain == Terrain.FOREST){
            return 0;
        }
        else{
            return 1;
        }
    }


    @Override
    public void draw() {
        System.out.println("Inside CavalryUnit::draw method.");
    }
}
