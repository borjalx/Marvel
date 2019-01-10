
package p05.marvel;

import auxiliares.Auxiliares;
import exceptions.MarvelException;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.User;
import persistence.MarvelDAO;

/**
 *
 * @author Borja S
 */
public class P05Marvel {

    private User usuarioActual;
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        MarvelDAO marvelDAO = new MarvelDAO();
        
        String opcion = "s";
        
        while(opcion != ""){
            try {
                opcion = Auxiliares.pedirCadena("Comando : ");
                marvelDAO.connect();
                String[] res = opcion.split(" ");
                int nArgumentos = res.length;
                switch(res[0].toLowerCase()){
                    case "r":
                        if(nArgumentos != 4){
                            throw new MarvelException("[ Wrong number of arguments ] Expected = 4, Recieved = " + nArgumentos);
                        }
                        User usuario = new User(res[1],res[2]);
                        usuario.setPlace(marvelDAO.getPlaceByName("new york"));
                        usuario.setSuperhero(marvelDAO.getSuperheroByName(res[3]));
                        marvelDAO.insertUser(usuario);
                        // crear gemas
                        
                        System.out.println("User registered");
                        
                        String opcion2 = "s";
                        
                        while(opcion2 != ""){
                            opcion2 = Auxiliares.pedirCadena("Comando : ");
                        }
                        
                        break;
                    case "v":
                        if(nArgumentos != 1){
                            throw new MarvelException("[ Wrong number of arguments ] Expected = 1, Recieved = " + nArgumentos);
                        }
                        System.out.println("Ver superhéroes");
                        break;
                    case "l":
                        if(nArgumentos != 3){
                            throw new MarvelException("[ Wrong number of arguments ] Expected = 3, Recieved = " + nArgumentos);
                        }
                        System.out.println("Login");
                        
                        break;
                    case "k":
                        if(nArgumentos != 1){
                            throw new MarvelException("[ Wrong number of arguments ] Expected = 1, Recieved = " + nArgumentos);
                        }
                        System.out.println("Ranking");
                        break;
                    default:
                        throw new MarvelException("[ Wrong command ] Expected = r|v|l|k, Recieved = " + res[0]);
                }
                marvelDAO.disconnect();
            } catch (MarvelException | SQLException ex) {
                System.out.println(ex.getMessage());;
            }
        }
    }    
    
}
