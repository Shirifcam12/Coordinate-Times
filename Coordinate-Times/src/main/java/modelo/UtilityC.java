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
 * Clase que nos ayuda a mostrar los comentarios 
 * @author Luna Menguante
 */
public class UtilityC {
    static Marcador marcadorObj;
    static Session session;
    /**
     * Método que nos ayuda a obtener los comentarios almacenados en la base de datos dado un id
     * @param id- el id que nos ayuda a buscar en la base
     * @return un Arraylist con los comentarios de la base dado el id 
     */
public ArrayList<Comentario> mostrarComentarios(int id){ 
    Query query = HibernateUtil.getCurrentSession().createQuery("FROM Comentario c WHERE c.idMarcador = :id");
    query.setParameter("id", id);
    ArrayList<Comentario> comentarios = (ArrayList<Comentario>) query.list();
    return comentarios;
} 
public ArrayList<Marcador> mostrarMarcador(int id){ 
    Query query = HibernateUtil.getCurrentSession().createQuery("FROM Marcador c WHERE c.idMarcador = :id");
    query.setParameter("id", id);
    ArrayList<Marcador> comentarios = (ArrayList<Marcador>) query.list();
    return comentarios;
}  
    public void guardaComentario(Comentario m) {
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            session.save(m);
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
        public void eliminarC(Comentario comentario){
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            session.delete(comentario);
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
    public void actualizaComentario(Comentario comentario) {
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            session.update(comentario);
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
}
