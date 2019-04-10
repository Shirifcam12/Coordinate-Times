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

@ManagedBean
@SessionScoped
public class MostrarComentarios {
    static ArrayList<Comentario> c;
    private UtilityC u = new UtilityC();

    public static ArrayList<Comentario> getC() {
        return c;
    }

    public static void setT(ArrayList<Comentario> t) {
        MostrarComentarios.c = t;
    }

    public UtilityC getU() {
        return u;
    }

    public void setU(UtilityC u) {
        this.u = u;
    }

    

    public MostrarComentarios() {
        FacesContext.getCurrentInstance()
                .getViewRoot()
                .setLocale(new Locale("es-Mx"));
    }

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


