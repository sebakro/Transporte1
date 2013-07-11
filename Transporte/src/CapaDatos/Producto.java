/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package CapaDatos;

/**
 *
 * @author Seba
 */

public class Producto {
    
    private String nombre;
    private String cantidad;
    
    public Producto(String nombre,String cantidad) {
        
        this.nombre = nombre;
        this.cantidad = cantidad;
    }
    
    public Producto() {
        this.nombre = "";
        this.cantidad = "";
    }

    /**
     * @return the nombre
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * @param nombre the nombre to set
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * @return the cantidad
     */
    public String getCantidad() {
        return cantidad;
    }

    /**
     * @param cantidad the cantidad to set
     */
    public void setCantidad(String cantidad) {
        this.cantidad = cantidad;
    }

}
