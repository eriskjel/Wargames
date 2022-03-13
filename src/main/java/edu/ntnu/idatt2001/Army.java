package edu.ntnu.idatt2001;

import java.util.*;

public class Army {

    private final String name;
    private final ArrayList<Unit> units;

    /**
     * Constructor
     * @param name name of army
     */
    public Army(String name){
        this.name = name;
        units = new ArrayList<>();
    }

    /**
     * Constuctor
     * @param name name of army
     * @param units list with units to fill army
     */
    public Army(String name, ArrayList<Unit> units){
        this.name = name;
        this.units = units;
    }

    /**
     *
     * @return name of army
     */
    public String getName(){
        return this.name;
    }

    /**
     * method that adds given unit to list of units
     * @param unit list with units
     */
    public void add(Unit unit){
        this.units.add(unit);
    }

    /**
     * method that adds all units from given list to army list of units
     * @param list list of units
     */
    public void addAll (List<Unit> list){
        units.addAll(list);
    }

    /**
     * removes unit from list of units
     * @param unit unit
     */
    public void remove(Unit unit){
        this.units.remove(unit);
    }

    /**
     * method that check is army unit list is emptpy
     * @return true if list has units, false if empty
     */
    public boolean hasUnits(){
        return !units.isEmpty();
    }

    /**
     * method that returns random unit from army list of units
     * @return unit
     */
    public Unit getRandom(){
        Random random = new Random();
        return units.get(random.nextInt((units.size())));
    }

    public List<Unit> getInfantryUnits(){
        return units.stream().filter(item -> item instanceof InfantryUnit).toList();
    }

    /**
     * equals method
     * @param o unit
     * @return true if eqal
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Army army = (Army) o;
        return name.equals(army.name) && units.equals(army.units);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, units);
    }

    /**
     *
     * @return string with all units in current army
     */
    @Override
    public String toString() {
        String results = new String("\n");
        for (Unit unit: units) {
            results += unit.toString() + "\n";
        }
        return results;
    }
}