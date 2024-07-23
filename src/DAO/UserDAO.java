/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import Clases.User;
import Conexion.Conexion;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import oracle.jdbc.OracleTypes;

/**
 *
 * @author Diego
 */
public class UserDAO {
    private Connection conexion;
    public UserDAO(){
    }
    public boolean AgregarUser(User use) throws SQLException{
        boolean centinela = false;
            try {
                this.conexion = new Conexion().obtenerConexion();
                String llamada = "{ call SP_AGREGAR_USER(?,?,?,?,?,?)}";
                CallableStatement cstmt = conexion.prepareCall(llamada);
                cstmt.setInt(1, use.getId());
                cstmt.setString(2, use.getContrasenia());
                cstmt.setString(3, use.getUsername());
                cstmt.setString(4, use.getNombre());
                cstmt.setString(5, use.getApellido());
                cstmt.setString(6, use.getEmail());

                int control = cstmt.executeUpdate();

                if(control>0){
                    centinela = true;
                }
            } catch (SQLException e) {
                System.out.println("ERROR"+e.getMessage());
            }finally{
                this.conexion.close();
            }
        return centinela ;
        }
    public List<User> ListarUser() throws SQLException {
        List<User> lista = new ArrayList<>();
        try {
            this.conexion = new Conexion().obtenerConexion();
            String query = " { call sp_listar_usuario(?) }";
            CallableStatement cstmt = conexion.prepareCall(query);
            cstmt.registerOutParameter(1,OracleTypes.CURSOR);
            cstmt.execute();
            ResultSet rs = (ResultSet) cstmt.getObject(1);
            while (rs.next()) {
                User use = new User();
                use.setId(rs.getInt("id"));
                use.setContrasenia(rs.getString("password"));
                use.setUsername(rs.getString("username"));
                use.setNombre(rs.getString("first_name"));
                use.setApellido(rs.getString("last_name"));
                use.setEmail(rs.getString("email"));
                lista.add(use);

            }
        } catch (SQLException e) {
            System.out.println("ERROR" + e.getMessage());
        } finally {
            this.conexion.close();
        }
        return lista;
    }
    public boolean eliminarUser(int id) throws SQLException {
        boolean centinela = true;
        try {
            this.conexion = new Conexion().obtenerConexion();
            String query = "DELETE FROM auth_user where id =" + id;
            CallableStatement cstmt = this.conexion.prepareCall(query);

            if (cstmt.executeUpdate() > 0) {
                centinela = false;
            }
        } catch (Exception e) {
            System.out.println("error al eliminar" + e.getMessage());
        } finally {
            this.conexion.close();
        }
        return centinela;
    }
    
    public boolean modificarUser(User use) throws SQLException{
    boolean centinela = false;
        try {
            this.conexion = new Conexion().obtenerConexion();
            String queryUsuario = "update auth_user set password= ?,username = ?, first_name = ?,last_name = ?,email = ?" + "WHERE id = ?" ;
            CallableStatement cstmt = conexion.prepareCall(queryUsuario);
            cstmt.setString(1, use.getContrasenia());
            cstmt.setString(2, use.getUsername());
            cstmt.setString(3, use.getNombre());
            cstmt.setString(4, use.getApellido());
            cstmt.setString(5, use.getEmail());
            cstmt.setInt(6, use.getId());
            
            int control = cstmt.executeUpdate();
            
            if(control>0){
                centinela = true;
            }
        } catch (Exception e) {
            System.out.println("ERROR AL MODIFICAR"+e.getMessage());
        }finally{
            this.conexion.close();
        }
        return centinela;
    
    }

}
