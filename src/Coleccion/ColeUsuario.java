/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Coleccion;

import Clases.Usuario;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Diego
 */
public class ColeUsuario {
    private List<Usuario> lista = new ArrayList<>();
    
    public ColeUsuario() {
    }
    
    public List<Usuario> getLista() {
        return lista;
    }

    public void setLista(List<Usuario> lista) {
        this.lista = lista;
    }
}
