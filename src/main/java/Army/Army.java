package Army;

import java.util.*;

public class Army {

    private final String name;
    private List<Unit> units;

    public Army(String name){
        this.name = name;
    }

    public Army(String name, List<Unit> units){
        this.name = name;
        this.units = units;
    }

    public String getName(){
        return this.name;
    }

    public void add (Unit unit){
        units.add(unit);
    }

    public void addAll (List<Unit> list){
        units.addAll(list);
    }

    public void remove(Unit unit){
        units.remove(unit);
    }

    public boolean hasUnits(){
        return units.isEmpty();
    }

    public Unit getRandom(){
        Random random = new Random();
        return units.get(random.nextInt((units.size())));
    }

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

    @Override
    public String toString() {
        return "Army{" +
                "name='" + name + '\'' +
                ", units=" + units +
                '}';
    }
}
