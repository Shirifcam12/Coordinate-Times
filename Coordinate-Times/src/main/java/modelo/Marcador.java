/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import javax.faces.bean.ManagedBean;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Transient;
/**
 * Bean manejado que parsea a la base el campo Marcador
 * @author Luna Menguante
 */
@ManagedBean
@Entity
@Table(catalog = "base", schema = "Base", name = "marcador")
@NamedQueries(
        @NamedQuery(name = "Marcador.findMarcadores", query = "SELECT m FROM Marcador m")
)
public class Marcador {

    @Id
    @Column(name = "idMarcador")
    private int idMarcador;

    @Column(name = "idTema")
    private int idTema;
    
    @Column(name = "latitud")
    private Double latitud;
    
    @Column(name = "longitud")
    private Double longitud;
    
    @Column(name = "datos_utiles")
    private String datos;
    
    @Column(name = "descripcion")
    private String descripcion;
    
/**
 * Método que devuelve el id asociado al marcador guardado en el bean
 * @return El id del marcador almacenado en el bean
 */
    public int getIdMarcador() {
        return idMarcador;
    }
/**
 * Método que asigna el id del marcador a guardar en el bean
 * @param idMarcador-el id del marcador a guardar en el bean
 */
    public void setIdMarcador(int idMarcador) {
        this.idMarcador = idMarcador;
    }
/**
 * Método que devuelve el id del tema asociado al marcador guardado en el bean
 * @return El id del tema asociado al marcador  almacenado en el bean
 */
    public int getIdTema() {
        return idTema;
    }
    /**
 * Método que asigna el id del del tema asociado al marcador a guardar en el bean
 * @param idTema-el id del tema asociado al marcador a guardar en el bean
 */
    public void setIdTema(int idTema) {
        this.idTema = idTema;
    }
/**
 * Método que devuelve la latitud del marcador guardado en el bean
 * @return La latitud del marcador almacenado en el bean
 */
    public Double getLatitud() {
        return latitud;
    }
    /**
 * Método que asigna la latitud del marcador a a guardar en el bean
 * @param latitud-la latitud del marcador a guardar en el bean
 */
    public void setLatitud(Double latitud) {
        this.latitud = latitud;
    }
/**
 * Método que devuelve la longitud del marcador guardado en el bean
 * @return La longitud del marcador almacenado en el bean
 */
    public Double getLongitud() {
        return longitud;
    }
   /**
 * Método que asigna la longitud del marcador a guardar en el bean
 * @param longitud-la longitud del marcador a guardar en el bean
 */
    public void setLongitud(Double longitud) {
        this.longitud = longitud;
    }
/**
 * Método que devuelve los datos asociados al marcador guardado en el bean
 * @return los datos asociados al marcador almacenado en el bean
 */
    public String getDatos() {
        return datos;
    }
  /**
 * Método que asigna los datos asociados al marcador a guardar en el bean
 * @param datos-los datos asociados al marcador a guardar en el bean
 */
    public void setDatos(String datos) {
        this.datos = datos;
    }
/**
 * Método que devuelve la descripción asociada al marcador guardado en el bean
 * @return la descripción asociada al marcador guardado en el bean
 */
    public String getDescripcion() {
        return descripcion;
    }
/**
 * Método que asigna la descripcion asociada al marcador a guardar en el bean
 * @param descripcion-la descripcion asociada al marcador a guardar en el bean
 */
    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
}
