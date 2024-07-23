/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Coleccion;

import Clases.Productos;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Diego
 */
public class ColeProductos {
    private List<Productos> lista = new ArrayList<>();
    
    public ColeProductos() {
    }
    
    public List<Productos> getLista() {
        return lista;
    }

    public void setLista(List<Productos> lista) {
        this.lista = lista;
    }
}
