package com.company;
/*
 * This class is the item class that holds the properties of an item*/
public class Item {
    private final String cost;
    private final String name;
    private final String type;
    public Item (String cost, String name, String type){
        this.cost = cost;
        this.name = name;
        this.type = type;
    }
    public String getCost(){

        return this.cost;
    }
    public String getName(){

        return this.name;
    }
    public String getType(){
        return this.type;
    }

}
