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
        if (name.equals("")){
            throw new IllegalArgumentException("Army name is not defined");
        }
        this.name = name;
        units = new ArrayList<>();
    }

    /**
     * Constructor for army
     * @param name name of army
     */
    public Army(String name, boolean isUploaded){
        if (name.equals("") || name == null){
            throw new IllegalArgumentException("Army name is not defined");
        }
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
        if (name.equals("")){
            throw new IllegalArgumentException("Army name is not defined");
        }
        this.name = name;
        this.units = units;
    }

    /**
     * Constructor for army
     * @param name name of army
     * @param units list with units to fill army
     */
    public Army(String name, ArrayList<Unit> units, boolean isUploaded){
        if (name.equals("")){
            throw new IllegalArgumentException("Army name is not defined");
        }
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
     * method that check is army unit list is empty
     * @return true if list has units, false if empty
     */
    public boolean hasUnits(){
        return !units.isEmpty();
    }

    public boolean hasUnitsAlive(){
        for (int i = 0; i < units.size(); i++) {
            if (units.get(i).getHealth() > 0){
                return true;
            }
        }
        return false;
    }

    /**
     * method that returns random unit from army list of units
     * @return unit
     */
    public Unit getRandom(){
        Random random = new Random();
        return units.get(random.nextInt((units.size())));
    }

    /**
     * gets random alive unit from army
     * @return random unit that is alive
     */
    public Unit getRandomAliveUnit(){
        Unit unit = this.getRandom();
        while(!unit.unitIsAlive()){
            if (unit.unitIsAlive()){
                return unit;
            }
            else{
                unit = this.getRandom();
            }
        }
        return unit;
    }

    /**
     * returns units in army
     * @return arraylist with all units
     */
    public ArrayList<Unit> getUnits(){
        return this.units;
    }

    /**
     * list with infantry units
     * @return arraylist with all infantry units
     */
    public ArrayList<Unit> getInfantryUnits(){
        return units.stream().filter(unit -> unit instanceof InfantryUnit).collect(Collectors.toCollection(ArrayList::new));
    }

    /**
     * list with RangedUnit
     * @return arraylist with all RangedUnit
     */
    public ArrayList<Unit> getRangedUnits(){
        return units.stream().filter(unit -> unit instanceof RangedUnit).collect(Collectors.toCollection(ArrayList::new));
    }

    /**
     * list with CavalryUnit
     * @return arraylist with all CavalryUnit
     */
    public ArrayList<Unit> getCavalryUnits(){
        return units.stream().filter(unit -> unit instanceof CavalryUnit).collect(Collectors.toCollection(ArrayList::new));
    }

    /**
     * list with CommanderUnit
     * @return arraylist with all CommanderUnit
     */
    public ArrayList<Unit> getCommanderUnits(){
        return units.stream().filter(unit -> unit instanceof CommanderUnit).collect(Collectors.toCollection(ArrayList::new));
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
     *
     * @param type type of unit
     * @return int with number of alive units by unit type
     */
    public int getNumAliveUnitsByType(String type){
        return (int) units.stream().filter(Unit::unitIsAlive).map(Unit::getUnitType).filter(unit -> unit.equals(type)).count();
    }

    /**
     * gets list of all units alive
     * @return arraylist with all units alive
     */
    public ArrayList<Unit> getListOfUnitsAlive(){
        return this.getUnits().stream().filter(Unit::unitIsAlive).collect(Collectors.toCollection(ArrayList::new));
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
     * getter for army file
     * @return file of army
     */
    public File getArmyFile(){
        return this.armyFile;
    }

    /**
     * sets army file
     * @param file file
     */
    public void setArmyFile(File file){
        this.armyFile = file;
    }

    /**
     * returns whether army has a file
     * @return true if army has a related file
     */
    public boolean armyHasFile(){
        return this.getArmyFile() != null;
    }

    /**
     * generates file name for army. consisting of army name and army id
     * @return
     */
    public String generateFileName(){
        return this.getName() + "-" + this.armyID;
    }

    /**
     * getter for whether army is uploaded or created
     * @return true if army is uploaded
     */
    public boolean isUploaded(){
        return !this.isUploaded;
    }

    /**
     * sets whether army is uploaded
     * @param isUploaded boolean
     */
    public void setIsUploaded(boolean isUploaded){
        this.isUploaded = isUploaded;
    }

    /**
     * resets army by setting each unit's health to their original health
     */
    public void resetArmy(){
        for (int i = 0; i < this.getUnits().size(); i++) {
            this.getUnits().get(i).setHealth(getUnits().get(i).getTotalHealth());
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

    /**
     *
     * @return string with all alive units in army
     */
    public String unitsAliveToString(){
        String results = "\n";
        for (Unit unit: units){
            if (unit.unitIsAlive()){
                results += unit + "\n";
            }
        }
        return results;
    }
}
