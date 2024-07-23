/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Clases;

/**
 *
 * @author Diego
 */
public class Productos {
    public int id_producto;
    public String nombre;
    public String descripcion;
    public int stock;
    public int precio;
    public byte[] img;
    public int tipo_producto_id_tproducto;
    public int proveedor_id_proveedor;

    public Productos() {
    }

    public Productos(int id_producto, String nombre, String descripcion, int stock, int precio, byte[] img, int tipo_producto_id_tproducto, int proveedor_id_proveedor) {
        this.id_producto = id_producto;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.stock = stock;
        this.precio = precio;
        this.img = img;
        this.tipo_producto_id_tproducto = tipo_producto_id_tproducto;
        this.proveedor_id_proveedor = proveedor_id_proveedor;
    }

    public int getId_producto() {
        return id_producto;
    }

    public void setId_producto(int id_producto) {
        this.id_producto = id_producto;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public int getPrecio() {
        return precio;
    }

    public void setPrecio(int precio) {
        this.precio = precio;
    }

    public byte[] getImg() {
        return img;
    }

    public void setImg(byte[] img) {
        this.img = img;
    }

    public int getTipo_producto_id_tproducto() {
        return tipo_producto_id_tproducto;
    }

    public void setTipo_producto_id_tproducto(int tipo_producto_id_tproducto) {
        this.tipo_producto_id_tproducto = tipo_producto_id_tproducto;
    }

    public int getProveedor_id_proveedor() {
        return proveedor_id_proveedor;
    }

    public void setProveedor_id_proveedor(int proveedor_id_proveedor) {
        this.proveedor_id_proveedor = proveedor_id_proveedor;
    }

    @Override
    public String toString() {
        return "Productos{" + "id_producto=" + id_producto + ", nombre=" + nombre + ", descripcion=" + descripcion + ", stock=" + stock + ", precio=" + precio + ", img=" + img + ", tipo_producto_id_tproducto=" + tipo_producto_id_tproducto + ", proveedor_id_proveedor=" + proveedor_id_proveedor + '}';
    }
    
    
}
