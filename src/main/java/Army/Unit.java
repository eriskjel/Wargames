package Army;

public abstract class Unit {


    protected final String name;
    protected int health;
    protected final int attack;
    protected final int armour;

    public Unit(String name, int health, int attack, int armour) {
        this.name = name;
        this.health = health;
        this.attack = attack;
        this.armour = armour;
    }

    public void attack(Unit opponent){
        int newHealth = opponent.getHealth() - (this.getAttack() + this.getAttackBonus() + (opponent.getArmour() + opponent.getResistBonus()));
        opponent.setHealth(newHealth);
    }

    public String getName() {
        return name;
    }

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public int getAttack() {
        return attack;
    }

    public int getArmour() {
        return armour;
    }

    @Override
    public String toString() {
        return "Name: " + this.getName() + ", health: " + this.getHealth() + ", attack: " + this.getAttack() + ", armour: " + this.getArmour();
    }

    public abstract int getAttackBonus();

    public abstract int getResistBonus();
}
