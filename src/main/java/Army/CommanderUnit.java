package Army;

public class CommanderUnit extends Unit{

    private int numAttacksReceived;

    public CommanderUnit(String name, int health, int attack, int armour){
        super(name, health, attack, armour);
        numAttacksReceived = 0;
    }
    public CommanderUnit(String name, int health){
        super(name, health, 25, 15);
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
