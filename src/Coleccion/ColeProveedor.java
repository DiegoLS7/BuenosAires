/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Coleccion;
import Clases.Proveedor;
import java.util.ArrayList;
import java.util.List;
import DAO.ProveedorDAO;
/**
 *
 * @author Diego
 */
public class ColeProveedor {
    private List<Proveedor> lista = new ArrayList<>();
    
    public ColeProveedor(){
    }
    
    public List<Proveedor> getLista() {
        return lista;
    }    
    
    public void setLista(List<Proveedor> lista) {
        this.lista = lista;
    }
//    public boolean agregar(Proveedor pro){
//        for (Empresa empresa : lista) {
//            if(empresa.getRutEmpresa().equals(emp.getRutEmpresa())){
//             return false;
//            }
//        }
//        lista.add(emp);
//        return true;
//    }
    
}
