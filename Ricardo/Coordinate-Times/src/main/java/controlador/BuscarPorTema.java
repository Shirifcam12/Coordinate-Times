import java.util.ArrayList;
import java.util.Iterator;
import java.util.Locale;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;

import modelo.Tema;
import modelo.UtilityT;

@ManagedBean
@RequestScoped
public class BuscarPorTema {
    private String nombre;
    private Tema user = new Tema();
    private UtilityT u = new UtilityT();
    private ArrayList<Tema> t = new ArrayList<Tema>();
    private String e;
    
    public String getE(){
        return e;
    }
    public void setE(String e){
        this.e = e;
    }
    
    public Tema getTema() {
        return user;
    }

    public void setTema(Tema user) {
        this.user = user;
    }
    
    public ArrayList<Tema> getT(){
        return t;
    }
    public void setT(ArrayList<Tema> t){
        this.t = t;
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
        if(t.size() == 0){
        return "noresultado?faces-redirect=true";
        }else{
        return "resultado?faces-redirect=true";
        }
        
    }
    public void imprimir(){
        e = "";
        Iterator<Tema> iterador = t.iterator();
        while(iterador.hasNext()){
            e += iterador.next().getnombreT() + "\n";
        }
    }
}


