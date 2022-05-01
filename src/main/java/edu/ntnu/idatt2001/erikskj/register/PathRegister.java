package edu.ntnu.idatt2001.erikskj.register;

import java.util.ArrayList;

public class PathRegister {

    private ArrayList<String> armyPathRegister = new ArrayList<>();


    public void add(String path){
        armyPathRegister.add(path);
    }

    public ArrayList<String> getRegister(){
        return this.armyPathRegister;
    }

    public int getSize(){
        return armyPathRegister.size();
    }

    public void removeAll(){
        armyPathRegister.clear();
    }

    public void removeArmyPath(String path){
        for (int i = 0; i < armyPathRegister.size(); i++) {
            if (path.equals(armyPathRegister.get(i))){
                armyPathRegister.remove(armyPathRegister.get(i));
            }
        }
    }
}
