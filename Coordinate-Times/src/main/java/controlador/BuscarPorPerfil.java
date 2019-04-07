package controlador;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Locale;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import modelo.Usuario;
import modelo.UtilityT;

@ManagedBean
@SessionScoped
public class BuscarPorPerfil {
    private String correo;
    static ArrayList<Usuario> us = new ArrayList<Usuario>();
    private UtilityT u = new UtilityT();

    public static ArrayList<Usuario> getUs() {
        return us;
    }

    public void setUs(ArrayList<Usuario> us) {
        this.us = us;
    }

    public UtilityT getU() {
        return u;
    }

    public void setU(UtilityT u) {
        this.u = u;
    }
    
    
    
    public String getCorreo(){
        return correo;
    }
    public void setCorreo(String correo){
        this.correo = correo;
    }

    public BuscarPorPerfil() {
        FacesContext.getCurrentInstance()
                .getViewRoot()
                .setLocale(new Locale("es-Mx"));
    }

    public String buscarPerfil() {
        
        us= u.buscarPerfil(correo);
        if(correo == "" ){
            return "";
        }
        if(us.isEmpty()){
        return "noresultado?faces-redirect=true";
        }else{
        return "resultadop?faces-redirect=true";
        }
        
    }
}