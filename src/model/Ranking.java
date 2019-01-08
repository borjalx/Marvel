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
public class Ranking {
    private User user;
    private Superhero superhero;
    private int gems;
    private int level;
    private int points;

    //Constructors
    public Ranking() {
    }

    public Ranking(User user, Superhero superhero, int gems, int level, int points) {
        this.user = user;
        this.superhero = superhero;
        this.gems = gems;
        this.level = level;
        this.points = points;
    }
    
    //Getters y Setters

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Superhero getSuperhero() {
        return superhero;
    }

    public void setSuperhero(Superhero superhero) {
        this.superhero = superhero;
    }

    public int getGems() {
        return gems;
    }

    public void setGems(int gems) {
        this.gems = gems;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public int getPoints() {
        return points;
    }

    public void setPoints(int points) {
        this.points = points;
    }
    
    
}
