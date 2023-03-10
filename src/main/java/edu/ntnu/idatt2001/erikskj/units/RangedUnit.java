package edu.ntnu.idatt2001.erikskj.units;

import edu.ntnu.idatt2001.erikskj.enums.Terrain;

public class RangedUnit extends Unit{

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

    /**
     * @return probability of units chance to hit a critical hit
     */
    @Override
    public double getCriticalHitProbability() {
        return 0.15;
    }

    /**
     * @return critical hit bonus
     */
    @Override
    public int getCriticalHitBonus() {
        return 6;
    }
}
