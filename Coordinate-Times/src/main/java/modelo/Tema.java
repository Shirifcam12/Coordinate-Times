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
 *
 * @author ricardo
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

    public int getIdT() {
        return idT;
    }

    public void setIdT(int id) {
        this.idT = id;
    }
    public int getIdU() {
        return idU;
    }

    public void setIdU(int id) {
        this.idU = id;
    }
    
    public String getnombreT(){
        return nombre;
    }
    public void setnombreT(String nombre){
        this.nombre = nombre;
    }
}