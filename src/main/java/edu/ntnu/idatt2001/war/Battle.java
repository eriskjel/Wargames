package edu.ntnu.idatt2001.war;


import edu.ntnu.idatt2001.enums.Terrain;
import edu.ntnu.idatt2001.units.Unit;

public class Battle {

    private final Army armyOne;
    private final Army armyTwo;
    private static Terrain terrain = null;

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
        while (armyOne.hasUnits() && armyTwo.hasUnits()) {

            Unit attacker1 = armyOne.getRandom();
            Unit attacker2 = armyTwo.getRandom();

            Unit victim1 = armyOne.getRandom();
            Unit victim2 = armyTwo.getRandom();

            int randomIndex = Math.random() <= 0.5 ? 1 : 2;

            if (randomIndex == 1){
                battleInfo(attacker1,victim2);
                attacker1.attack(victim2, terrain);
                if (isDead(victim2)){
                    armyTwo.remove(victim2);
                }
            }
            else{
                battleInfo(attacker2,victim1);
                attacker2.attack(victim1, terrain);
                if (isDead(victim1)){
                    armyOne.remove(victim1);
                }
            }
            System.out.println(checkWin(armyOne, armyTwo));
        }
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

    /**
     * Method that checks if one army has won, by checking if the other army has any units left.
     * @param armyOne army 1
     * @param armyTwo army 2
     */
    public static String checkWin(Army armyOne, Army armyTwo){
        String result = "";
        if (!armyOne.hasUnits()) {
            result += armyTwo.getName() + " has won! Remaining units: " + armyTwo;
        } else if (!armyTwo.hasUnits()) {
            result += armyOne.getName() + " has won! Remaining units: " + armyOne;
        }
        return result;
    }

    /**
     * method that outputs battle info. It will display who attacks who, and the stats of the parts involved
     * @param attacker attacker unit
     * @param victim victim unit
     */
    public static void battleInfo(Unit attacker, Unit victim){
        System.out.println(attacker.getName() + " with " + attacker.getHealth() + " health has a " + (attacker.getAttack() + attacker.getAttackBonus(terrain)) + " total attack points, and attacks " + victim.getName() +  " with " + victim.getHealth() + " health who has " + (victim.getResistBonus(terrain) + victim.getArmour()) + " total armour points.");
    }

    /**
     * checks if a unit is dead
     * @param victim unit
     * @return true if unit is dead, false if alive
     */
    public static boolean isDead(Unit victim){
        if (victim.getHealth() < 1) {
            System.out.println(victim.getName() + " has died in combat.");
            return true;
        } else {
            System.out.println(victim.getName() + " now has " + victim.getHealth() + " health remaining");
            return false;
        }
    }
}