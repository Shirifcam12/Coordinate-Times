/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Locale;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.SessionScoped;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import modelo.Marcador;
import modelo.UtilityM;

/**
 * Bean manejado para Mostrar Marcador 
 * @author Luna Menguante
 */
@ManagedBean
@SessionScoped
public class MostrarMarcador {
    private String nombre;
    private Marcador user = new Marcador();
    private UtilityM u = new UtilityM();
    private ArrayList<Marcador> t;
    private String e;
/**
 * Método que devuelve el nombre guardado en el bean
 * @return El nombre almacenado en el bean
 */
    public String getNombre() {
        return nombre;
    }
/**
 * Método que asigna el nombre a guardar en el bean
 * @param nombre- El nombre a almacenar en el bean
 */ 
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
/**
 * Método que devuelve el marcador a guardar en el bean
 * @return El usuario almacenado en el bean
 */
    public Marcador getUser() {
        return user;
    }
/**
 * Método que asigna el marcador a guardar en el bean
 * @param user- El marcador a almacenar en el bean
 */
    public void setUser(Marcador user) {
        this.user = user;
    }
/**
 * Método que devuelve la instancia de UtilityT guardada en el bean
 * @return La instancia de UtilityT almacenada en el bean
 */
    public UtilityM getU() {
        return u;
    }
/**
 * Método que asigna la instancia de UtilityT a guardar en el bean
 * @param u- La instancia de UtilityT a almacenar en el bean
 */
    public void setU(UtilityM u) {
        this.u = u;
    }
  /**
 * Método que devuelve la lista de comentarios guardados en el bean
 * @return La lista de comentarios almacenada en el bean
 */
    public ArrayList<Marcador> getT() {
        return t;
    }
/**
 * Método que asigna la lista de comentarios a guardar en el bean
 * @param t- la lista de comentarios a almacenar
 */
    public void setT(ArrayList<Marcador> t) {
        this.t = t;
    }
/**
 * Método que devuelve la cadena guardada en el bean
 * @return La cadena almacenada en el bean
 */
    public String getE() {
        return e;
    }
/**
 * Método que asigna la cadena a guardar en el bean
 * @param e- la cadena a almacenar
 */
    public void setE(String e) {
        this.e = e;
    }
    
/**
 * Método constructor de la clase BuscarConTema
 * 
 */
    public MostrarMarcador() {
        FacesContext.getCurrentInstance()
                .getViewRoot()
                .setLocale(new Locale("es-Mx"));
    }
    /**
 * Método que  nos ayuda a mostrar un marcador
 * @return el redireccionamiento resultado de la busqueda
 */
    public String mostrarMarc(String tema){
        t = u.MostrarMarcadores(tema);
        return "resultado?faces-redirect=true";
    }
     /**
 * Método que  nos ayuda a mostrar un marcador
 * @return el redireccionamiento resultado de la busqueda
 */
    public String mostrarMarc1(String tema){
        t = u.MostrarMarcadores(tema);
        return "resultado1?faces-redirect=true";
    }
 }

    
