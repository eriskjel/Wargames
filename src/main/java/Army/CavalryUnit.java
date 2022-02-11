package Army;

public class CavalryUnit extends Unit{
    public CavalryUnit(String name, int health, int attack, int armour){
        super(name, health, attack, armour);
    }
    public CavalryUnit(String name, int health){
        super(name, health, 20, 12);
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
