package controlador;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Locale;
import java.util.Map;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.SessionScoped;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import modelo.Tema;
import modelo.UtilityT;
import org.primefaces.PrimeFaces;
import org.primefaces.event.SelectEvent;
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
    static int a;
    static String nombreg;
    /**
 * Método que devuelve la lista de temas guardados en el bean
 * @return La lista de usuarios almacenada en el bean
 */
    public static ArrayList<Tema> getT() {
        return t;
    }
    
    public static ArrayList<Tema> setT(ArrayList<Tema> t){
        BuscarPorTema.t = t;
        return null;
    }
    
    public static String getNombreg(){
        return nombreg;
    }

    public int getA() {
        return a;
    }

    public void setA(int a) {
        this.a = a;
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
        a=0;
        nombreg = nombre;
        t = u.buscarTema(nombre);
        if(nombre == "" ){
            return "principal?faces-redirect=true";
        }
        if(t.isEmpty()){
            a=1;
        FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_ERROR, "No se encontro el tema", ""));
        return "principal?faces-redirect=true";
        }else{
            a=1;
        return "principal?faces-redirect=true";
        }
        
    }
    public String seCancelo() { 
        a=0;
       return "principal?faces-redirect=true";
    }
    
    public static String seCancelo1(){
        a=0;
        return "principal?faces-redirect=true";
    }
    
    public boolean hayTema(){
        if(t.isEmpty()){
            return false;
        }
        return true;
    }
    
    public boolean seBusco(){
        if(a==0){
            return false;
        }
        return true;
    }
}



