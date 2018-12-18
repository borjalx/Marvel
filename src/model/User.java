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
public class User extends Person{

    private String username;
    private String pass;
    private Superhero superhero;
    private int points;
    
    //Constructor
    public User() {
    }
    
    public User(String username, String pass, Superhero superhero, int points, int level, Place place) {
        this.username = username;
        this.pass = pass;
        this.superhero = superhero;
        this.points = points;
        this.level = level;
        this.place = place;
    }
    
    //Getters y Setters
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public Superhero getSuperhero() {
        return superhero;
    }

    public void setSuperhero(Superhero superhero) {
        this.superhero = superhero;
    }

    public int getPoints() {
        return points;
    }

    public void setPoints(int points) {
        this.points = points;
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
    
    //toString

    @Override
    public String toString() {
        return "User => " + "Nombre de usuario = " + username + " | Contraseña = " + pass + " | Superheroe = " + superhero + " | Nivel = " + level + " | Lugar = " + place + " | Puntos = " + points + '}';
    }
    
}
