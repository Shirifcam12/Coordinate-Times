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
 * Bean manejado para manejar usuarios
 * @author Luna Menguante
 */
@ManagedBean
@SessionScoped
public class UsuarioBean {
    private Usuario usuario = new Usuario();
    static Usuario usuarioelimina = new Usuario();
    static Usuario usuario1;
/**
 * Método que devuelve el usuario para el caso de uso inicio-sesión guardado en el bean
 * @return El usuario para el caso de uso inicio-sesión almacenado en el bean
 */
    public Usuario getUsuario() {
        return usuario;
    }
/**
 * Método que asigna el usuario para el caso de uso inicio-sesión guardado en el bean
 * @param usuario-el usuario para el caso de uso inicio-sesión a guardar en el bean
 */
    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }
/**
 * Método que revisa si los datos ingresados en el caso de uso iniciar sesión son correctos
 * @return null si el inicio de sesión es correcto y un mensaje de error en otro caso
 * @throws Exception Error alguna parte del inicio de sesión
 */
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
            usuario = new Usuario();
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_ERROR, "Datos Incorrectos", ""));
            resultado=null;
        }
        }catch(Exception e){
            throw e;
        }
        return resultado;
    }
/**
 * Método que devuelve el usuario para el caso de uso elimina usuario guardado en el bean
 * @return El usuario para el el caso de uso elimina usuario almacenado en el bean
 */
    public static Usuario getUsuarioelimina() {
        return usuarioelimina;
    }
/**
 * Método que asigna el usuario para el caso de uso elimina guardado en el bean
 * @param usuarioelimina-el usuario para el caso de uso eliminaa guardar en el bean
 */
    public void setUsuarioelimina(Usuario usuarioelimina) {
        this.usuarioelimina = usuarioelimina;
    }
/**
 * Método que devuelve el usuario 1 guardado en el bean
 * @return El usuario 1 almacenado en el bean
 */
    public static Usuario getUsuario1() {
        return usuario1;
    }
/**
 * Método que asigna el usuario1 guardado en el bean
 * @param usuario1-el usuario para el caso de uso eliminaa guardar en el bean
 */
    public static void setUsuario1(Usuario usuario1) {
        UsuarioBean.usuario1 = usuario1;
    }
    /**
     * Metodo que se encarga de verificar la sesión ingresada
     * @return true si la sesión es verificada false eoc
     */
    public boolean verificarSesion(){
        boolean estado;
        if(FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("usuario")==null){
            estado=false;
        }else{
            estado=true;
        }
        return estado;  
    }
    /**
     * Metodo que se encarga de hacer el cierre de sesión
     * @return el redireccionamiento resultado de la busqueda
     */
public String cerrarSesion(){
        FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
        return "principal.xhtml?faces-redirect=true";
}
}    

