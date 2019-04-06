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
    static Tema temaObj;
    static Usuario userObj;
    static Session sessionObj;
 	private Usuario us = new Usuario();
    private Tema temaoc = new Tema();
    
	public Tema buscarTema(String tema){ 
		Query query = HibernateUtil.getCurrentSession().createQuery("FROM Tema c WHERE c.nombre = :tema");
		query.setParameter("tema", tema);
		if(!query.list().isEmpty()){
            temaoc=(Tema)query.list().get(0);
        }
		return temaoc;
	}
	public Usuario buscarPerfil(String correo){ 
		Query query = HibernateUtil.getCurrentSession().createQuery("FROM Usuario c WHERE c.correo = :correo");
		query.setParameter("correo", correo);
		if(!query.list().isEmpty()){
            us=(Usuario)query.list().get(0);
        }
		return us;
	}
}	    
