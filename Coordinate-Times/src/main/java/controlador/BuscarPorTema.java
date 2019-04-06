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
    private Tema user = new Tema();
    private UtilityT u = new UtilityT();
    
    public Tema getTema() {
        return user;
    }

    public void setTema(Tema user) {
        this.user = user;
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
        
        user = u.buscarTema(nombre);
        System.out.println(user);
        if(nombre == "" ){
            return "";
        }
        if(user == null){
        return "noresultado?faces-redirect=true";
        }else{
        return "resultado?faces-redirect=true";
        }
        
    }
}


