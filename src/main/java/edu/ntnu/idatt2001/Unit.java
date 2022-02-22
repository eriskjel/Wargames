package edu.ntnu.idatt2001;

public abstract class Unit {


    protected final String name;
    protected int health;
    protected final int attack;
    protected final int armour;
    protected int numAttacksGiven;
    protected  int numAttacksReceived;

    public Unit(String name, int health, int attack, int armour) throws IllegalArgumentException {
        if (health < 0){
            throw new IllegalArgumentException("Health must be larger than zero.");
        }
        if (attack < 0){
            throw new IllegalArgumentException("Armour stat must be larger than zero.");
        }
        this.name = name;
        this.health = health;
        this.attack = attack;
        this.armour = armour;
        this.numAttacksGiven = 0;
        this.numAttacksReceived = 0;
    }

    public void attack(Unit opponent){
        int newHealth = opponent.getHealth() - (this.getAttack() + this.getAttackBonus()) + (opponent.getArmour() + opponent.getResistBonus());
        opponent.setHealth(newHealth);
        this.incrementAttacksGiven();
        opponent.incrementAttacksReceived();
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

    public void incrementAttacksGiven(){
        this.numAttacksGiven++;
    }

    public void incrementAttacksReceived(){
        this.numAttacksReceived++;
    }

    @Override
    public String toString() {
        return "Name: " + this.getName() + ", health: " + this.getHealth() + ", attack: " + this.getAttack() + ", armour: " + this.getArmour();
    }

    public abstract int getAttackBonus();

    public abstract int getResistBonus();
}
