/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package persistence;

import auxiliares.Auxiliares;
import exceptions.MarvelException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import model.User;
import model.*;

/**
 *
 * @author Borja S
 */
public class MarvelDAO {
    
    //Conexiones
    private Connection connection;
    
    public void connect() throws SQLException {
        String url = "jdbc:mysql://localhost:3306/restaurant";
        String user = "root";
        String password = "";
        connection = DriverManager.getConnection(url, user, password);
    }

    public void disconnect() throws SQLException {
        if (connection != null) {
            connection.close();
        }
    }
    
    //Funciones de User
    
    //Función que inserta un usuario en la BBDD
    private void insertUser() throws SQLException, MarvelException{
        //Excepciones
            //No hay places
            //No hay superheroes
        if(selectAllSuperheroes().isEmpty()){
            throw new MarvelException("No hay superhéroes disponibles");
        }
            //Ya hay usuario con ese nombre
        String username = Auxiliares.pedirCadena("Nombre de usuario = ");
        if(existsUserByUsername(username)){
            throw new MarvelException("Ya existe un usuario con el nombre " + username);
        }
        String pass = Auxiliares.pedirCadena("Contraseña = ");
        Superhero superhero = chooseSuperhero();
        Place place = getPlaceByName("new york");
        //User aux = new User(username, pass, superhero, place);
        
        PreparedStatement ps = connection.prepareStatement("insert into user values (?,?,?,?,?,?)");
        
        ps.setString(1, username);
        ps.setString(2, pass);
        ps.setInt(3, 1);
        ps.setString(4, superhero.getName());
        ps.setString(5, place.getName());
        ps.setInt(6, 0);
        
        ps.executeUpdate();
        
        ps.close();
    }
    
    //Función que nos devuelve un boolean dependiendo de si un usuario existe
    private boolean existsUser(User usuario) throws SQLException{
        Statement st = connection.createStatement();
        String query = "select * from user where name='"+ usuario.getUsername() +"';";
        ResultSet rs = st.executeQuery(query);
        boolean exists = rs.next();
        st.close();
        rs.close();
        return exists;
    }
    
    //Función que nos devuelve un boolean dependiendo de si un usuario existe por su nombre de usuario
    private boolean existsUserByUsername(String nombreUsuario) throws SQLException{
        Statement st = connection.createStatement();
        String query = "select * from user where name='"+ nombreUsuario +"';";
        ResultSet rs = st.executeQuery(query);
        boolean exists = rs.next();
        st.close();
        rs.close();
        return exists;
    }
    
    
    //Funciones de Enemy
    
    //Funciones de Place - ACABAR ESTO Y SEGUIR CON INSERTUSER
    //Función que a partir de su nombre devuelve un Place
    public Place getPlaceByName(String name) throws SQLException, MarvelException {
        Place aux = new Place();
        if (!existsPlace(aux)) {
            throw new MarvelException("No existe ningún lugar con ese nombre");
        }
        Statement st = connection.createStatement();
        String query = "select * from place where name ='"+name+"'";
        ResultSet rs = st.executeQuery(query);
        Place p = new Place();
        if (rs.next()) {
            fillPlace(p, rs);
        }
        rs.close();
        st.close();
        return p;
    }
    
    //Función que llena un lugar
    private void fillPlace(Place p, ResultSet rs) throws SQLException {
        p.setName(rs.getString("name"));
        p.setDescription(rs.getString("name"));
        p.setNorth(rs.getString("north"));
        p.setSouth(rs.getString("south"));
        p.setEast(rs.getString("east"));
        p.setWest(rs.getString("west"));
    }
    
    //Función que nos devuelve un boolean dependiendo de si un Place existe
    private boolean existsPlace(Place place) throws SQLException{
        Statement st = connection.createStatement();
        String query = "select * from place where name='"+ place.getName() +"';";
        ResultSet rs = st.executeQuery(query);
        boolean exists = rs.next();
        st.close();
        rs.close();
        return exists;
    }
    
    //Funciones de Gem
    
    //Funciones de Superhero
    //Función que devuelve todos los súperheroes
    private List<Superhero> selectAllSuperheroes() throws SQLException{
        List<Superhero> allSH = new ArrayList<>();
        Statement st = connection.createStatement();
        String select = "select * from superhero";
        ResultSet rs = st.executeQuery(select);
        while(rs.next()){
            Superhero s = new Superhero();
            fillSH(s,rs);
            allSH.add(s);
        }
        rs.close();
        st.close();
        return allSH;
    }
    
    //Función que a partir de un ResultSet rellena un superheroe
    private void fillSH(Superhero s, ResultSet rs) throws SQLException {
        s.setName(rs.getString("name"));
        s.setSuperpower(rs.getString("superpower"));
    }
    
    //Función que devuelve un Superhero a partir de la elección del usuario
    private Superhero chooseSuperhero() throws SQLException{
    
        List<Superhero> superheroes = selectAllSuperheroes();
        
        for (int i = 0; i < superheroes.size(); i++) {
            System.out.println(i+superheroes.get(i).toString());
        }
        
        int eleccion = Auxiliares.pedirNumeroRango("Número de Superhéroe = ", 0,  superheroes.size());
        
        return superheroes.get(eleccion);
    }
    //Funciones de Ranking
    
}
