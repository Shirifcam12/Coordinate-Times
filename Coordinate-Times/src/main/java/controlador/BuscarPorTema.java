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
/**
 * Bean manejado para el caso de uso buscar tema
 * @author Luna Menguante
 */
@ManagedBean
@SessionScoped
public class BuscarPorTema {
    private String nombre;
    static ArrayList<Tema> t = new ArrayList<Tema>();
    private UtilityT u = new UtilityT();
    /**
 * Método que devuelve la lista de temas guardados en el bean
 * @return La lista de usuarios almacenada en el bean
 */
    public static ArrayList<Tema> getT() {
        return t;
    }
/**
 * Método que asigna la lista de temas a guardar en el bean
 * @param us- la lista de temas a almacenar
 */
    public void setT(ArrayList<Tema> t) {
        this.t = t;
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
 * Método que devuelve el nombre guardado en el bean
 * @return El nombre almacenado en el bean
 */
    public String getNombre(){
        return nombre;
    }
/**
 * Método que asigna el nombre a guardar en el bean
 * @param nombre- El nombre a almacenar en el bean
 */    
    public void setNombre(String nombre){
        this.nombre = nombre;
    }
/**
 * Método constructor de la clase BuscarConTema
 * 
 */
    public BuscarPorTema() {
        FacesContext.getCurrentInstance()
                .getViewRoot()
                .setLocale(new Locale("es-Mx"));
    }
/**
 * Método que  cubre el caso de uso Buscar Tema
 * @return el redireccionamiento resultado de la busqueda
 */
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
/**
 * Método que  cubre el caso de uso Buscar Tema
 * @return el redireccionamiento resultado de la busqueda
 */
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


