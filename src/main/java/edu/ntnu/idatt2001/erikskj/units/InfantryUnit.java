package edu.ntnu.idatt2001.erikskj.units;

import edu.ntnu.idatt2001.erikskj.enums.Terrain;

public class InfantryUnit extends Unit{

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
    public int getAttackBonus(Terrain terrain) {
        if (terrain == Terrain.FOREST){
            return 4;
        }
        else {
            return 2;
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
            return 3;
        }
        else {
            return 1;
        }
    }

    /**
     * @return probability of units chance to hit a critical hit
     */
    @Override
    public double getCriticalHitProbability() {
        return 0.1;
    }

    /**
     * @return critical hit bonus
     */
    @Override
    public int getCriticalHitBonus() {
        return 4;
    }
}
