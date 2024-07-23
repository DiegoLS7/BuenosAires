/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import Clases.Servicio;
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
public class ServicioDAO {
    private Connection conexion;
    
    public ServicioDAO(){
    }
    public boolean AgregarServicio(Servicio ser) throws SQLException{
    boolean centinela = false;
        try {
            this.conexion = new Conexion().obtenerConexion();
            String queryServicio = "{ call SP_AGREGAR_SERVICIO(?,?,?,?,?)}";
            CallableStatement cstmt = conexion.prepareCall(queryServicio);
            cstmt.setInt(1, ser.getIdServicio());
            cstmt.setString(2, ser.getNombre());
            cstmt.setString(3, ser.getDescripcion());
            cstmt.setInt(4, ser.getPrecio());
            cstmt.setInt(5, ser.getTipoServ());
            
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
    public List<Servicio> ListarServicio() throws SQLException {
        List<Servicio> lista = new ArrayList<>();
        try {
            this.conexion = new Conexion().obtenerConexion();
            String query = " { call sp_listar_servicio(?) }";
            CallableStatement cstmt = conexion.prepareCall(query);
            cstmt.registerOutParameter(1,OracleTypes.CURSOR);
            cstmt.execute();
            ResultSet rs = (ResultSet) cstmt.getObject(1);
            while (rs.next()) {
                Servicio ser = new Servicio();
                ser.setIdServicio(rs.getInt("id_servicio"));
                ser.setNombre(rs.getString("nombre"));
                ser.setDescripcion(rs.getString("descripcion"));
                ser.setPrecio(rs.getInt("precio"));
                ser.setTipoServ(rs.getInt("id_ts"));
                lista.add(ser);

            }
        } catch (Exception e) {
            System.out.println("ERROR" + e.getMessage());
        } finally {
            this.conexion.close();
        }
        return lista;
    }
    public boolean eliminarServicio(int idServicio) throws SQLException {
        boolean centinela = true;
        try {
            this.conexion = new Conexion().obtenerConexion();
            String query = "DELETE FROM Servicio where id_servicio ="  + idServicio  ;
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
    public boolean modificarServicio(Servicio ser) throws SQLException{
    boolean centinela = false;
        try {
            this.conexion = new Conexion().obtenerConexion();
            String queryServicio = "update Servicio set nombre = ?,descripcion = ?,precio= ?,id_ts = ?" + "WHERE id_servicio = ?" ;
            CallableStatement cstmt = conexion.prepareCall(queryServicio);
            cstmt.setString(1, ser.getNombre());
            cstmt.setString(2, ser.getDescripcion());
            cstmt.setInt(3, ser.getPrecio());
            cstmt.setInt(4, ser.getTipoServ());
            cstmt.setInt(5, ser.getIdServicio());
            
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
