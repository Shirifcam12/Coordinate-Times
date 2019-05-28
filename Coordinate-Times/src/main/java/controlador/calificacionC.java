package controlador;
import modelo.UtilityCa;
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
import modelo.Calificacion;
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
@RequestScoped
public class calificacionC {
    private UtilityCa u = new UtilityCa();
    private UtilityC um = new UtilityC();
    private Calificacion calificacion = new Calificacion();
    private UsuarioBean ub = new UsuarioBean();


    public calificacionC() {
        FacesContext.getCurrentInstance()
                .getViewRoot()
                .setLocale(new Locale("es-Mx"));
    }
    
    public String calificaComentarios(Comentario comentario,Boolean b){
        Usuario usuario = ub.elUsuario();
        if(u.mostrarCalificaciones(comentario.getIdComentario(),usuario.getId()).isEmpty()){
            if(b){
                calificacion.setIdUsuario(usuario.getId());
                calificacion.setIdComentario(comentario.getIdComentario());
                calificacion.setEleccion(b);
                u.guardaCalificacion(calificacion);
                comentario.setGusta(comentario.getGusta()+1);
                comentario.setTotal(comentario.getTotal());
                um.actualizaComentario(comentario);
                return "resultado?faces-redirect=true";
            }else{
                calificacion.setIdUsuario(usuario.getId());
                calificacion.setIdComentario(comentario.getIdComentario());
                calificacion.setEleccion(b);
                u.guardaCalificacion(calificacion);
                comentario.setNogusta(comentario.getNogusta()+1);
                comentario.setTotal(comentario.getTotal());
                um.actualizaComentario(comentario);
                return "resultado?faces-redirect=true";
            }
        }else{
            calificacion = u.mostrarCalificaciones(comentario.getIdComentario(),usuario.getId()).get(0);
          if(b == false && calificacion.isEleccion()== true){
              calificacion.setEleccion(b);
              u.actualizaCalificacion(calificacion);
              comentario.setGusta(comentario.getGusta()-1);
              comentario.setNogusta(comentario.getNogusta()+1);
              comentario.setTotal(comentario.getTotal());
              um.actualizaComentario(comentario);
              return "resultado?faces-redirect=true";
          }
          if(b == true && calificacion.isEleccion()== false){
              calificacion.setEleccion(b);
              u.actualizaCalificacion(calificacion);
              comentario.setGusta(comentario.getGusta()+1);
              comentario.setNogusta(comentario.getNogusta()-1);
              comentario.setTotal(comentario.getTotal());
              um.actualizaComentario(comentario);
              return "resultado?faces-redirect=true";
          }
          if(b == true && calificacion.isEleccion() == true){
              u.eliminaCalificacion(calificacion);
              comentario.setGusta(comentario.getGusta()- 1);
              comentario.setTotal(comentario.getTotal());
              um.actualizaComentario(comentario);
              return "resultado?faces-redirect=true";
          }
          if(b == false && calificacion.isEleccion() == false){
              u.eliminaCalificacion(calificacion);
              comentario.setNogusta(comentario.getNogusta()- 1);
              comentario.setTotal(comentario.getTotal());
              um.actualizaComentario(comentario);
              return "resultado?faces-redirect=true";
          }
        }
     return "resultado?faces-redirect=true";
    }   
}
