package edu.ntnu.idatt2001.erikskj.war;


import edu.ntnu.idatt2001.erikskj.enums.Terrain;
import edu.ntnu.idatt2001.erikskj.units.Unit;

/**
 * Class that manages the battle and simulation aspect of the project. Class takes two armies and a terrain, and holds a battleInfo string.
 */
public class Battle {

    private final Army armyOne;
    private final Army armyTwo;
    private static Terrain terrain;
    //Battleinfo String is a complete log containing who attacked who, if someone died and also info about the winning army
    private static String battleInfo = "";

    /**
     * constructor for battle class
     * @param armyOne army one
     * @param armyTwo army two
     * @param terrain battle terrain
     */
    public Battle(Army armyOne, Army armyTwo, Terrain terrain) {
        this.armyOne = armyOne;
        this.armyTwo = armyTwo;
        Battle.terrain = terrain;
    }

    /**
     * getter for battleinfo
     * @return string with all the battle info
     */
    public String getBattleInfo(){
        return battleInfo;
    }



    /**
     * Method that simulates a battle. There are battles until there is only one army remaining.
     * One random attacker is picked from army one, as well a random victim also from army two. Vice Verca for other army.
     * Then it is random who gets to attack the other unit first. After each battle, the health of the victim unit is checked
     * and if dead, the unit is removed from battle. At the end of each while-iteration the program will check if there is a winner
     * if so, when the program stops and outputs the winners, if not then the battle goes on.
     */
    public void simulate() {
        while (armyOne.hasUnitsAlive() && armyTwo.hasUnitsAlive()) {

            Unit attacker1 = armyOne.getRandomAliveUnit();
            Unit attacker2 = armyTwo.getRandomAliveUnit();

            Unit victim1 = armyOne.getRandomAliveUnit();
            Unit victim2 = armyTwo.getRandomAliveUnit();

            int randomIndex = Math.random() <= 0.5 ? 1 : 2;

            if (randomIndex == 1){
                updateBattleInfoWithAttack(attacker1,victim2);
                attacker1.attack(victim2, terrain);
                updateBattleInfoWithHealthStatus(victim2);
            }
            else{
                updateBattleInfoWithAttack(attacker2,victim1);
                attacker2.attack(victim1, terrain);
                updateBattleInfoWithHealthStatus(victim1);
            }
            checkWinner(armyOne, armyTwo);
        }
    }

    /**
     * method that outputs battle info. It will display who attacks who, and the stats of the parts involved
     * @param attacker attacker unit
     * @param victim victim unit
     */
    public void updateBattleInfoWithAttack(Unit attacker, Unit victim){
        System.out.println(attacker.getName() + " with " + attacker.getHealth() + " HP, attacks " + victim.getName() +  " with " + victim.getHealth() + " HP.\n");
        battleInfo += attacker.getName() + " with " + attacker.getHealth() + "HP, attacks " + victim.getName() +  " with " + victim.getHealth() + " HP.\n";
    }

    /**
     * checks if victim of attack has died from the attack. If so then that is logged, and also the unit's health is set to zero.
     * @param victim victim of attack
     */
    public void updateBattleInfoWithHealthStatus(Unit victim){
        if (!victim.unitIsAlive()){
            System.out.println(victim.getName() + " has died in combat.\n");
            battleInfo += victim.getName() + " has died in combat.\n";
            victim.setHealth(0);
        }
        else{
            System.out.println(victim.getName() + " now has " + victim.getHealth() + " HP remaining.\n");
            battleInfo += victim.getName() + " now has " + victim.getHealth() + " HP remaining.\n";
        }
    }

    /**
     * checks if there is a winner amongst the two armies battling, and returns the winning army if there is one
     * @param armyOne army1
     * @param armyTwo army2
     * @return winning army or null
     */
    public static Army checkWinner(Army armyOne, Army armyTwo){
        if (!armyOne.hasUnitsAlive()) {
            printWinner(armyTwo);
            return armyTwo;
        } else if (!armyTwo.hasUnitsAlive()) {
            printWinner(armyOne);
            return armyOne;
        }
        return null;
    }

    /**
     * method that prints how has one, and appends this to the battleinfo string
     * @param armyWinner winning army
     */
    public static void printWinner(Army armyWinner){
        String result = armyWinner.getName() + " has won! Remaining units: " + armyWinner.unitsAlive();
        battleInfo += result;

        //for testing purposes
        System.out.println(result);
    }

    /**
     * method that outputs battle info. It will display who attacks who, and the stats of the parts involved
     * @param attacker attacker unit
     * @param victim victim unit
     */
    public void appendBattleInfoWithFullStats(Unit attacker, Unit victim){
        System.out.println(attacker.getName() + " with " + attacker.getHealth() + " health has a " + (attacker.getAttack() + attacker.getAttackBonus(terrain)) + " total attack points, and attacks " + victim.getName() +  " with " + victim.getHealth() + " health who has " + (victim.getResistBonus(terrain) + victim.getArmour()) + " total armour points.");
        battleInfo += attacker.getName() + " with " + attacker.getHealth() + " health has a " + (attacker.getAttack() + attacker.getAttackBonus(terrain)) + " total attack points, and attacks " + victim.getName() +  " with " + victim.getHealth() + " health who has " + (victim.getResistBonus(terrain) + victim.getArmour()) + " total armour points. \n";
    }

    /**
     * tostring
     * @return string with army names
     */
    @Override
    public String toString() {
        return "Battle{" +
                "armyOne=" + armyOne +
                ", armyTwo=" + armyTwo +
                '}';
    }
}