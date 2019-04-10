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
        System.out.println(us);
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
                FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_ERROR, "No se encontro el Perfil", ""));
        return null;
        }else{
        return "perfil?faces-redirect=true";
        }
        
    }
    public String buscarPerfil1() {
        
        us= u.buscarPerfil(correo);
        if(correo == "" ){
            return "";
        }
        if(us.isEmpty()){
                FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_ERROR, "No se encontro el Perfil", ""));
        return null;
        }else{
        return "perfilb?faces-redirect=true";
        }
        
    }
}