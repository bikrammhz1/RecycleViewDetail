package com.example.rbmhz.latestrecycleview.mData;

import java.util.ArrayList;

/**
 * @author Bikram Maharjan
 * @version 1.1
 * @Date 4/10/2017.
 */

public class CRUD {
    ArrayList<Spacecraft> spacecrafts;
    public  CRUD(ArrayList<Spacecraft>spacecrafts){
        this.spacecrafts = spacecrafts;
    }

    //Add
    public boolean addNew(Spacecraft spacecraft){
        try {
            spacecrafts.add(spacecraft);
            return true;
        }catch (Exception e){
            e.printStackTrace();
        }
        return false;
    }
    //Retrive
    public ArrayList<Spacecraft> getSpacecrafts(){
        return spacecrafts;
    }
    //updating
    public boolean update(int position,Spacecraft newSpacecraft){
        try {
            spacecrafts.remove(position);
            spacecrafts.add(position,newSpacecraft);
            return true;
        }catch (Exception e){
            e.printStackTrace();
        }
        return false;
    }

    public boolean delete(int pos){

        try {
            spacecrafts.remove(pos);
            return true;
        }catch (Exception e){
            e.printStackTrace();
        }
        return false;
    }
}
