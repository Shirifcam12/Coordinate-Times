/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

/**
 * Bean manejado que parsea a la base el campo Tema
 * @author Luna Menguante
 */
@Entity
@Table(catalog = "base", schema = "Base", name = "tema")
public class Tema {
    @Id
    @Column(name = "idTema")
    private int idT;

    @Column(name = "idUsuario")
    private int idU;

    @Column(name = "nombreTema")
    private String nombre;
/**
 * Método que devuelve el id asociado al tema guardado en el bean
 * @return el id asociado al tema guardado en el bean
 */
    public int getIdT() {
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
    public int getIdU() {
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
}