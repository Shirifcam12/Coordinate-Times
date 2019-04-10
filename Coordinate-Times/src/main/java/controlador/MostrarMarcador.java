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
 *
 * @author ricardo
 */
@ManagedBean
@SessionScoped
public class MostrarMarcador {
    private String nombre;
    private Marcador user = new Marcador();
    private UtilityM u = new UtilityM();
    private ArrayList<Marcador> t;
    private String e;

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Marcador getUser() {
        return user;
    }

    public void setUser(Marcador user) {
        this.user = user;
    }

    public UtilityM getU() {
        return u;
    }

    public void setU(UtilityM u) {
        this.u = u;
    }

    public ArrayList<Marcador> getT() {
        return t;
    }

    public void setT(ArrayList<Marcador> t) {
        this.t = t;
    }

    public String getE() {
        return e;
    }

    public void setE(String e) {
        this.e = e;
    }
    

    public MostrarMarcador() {
        FacesContext.getCurrentInstance()
                .getViewRoot()
                .setLocale(new Locale("es-Mx"));
    }

    public String mostrarMarc(String tema){
        t = u.MostrarMarcadores(tema);
        return "resultado?faces-redirect=true";
    }
    public String mostrarMarc1(String tema){
        t = u.MostrarMarcadores(tema);
        return "resultado1?faces-redirect=true";
    }
 }

    
