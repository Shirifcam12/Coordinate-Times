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
public class UtilityU {
    static  Usuario usObj;
    static Session sessionObj;
    
public Usuario verificaDatos(Usuario usuario)throws Exception   { 
    Usuario us=null;
    try{
        sessionObj=HibernateUtil.getCurrentSession();
        String pquery="FROM Usuario WHERE correo= '"+usuario.getCorreo()+"'"
                + "and contraseña= '"+usuario.getContrasena()+"'";
        Query query=sessionObj.createQuery(pquery);
        if(!query.list().isEmpty()){
            us=(Usuario)query.list().get(0);
        }
    }catch(Exception e){
        System.out.println(e);
    }
    return us;
    
}

}
