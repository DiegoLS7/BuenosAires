/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import Clases.SoliServ;
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
public class SoliServicioDAO {
    private Connection conexion;
    public SoliServicioDAO(){
    }
    
    public boolean AgregarSoliServ(SoliServ slse) throws SQLException{
    boolean centinela = false;
        try {//SP_AGREGAR_SOLISER
            this.conexion = new Conexion().obtenerConexion();
            String llamada = " { call SP_AGREGAR_SOLISER(?,?,?,?,?,?)}";
            CallableStatement cstmt = conexion.prepareCall(llamada);
            cstmt.setInt(1, slse.getIdSolicitud());
            cstmt.setDate(2, slse.getFechaSolicitud());
            cstmt.setInt(3, slse.getServicioID());
            cstmt.setInt(4, slse.getMetodoID());
            cstmt.setInt(5, slse.getUsuarioID());
            cstmt.setInt(6, slse.getConfirmacion());
            
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
    public List<SoliServ> ListarSoliServ() throws SQLException {
        List<SoliServ> lista = new ArrayList<>();
        try {
            this.conexion = new Conexion().obtenerConexion();
            this.conexion = new Conexion().obtenerConexion();
            String query = " { call sp_listar_soliser(?) }";
            CallableStatement cstmt = conexion.prepareCall(query);
            cstmt.registerOutParameter(1,OracleTypes.CURSOR);
            cstmt.execute();
            ResultSet rs = (ResultSet) cstmt.getObject(1);
            while (rs.next()) {
                SoliServ slse = new SoliServ();
                slse.setIdSolicitud(rs.getInt("id_solicitud"));
                slse.setFechaSolicitud(rs.getDate("fecha_solicitud"));
                slse.setServicioID(rs.getInt("id_servicio"));
                slse.setUsuarioID(rs.getInt("id_metodo"));
                slse.setUsuarioID(rs.getInt("id_user"));
                slse.setServicioID(rs.getInt("confirmacion"));
                lista.add(slse);

            }
        } catch (Exception e) {
            System.out.println("ERROR" + e.getMessage());
        } finally {
            this.conexion.close();
        }
        return lista;
    }
    public boolean eliminarSoliServ(int idSolicitud) throws SQLException {
        boolean centinela = true;
        try {
            this.conexion = new Conexion().obtenerConexion();
            String query = "DELETE FROM solicitud_serv where id_solicitud ="+ idSolicitud  ;
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
    public boolean modificarSoliServ(SoliServ slse) throws SQLException{
    boolean centinela = false;
        try {
            this.conexion = new Conexion().obtenerConexion();
            String queryServicio = "update solicitud_serv set fecha_solicitud = ?,id_servicio= ?,id_metodo = ?, id_user=?, confirmacion=? " + "WHERE id_solicitud = ?" ;
            CallableStatement cstmt = conexion.prepareCall(queryServicio);
            
            cstmt.setDate(1, slse.getFechaSolicitud());
            cstmt.setInt(2, slse.getServicioID());
            cstmt.setInt(3, slse.getMetodoID());
            cstmt.setInt(4, slse.getUsuarioID());
            cstmt.setInt(5, slse.getConfirmacion());
            cstmt.setInt(6, slse.getIdSolicitud());
            
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
