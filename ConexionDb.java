package controlador;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Usuario
 */
public class ConexionDb {
    String db = "miinventario";
    String url = "jdbc:mysql://localhost:3306/";
    String user = "root";
    String password = "";
    String driver = "com.mysql.cj.jdbc.Driver";
    Connection cx;

    /**
     *
     */
    public ConexionDb() {

    }

    /**
     *
     * @return
     */
    public Connection conectar() {
        try {
            Class.forName(driver);
            cx = DriverManager.getConnection(url + db, user, password);
            System.out.println("Se conectó a la base de datos " + db);
        } catch (ClassNotFoundException | SQLException ex) {
            System.out.println("No se pudo conectar a la base de datos " + db);
            Logger.getLogger(ConexionDb.class.getName()).log(Level.SEVERE, null, ex);
        }
        return cx;
    }

    /**
     *
     */
    public void desconectar(){
        try{
            cx.close();
        }catch (SQLException ex){
            Logger.getLogger(ConexionDb.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }

    /**
     *
     * @param args
     */
    public static void main(String[]args){
        ConexionDb conexiondb=new ConexionDb();
        conexiondb.conectar();
    }
    
}

   

  
