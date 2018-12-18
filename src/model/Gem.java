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
public class Gem {
    
    private String name;
    private User user;
    private Person owner;
    private Place place;
    
    //Constructors
    public Gem() {
    }
    
    public Gem(String name, User user, Person owner, Place place) {
        this.name = name;
        this.user = user;
        this.owner = owner;
        this.place = place;
    }
    
    //Getters y Setters
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Person getOwner() {
        return owner;
    }

    public void setOwner(Person owner) {
        this.owner = owner;
    }

    public Place getPlace() {
        return place;
    }

    public void setPlace(Place place) {
        this.place = place;
    }

    //toString

    @Override
    public String toString() {
        return "Gema => " + " Nombre = " + name + " | Usuario = " + user + " | Propietario = " + owner + " | Lugar =" + place.getName() + '}';
    }
    

    
}
