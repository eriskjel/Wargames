package Army;

public class RangedUnit extends Unit{

    public RangedUnit(String name, int health, int attack, int armour){
        super(name, health, attack, armour);
    }
    public RangedUnit(String name, int health){
        super(name, health, 15, 8);
    }

    @Override
    public int getAttackBonus() {
        return 4;
    }

    @Override
    public int getResistBonus() {
        //TODO: ADD DYNAMIC METHOD
        return 1;
    }
}
