package edu.ntnu.idatt2001.units;

import java.util.ArrayList;

public interface UnitInterface {
    Unit createUnit(String name, int health);
    ArrayList<Unit> getListOfUnits(int n, String name, int health);
}
