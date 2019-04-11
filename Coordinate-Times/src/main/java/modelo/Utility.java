package modelo;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import org.hibernate.Session;

/**
 * Clase que nos ayuda a guardar usuarios en la base de datos
 * @author Luna Menguante
 */
public class Utility {

    static Usuario userObj;
    static Session sessionObj;
/**
 * Método que guarda un usuario en la base de datos
 * @param usuario el usuario a agregar
 */
    public void save(Usuario usuario) {
        try {
            sessionObj = HibernateUtil.getSessionFactory().openSession();
            sessionObj.beginTransaction();
            sessionObj.save(usuario);
            sessionObj.getTransaction().commit();
                                    FacesContext.getCurrentInstance()
                    .addMessage(null,
                            new FacesMessage(FacesMessage.SEVERITY_INFO,
                                    "Felicidades, el registro se ha realizado correctamente", ""));
        } catch (Exception e) {
            if (null != sessionObj.getTransaction()) {
             FacesContext.getCurrentInstance()
                    .addMessage(null,
                            new FacesMessage(FacesMessage.SEVERITY_ERROR,
                                    "Ese correo ya esta registrado", ""));
                System.out.println("\n.......Transaction Is Being Rolled Back.......");
                sessionObj.getTransaction().rollback();
            }
        } finally {
            if (sessionObj != null) {
                sessionObj.close();
            }
        }
    }
}
