package controlador;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Locale;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import modelo.Tema;
import modelo.UtilityT;

@ManagedBean
@SessionScoped
public class BuscarPorTema {
    private String nombre;
    static ArrayList<Tema> t = new ArrayList<Tema>();
    private UtilityT u = new UtilityT();

    public static ArrayList<Tema> getT() {
        return t;
    }

    public void setT(ArrayList<Tema> t) {
        this.t = t;
    }

    public UtilityT getU() {
        return u;
    }

    public void setU(UtilityT u) {
        this.u = u;
    }
    
    
    
    public String getNombre(){
        return nombre;
    }
    public void setNombre(String nombre){
        this.nombre = nombre;
    }

    public BuscarPorTema() {
        FacesContext.getCurrentInstance()
                .getViewRoot()
                .setLocale(new Locale("es-Mx"));
    }

    public String buscarTema() {
        
        t = u.buscarTema(nombre);
        if(nombre == "" ){
            return "";
        }
        if(t.isEmpty()){
        FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_ERROR, "No se encontro el tema", ""));
        return null;
        }else{
        return "resultado?faces-redirect=true";
        }
        
    }
    public String buscarTema1() {
        
        t = u.buscarTema(nombre);
        if(nombre == "" ){
            return "";
        }
        if(t.isEmpty()){
        FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_ERROR, "No se encontro el tema", ""));
        return null;
        }else{
        return "resultado1?faces-redirect=true";
        }
        
    }
}


