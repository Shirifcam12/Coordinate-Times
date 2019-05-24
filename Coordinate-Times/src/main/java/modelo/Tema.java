/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 * Bean manejado que parsea a la base el campo Tema
 * @author Luna Menguante
 */
@Entity
@Table(catalog = "base", schema = "Base", name = "tema")
@NamedQueries(
        @NamedQuery(name = "Tema.findTemas", query = "SELECT t from Tema t")
)
public class Tema implements Serializable {
    @Id
    @Column(name = "idTema")
    private Integer idT;

    @Column(name = "idUsuario")
    private Integer idU;
    
    @Column(name = "color_id")
    private Integer idC;
    
    @Column(name = "nombreTema")
    private String nombre;
/**
 * Método que devuelve el id asociado al tema guardado en el bean
 * @return el id asociado al tema guardado en el bean
 */
    public Integer getIdT() {
        return idT;
    }
/**
 * Método que asigna el id del tema a guardar en el bean
 * @param id-el id del tema a guardar en el bean
 */
    public void setIdT(int id) {
        this.idT = id;
    }
    /**
 * Método que devuelve el id del usuario asociado al tema guardado en el bean
 * @return el id del usuario asociado al tema guardado en el bean
 */
    public Integer getIdU() {
        return idU;
    }
/**
 * Método que asigna el id del usuario asociado al tema a guardar en el bean
 * @param id-el id del tema a guardar en el bean
 */
    public void setIdU(int id) {
        this.idU = id;
    }
  /**
 * Método que devuelve el nombre del tema guardado en el bean
 * @return el nombre del tema guardado en el bean
 */   
    public String getnombreT(){
        return nombre;
    }
    /**
 * Método que asigna el nombre del tema a guardar en el bean
 * @param nombre-el nombre del tema a guardar en el bean
 */
    public void setnombreT(String nombre){
        this.nombre = nombre;
    }

    public Integer getIdC() {
        return idC;
    }

    public void setIdC(int idC) {
        this.idC = idC;
    }
    @Override
    public String toString() {
        return nombre;
    }
    
}