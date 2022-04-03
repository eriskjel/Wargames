package edu.ntnu.idatt2001;

import edu.ntnu.idatt2001.units.*;

import java.io.FileWriter;
import java.io.IOException;
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

    public ArrayList<Unit> getUnits(){
        return this.units;
    }

    public ArrayList<Unit> getInfantryUnits(){
        ArrayList<Unit> infantryUnits = new ArrayList<>();
        units.stream().filter(item -> item instanceof InfantryUnit).forEach(infantryUnits::add);
        return infantryUnits;
    }

    public ArrayList<Unit> getRangedUnits(){
        ArrayList<Unit> rangedUnits = new ArrayList<>();
        units.stream().filter(item -> item instanceof RangedUnit).forEach(rangedUnits::add);
        return rangedUnits;
    }

    public ArrayList<Unit> getCavalryUnits(){
        ArrayList<Unit> cavalryUnits = new ArrayList<>();
        units.stream().filter(item -> item instanceof CavalryUnit).forEach(cavalryUnits::add);
        return cavalryUnits;
    }

    public ArrayList<Unit> getCommanderUnits(){
        ArrayList<Unit> commanderUnits = new ArrayList<>();
        units.stream().filter(item -> item instanceof CommanderUnit).forEach(commanderUnits::add);
        return commanderUnits;
    }

    public void writeToFile(ArrayList<Unit> units) {
        try (FileWriter fileWriter = new FileWriter("C:\\Users\\eriks\\OneDrive - NTNU\\NTNU\\2. Semester\\IDATT2001\\Wargames\\csv\\test.csv")) {
            fileWriter.write(this.name + "\n");
            for (Unit unit : units) {
                fileWriter.write(unit.toCSVFormat());
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

    }




    /**
     * equals method
     * @param o unit
     * @return true if equal
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
        String results = "\n";
        for (Unit unit: units) {
            results += unit.toString() + "\n";
        }
        return results;
    }
}
