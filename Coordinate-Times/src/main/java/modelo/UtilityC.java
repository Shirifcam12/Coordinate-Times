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
 * Clase que nos ayuda a mostrar los comentarios 
 * @author Luna Menguante
 */
public class UtilityC {
    static Marcador marcadorObj;
    static Session sessionObj;
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
}
