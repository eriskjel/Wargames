package edu.ntnu.idatt2001.erikskj.war;


import edu.ntnu.idatt2001.erikskj.enums.Terrain;
import edu.ntnu.idatt2001.erikskj.gui.controllers.SimulationController;
import edu.ntnu.idatt2001.erikskj.units.Unit;

public class Battle {

    private final Army armyOne;
    private final Army armyTwo;
    private static Terrain terrain = null;
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
                updateBattleInfoIfDead(victim2);
            }
            else{
                updateBattleInfoWithAttack(attacker2,victim1);
                attacker2.attack(victim1, terrain);
                updateBattleInfoIfDead(victim1);
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

    public void updateBattleInfoIfDead(Unit victim){
        if (!victim.unitIsAlive()){
            //armyTwo.remove(victim2);
            System.out.println(victim.getName() + " has died in combat.\n");
            battleInfo += victim.getName() + " has died in combat.\n";
            victim.setHealth(0);
        }
        else{
            System.out.println(victim.getName() + " now has " + victim.getHealth() + " HP remaining.\n");
            battleInfo += victim.getName() + " now has " + victim.getHealth() + " HP remaining.\n";
        }
    }

    public String getBattleInfo(){
        return battleInfo;
    }



    /**
     * Method that checks if one army has won, by checking if the other army has any units left.
     * @param armyWinner army that won battle
     */
    public static String printWinner(Army armyWinner){
        String result = armyWinner.getName() + " has won! Remaining units: " + armyWinner.unitsAlive();
        battleInfo += result;

        //for testing purposes
        System.out.println(result);
        return result;
    }

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