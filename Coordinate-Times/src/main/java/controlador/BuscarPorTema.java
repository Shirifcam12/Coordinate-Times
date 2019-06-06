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
    
/**
 * Método estatico que asgina una nueva arraylist de temas en el bean
 * @param t -- nueva arraylist de temas
 * @return null
 */
    public static ArrayList<Tema> setT(ArrayList<Tema> t){
        BuscarPorTema.t = t;
        return null;
    }
/**
 * Metodo statico que obtiene el nombre del tema que se busco para utilizarlo en otras clases
 * @return nombreg -- el nombre del tema que fue buscado
 */
    public static String getNombreg(){
        return nombreg;
    }
/**
 * Metodo que obtiene un entero a, que sirve para hacer validaciones
 * @return a -- el entero para hacer validaciones
 */
    public int getA() {
        return a;
    }
    
/**
 * Metodo que asigna un entero nuevo, al entero que hace las validaciones en el bean
 * @param a  -- el nuevo entero
 */
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
 * Constructor por Omision de BuscarPorTema
 * 
 */
    public BuscarPorTema() {
        
        FacesContext.getCurrentInstance()
                .getViewRoot()
                .setLocale(new Locale("es-Mx"));
    }
    
/**
 * Método que  cubre el caso de uso Buscar Tema
 * @return el redireccionamiento con resultado de la busqueda
 */
    public String buscarTema() {
        a=0;
        nombreg = nombre;
        t = u.buscarTema(nombre.toUpperCase());
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
    
    /**
     * Metodo que cancela la busqueda que fue realizada
     * @return el redireccionamiento hacia la pagina principal
     */
    public String seCancelo() { 
        a=0;
        MarcadoresController.a = false;
       return "principal?faces-redirect=true";
    }
    
    /**
     * Metodo statico que cancela la busqueda que fue realizada
     * @return el redireccionamiento hacia la pagina principal
     */
    public static String seCancelo1(){
        a=0;
        MarcadoresController.a = false;
        return "principal?faces-redirect=true";
    }
    
    /**
     * Metodo que verifica que si se encontro algo al momento de realizar la busqueda
     * @return true -- si se encontro, false -- en  otro caso
     */
    public boolean hayTema(){
        if(t.isEmpty()){
            return false;
        }
        return true;
    }
    
    /**
     * Metodo que verifica que si se realizo alguna busqueda
     * @return false -- si no se realizó, true -- en caso contrario
     */
    public boolean seBusco(){
        if(a==0){
            return false;
        }
        return true;
    }
}



