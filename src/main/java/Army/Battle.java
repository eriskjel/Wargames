package Army;


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
                battleInfo(attacker1,victim2);
                attacker1.attack(victim2);
                if (healthStatus(victim2)){
                    armyTwo.remove(victim2);
                }
            }
            else{
                battleInfo(attacker2,victim1);
                attacker2.attack(victim1);
                if (healthStatus(victim1)){
                    armyOne.remove(victim1);
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

    public static void battleInfo(Unit attacker, Unit victim){
        System.out.println(attacker.getName() + " with " + attacker.getHealth() + " health has a " + (attacker.getAttack() + attacker.getAttackBonus()) + " total attack points, and attacks " + victim.getName() +  " with " + victim.getHealth() + " health who has " + (victim.getResistBonus() + victim.getArmour()) + " total armour points.");
    }

    public static boolean healthStatus(Unit victim){
        if (victim.getHealth() < 1) {
            System.out.println(victim.getName() + " has died in combat.");
            return true;
            //armyOne.remove(victim);
        } else {
            System.out.println(victim.getName() + " now has " + victim.getHealth() + " health remaining");
            return false;
        }
    }
}