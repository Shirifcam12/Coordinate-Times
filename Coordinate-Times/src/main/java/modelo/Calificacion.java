/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import java.util.ArrayList;
import javax.faces.bean.ManagedBean;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;
/**
 * Bean manejado que parsea a la base el campo Calificacion
 * @author Luna Menguante
 */
@ManagedBean
@Entity
@Table(catalog = "base", schema = "Base", name = "calificacion")
public class Calificacion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idCalificacion")
    private int idCalificacion;

    @Column(name = "idUsuario")
    private int idUsuario;
    
    @Column(name = "idComentario")
    private int idComentario;
    
    @Column(name="eleccion")
    private boolean eleccion;
  
    /**
     * Metodo que obtiene el identificador de la calificacion
     * @return idCalificacion -- el identificador de la calificacion
     */
    public int getIdCalificacion() {
        return idCalificacion;
    }

    /**
     * Metodo que aisgna un nuevo identificador de calificación
     * @param idCalificacion -- la nueva calificacion.
     */
    public void setIdCalificacion(int idCalificacion) {
        this.idCalificacion = idCalificacion;
    }
    
    /**
     * Metodo que obtiene el identificador del usuario
     * @return idUsuario -- el id del Usuario
     */
    public int getIdUsuario() {
        return idUsuario;
    }

    /**
     * Metodo que asigna una nueva id al id del usuario
     * @param idUsuario -- la nueva idUsuario
     */
    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }

    /**
     * Metodo que obtiene el identificador del comentario
     * @return idComentario -- el identificador del comentario
     */
    public int getIdComentario() {
        return idComentario;
    }

    /**
     * Metodo que asigna un nuevo id al identificador del comentario
     * @param idComentario -- el nuevo idComentario
     */
    public void setIdComentario(int idComentario) {
        this.idComentario = idComentario;
    }

    /**
     * Metodo que obtiene la eleccion de la calificacion
     * @return eleccion -- la eleccion de la calificacion
     */
    public boolean isEleccion() {
        return eleccion;
    }

    /**
     * Metodo que asigna una nueva eleccion al la calificacion
     * @param eleccion -- la nueva eleccion de la calificacion
     */
    public void setEleccion(boolean eleccion) {
        this.eleccion = eleccion;
    }    
}