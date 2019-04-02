package com.charlie.proyecto.web;

import org.hibernate.Session;

public class Base{

    static User userObj;
    static Session sessionObj;

	public boolean eliminar(Usuario user){
		try {
            sessionObj = HibernateUtil.getSessionFactory().openSession();
            sessionObj.beginTransaction();
            sessionObj.delete(usuario);
            sessionObj.getTransaction().commit();
        } catch (Exception sqlException) {
            if (null != sessionObj.getTransaction()) {
                System.out.println("\n.......Transaction Is Being Rolled Back.......");
                sessionObj.getTransaction().rollback();
                return False;
            }
            sqlException.printStackTrace();
        } finally {
            if (sessionObj != null) {
                sessionObj.close();
            }
        }
        return True;
	}
}