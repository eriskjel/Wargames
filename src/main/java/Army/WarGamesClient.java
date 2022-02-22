package Army;

public class WarGamesClient {


    public static void main(String[] args) {

        Army armyOne = new Army("Human Army");
        Army armyTwo = new Army("Orcish Horde");


        /*
        for (int i = 0; i < 500; i++) {
            armyOne.add(new InfantryUnit("Footman", 100));
            armyTwo.add(new InfantryUnit("Grunt", 100));
        }
        for (int i = 0; i < 500; i++) {
            armyOne.add(new RangedUnit("Archer", 100));
            armyTwo.add(new RangedUnit("Ranged",100));
        }

        for (int i = 0; i < 100; i++) {
            armyOne.add(new CavalryUnit("Knight", 100));
            armyTwo.add(new CavalryUnit("Raider", 100));
        }
        for (int i = 0; i < 200; i++) {
            armyOne.add(new RangedUnit("Archer", 100));
            armyTwo.add(new RangedUnit("Spearman", 100));
        }
        for (int i = 0; i < 1; i++) {
            armyOne.add(new CommanderUnit("Mountain King", 180));
            armyTwo.add(new CommanderUnit("Gul´dan", 180));
        }
         */

        for (int i = 0; i < 1; i++) {
            armyOne.add(new RangedUnit("Archer", 100));
            armyTwo.add(new RangedUnit("Spearman", 50));
        }
        /*
        for (int i = 0; i < 5; i++) {
            armyOne.add(new RangedUnit("Archer", 100));
            armyTwo.add(new RangedUnit("Ranged",100));
        }

        for (int i = 0; i < 3; i++) {
            armyOne.add(new CavalryUnit("Knight", 100));
            armyTwo.add(new CavalryUnit("Raider", 100));
        }
        for (int i = 0; i < 2; i++) {
            armyOne.add(new RangedUnit("Archer", 100));
            armyTwo.add(new RangedUnit("Spearman", 100));
        }
        for (int i = 0; i < 1; i++) {
            armyOne.add(new CommanderUnit("Mountain King", 180));
            armyTwo.add(new CommanderUnit("Gul´dan", 180));
        }
        */
        Battle battle = new Battle(armyOne, armyTwo);
        battle.simulate();
    }
}
