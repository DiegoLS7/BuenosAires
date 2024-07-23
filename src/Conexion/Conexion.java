/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Conexion;

import java.sql.Connection;
import java.sql.DriverManager;

/**
 *
 * @author Gemin
 */
public class Conexion {
    
    private static Connection conexion;
    //Credenciales
    private String usuario = "buenosaires";
    private String pass = "buenosaires";
    private String url = "jdbc:oracle:thin:"+usuario+"/"+pass+"@localhost:1521:system";

    public Conexion() {
        try {
            //cargar la libreria jdbc para oracle
            Class.forName("oracle.jdbc.OracleDriver").newInstance();
            this.conexion = DriverManager.getConnection(url,usuario, pass);
        } catch (Exception e) {
            System.out.println("Error de conexión : "+e.getMessage());
        }
    }
    
    public Connection obtenerConexion(){
        return conexion;
    }
    
    
    
}
