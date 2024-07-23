/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import Clases.Proveedor;
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
public class ProveedorDAO {
    private Connection conexion;
    
    public ProveedorDAO() {
    }
    public boolean agregarProveedor(Proveedor pro) throws SQLException{
    boolean centinela = false;
        try {
            this.conexion = new Conexion().obtenerConexion();
            String queryProveedor = "insert into Proveedor values(?,?,?,?)";
            CallableStatement cstmt = conexion.prepareCall(queryProveedor);
            cstmt.setInt(1, pro.getIdProveedor());
            cstmt.setString(2, pro.getNombre());
            cstmt.setInt(3, pro.getTelefono());
            cstmt.setString(4, pro.getEmail());
            
            int control = cstmt.executeUpdate();
            
            if(control>0){
                centinela = true;
            }
        } catch (SQLException e) {
            System.out.println("ERROR"+e.getMessage());
        }finally{
            this.conexion.close();
        }
    
        return centinela;
    }
    public List<Proveedor> ListarProovedor() throws SQLException{
    List<Proveedor> lista = new ArrayList<>();
        try {
            this.conexion = new Conexion().obtenerConexion();
            String query = " { call sp_listar_proveedor(?) }";
            CallableStatement cstmt = conexion.prepareCall(query);
            cstmt.registerOutParameter(1,OracleTypes.CURSOR);
            cstmt.execute();
            ResultSet rs = (ResultSet) cstmt.getObject(1);
            while(rs.next()){
            Proveedor pro = new Proveedor();
            pro.setIdProveedor(rs.getInt("id_proveedor"));
            pro.setNombre(rs.getString("nombre"));
            pro.setTelefono(rs.getInt("telefono"));
            pro.setEmail(rs.getString("email"));
            lista.add(pro);
            }
        } catch (Exception e) {
            System.out.println("ERROR"+e.getMessage());
        }finally{
        this.conexion.close();
        }
        return lista;
    }    
    public boolean eliminarProveedor(int idProveedor) throws SQLException {
        boolean centinela = true;
        try {
            this.conexion = new Conexion().obtenerConexion();
            String query = "DELETE FROM Proveedor where id_Proveedor ="  + idProveedor ;
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
    public boolean modificarProveedor(Proveedor pro) throws SQLException{
    boolean centinela = false;
        try {
            this.conexion = new Conexion().obtenerConexion();
            String queryProveedor = "update Proveedor set nombre = ?,Telefono = ?,Email= ?" + "WHERE id_proveedor = ?" ;
            CallableStatement cstmt = conexion.prepareCall(queryProveedor);
            cstmt.setString(1, pro.getNombre());
            cstmt.setInt(2, pro.getTelefono());
            cstmt.setString(3, pro.getEmail());
            cstmt.setInt(4, pro.getIdProveedor());
            
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
