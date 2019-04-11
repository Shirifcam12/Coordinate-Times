package modelo;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

/**
 * Hibernate Utility class with a convenient method to get Session Factory
 * object.
 *
 * @author Luna Menguante
 */
public class HibernateUtil {

    private static final SessionFactory sessionFactory;
    private static Session session;

    static {
        try {
            // Create the SessionFactory from standard (hibernate.cfg.xml)
            // config file.
            sessionFactory = new Configuration().configure("hibernate.cfg.xml").buildSessionFactory();
        } catch (Throwable ex) {
            // Log the exception.
            System.err.println("Initial SessionFactory creation failed." + ex);
            throw new ExceptionInInitializerError(ex);
        }
    }
/**
 * Metodo que devuelve la SessionFactory dada por Hibernate
 * @return la SessionFactory dada por Hibernate
 */
    public static SessionFactory getSessionFactory() {
        return sessionFactory;
    }
     /**
   * Método que abre una nueva sesión
   */
  public static void openSession() {
    session = sessionFactory.openSession();
  }
 
  /**
   * Método que devuelve la sesión actual
   * @return la sesión actual
   */
  public static Session getCurrentSession() {
 
    if ((session == null) || (!session.isOpen()))
      openSession();
 
    return session;
  }
 
  /**
   * Método que cierra Hibernate
   */
  public static void closeSessionFactory() {
 
    if (session != null)
      session.close();
 
    if (sessionFactory != null)
      sessionFactory.close();
  }
}

