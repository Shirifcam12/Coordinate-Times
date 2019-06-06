package modelo;

import controlador.BuscarPorTema;
import org.hibernate.Session;
import controlador.UsuarioBean;
import javax.faces.context.FacesContext;
import javax.faces.application.FacesMessage;
import java.util.Locale;
import org.hibernate.HibernateException;
/**
 * Clase que realiza la conexión de la base de datos y elimina características de la misma
 * @author Luna Menguante
 */
public class Base{

    static Session session;
    private UsuarioBean a ;
/**
 * Método que elimina a un usuario de la base de datos
 * @param usuario- el usuario a eliminar
 */
    public void eliminarU(Usuario usuario){
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            session.delete(usuario);
            session.getTransaction().commit();
        } catch (Exception sqlException) {
            if (null != session.getTransaction()) {
                System.out.println("\n.......Transaction Is Being Rolled Back.......");
                session.getTransaction().rollback();
            }
            sqlException.printStackTrace();
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }
/**
 * Método que elimina un tema de la base de datos
 * @param tema el tema a eliminar
 */
    public void eliminarT(Tema tema){
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            session.delete(tema);
            session.getTransaction().commit();
        } catch (Exception sqlException) {
            if (null != session.getTransaction()) {
                System.out.println("\n.......Transaction Is Being Rolled Back.......");
                session.getTransaction().rollback();
            }
            sqlException.printStackTrace();
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }
    
    /**
     * Metodo que agrega un tema al la base de datos
     * @param tema  -- el tema que vamos a agregar
     */
      public void agregarTema(Tema tema) {
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            System.out.println("hola");
            session.save(tema);
            session.getTransaction().commit();
        } catch (HibernateException ex) {
            if (null != session.getTransaction()) {
                System.out.println("\n.......Transaction Is Being Rolled Back.......");
                session.getTransaction().rollback();
            }
            ex.printStackTrace();
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
    }
      
      /**
       * Metodo que edita el usuario que se encuentra en la base de datos
       * @param usuario -- el usuario que vamos a editar
       */
    public void editaU(Usuario usuario){
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            session.update(usuario);
            session.getTransaction().commit();
        } catch (Exception sqlException) {
            if (null != session.getTransaction()) {
                System.out.println("\n.......Transaction Is Being Rolled Back.......");
                session.getTransaction().rollback();
            }
            sqlException.printStackTrace();
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }

    
}