/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package CapaConexion;

import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author REscobar
 */
public class Conexion {
    //Atributos del API JDBC
    public Connection cnn;
    public Statement stm;
    public ResultSet rst;
    
    //Atributos para la conexión
    private String nombreBaseDatos;
    private String nombreTabla;
    private String driver;
    private String url;
    private String user;
    private String password;
    private String sentenciaSQL;
    
    private boolean esSelect;

    public Conexion(String nombreBaseDatos, String nombreTabla, 
            String driver, String url, 
            String user, String password, 
            String sentenciaSQL, boolean esSelect) {
        this.nombreBaseDatos = nombreBaseDatos;
        this.nombreTabla = nombreTabla;
        this.driver = driver;
        this.url = url;
        this.user = user;
        this.password = password;
        this.sentenciaSQL = sentenciaSQL;
        this.esSelect = esSelect;
    }
    public Conexion()
    {
        this.nombreBaseDatos = "";
        this.nombreTabla = "";
        this.driver = "";
        this.url = "";
        this.user = "";
        this.password = "";
        this.sentenciaSQL = "";
        this.esSelect = false;
    }

    /**
     * @return the cnn
     */
    public Connection getCnn() {
        return cnn;
    }

    /**
     * @param cnn the cnn to set
     */
    public void setCnn(Connection cnn) {
        this.cnn = cnn;
    }

    /**
     * @return the stm
     */
    public Statement getStm() {
        return stm;
    }

    /**
     * @param stm the stm to set
     */
    public void setStm(Statement stm) {
        this.stm = stm;
    }

    /**
     * @return the rst
     */
    public ResultSet getRst() {
        return rst;
    }

    /**
     * @param rst the rst to set
     */
    public void setRst(ResultSet rst) {
        this.rst = rst;
    }

    /**
     * @return the nombreBaseDatos
     */
    public String getNombreBaseDatos() {
        return nombreBaseDatos;
    }

    /**
     * @param nombreBaseDatos the nombreBaseDatos to set
     */
    public void setNombreBaseDatos(String nombreBaseDatos) {
        this.nombreBaseDatos = nombreBaseDatos;
    }

    /**
     * @return the nombreTabla
     */
    public String getNombreTabla() {
        return nombreTabla;
    }

    /**
     * @param nombreTabla the nombreTabla to set
     */
    public void setNombreTabla(String nombreTabla) {
        this.nombreTabla = nombreTabla;
    }

    /**
     * @return the driver
     */
    public String getDriver() {
        return driver;
    }

    /**
     * @param driver the driver to set
     */
    public void setDriver(String driver) {
        this.driver = driver;
    }

    /**
     * @return the url
     */
    public String getUrl() {
        return url;
    }

    /**
     * @param url the url to set
     */
    public void setUrl(String url) {
        this.url = url;
    }

    /**
     * @return the user
     */
    public String getUser() {
        return user;
    }

    /**
     * @param user the user to set
     */
    public void setUser(String user) {
        this.user = user;
    }

    /**
     * @return the password
     */
    public String getPassword() {
        return password;
    }

    /**
     * @param password the password to set
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * @return the sentenciaSQL
     */
    public String getSentenciaSQL() {
        return sentenciaSQL;
    }

    /**
     * @param sentenciaSQL the sentenciaSQL to set
     */
    public void setSentenciaSQL(String sentenciaSQL) {
        this.sentenciaSQL = sentenciaSQL;
    }

    /**
     * @return the esSelect
     */
    public boolean isEsSelect() {
        return esSelect;
    }

    /**
     * @param esSelect the esSelect to set
     */
    public void setEsSelect(boolean esSelect) {
        this.esSelect = esSelect;
    }
    
    //Cerrar conexion
    public void cerrarConexion()
    {
        try {
            this.getCnn().close();
        } catch (SQLException ex) {
            Logger.getLogger(Conexion.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    //Conexion principal.
    public void conectar()
    {
        try {
            //Cargar driver de Conexion
            Class.forName(this.getDriver());
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Conexion.class.getName()).log(Level.SEVERE, null, ex);
            return;
        }
        try {
            //Crear la nueva conexion
            this.setCnn(DriverManager.getConnection(this.getUrl(), 
                        this.getUser(), 
                        this.getPassword()));
            //Crear el objeto para ejecutar sentencias SQL
            this.setStm(this.getCnn().createStatement());            
        } catch (SQLException ex) {
            Logger.getLogger(Conexion.class.getName()).log(Level.SEVERE, null, ex);
            return;
        }
        //Verificar si se ejecuta una QUERY o un UPDATE
        if(this.isEsSelect() == true)
        {
            try {
                //Ejecutar una QUERY
                this.setRst(this.getStm().executeQuery(this.getSentenciaSQL()));
            } catch (SQLException ex) {
                Logger.getLogger(Conexion.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        else
        {
            try {
                //Ejecutar un UPDATE
                this.getStm().executeUpdate(this.getSentenciaSQL());
            } catch (SQLException ex) {
                Logger.getLogger(Conexion.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }    
}
