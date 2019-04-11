package controlador;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Locale;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import modelo.Comentario;
import modelo.UtilityC;
/**
 * Bean manejado para Mostrar comentarios 
 * @author Luna Menguante
 */
@ManagedBean
@SessionScoped
public class MostrarComentarios {
    static ArrayList<Comentario> c;
    private UtilityC u = new UtilityC();
      /**
 * Método que devuelve la lista de comentarios guardados en el bean
 * @return La lista de comentarios almacenada en el bean
 */
    public static ArrayList<Comentario> getC() {
        return c;
    }
/**
 * Método que asigna la lista de comentarios a guardar en el bean
 * @param t- la lista de comentarios a almacenar
 */
    public static void setT(ArrayList<Comentario> t) {
        MostrarComentarios.c = t;
    }
/**
 * Método que devuelve la instancia de UtilityC guardada en el bean
 * @return La instancia de UtilityC almacenada en el bean
 */
    public UtilityC getU() {
        return u;
    }
/**
 * Método que asigna la instancia de UtilityC a guardar en el bean
 * @param u- La instancia de UtilityC a almacenar en el bean
 */
    public void setU(UtilityC u) {
        this.u = u;
    }

    
/**
 * Método constructor de la clase Mostrar comentarios
 * 
 */
    public MostrarComentarios() {
        FacesContext.getCurrentInstance()
                .getViewRoot()
                .setLocale(new Locale("es-Mx"));
    }
/**
 * Método que  nos ayuda a mostrar un comentario
 * @return el redireccionamiento resultado de la busqueda
 */
    public String mostrarC(int id) {
        c = u.mostrarComentarios(id);
        if(c.isEmpty()){
        FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_ERROR, "No hay comentarios en el marcador", ""));
        return null;
        }else{
        return "comentario?faces-redirect=true";
        }
        
    }
    /**
 * Método que  nos ayuda a mostrar un comentario
 * @return el redireccionamiento resultado de la busqueda
 */
    public String mostrarC1(int id) {
        c = u.mostrarComentarios(id);
        if(c.isEmpty()){
        FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_ERROR, "No hay comentarios en el marcador", ""));
        return null;
        }else{
        return "comentario1?faces-redirect=true";
        }
    }
}


