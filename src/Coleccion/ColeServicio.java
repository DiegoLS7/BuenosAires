/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Coleccion;

import Clases.Servicio;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Diego
 */
public class ColeServicio {
    private List<Servicio> lista = new ArrayList<>();
    
    public ColeServicio() {
    }
    
    public List<Servicio> getLista() {
        return lista;
    }

    public void setLista(List<Servicio> lista) {
        this.lista = lista;
    }
    


//    public boolean AgregarServicio(Servicio ser){
//        for (Servicio servicio : lista) {
//            if(servicio.getIdServicio().equals(ser.getIdServicio())){
//             return false;
//            }
//        }
//        lista.add(ser);
//        return true;
//    }
}
