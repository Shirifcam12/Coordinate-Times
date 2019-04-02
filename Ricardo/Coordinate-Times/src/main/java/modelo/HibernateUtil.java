package modelo;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

/**
 * Hibernate Utility class with a convenient method to get Session Factory
 * object.
 *
 * @author ricardo
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

    public static SessionFactory getSessionFactory() {
        return sessionFactory;
    }
     /**
   * Abre una nueva sesión
   */
  public static void openSession() {
    session = sessionFactory.openSession();
  }
 
  /**
   * Devuelve la sesión actual
   * @return
   */
  public static Session getCurrentSession() {
 
    if ((session == null) || (!session.isOpen()))
      openSession();
 
    return session;
  }
 
  /**
   * Cierra Hibernate
   */
  public static void closeSessionFactory() {
 
    if (session != null)
      session.close();
 
    if (sessionFactory != null)
      sessionFactory.close();
  }
}

