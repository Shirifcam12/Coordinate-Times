package controlador;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.Locale;
import java.util.Random;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import modelo.Comentario;
import modelo.HibernateUtil;
import modelo.Tema;
import modelo.Usuario;
import modelo.UtilityC;
import org.hibernate.Query;
/**
 * Bean manejado para Mostrar comentarios 
 * @author Luna Menguante
 */
@ManagedBean
@SessionScoped
public class comentarioC {
    private ArrayList<Comentario> c;
    private ArrayList<Usuario> comentaristas;
    private UtilityC u = new UtilityC();
    private Comentario comentario = new Comentario();
    private UsuarioBean ub = new UsuarioBean();
    private String comentariot;
      /**
 * Método que devuelve la lista de comentarios guardados en el bean
 * @return La lista de comentarios almacenada en el bean
 */
    public ArrayList<Comentario> getC() {
        System.out.println(c);
        return c;
    }
/**
 * Método que asigna la lista de comentarios a guardar en el bean
 * @param t- la lista de comentarios a almacenar
 */
    public  void setT(ArrayList<Comentario> t) {
        c = t;
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

    public Comentario getComentario() {
        return comentario;
    }

    public void setComentario(Comentario comentario) {
        this.comentario = comentario;
    }

    public String getComentariot() {
        return comentariot;
    }

    public void setComentariot(String comentariot) {
        this.comentariot = comentariot;
    }


    
/**
 * Método constructor de la clase Mostrar comentarios
 * 
 */
    public comentarioC() {
        FacesContext.getCurrentInstance()
                .getViewRoot()
                .setLocale(new Locale("es-Mx"));
    }
/**
 * Método que  nos ayuda a mostrar un comentario
 * @return el redireccionamiento resultado de la busqueda
 */
   public ArrayList<Comentario> mostrarComentarios(int idm) {
    Query query = HibernateUtil.getCurrentSession().createQuery("FROM Comentario c WHERE c.idMarcador = :idm");
    query.setParameter("idm", idm);
     c = (ArrayList<Comentario>) query.list();
     System.out.println(c);
    return c;     
   }
   
    public String agregaComentario(int idm){
          if(!(ub.esComentarista())){
                 FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_ERROR, "No dispones de permisos ", ""));        
                }else{
          FacesContext context = FacesContext.getCurrentInstance();
          Usuario a = (Usuario) context.getExternalContext().getSessionMap().get("usuario");
          comentario.setIdUsuario(a.getId());
          comentario.setIdTema(u.mostrarMarcador(idm).get(0).getIdTema());
          Date date = new Date();
          DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
          String fecha = ""+dateFormat.format(date); 
          comentario.setFecha(fecha);
          comentario.setIdMarcador(idm);
          comentario.setComentario(comentariot);
          comentario.setGusta(0);
          comentario.setNogusta(0);
          comentario.setTotal(0);
          u.guardaComentario(comentario);
          comentariot = null;
		}
        return "resultado?faces-redirect=true";
	}
        public String eliminaComentario(Comentario com){
          if(!(ub.esComentarista())){
                 FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_ERROR, "No dispones de permisos ", ""));        
                }else{
              		FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_INFO, "Se ha eliminado correctamenten el Comentario", ""));
                  u.eliminarC(com);
		}
        return "resultado?faces-redirect=true";
	}
    public boolean suComentario(int id){
        int w = id;
        FacesContext context = FacesContext.getCurrentInstance();
        Usuario a = (Usuario) context.getExternalContext().getSessionMap().get("usuario");
        if(a==null) return false;
        if(a.getId() == w){
            return true;
        }
        return false;
    }
    
  public ArrayList<Usuario> obtenerComentaristas(){ 
    Query query = HibernateUtil.getCurrentSession().createQuery("FROM Usuario u WHERE u.tipo = '1'");
    comentaristas = (ArrayList<Usuario>) query.list();
    System.out.println(comentaristas);
    return comentaristas;
  }  
  
}


