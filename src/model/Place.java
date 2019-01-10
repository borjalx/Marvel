/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

/**
 *
 * @author Borja S
 */
public class Place {
    
    private String name;
    private String description;
    private String north;
    private String south;
    private String east;
    private String west;

    //Constructors
    public Place(String name, String description, String north, String south, String east, String west) {
        this.name = name;
        this.description = description;
        this.north = north;
        this.south = south;
        this.east = east;
        this.west = west;
    }

    public Place(String name){
        this.name = name;
        this.description = "";
        this.north = "";
        this.south = "";
        this.east = "";
        this.west = "";
    }
    
    public Place() {
    }

    //Getters and Setters
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getNorth() {
        return north;
    }

    public void setNorth(String north) {
        this.north = north;
    }

    public String getSouth() {
        return south;
    }

    public void setSouth(String south) {
        this.south = south;
    }

    public String getEast() {
        return east;
    }

    public void setEast(String east) {
        this.east = east;
    }

    public String getWest() {
        return west;
    }

    public void setWest(String west) {
        this.west = west;
    }
    
    
    
    
}
