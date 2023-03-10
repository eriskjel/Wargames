package edu.ntnu.idatt2001.erikskj.units;

import edu.ntnu.idatt2001.erikskj.enums.Terrain;

import java.util.Objects;
import java.util.Random;

public abstract class Unit {


    protected final String name;
    protected final int totalHealth;
    protected int health;
    protected final int attack;
    protected final int armour;
    protected int numAttacksGiven;
    protected int numAttacksReceived;

    /**
     * Constructor of Unit class.
     * @param name Name of unit
     * @param health Health of unit
     * @param attack Attack stat
     * @param armour Armour stat
     * @throws IllegalArgumentException Throws exception if health, attack or armour stat it lower than zero.
     */
    public Unit(String name, int health, int attack, int armour) throws IllegalArgumentException {
        if (name.isBlank()){
            throw new IllegalArgumentException("Name must not be a blank string.");
        }
        if (health <= 0){
            throw new IllegalArgumentException("Health must be an integer larger than zero.");
        }
        if (attack <= 0){
            throw new IllegalArgumentException("Armour stat must be an integer larger than zero.");
        }
        if (armour <= 0){
            throw new IllegalArgumentException("Armour stat must be an integer larger than zero.");
        }
        this.name = name;
        this.totalHealth = health;
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
    public void attack(Unit opponent, Terrain terrain, boolean criticalHit){
        int criticalHitBonus = 0;
        if (criticalHit){
            criticalHitBonus = getCriticalHitBonus();
        }
        int newHealth = opponent.getHealth() - (this.getAttack() + this.getAttackBonus(terrain) + criticalHitBonus) + (opponent.getArmour() + opponent.getResistBonus(terrain));
        opponent.setHealth(newHealth);
        opponent.incrementAttacksReceived();
        this.incrementAttacksGiven();
    }

    /**
     * Attack methods that simulates an attack from an object onto another object.
     * Updates victims health and increment attacks received and attacks given counter.
     * @param opponent Unit
     */
    public void attack(Unit opponent, Terrain terrain){
        int newHealth = opponent.getHealth() - (this.getAttack() + this.getAttackBonus(terrain)) + (opponent.getArmour() + opponent.getResistBonus(terrain));
        opponent.setHealth(newHealth);
        opponent.incrementAttacksReceived();
        this.incrementAttacksGiven();
    }

    /**
     * generates a boolean based on the probability of the unit to hit a critical hit
     * @return true if attacking unit will attack with critical hit
     */
    public boolean getCriticalHitBoolean(){
        Random random = new Random();
        return random.nextDouble() < getCriticalHitProbability();
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
     *
     * @return initial and total health of unit
     */
    public int getTotalHealth(){
        return totalHealth;
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
     * returns whether unit is alive
     * @return true if unit has more than health points
     */
    public boolean unitIsAlive(){
        return getHealth() > 0;
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
     *  getter for unit info in csv format
     * @return String with unit info in CSV format
     */
    public String toCSVFormat() {
        return this.getClass().getSimpleName() + "," + this.name + "," +this.getHealth() + "\n";
    }

    /**
     * Returns the type of the unit. Also removes the "unit" part of the name. InfantryUnit -> Infantry
     * @return String with type of unit
     */
    public String getUnitType(){
        return this.getClass().getSimpleName().replaceAll("Unit", "");
    }

    /**
     *
     * @return attack bonus of unit
     */
    public abstract int getAttackBonus(Terrain terrain);


    /**
     *
     * @return armour bonus of unit
     * @param terrain battle terrain
     */
    public abstract int getResistBonus(Terrain terrain);

    /**
     *
     * @return probability of units chance to hit a critical hit
     */
    public abstract double getCriticalHitProbability();

    /**
     *
     * @return critical hit bonus
     */
    public abstract int getCriticalHitBonus();


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Unit unit = (Unit) o;
        return health == unit.health && attack == unit.attack && armour == unit.armour && numAttacksGiven == unit.numAttacksGiven && numAttacksReceived == unit.numAttacksReceived && Objects.equals(name, unit.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, health, attack, armour, numAttacksGiven, numAttacksReceived);
    }
}
