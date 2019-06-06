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
 * Método que obtiene un usuario de la base de datos
 * @param correo -- el correo del usuario
 * @param password -- la contraseña del usuario
 */

    public Usuario obtenUsuario(String correo, String password) {
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            Query query = session.getNamedQuery("Usuario.findByCorreoPassword");
            query.setParameter("correo", correo).setParameter("contrasena", password);
            Usuario usuario = (Usuario) query.uniqueResult();
            return usuario;
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
    }

    /**
     * Metodo que guarda un usuario en la base de datos
     * @param usuario -- el usuario que vamos a guardar
     */
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
    /**
     * Metodo que actualiza un usuario en la base de datos
     * @param usuario -- el usuario que vamos a actualizar
     */
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

    /**
     * Metodo que obtiene un usuario dado el hash el cual fue guardado
     * @param hash -- el hash del usuario
     * @return usuario -- el usuario que tiene el hash
     */
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
