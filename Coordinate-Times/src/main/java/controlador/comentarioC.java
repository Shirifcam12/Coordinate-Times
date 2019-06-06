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
 * Bean manejado para realizar todas las funciones que tengan que ver con comentarios
 * @author Luna Menguante
 */
@ManagedBean
@RequestScoped
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

    /**
     * Metodo que obtiene el comentario 
     * @return comentario -- el comentario escrito
     */
    public Comentario getComentario() {
        return comentario;
    }

    /**
     * Metodo que asigna un nuevo comentario al comentario
     * @param comentario -- el nuevo comentario
     */
    public void setComentario(Comentario comentario) {
        this.comentario = comentario;
    }

    /**
     * Metodo que obtiene el comentario escrito del comentario
     * @return comentariot -- el comentario escrito del comentario
     */
    public String getComentariot() {
        return comentariot;
    }

    /**
     * Metodo que asigna un nuevo comentario escrito al comentario
     * @param comentariot -- el nuevo comentario escrito del comentario
     */
    public void setComentariot(String comentariot) {
        this.comentariot = comentariot;
    }


    
/**
 * Constructor por Omision del comentarioC.
 * 
 */
    public comentarioC() {
        FacesContext.getCurrentInstance()
                .getViewRoot()
                .setLocale(new Locale("es-Mx"));
    }
/**
 * Método que  nos ayuda a mostrar todos los comentarios de un marcador
 * @return c -- todos los comentarios del marcador
 */
   public ArrayList<Comentario> mostrarComentarios(int idm) {
    Query query = HibernateUtil.getCurrentSession().createQuery("FROM Comentario c WHERE c.idMarcador = :idm ORDER BY c.total DESC");
    query.setParameter("idm", idm);
     c = (ArrayList<Comentario>) query.list();
     System.out.println(c);
    return c;     
   }
   
   /**
    * Metodo que agrega un comentario dado el identificador del marcador
    * @param idm -- identificador del marcador
    * @return al redireccionamiento a la pagina que contiene a los comentarios
    */
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
        FacesContext.getCurrentInstance().addMessage(null,
            new FacesMessage(FacesMessage.SEVERITY_INFO, "Se ha eliminado correctamenten el Comentario", ""));
             u.eliminarC(com);		
        return "resultado?faces-redirect=true";
	}
        
    /**
     * Metodo que verifica si el comentario echo es de del usuario conectado actualmente
     * @param id -- el identicador del usuario del comentario
     * @return true -- si es su comentario , false -- en caso contrario
     */
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
  
  /**
   * Metodo que obtiene la lista de todos los que son comentaristas en la base de datos
   * @return comentaristas -- todos los usuarios que son comentaristas
   */
  public ArrayList<Usuario> obtenerComentaristas(){ 
    Query query = HibernateUtil.getCurrentSession().createQuery("FROM Usuario u WHERE u.tipo = '1'");
    comentaristas = (ArrayList<Usuario>) query.list();
    System.out.println(comentaristas);
    return comentaristas;
  }  
  
}


