package edu.ntnu.idatt2001.erikskj.war;

import edu.ntnu.idatt2001.erikskj.units.*;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Random;
import java.util.stream.Collectors;

public class Army {

    private int armyID;
    private final String name;
    private final ArrayList<Unit> units;
    private File armyFile;
    private boolean isUploaded;

    /**
     * Constructor for army
     * @param name name of army
     */
    public Army(String name){
        this.name = name;
        units = new ArrayList<>();
    }

    /**
     * Constructor for army
     * @param name name of army
     */
    public Army(String name, boolean isUploaded){
        this.name = name;
        units = new ArrayList<>();
        this.isUploaded = isUploaded;
    }

    /**
     * Constructor for army
     * @param name name of army
     * @param units list with units to fill army
     */
    public Army(String name, ArrayList<Unit> units){
        this.name = name;
        this.units = units;
    }

    /**
     * Constructor for army
     * @param name name of army
     * @param units list with units to fill army
     */
    public Army(String name, ArrayList<Unit> units, boolean isUploaded){
        this.name = name;
        this.units = units;
        this.isUploaded = isUploaded;
    }

    /**
     * getter for armyid
     * @return army id
     */
    public int getArmyID() {
        return armyID;
    }

    /**
     * setter for army id
     * @param armyID armyid
     */
    public void setArmyID(int armyID) {
        this.armyID = armyID;
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

    /**
     * method that gets all the different types of units in an army
     * @return arraylist of string with all the different units types in an army
     */
    public ArrayList<String> getArrayWithUnitNames(){
        return this.getUnits().stream().map(Unit::getUnitType).distinct().collect(Collectors.toCollection(ArrayList::new));
    }

    /**
     * returns the number of each unit
     * @param type type of unit
     * @return int with number of unique units
     */
    public int getNumUnitsByType(String type){
        return (int) this.getUnits().stream().map(Unit::getUnitType).filter(unit -> unit.equals(type)).count();
    }

    /**
     * gets the sum of the combined health points in an army
     * @return int with sum of health points in army
     */
    public int getSumHealth(){
        int sum = 0;
        for (int i = 0; i < this.getUnits().size(); i++) {
            sum += this.getUnits().get(i).getHealth();
        }
        return sum;
    }

    /**
     * gets path of file where army will be saved in project
     * the filepath consists of the predetermined path plus the army name plus the army id
     * @return string with filepath
     */
    public String getFilePathAndName(){
        return "src/main/resources/armyRegister/" + this.getName() + "-" + this.getArmyID() + ".csv";
    }

    public File getArmyFile(){
        return this.armyFile;
    }

    public void setArmyFile(File file){
        this.armyFile = file;
    }

    public String generateFileName(){
        return this.getName() + "-" + this.armyID;
    }

    public boolean isUploaded(){
        return this.isUploaded;
    }

    public void setIsUploaded(boolean isUploaded){
        this.isUploaded = isUploaded;
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


    /**
     * @return string with all units in current army
     */
    public String toStringTest() {
        return "Army{" +
                "armyID=" + armyID +
                ", name='" + name + '\'' +
                ", units=" + units +
                ", armyFile=" + armyFile +
                ", isUploaded=" + isUploaded +
                '}';
    }
}
