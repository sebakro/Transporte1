/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package CapaNegocio;

import CapaConexion.Conexion;
import CapaDatos.Producto;
import CapaPresentacion.IngresarProducto;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author REscobar
 */
public class NegocioProducto
{
    private Conexion cnn;

    public NegocioProducto()
    {
        this.cnn = new Conexion();
    }
    
    private void configurarConexion()
    {
        cnn.setNombreBaseDatos("restaurant");
        cnn.setNombreTabla("producto");
        
        cnn.setDriver("com.mysql.jdbc.Driver");
        cnn.setUrl("jdbc:mysql://localhost:3306/" + cnn.getNombreBaseDatos());
        
        cnn.setUser("root");
        cnn.setPassword("");   
    }
    public void ingresarProducto(Producto acad)
    {
        this.configurarConexion();
        cnn.setEsSelect(false);
        
        cnn.setSentenciaSQL("insert into " + 
                cnn.getNombreTabla() + 
                " values('" + acad.getNombre() + "','" + 
                acad.getCantidad() + "')");        
        cnn.conectar();
        cnn.cerrarConexion();  
    }
    
    public void eliminarProducto(String nombre)
    {
        this.configurarConexion();
        
        cnn.setEsSelect(false);
        cnn.setSentenciaSQL("Delete from " + 
                            cnn.getNombreTabla() +
                            " where nombre='" + nombre + "'");
        cnn.conectar();           
        cnn.cerrarConexion();
    }
    
    public void modificarProducto(Producto acad,String nombre)
    {
        this.configurarConexion();
        this.cnn.setEsSelect(false);
        
        cnn.setSentenciaSQL("Update " + 
                cnn.getNombreTabla() +
                "set Cantidad='" + acad.getCantidad() + "'"+" where nombre='" + nombre + "'");

        cnn.conectar();                       
        cnn.cerrarConexion();
    }
    
    public boolean existeNombre(String nombre) {
        boolean siEsta = false;
        
        this.configurarConexion();
        cnn.setEsSelect(true);
        
        cnn.setSentenciaSQL("Select * from " + 
                            cnn.getNombreTabla() +
                            " where Nombre='" + nombre + "'");
        cnn.conectar();
        
        try 
        {
            if(cnn.getRst().next())
            {
                siEsta = true;
            }
        } catch (SQLException ex) {
            Logger.getLogger(IngresarProducto.class.getName()).log(Level.SEVERE, null, ex);
        }                
        cnn.cerrarConexion();                
        return siEsta;
    }

    public Producto buscarProducto(String nombre) {
        Producto acad = new Producto();
        this.configurarConexion();
        cnn.setEsSelect(true);
        
        cnn.setSentenciaSQL("Select * from " + 
                            cnn.getNombreTabla() +
                            " where nombre='" + nombre + "'");               
        cnn.conectar();
        
        try 
        {
            if(cnn.getRst().next())
            {
                acad.setNombre(cnn.getRst().getString("nombre"));
                acad.setCantidad(cnn.getRst().getString("cantidad"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(IngresarProducto.class.getName()).log(Level.SEVERE, null, ex);
        }                
        cnn.cerrarConexion();                                                    
        return acad;
    }
    
    public ArrayList<Producto> getProducto()
    {
        ArrayList<Producto> lista = new ArrayList();
        this.configurarConexion();
        cnn.setEsSelect(true);
        
        cnn.setSentenciaSQL("Select * from " + cnn.getNombreTabla());
        cnn.conectar();
        try {
            while(cnn.getRst().next())
            {
                Producto acad = new Producto();
                acad.setNombre(cnn.getRst().getString("nombre"));
                acad.setCantidad(cnn.getRst().getString("cantidad"));
                lista.add(acad);
            }
        } catch (SQLException ex) {
            Logger.getLogger(NegocioProducto.class.getName()).log(Level.SEVERE, null, ex);
        }        
        cnn.cerrarConexion();
        return lista;
    }    
}
