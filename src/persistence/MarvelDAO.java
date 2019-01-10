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
        String url = "jdbc:mysql://localhost:3306/marvel";
        String user = "root";
        String password = "";
        connection = DriverManager.getConnection(url, user, password);
    }

    public void disconnect() throws SQLException {
        if (connection != null) {
            connection.close();
        }
    }
    
    /***************************************************/
    //Funciones de User
    /***************************************************/
    
    //Función que inserta un usuario en la BBDD
    public void insertUser(User usuario) throws SQLException, MarvelException{
        //Excepciones
            //No hay places
            //No hay superheroes
            //Ya hay usuario con ese nombre
            
        if(selectAllSuperheroes().isEmpty()){
            throw new MarvelException("No hay superhéroes disponibles");
        }
        if(existsUserByUsername(usuario.getUsername())){
            throw new MarvelException("[User already exists] username = " + usuario.getUsername());
        }
        //User aux = new User(username, pass, superhero, place);
        
        PreparedStatement ps = connection.prepareStatement("insert into user values (?,?,?,?,?,?)");
        
        ps.setString(1, usuario.getUsername());
        ps.setString(2, usuario.getPass());
        ps.setInt(3, usuario.getLevel());
        ps.setString(4, usuario.getSuperhero().getName());
        ps.setString(5, usuario.getPlace().getName());
        ps.setInt(6, usuario.getPoints());
        
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
        String query = "select * from user where username='"+ nombreUsuario +"';";
        ResultSet rs = st.executeQuery(query);
        boolean exists = rs.next();
        st.close();
        rs.close();
        return exists;
    }
    
    //Función que devuelve un usuario(si existe) a partir del username
    public User getUserByUsername(String username) throws MarvelException, SQLException{
        User aux = new User(username);
        if (!existsUser(aux)) {
            throw new MarvelException("[ User already exists ] username =  " + username);
        }
        Statement st = connection.createStatement();
        String query = "select * from user where username ='"+username+"'";
        ResultSet rs = st.executeQuery(query);
        User u = new User();
        if (rs.next()) {
            fillUser(u, rs);
        }
        rs.close();
        st.close();
        return u;
    }
    
    //Función que rellena un objeto User a partir de un ResultSet
    private void fillUser(User u, ResultSet rs) throws SQLException, MarvelException{       
        u.setUsername(rs.getString("username"));
        u.setPass(rs.getString("pass"));
        u.setLevel(rs.getInt("level"));
        u.setSuperhero(getSuperheroByName(rs.getString("superhero")));
        u.setPlace(getPlaceByName(rs.getString("place")));
        u.setPoints(rs.getInt("points"));
    }
    
    /***************************************************/
    //Funciones de Enemy
    /***************************************************/
    
    //Función que devuelve una lista de Enemy a partir de un Place
    private List<Enemy> getEnemiesByPlace(Place place) throws SQLException, MarvelException{
        List<Enemy> enemigos = new ArrayList<Enemy>();
        Statement st = connection.createStatement();
        String select = "select * from enemy where place='"+place.getName()+"';";
        ResultSet rs = st.executeQuery(select);
        while(rs.next()){
            Enemy e = new Enemy();
            fillEnemy(e,rs);
            enemigos.add(e);
        }
        rs.close();
        st.close();
        
        return enemigos;
    }
    
    //Función que rellena un objeto User a partir de un ResultSet
    private void fillEnemy(Enemy e, ResultSet rs) throws SQLException, MarvelException{       
        e.setName(rs.getString("name"));
        e.setDebility(rs.getString("debility"));
        e.setLevel(rs.getInt("level"));
        e.setPlace(getPlaceByName(rs.getString("place")));
    }
    /***************************************************/
    //Funciones de Place
    /***************************************************/
    
    //Función que devuelve un List con todos los places
    
    //Función que a partir de su nombre devuelve un Place
    public Place getPlaceByName(String name) throws SQLException, MarvelException {
        Place aux = new Place(name);
        if (!existsPlace(aux)) {
            throw new MarvelException("[There isn't a place with that name] Place name = " + name);
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
        p.setDescription(rs.getString("description"));
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
    
    /***************************************************/
    //Funciones de Gem
    /***************************************************/
    //Función que a partir de una Gem le asocia un lugar donde no haya otras Gems
    private void moveGem(Gem gema){
        
    }
    
    //Función que a partir de un objeto User se le asocian sus 6 gemas
    public List<Gem> getUserGems(User user){
        
        /*Deberán crearse las seis gemas para el juego del usuario y situarse aleatoriamente  en  el  mapa  (en  seis lugares  diferentes a donde
        está el usuario y diferentes entre ellas).Si al situarlas coinciden con el lugar donde hay un villano, el villano pasará a ser propietario de la 
        gema.En caso de que hubiese más de un villano se puede asignar a cualquiera de ellos.*/
        
        //Creamos las gemas
        //No pueden haber 2 gemas en el mismo lugar en caso de haber una se transporta a otro lugar
        //Si en el lugar donde cae hay un enemigo se le asocia a este 
    }
    
    //Función que devuelve una Gem a partir de un Place
    private List<Gem> getGemsByOwner(Person person) throws SQLException, MarvelException{
        List<Gem> gemas = new ArrayList<Gem>();
        Statement st = connection.createStatement();
        String select = "select * from gem where place='"+person.getName()+"';";
        ResultSet rs = st.executeQuery(select);
        while(rs.next()){
            Gem g = new Gem();
            fillGem(g,rs);
            gemas.add(g);
        }
        rs.close();
        st.close();
        
        return gemas;
    }
    
    //Función que 
    private void fillGem(Gem g, ResultSet rs) throws SQLException, MarvelException{       
        g.setName(rs.getString("name"));
        g.setUser(getUserByUsername(rs.getString("user")));
        g.setOwner(getUserByUsername(rs.getString("owner")));
        g.setPlace(getPlaceByName(rs.getString("place")));
    }
    /***************************************************/
    //Funciones de Superhero
    /***************************************************/
    
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
    
    //Función que devuelve un objeto Superhero si encuentra un superheroe por el nombre introducido
    public Superhero getSuperheroByName(String name) throws SQLException, MarvelException{
        Superhero aux = new Superhero(name);
        if (!existsSuperhero(aux)) {
            throw new MarvelException("[There isn't a superhero with that name] Superhero name = " + name);
        }
        Statement st = connection.createStatement();
        String query = "select * from superhero where name ='"+name+"'";
        ResultSet rs = st.executeQuery(query);
        Superhero s = new Superhero();
        if (rs.next()) {
            fillSH(s, rs);
        }
        rs.close();
        st.close();
        return s;
    }
    
    //Función que a partir de un nombre devuelve un boolean dependiendo de si un superhero existe
    private boolean existsSuperhero(Superhero superheroe) throws SQLException{
        Statement st = connection.createStatement();
        String query = "select * from superhero where name='"+ superheroe.getName() +"';";
        ResultSet rs = st.executeQuery(query);
        boolean exists = rs.next();
        st.close();
        rs.close();
        return exists;
    }
    
    //Función que devuelve un Superhero a partir de la elección del usuario
    /*private Superhero chooseSuperhero() throws SQLException{
    
        List<Superhero> superheroes = selectAllSuperheroes();
        
        for (int i = 0; i < superheroes.size(); i++) {
            System.out.println(i+superheroes.get(i).toString());
        }
        
        int eleccion = Auxiliares.pedirNumeroRango("Número de Superhéroe = ", 0,  superheroes.size());
        
        return superheroes.get(eleccion);
    }*/
    
    /***************************************************/
    //Funciones de Ranking
    /***************************************************/
    
    //Funciones de Person
    
}
