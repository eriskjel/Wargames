package edu.ntnu.idatt2001;

public abstract class Unit {


    protected final String name;
    protected int health;
    protected final int attack;
    protected final int armour;
    protected int numAttacksGiven;
    protected  int numAttacksReceived;

    /**
     * Constructor of Unit class.
     * @param name Name of unit
     * @param health Health of unit
     * @param attack Attack stat
     * @param armour Armour stat
     * @throws IllegalArgumentException Throws exception if health, attack or armour stat it lower than zero.
     */
    public Unit(String name, int health, int attack, int armour) throws IllegalArgumentException {
        if (health < 0){
            throw new IllegalArgumentException("Health must be larger than zero.");
        }
        if (attack < 0){
            throw new IllegalArgumentException("Armour stat must be larger than zero.");
        }
        if (armour < 0){
            throw new IllegalArgumentException("Armour stat must be larger than zero.");
        }
        this.name = name;
        this.health = health;
        this.attack = attack;
        this.armour = armour;
        this.numAttacksGiven = 0;
        this.numAttacksReceived = 0;
    }

    /**
     * Attack methods that simulates an attack from an object onto another object.
     * Updates victims health and increment attacks received and attacks given counter.
     * @param opponent Unit
     */
    public void attack(Unit opponent){
        int newHealth = opponent.getHealth() - (this.getAttack() + this.getAttackBonus()) + (opponent.getArmour() + opponent.getResistBonus());
        opponent.setHealth(newHealth);
        opponent.incrementAttacksReceived();
        this.incrementAttacksGiven();
    }

    /**
     *
     * @return name of unit
     */
    public String getName() {
        return name;
    }

    /**
     *
     * @return health of unit
     */
    public int getHealth() {
        return health;
    }

    /**
     * Setter for health
     * @param health desired new health
     */
    public void setHealth(int health) {
        this.health = health;
    }

    /**
     *
     * @return Attack stat
     */
    public int getAttack() {
        return attack;
    }

    /**
     *
     * @return Armour stat
     */
    public int getArmour() {
        return armour;
    }

    /**
     * Method that increments number of attacks given
     */
    public void incrementAttacksGiven(){
        this.numAttacksGiven++;
    }

    /**
     * Method that increments number of attacks received
     */
    public void incrementAttacksReceived(){
        this.numAttacksReceived++;
    }

    /**
     *
     * @return String with information of unit
     */
    @Override
    public String toString() {
        return "Name: " + this.getName() + ", health: " + this.getHealth() + ", attack: " + this.getAttack() + ", armour: " + this.getArmour();
    }

    /**
     *
     * @return attack bonus of unit
     */
    public abstract int getAttackBonus();

    /**
     *
     * @return armour bonus of unit
     */
    public abstract int getResistBonus();
}