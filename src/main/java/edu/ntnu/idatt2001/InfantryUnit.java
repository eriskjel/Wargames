package edu.ntnu.idatt2001;

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
    public int getAttackBonus() {
        return 2;
    }

    /**
     *
     * @return armour bonus of this unit
     */
    @Override
    public int getResistBonus() {
        return 1;
    }

}
