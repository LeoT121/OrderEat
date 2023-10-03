/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Control;

import static com.mysql.cj.conf.PropertyKey.logger;
import com.mysql.cj.jdbc.AbandonedConnectionCleanupThread;
import java.sql.Connection;
import java.sql.DriverManager;

/**
 *
 * @author MARIA DEL REFUGIO
 */
public class Conexion {
    public static Connection getConexion(){
        String url, userName, password;
        
        url = "";
        userName = "";
        password = "";
        
        Connection con = null;
        
        try{
            ///Class.forName("com.mysql.cj.jdbc.Driver");
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection(url, userName, password);
            ///System.out.println("Hola como estas");
        }catch(Exception e){
            System.out.println(e.getMessage());
        }
        return con;
    }      
}
