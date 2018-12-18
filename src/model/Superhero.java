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
public class Superhero {
    
    private String name;
    private String superpower;
    
    //Constructor
    public Superhero() {
    }

    public Superhero(String name, String superpower) {
        this.name = name;
        this.superpower = superpower;
    }
    
    //Getters y Setters

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSuperpower() {
        return superpower;
    }

    public void setSuperpower(String superpower) {
        this.superpower = superpower;
    }

    //toString
    @Override
    public String toString() {
        return "Superheroe =>" + " Nombre = " + name + " | Superpoder = " + superpower + '}';
    }
    
    
}
