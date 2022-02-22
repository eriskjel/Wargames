package Army;

import java.util.Random;

public class Battle {

    private final Army armyOne;
    private final Army armyTwo;

    public Battle(Army armyOne, Army armyTwo) {
        this.armyOne = armyOne;
        this.armyTwo = armyTwo;
    }

    public void simulate() {
        while (armyOne.hasUnits() && armyTwo.hasUnits()) {

            Unit attacker1 = armyOne.getRandom();
            Unit attacker2 = armyTwo.getRandom();

            Unit victim1 = armyOne.getRandom();
            Unit victim2 = armyTwo.getRandom();

            int randomIndex = Math.random() <= 0.5 ? 1 : 2;
            if (randomIndex == 1){
                System.out.println(attacker1.getName() + " with " + attacker1.getHealth() + " health has a " + (attacker1.getAttack() + attacker1.getAttackBonus()) + " attack strength, and attacks " + victim2.getName() +  " with " + victim2.getHealth() + " health who has " + (victim2.getResistBonus() + victim2.getArmour()) + " total armour points.");
                attacker1.attack(victim2);
                if (victim2.getHealth() < 1) {
                    System.out.println(victim2.getName() + " has died in combat. \n");
                    armyTwo.remove(victim2);
                } else {
                    System.out.println(victim2.getName() + " now has " + victim2.getHealth() + " health remaining \n");
                }
            }
            else{
                System.out.println(attacker2.getName() + " with " + attacker2.getHealth() + " health has a " + (attacker2.getAttack() + attacker2.getAttackBonus()) + " attack strength, and attacks " + victim1.getName() +  " with " + victim1.getHealth() + " health who has a " + (victim1.getResistBonus() + victim1.getArmour()) + " total armour points.");
                attacker2.attack(victim1);
                if (victim1.getHealth() < 1) {
                    System.out.println(victim1.getName() + " has died in combat.");
                    armyOne.remove(victim1);
                } else {
                    System.out.println(victim1.getName() + " now has " + victim1.getHealth() + " health remaining");
                }
            }
            checkWin(armyOne, armyTwo);
        }
    }

    @Override
    public String toString() {
        return "Battle{" +
                "armyOne=" + armyOne +
                ", armyTwo=" + armyTwo +
                '}';
    }

    public static void checkWin(Army armyOne, Army armyTwo){
        String result = "";
        if (!armyOne.hasUnits()) {
            result += "Army two has won! Remaining units: " + armyTwo.toString();
        } else if (!armyTwo.hasUnits()) {
            result += "Army one has won! Remaining units: " + armyOne.toString();
        }
        System.out.println(result);
    }
}