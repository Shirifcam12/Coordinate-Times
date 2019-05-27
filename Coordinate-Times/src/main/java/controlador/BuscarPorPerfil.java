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
/**
 * Bean manejado para el caso de uso buscar perfil
 * @author Luna Menguante
 */
@ManagedBean
@SessionScoped
public class BuscarPorPerfil {
    private String correo;
    static ArrayList<Usuario> us = new ArrayList<Usuario>();
    private UtilityT u = new UtilityT();
/**
 * Método que devuelve la lista de usuarios guardados en el bean
 * @return La lista de usuarios almacenada en el bean
 */
    public static ArrayList<Usuario> getUs() {
        return us;
    }
/**
 * Método que asigna la lista de usuarios a guardar en el bean
 * @param us- la lista de usuarios a almacenar
 */
    public void setUs(ArrayList<Usuario> us) {
        this.us = us;
    }
/**
 * Método que devuelve la instancia de UtilityT guardada en el bean
 * @return La instancia de UtilityT almacenada en el bean
 */
    public UtilityT getU() {
        return u;
    }
/**
 * Método que asigna la instancia de UtilityT a guardar en el bean
 * @param u- La instancia de UtilityT a almacenar en el bean
 */
    public void setU(UtilityT u) {
        this.u = u;
    }
    
    
    /**
 * Método que devuelve el correo guardado en el bean
 * @return El correo almacenado en el bean
 */
    public String getCorreo(){
        return correo;
    }
    /**
 * Método que asigna el correo a guardar en el bean
 * @param correo- el correo a almacenar
 */
    public void setCorreo(String correo){
        this.correo = correo;
    }
/**
 * Método constructor de la clase BuscarConPerfil
 * 
 */
    public BuscarPorPerfil() {
        FacesContext.getCurrentInstance()
                .getViewRoot()
                .setLocale(new Locale("es-Mx"));
    }
/**
 * Método que  cubre el caso de uso Buscar Perfil
 * @return el redireccionamiento resultado de la busqueda
 */
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
        return "perfilb?faces-redirect=true";
        }
        
    }
}