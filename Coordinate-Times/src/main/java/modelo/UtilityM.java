/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;


/**
 * Clase que nos ayuda con las operaciones de la base de datos con los Marcadores
 * @author Luna Menguante
 */
public class UtilityM implements Serializable {
     static Tema temaObj;
     static Session sessionObj;
     static Session session;
 /**
  * Método que obtiene los marcadores asociados a un tema dado
  * @param tema- el tema del cual buscamos los marcadores
  * @return un Arraylist con los marcadores asociados al tema que se busque
  */
public ArrayList<Marcador> MostrarMarcadores(String tema){ 
    Query query = HibernateUtil.getCurrentSession().createQuery("FROM Marcador m WHERE m.idTema = (SELECT idT FROM Tema c WHERE c.nombre = :tema)");
    query.setParameter("tema", tema);
    ArrayList<Marcador> marcadores = (ArrayList<Marcador>) query.list();
    return marcadores;
}
/**
 * Metodo que obtiene todos los Marcadores
 * @return la lista con todos los marcadores
 */
    public List<Marcador> obtenMarcadores() {
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            Query query = session.getNamedQuery("Marcador.findMarcadores");
            return query.list();
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
    }
    
    /**
     * Metodo que guarda un marcador en la base de datos
     * @param m -- marcador que vamos a guardar
     */
    public void guardaMarcador(Marcador m) {
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
    
    /**
     * Metodo que elimina un marcador dado un titulo de la base de datos
     * @param titulo -- titulo del marcador
     */
        public void eliminaMarcadorPorTitulo(String titulo) {
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            Query q = session.createQuery("delete from Marcador where descripcion = :descripcion").setParameter("descripcion", titulo);
            q.executeUpdate();
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
    }
    
        /**
         * Metodo que busca un marcador por un titulo
         * @param titulo -- el titulo del marcador
         * @return el marcador que tiene el titulo
         */
       public Marcador buscarMarcadorPorTitulo(String titulo) {
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            Query q = session.createQuery("from Marcador where descripcion = :descripcion").setParameter("descripcion", titulo);
            List<Marcador> lista = q.list();
            return lista.get(0);
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
    }

}
