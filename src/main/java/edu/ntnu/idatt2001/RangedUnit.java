package edu.ntnu.idatt2001;

public class RangedUnit extends Unit{

    public RangedUnit(String name, int health, int attack, int armour){
        super(name, health, attack, armour);
        numAttacksReceived = 0;
    }
    public RangedUnit(String name, int health){
        super(name, health, 15, 8);
        numAttacksReceived = 0;
    }

    @Override
    public int getAttackBonus() {
        return 3;
    }

    @Override
    public int getResistBonus() {
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
}
