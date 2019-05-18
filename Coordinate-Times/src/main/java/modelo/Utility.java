package modelo;
import java.util.ArrayList;
import java.util.Arrays;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;

/**
 * Clase que nos ayuda a guardar usuarios en la base de datos
 * @author Luna Menguante
 */
public class Utility {

    static Usuario userObj;
    static Session session;
/**
 * Método que guarda un usuario en la base de datos
 * @param usuario el usuario a agregar
 */

    public Usuario obtenUsuario(String nombre, String password) {
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            Query query = session.getNamedQuery("Usuario.findByNombrePassword");
            query.setParameter("nombre", nombre).setParameter("password", password);
            Usuario usuario = (Usuario) query.uniqueResult();
            return usuario;
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
    }

    public void guardaUsuario(Usuario usuario) {
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            // Le asignamos el rol de usuario
            usuario.setTipo(1);
            session.save(usuario);
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
    public void actualizaUsuario(Usuario usuario) {
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            session.update(usuario);
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

    public Usuario obtenUsuario(String hash) {
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            Query query = session.getNamedQuery("Usuario.findByHash");
            query.setParameter("hash", hash);
            Usuario usuario = (Usuario) query.uniqueResult();
            return usuario;
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
    }
}
