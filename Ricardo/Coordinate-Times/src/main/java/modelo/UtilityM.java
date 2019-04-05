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
public class UtilityM {
     static Tema temaObj;
     static Session sessionObj;
    
public ArrayList<Marcador> MostrarMarcadores(String tema){ 
    Query query = HibernateUtil.getCurrentSession().createQuery("FROM Marcador m WHERE m.idTema = (SELECT idT FROM Tema c WHERE c.nombre = :tema)");
    query.setParameter("tema", tema);
    ArrayList<Marcador> marcadores = (ArrayList<Marcador>) query.list();
    return marcadores;
}

}
