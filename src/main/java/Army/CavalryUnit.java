package Army;

public class CavalryUnit extends Unit{
    private int numAttacksReceived;
    public CavalryUnit(String name, int health, int attack, int armour){
        super(name, health, attack, armour);
        numAttacksReceived = 0;
    }
    public CavalryUnit(String name, int health){
        super(name, health, 20, 12);
        numAttacksReceived = 0;
    }

    @Override
    public int getAttackBonus() {
        if (numAttacksReceived == 0){
            numAttacksReceived++;
            return 6;
        }
        else{
            numAttacksReceived++;
            return 2;
        }
    }

    @Override
    public int getResistBonus() {
        return 1;
    }
}
