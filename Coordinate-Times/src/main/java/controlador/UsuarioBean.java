/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Locale;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import modelo.Usuario;
import modelo.UtilityU;


/**
 *
 * @author ricardo
 */
@ManagedBean
@SessionScoped
public class UsuarioBean {
    private Usuario usuario=new Usuario();
    static Usuario usuarioelimina = new Usuario();
    static Usuario usuario1;

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public String verificaDatos() throws Exception{
        
        UtilityU usUT=new UtilityU();
        String resultado;
        try{
        usuario=usUT.verificaDatos(this.usuario);
        if(usuario!=null){
            usuario1 = usuario;
            usuarioelimina = usuario;
            FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("usuario",usuario);
            resultado="exito";
        }else{
            resultado="error";
        }
        }catch(Exception e){
            throw e;
        }
        return resultado;
    }

    public static Usuario getUsuarioelimina() {
        return usuarioelimina;
    }

    public void setUsuarioelimina(Usuario usuarioelimina) {
        this.usuarioelimina = usuarioelimina;
    }

    public static Usuario getUsuario1() {
        return usuario1;
    }

    public static void setUsuario1(Usuario usuario1) {
        UsuarioBean.usuario1 = usuario1;
    }
    public boolean verificarSesion(){
        boolean estado;
        if(FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("usuario")==null){
            estado=false;
        }else{
            estado=true;
        }
        return estado;  
    }
    
public String cerrarSesion(){
    
        FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
        return "inicio-sesion?faces-redirect=true";
}
}    

