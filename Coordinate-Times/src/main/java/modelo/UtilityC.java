/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;
import java.util.ArrayList;
import org.hibernate.Query;
import org.hibernate.Session;


/**
 *
 * @author ricardo
 */
public class UtilityC {
    static Marcador marcadorObj;
    static Session sessionObj;
public ArrayList<Comentario> mostrarComentarios(int id){ 
    Query query = HibernateUtil.getCurrentSession().createQuery("FROM Comentario c WHERE c.idMarcador = :id");
    query.setParameter("id", id);
    ArrayList<Comentario> comentarios = (ArrayList<Comentario>) query.list();
    return comentarios;
}   
}
