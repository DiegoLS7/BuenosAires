/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Tedy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

/**
 *
 * @author Diego
 */
public class ClienteApiRest {

    /**
     * @param args the command line arguments
     * @throws java.net.MalformedURLException
     * @throws org.json.simple.parser.ParseException
     */
    public static void main(String[] args) throws MalformedURLException, IOException, ParseException {
        URL direccion =  new URL("http://localhost:51214/api/Producto");
        HttpURLConnection con = (HttpURLConnection)direccion.openConnection();
        
        con.setRequestMethod("GET");
        
        int respuestaservidor = con.getResponseCode();
        if(respuestaservidor==200){
            StringBuilder respuesta;
            try (BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()))) {
                String datos;
                respuesta = new StringBuilder();
                while((datos = in.readLine())!=null){
                    respuesta.append(datos);
                }
            }
            
            JSONParser parser =  new JSONParser();
            Object productoJSON = parser.parse(respuesta.toString());
            System.out.println("JSON PRODCUTO"+productoJSON);
            JSONArray arreglo =  (JSONArray) productoJSON;
            for (int i = 0; i < arreglo.size(); i++) {
                JSONObject p = (JSONObject) arreglo.get(i);
                
                System.out.println(p.get("Id_Producto")+" "+p.get("Nombre")+" "+p.get("Descripcion")+" "+p.get("Stock")+" "+p.get("Precio")+" "+p.get("Img")+" "+p.get("Id_Tproducto")+" "+p.get("Id_Proveedor") );
                
            }
        } else{
            System.out.println("Problemas con el servidor");
        }
    }
    
}
