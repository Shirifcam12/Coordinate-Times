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
 * Clase que nos ayuda a buscar temas y a buscar perfiles
 * @author Luna Menguante
 */
public class UtilityT {
    static Tema temaObj;
    static Usuario userObj;
    static Session sessionObj;
    private Usuario us = new Usuario();
    private Tema temaoc = new Tema();
    /**
     * Método que busca un tema en la base de datos dado el nombre del mismo
     * @param tema- el nombre del tema a buscar 
     * @return Un Array list que contiene los temas asociados al nombre dado
     */
	public ArrayList<Tema> buscarTema(String tema){ 
            Query query = HibernateUtil.getCurrentSession().createQuery("FROM Tema c WHERE c.nombre = :tema");
            query.setParameter("tema", tema);
            ArrayList<Tema> temas = (ArrayList<Tema>) query.list();
            return temas;
	}
        /**
         * Método que busca usuarios dado un correo
         * @param correo- el correo con el cual vamos a buscar 
         * @return un Array list con el perfil asociado al correo dado
         */
	public ArrayList<Usuario> buscarPerfil(String correo){ 
	    Query query = HibernateUtil.getCurrentSession().createQuery("FROM Usuario c WHERE c.correo = :correo");
            query.setParameter("correo", correo);
            ArrayList<Usuario> usuarios = (ArrayList<Usuario>) query.list();
            return usuarios;
        }
	
}	    
