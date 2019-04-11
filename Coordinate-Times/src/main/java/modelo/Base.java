package modelo;

import org.hibernate.Session;
import controlador.UsuarioBean;
import javax.faces.context.FacesContext;
import javax.faces.application.FacesMessage;
import java.util.Locale;
/**
 * Clase que realiza la conexión de la base de datos y elimina características de la misma
 * @author Luna Menguante
 */
public class Base{

    static Session sessionObj;
    private UsuarioBean a ;
/**
 * Método que elimina a un usuario de la base de datos
 * @param usuario- el usuario a eliminar
 */
    public void eliminarU(Usuario usuario){
        try {
            sessionObj = HibernateUtil.getSessionFactory().openSession();
            sessionObj.beginTransaction();
            sessionObj.delete(usuario);
            sessionObj.getTransaction().commit();
        } catch (Exception sqlException) {
            if (null != sessionObj.getTransaction()) {
                System.out.println("\n.......Transaction Is Being Rolled Back.......");
                sessionObj.getTransaction().rollback();
            }
            sqlException.printStackTrace();
        } finally {
            if (sessionObj != null) {
                sessionObj.close();
            }
        }
    }
/**
 * Método que elimina un tema de la base de datos
 * @param tema el tema a eliminar
 */
    public void eliminarT(Tema tema){
        try {
            sessionObj = HibernateUtil.getSessionFactory().openSession();
            sessionObj.beginTransaction();
            sessionObj.delete(tema);
            sessionObj.getTransaction().commit();
        } catch (Exception sqlException) {
            if (null != sessionObj.getTransaction()) {
                System.out.println("\n.......Transaction Is Being Rolled Back.......");
                sessionObj.getTransaction().rollback();
            }
            sqlException.printStackTrace();
        } finally {
            if (sessionObj != null) {
                sessionObj.close();
            }
        }
    }
}