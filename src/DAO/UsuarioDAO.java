/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import Clases.Usuario;
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
public class UsuarioDAO {
    private Connection conexion;
    public UsuarioDAO(){
    }
    public boolean AgregarUsuario(Usuario usu) throws SQLException{
    boolean centinela = false;
        try {
            this.conexion = new Conexion().obtenerConexion();
            String llamada = "{ call SP_AGREGAR_USUARIO(?,?,?,?,?,?,?,?,?,?)}";
            CallableStatement cstmt = conexion.prepareCall(llamada);
            cstmt.setInt(1, usu.getIdUsuario());
            cstmt.setString(2, usu.getEmail());
            cstmt.setString(3, usu.getContrasenia());
            cstmt.setString(4, usu.getpNombre());
            cstmt.setString(5, usu.getsNombre());
            cstmt.setString(6, usu.getpApellido());
            cstmt.setString(7, usu.getsApellido());
            cstmt.setInt(8, usu.getnCelular());
            cstmt.setBytes(9, usu.getImg());
            cstmt.setInt(10, usu.getTipoUsu());
            
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
    public List<Usuario> ListarUsuario() throws SQLException {
        List<Usuario> lista = new ArrayList<>();
        try {
            this.conexion = new Conexion().obtenerConexion();
            this.conexion = new Conexion().obtenerConexion();
            String query = " { call sp_listar_usuario(?) }";
            CallableStatement cstmt = conexion.prepareCall(query);
            cstmt.registerOutParameter(1,OracleTypes.CURSOR);
            cstmt.execute();
            ResultSet rs = (ResultSet) cstmt.getObject(1);
            while (rs.next()) {
                Usuario usu = new Usuario();
                usu.setIdUsuario(rs.getInt("id_usuario"));
                usu.setEmail(rs.getString("email"));
                usu.setContrasenia(rs.getString("contrasenia"));
                usu.setpNombre(rs.getString("p_nombre"));
                usu.setsNombre(rs.getString("s_nombre"));
                usu.setpApellido(rs.getString("p_apellido"));
                usu.setsApellido(rs.getString("s_apellido"));
                usu.setnCelular(rs.getInt("n_celular"));
                usu.setImg(rs.getBytes("img"));
                usu.setTipoUsu(rs.getInt("tipo_usuario_id_tipo_u"));
                lista.add(usu);

            }
        } catch (Exception e) {
            System.out.println("ERROR" + e.getMessage());
        } finally {
            this.conexion.close();
        }
        return lista;
    }
    public boolean eliminarUsuario(int idUsuario) throws SQLException {
        boolean centinela = true;
        try {
            this.conexion = new Conexion().obtenerConexion();
            String query = "DELETE FROM Usuario where id_Usuario =" + idUsuario;
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
    public boolean modificarUsuario(Usuario usu) throws SQLException{
    boolean centinela = false;
        try {
            this.conexion = new Conexion().obtenerConexion();
            String queryUsuario = "update Usuario set email= ?,contrasenia = ?, p_nombre = ?,s_nombre = ?,p_apellido = ?,s_apellido = ?,n_celular = ?,img = ?,tipo_usuario_id_tipo_u = ?" + "WHERE id_usuario = ?" ;
            CallableStatement cstmt = conexion.prepareCall(queryUsuario);
            cstmt.setString(1, usu.getEmail());
            cstmt.setString(2, usu.getContrasenia());
            cstmt.setString(3, usu.getpNombre());
            cstmt.setString(4, usu.getsNombre());
            cstmt.setString(5, usu.getpApellido());
            cstmt.setString(6, usu.getsApellido());
            cstmt.setInt(7, usu.getnCelular());
            cstmt.setBytes(8, usu.getImg());
            cstmt.setInt(9, usu.getTipoUsu());
            cstmt.setInt(10, usu.getIdUsuario());
            
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
