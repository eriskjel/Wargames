package edu.ntnu.idatt2001.factory;


import edu.ntnu.idatt2001.units.UnitInterface;

public class FactoryDemo {

    public static void main(String[] args) {
        UnitFactory unitFactory = new UnitFactory();

        UnitInterface unit1 = unitFactory.getUnit("INFANTRY UNIT", "Footman", 100);
        unit1.draw();

        UnitInterface unit2 = unitFactory.getUnit("RANGED UNIT", "Archer", 100);
        unit2.draw();

        UnitInterface unit3 = unitFactory.getUnit("RANGED UNIT", "Archer", 100);
        unit3.draw();

        UnitInterface unit4 = unitFactory.getUnit("RANGED UNIT", "Archer", 100);
        unit4.draw();

        System.out.println(unitFactory.getListOfUnits("INFANTRY UNIT", 5, "TEST", 100));

    }
}
