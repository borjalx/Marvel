
package p05.marvel;

import auxiliares.Auxiliares;
import exceptions.MarvelException;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.User;

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
        String opcion = "";
        
        while(opcion != ""){
            try {
                opcion = Auxiliares.pedirCadena("Comando : ");
                
                String[] res = opcion.split(" ");
                int nArgumentos = res.length;
                switch(res[0].toLowerCase()){
                    case "r":
                        System.out.println("Registro");
                        if(nArgumentos != 4){
                            throw new MarvelException("[ Wrong number of arguments ] Expected = 4, Recieved = " + nArgumentos);
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
                        throw new MarvelException("[ Wrong command ] Expected = r|v|l|k, Recieved = " + nArgumentos);
                }
            } catch (MarvelException ex) {
                Logger.getLogger(P05Marvel.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }    
    
}
