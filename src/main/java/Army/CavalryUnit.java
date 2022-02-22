package Army;

public class CavalryUnit extends Unit{
    private int numAttacksGiven;
    public CavalryUnit(String name, int health, int attack, int armour){
        super(name, health, attack, armour);
    }
    public CavalryUnit(String name, int health){
        super(name, health, 20, 12);
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
