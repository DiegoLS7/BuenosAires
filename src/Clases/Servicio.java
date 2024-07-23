/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Clases;

import java.sql.Date;

/**
 *
 * @author Diego
 */
public class Servicio {
    public int idServicio;
    public String nombre;
    public String descripcion;
    public int precio;
    public int tipoServ;

    public Servicio() {
    }

    public Servicio(int idServicio, String nombre, String descripcion, int precio, int tipoServ) {
        this.idServicio = idServicio;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.precio = precio;
        this.tipoServ = tipoServ;
    }

    public int getIdServicio() {
        return idServicio;
    }

    public void setIdServicio(int idServicio) {
        this.idServicio = idServicio;
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

    public int getPrecio() {
        return precio;
    }

    public void setPrecio(int precio) {
        this.precio = precio;
    }

    public int getTipoServ() {
        return tipoServ;
    }

    public void setTipoServ(int tipoServ) {
        this.tipoServ = tipoServ;
    }

    @Override
    public String toString() {
        return "Servicio{" + "idServicio=" + idServicio + ", nombre=" + nombre + ", descripcion=" + descripcion + ", precio=" + precio + ", tipoServ=" + tipoServ + '}';
    }
    
    
    
}
