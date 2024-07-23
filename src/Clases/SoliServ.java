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
public class SoliServ {
    public int idSolicitud;
    public Date fechaSolicitud;
    public int servicioID;
    public int metodoID;
    public int usuarioID;
    public int confirmacion;

    public SoliServ() {
    }

    public SoliServ(int idSolicitud, Date fechaSolicitud, int servicioID, int metodoID, int usuarioID, int confirmacion) {
        this.idSolicitud = idSolicitud;
        this.fechaSolicitud = fechaSolicitud;
        this.servicioID = servicioID;
        this.metodoID = metodoID;
        this.usuarioID = usuarioID;
        this.confirmacion = confirmacion;
    }

    public int getIdSolicitud() {
        return idSolicitud;
    }

    public void setIdSolicitud(int idSolicitud) {
        this.idSolicitud = idSolicitud;
    }

    public Date getFechaSolicitud() {
        return fechaSolicitud;
    }

    public void setFechaSolicitud(Date fechaSolicitud) {
        this.fechaSolicitud = fechaSolicitud;
    }

    public int getServicioID() {
        return servicioID;
    }

    public void setServicioID(int servicioID) {
        this.servicioID = servicioID;
    }

    public int getMetodoID() {
        return metodoID;
    }

    public void setMetodoID(int metodoID) {
        this.metodoID = metodoID;
    }

    public int getUsuarioID() {
        return usuarioID;
    }

    public void setUsuarioID(int usuarioID) {
        this.usuarioID = usuarioID;
    }

    public int getConfirmacion() {
        return confirmacion;
    }

    public void setConfirmacion(int confirmacion) {
        this.confirmacion = confirmacion;
    }

    @Override
    public String toString() {
        return "SoliServ{" + "idSolicitud=" + idSolicitud + ", fechaSolicitud=" + fechaSolicitud + ", servicioID=" + servicioID + ", metodoID=" + metodoID + ", usuarioID=" + usuarioID + ", confirmacion=" + confirmacion + '}';
    }
    
    


    
    
}
