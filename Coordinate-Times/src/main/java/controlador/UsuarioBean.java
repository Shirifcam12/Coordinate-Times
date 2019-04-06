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
    private Usuario usuarioelimina = new Usuario();

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }
    public Usuario getUsuarioElimina() {
        return usuarioelimina;
    }

    public void setUsuarioElimina(Usuario usuarioelimina) {
        this.usuarioelimina = usuarioelimina;
    }

    public String verificaDatos() throws Exception{
        
        UtilityU usUT=new UtilityU();
        Usuario usuario;
        String resultado;
        try{
        usuario=usUT.verificaDatos(this.usuario);
        if(usuario!=null){
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

