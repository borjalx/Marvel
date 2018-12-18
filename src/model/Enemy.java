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
public class Enemy extends Person{
    
    private String name;
    private String debility;


    //Constructors
    public Enemy(String name, String debility) {
        super();
        this.name = name;
        this.debility = debility;
    }

    //Getters y Setters
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDebility() {
        return debility;
    }

    public void setDebility(String debility) {
        this.debility = debility;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public Place getPlace() {
        return place;
    }

    public void setPlace(Place place) {
        this.place = place;
    }

    //To String
    @Override
    public String toString() {
        return "Enemigo => " + "Nombre = " + name + " | Debilidad = " + debility + " | Nivel = " + level + " | Lugar = " + place + "|";
    }
    
    
    
}
