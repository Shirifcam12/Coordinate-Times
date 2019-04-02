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
public class UtilityT {
    static Usuario userObj;
    static Session sessionObj;
    
public ArrayList<Tema> buscarTema(String tema){ 
Query query = HibernateUtil.getCurrentSession().createQuery("FROM Tema c WHERE c.nombre = :tema");
query.setParameter("tema", tema);
ArrayList<Tema> temas = (ArrayList<Tema>) query.list();
return temas;
}
    
}
