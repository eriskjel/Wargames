package Army;

public class CommanderUnit extends Unit{

    public CommanderUnit(String name, int health, int attack, int armour){
        super(name, health, attack, armour);
    }
    public CommanderUnit(String name, int health){
        super(name, health, 25, 15);
    }

    @Override
    public int getAttackBonus() {
        //TODO: ADD DYNAMIC METHOD
        return 3;
    }

    @Override
    public int getResistBonus() {
        return 1;
    }
}
