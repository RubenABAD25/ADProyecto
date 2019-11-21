package conector;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ConexionDB {
        Connection connection = null;
    public Connection connect(){
        String url = "jdbc:mysql://localhost:3306/parqueadero";
        String user = "parqueadero";
        String pass = "admin";
        
        try{
        connection = DriverManager.getConnection(url,user,pass);
        System.out.println("Conectado!!");

        }catch(SQLException e){
        System.out.println(e.getErrorCode());
        }
        return connection;
    }

    public void cerrarConexion(){
        
            try {
                connection.close();
                System.out.println("Desconectado!!");
            } catch (SQLException ex) {
                Logger.getLogger(ConexionDB.class.getName()).log(Level.SEVERE, null, ex);
            }
    }
        
    
}