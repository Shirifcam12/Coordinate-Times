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
 * Clase que nos ayuda a mostrar los marcadores
 * @author Luna Menguante
 */
public class UtilityM {
     static Tema temaObj;
     static Session sessionObj;
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

}
