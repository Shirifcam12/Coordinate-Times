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
import javax.persistence.Table;
import javax.persistence.Transient;
/**
 * Bean manejado que parsea a la base el campo Comentario
 * @author Luna Menguante
 */
@ManagedBean
@Entity
@Table(catalog = "base", schema = "Base", name = "comentario")
public class Comentario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idComentario")
    private int idComentario;

    @Column(name = "idUsuario")
    private int idUsuario;
    
    @Column(name = "idMarcador")
    private int idMarcador;
    
    @Column(name = "idTema")
    private int idTema;
    
    @Column(name = "comentario")
    private String comentario;
    
    @Column(name = "fecha")
    private String fecha;
/**
 * Método que devuelve el id asociado al comentario guardado en el bean
 * @return Elcomentario  almacenado en el bean
 */

    public int getIdComentario() {
        return idComentario;
    }
/**
 * Método que asigna el id del comentario a guardar en el bean
 * @param idComentario-el comentario a guardar en el bean
 */
    public void setIdComentario(int idComentario) {
        this.idComentario = idComentario;
    }
/**
 * Método que devuelve el id del usuario asociado al comentario guardado en el bean
 * @return El usuario asociado al comentario  almacenado en el bean
 */
    public int getIdUsuario() {
        return idUsuario;
    }
/**
 * Método que asigna el id del del usuario asociado al comentario a guardar en el bean
 * @param idUsuario-el usuario asociado al comentario a guardar en el bean
 */
    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }
/**
 * Método que devuelve el id del marcador asociado al comentario guardado en el bean
 * @return El marcador asociado al comentario  almacenado en el bean
 */
    public int getIdMarcador() {
        return idMarcador;
    }
/**
 * Método que asigna el id del del marcador asociado al comentario a guardar en el bean
 * @param idMarcador-el marcador asociado al comentario a guardar en el bean
 */
    public void setIdMarcador(int idMarcador) {
        this.idMarcador = idMarcador;
    }
/**
 * Método que devuelve el id del tema asociado al comentario guardado en el bean
 * @return El id del tema asociado al comentario  almacenado en el bean
 */
    public int getIdTema() {
        return idTema;
    }
/**
 * Método que asigna el id del del marcador asociado al comentario a guardar en el bean
 * @param idTema-el id del tema asociado al comentario a guardar en el bean
 */
    public void setIdTema(int idTema) {
        this.idTema = idTema;
    }
/**
 * Método que devuelve el comentario guardado en el bean
 * @return El comentario  almacenado en el bean
 */
    public String getComentario() {
        return comentario;
    }
/**
 * Método que asigna el comentario a guardar en el bean
 * @param comentario-el  comentario a guardar en el bean
 */
    public void setComentario(String comentario) {
        this.comentario = comentario;
    }

 /**
 * Método que devuelve la fecha en que se realizó el comentario guardado en el bean
 * @return La fecha asociada al comentario  almacenado en el bean
 */   public String getFecha() {
        return fecha;
    }
/**
 * Método que asigna la fecha asignada al comentario a guardar en el bean
 * @param fecha-la fecha asociada al comentario a guardar en el bean
 */
    public void setFecha(String fecha) {
        this.fecha = fecha;
    }
    

}
