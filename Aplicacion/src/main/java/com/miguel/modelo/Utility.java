/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.miguel.modelo;

import org.hibernate.Session;
import java.util.ArrayList;
import java.util.List;
/**
 *
 * @author miguel
 */
public class Utility {

    static User userObj;
    static Session sessionObj;

    public void save(User usuario) {
        try {
            sessionObj = HibernateUtil.getSessionFactory().openSession();
            sessionObj.beginTransaction();
            sessionObj.save(usuario);
            sessionObj.getTransaction().commit();
        } catch (Exception sqlException) {
            if (null != sessionObj.getTransaction()) {
                System.out.println("\n.......Transaction Is Being Rolled Back.......");
                sessionObj.getTransaction().rollback();
                
            }
            sqlException.printStackTrace();
        } finally {
            if (sessionObj != null) {
                sessionObj.close();
            }
        }
    }
    public void find(User usuario){
        try {
            sessionObj = HibernateUtil.getSessionFactory().openSession();
            sessionObj.beginTransaction();
            sessionObj.getTransaction().commit();
        } catch (Exception sqlException)  {
            if (null != sessionObj.getTransaction()) {
                System.out.println("\n.......Transaction Is Being Rolled Back.......");
                sessionObj.getTransaction().rollback();
                
            }
        }
        
        ArrayList<Object[]> usuarios=new ArrayList(sessionObj.createQuery("SELECT * FROM Base.usuario").list());
        for(int i=0;i<usuarios.size();i++){
            Object[] j=usuarios.get(i);
        if(((String)j[0]).equals(usuario.getNombre()))
            if(((String)j[3]).equals(usuario.getCorreo()))
                if(((String)j[2]).equals(usuario.getContrasena())){
                    System.out.println("a");
                    break;
                }
               
        }
        System.out.println("a");     
        }
        
}


