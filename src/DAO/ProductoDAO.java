/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import Clases.Productos;
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
public class ProductoDAO {
    private Connection con;
    public ProductoDAO(){
    }
    public boolean AgregarProducto(Productos pro) throws SQLException{
    boolean centinela = false;
        try {
            this.con = new Conexion().obtenerConexion();
            String llamada = " { call SP_AGREGAR_PRODUCTO (?,?,?,?,?,?,?,?) } ";
            CallableStatement cstmt = con.prepareCall(llamada);
            cstmt.setInt(1, pro.getId_producto());
            cstmt.setString(2, pro.getNombre());
            cstmt.setString(3, pro.getDescripcion());
            cstmt.setInt(4, pro.getStock());
            cstmt.setInt(5, pro.getPrecio());
            cstmt.setBytes(6, pro.getImg());
            cstmt.setInt(7, pro.getTipo_producto_id_tproducto());
            cstmt.setInt(8, pro.getProveedor_id_proveedor());      
            int control = cstmt.executeUpdate();
            
            if(control>0){
                centinela = true;
            }
        } catch (SQLException e) {
            System.out.println("ERROR"+e.getMessage());
        }finally{
            this.con.close();
        }
    return centinela ;
    }
    public List<Productos> ListarProducto() throws SQLException {
        List<Productos> lista = new ArrayList<>();
        try {
            this.con = new Conexion().obtenerConexion();
            String llamada = "{ CALL sp_listar_productos(?) } ";
            CallableStatement cstmt = con.prepareCall(llamada);
            cstmt.registerOutParameter(1,OracleTypes.CURSOR);
            cstmt.execute();
            ResultSet rs = (ResultSet) cstmt.getObject(1);
            while (rs.next()) {
                Productos pro = new Productos();
                pro.setId_producto(rs.getInt("Id_producto"));
                pro.setNombre(rs.getString("Nombre"));
                pro.setDescripcion(rs.getString("descripcion"));
                pro.setStock(rs.getInt("Stock"));
                pro.setPrecio(rs.getInt("Precio"));
                pro.setImg(rs.getBytes("img"));
                pro.setTipo_producto_id_tproducto(rs.getInt("id_tproducto"));
                pro.setProveedor_id_proveedor(rs.getInt("id_proveedor"));
                lista.add(pro);

            }
        } catch (Exception e) {
            System.out.println("ERROR" + e.getMessage());
        } finally {
            this.con.close();
        }
        return lista;
    }
    public boolean eliminarProducto(int Id_producto) throws SQLException {
        boolean centinela = true;
        try {
            this.con = new Conexion().obtenerConexion();
            String query = "DELETE FROM producto where Id_producto =" + Id_producto;
            CallableStatement cstmt = this.con.prepareCall(query);

            if (cstmt.executeUpdate() > 0) {
                centinela = false;
            }
        } catch (Exception e) {
            System.out.println("error al eliminar" + e.getMessage());
        } finally {
            this.con.close();
        }
        return centinela;
    }
    public boolean modificarProducto(Productos pro) throws SQLException{
    boolean centinela = false;
        try {
            this.con = new Conexion().obtenerConexion();
            String queryProducto = "update Usuario set nombre= ?,descripcion = ?, stock = ?,precio = ?,img = ?,tipo_producto_id_tproducto = ?,proveedor_id_proveedor = ?" + "WHERE id_producto = ?" ;
            CallableStatement cstmt = con.prepareCall(queryProducto);
            cstmt.setString(1, pro.getNombre());
            cstmt.setString(2, pro.getDescripcion());
            cstmt.setInt(3, pro.getStock());
            cstmt.setInt(4, pro.getPrecio());
            cstmt.setBytes(5, pro.getImg());
            cstmt.setInt(6, pro.getTipo_producto_id_tproducto());
            cstmt.setInt(7, pro.getProveedor_id_proveedor());
            cstmt.setInt(8, pro.getId_producto());
            
            int control = cstmt.executeUpdate();
            
            if(control>0){
                centinela = true;
            }
        } catch (Exception e) {
            System.out.println("ERROR AL MODIFICAR"+e.getMessage());
        }finally{
            this.con.close();
        }
        return centinela;
    
    }

}
