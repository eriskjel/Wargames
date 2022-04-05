package edu.ntnu.idatt2001.factory;


import edu.ntnu.idatt2001.units.Unit;
import edu.ntnu.idatt2001.units.UnitInterface;

public class FactoryDemo {

    public static void main(String[] args) {
        UnitFactory unitFactory = new UnitFactory();

        Unit unit1 = unitFactory.createUnit("INFANTRY UNIT", "Footman", 100);


        Unit unit2 = unitFactory.createUnit("RANGED UNIT", "Archer", 100);


        Unit unit3 = unitFactory.createUnit("RANGED UNIT", "Archer", 100);


        Unit unit4 = unitFactory.createUnit("RANGED UNIT", "Archer", 100);


        System.out.println(unitFactory.getListOfUnits("INFANTRY UNIT", 5, "TEST", 100));

    }
}
