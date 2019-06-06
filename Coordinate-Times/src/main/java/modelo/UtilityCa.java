/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;
import java.util.ArrayList;
import static modelo.UtilityM.session;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;


/**
 * Clase que nos ayuda a las operaciones de la base de datos con las Calificaciones
 * @author Luna Menguante
 */
public class UtilityCa {
    static Session session;

    /**
     * Metodo que obtiene la session
     * @return session
     */
    public static Session getSession() {
        return session;
    }

    /**
     * Metodo que asigna una nueva session
     * @param session -- la nueva session
     */
    public static void setSession(Session session) {
        UtilityCa.session = session;
    }

    /**
     * Metodo que actualiza una calificacion
     * @param ca -- la calificacion que vamos a actualizar
     */
    public void actualizaCalificacion(Calificacion ca) {
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            session.update(ca);
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
     * Metodo que guarda una calificacion en la base de datos
     * @param ca -- calificacion
     */
    public void guardaCalificacion(Calificacion ca) {
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            session.save(ca);
            session.getTransaction().commit();
        } catch (HibernateException e) {
            if (null != session.getTransaction()) {
                System.out.println("\n.......Transaction (Insert marker) Is Being Rolled Back.......");
                session.getTransaction().rollback();
            }
            e.printStackTrace();
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
    }
    
    /**
     * Metodo que elimina una calificacion de la base de datos
     * @param ca -- calificacion a eliminar
     */
    public void eliminaCalificacion(Calificacion ca) {
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            session.delete(ca);
            session.getTransaction().commit();
        } catch (HibernateException e) {
            if (null != session.getTransaction()) {
                System.out.println("\n.......Transaction (Insert marker) Is Being Rolled Back.......");
                session.getTransaction().rollback();
            }
            e.printStackTrace();
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
    }
    
    /**
     * Metodo que muestra las calificaciones dado un identificador del comentario, y del identificador del Usuario
     * @param idc -- identificador del comentario
     * @param idu -- identificador del usuario
     * @return calificaciones -- las calificaciones 
     */
    public ArrayList<Calificacion> mostrarCalificaciones(int idc,int idu){ 
        Query query = HibernateUtil.getCurrentSession().createQuery("FROM Calificacion c WHERE c.idComentario = :idc AND c.idUsuario = :idu");
        query.setParameter("idc", idc);
        query.setParameter("idu",idu);
        ArrayList<Calificacion> calificaciones = (ArrayList<Calificacion>) query.list();
        System.out.println(calificaciones);
    return calificaciones;
}   
}
