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
}
