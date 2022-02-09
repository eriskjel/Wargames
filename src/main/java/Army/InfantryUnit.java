package Army;

public class InfantryUnit extends Unit{

    public InfantryUnit(String name, int health, int attack, int armour){
        super(name, health, attack, armour);
    }
    public InfantryUnit(String name, int health){
        //TODO: finish constructor
    }

    @Override
    public int getAttackBonus() {
        return 3;
    }

    @Override
    public int getResistBonus() {
        return 1;
    }
}
