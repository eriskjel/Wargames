package edu.ntnu.idatt2001;

public class CommanderUnit extends Unit{


    public CommanderUnit(String name, int health, int attack, int armour){
        super(name, health, attack, armour);
        numAttacksGiven = 0;
    }
    public CommanderUnit(String name, int health){
        super(name, health, 25, 15);
        numAttacksGiven = 0;
    }

    @Override
    public int getAttackBonus() {
        if (this.numAttacksGiven == 0){
            return 6;
        }
        else{
            return 2;
        }
    }

    @Override
    public int getResistBonus() {
        return 1;
    }
}
