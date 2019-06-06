/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Locale;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import modelo.Usuario;
import modelo.Utility;
import org.primefaces.model.ByteArrayContent;
import org.primefaces.model.StreamedContent;



/**
 * Bean manejado para manejar usuarios
 * @author Luna Menguante
 */
@ManagedBean
@RequestScoped
public class UsuarioBean {
    private Usuario usuario = new Usuario();
    static Usuario usuarioelimina = new Usuario();
    static Usuario usuario1;
    private String correo;
    private String contraseña;
    private static String contraseña1;
    private final Utility utility = new Utility();

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
    public String inicioSesion() throws Exception{ 
          contraseña1 = contraseña;
          Usuario u = utility.obtenUsuario(correo, contraseña);
        if (u != null) {
            if (!u.isActivo()) {
                
                FacesContext.getCurrentInstance()
                        .addMessage("loginGrowl",
                                new FacesMessage(FacesMessage.SEVERITY_ERROR,
                                        "Error!", "Verificar correo de validación"));
                return null;
            }
            if(!u.getCorreo().equals(correo)){
                
                 FacesContext.getCurrentInstance()
                .addMessage("loginGrowl",
                        new FacesMessage(FacesMessage.SEVERITY_ERROR,
                                "Error!", "Error al iniciar sesión, verificar usuario y/o contraseña"));
               return null;
                
            }
            FacesContext context = FacesContext.getCurrentInstance();
            context.getExternalContext().getSessionMap().put("usuario", u);
            return "principal.xhtml?faces-redirect=true";
        }
        
        FacesContext.getCurrentInstance()
                .addMessage("loginGrowl",
                        new FacesMessage(FacesMessage.SEVERITY_ERROR,
                                "Error!", "Error al iniciar sesión, verificar usuario y/o contraseña"));
        return null;
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
    public void cerrarSesion() throws IOException {
        FacesContext context = FacesContext.getCurrentInstance();
        context.getExternalContext().invalidateSession();
        context.getExternalContext().redirect("principal.xhtml?faces-redirect=true");
    }

    /**
     * Metodo que obtiene el correo del usuario
     * @return corre -- el correo del usuario
     */
    public String getCorreo() {
        return correo;
    }

    /**
     * Metodo que asigna un nuevo correo al usuario
     * @param correo -- el nuevo correo del usuario
     */
    public void setCorreo(String correo) {
        this.correo = correo;
    }

    /**
     * Metodo que obtiene la contraseña del Usuario
     * @return contraseña -- la contraseña del usuario
     */
    public String getContraseña() {
        return contraseña;
    }

    /**
     * Metodo que asigna una nueva contraseña al Usuario
     * @param contraseña -- la nueva contraseña del usuario
     */
    public void setContraseña(String contraseña) {
        this.contraseña = contraseña;
    }
    
    /**
     * Metodo que revisa si un usuario esta conectado
     * @return true -- si esta conectado, false-- en otro caso
     */
    public boolean estaConectado (){
        FacesContext context = FacesContext.getCurrentInstance();
        Usuario a = (Usuario) context.getExternalContext().getSessionMap().get("usuario");
        return a != null;
    }
    
    /**
     * Metodo que revisa si un usuario conectado es administrador
     * @return true -- si es administrador, false -- en otro caso
     */
    public boolean esAdministrador (){
        FacesContext context = FacesContext.getCurrentInstance();
        Usuario a = (Usuario) context.getExternalContext().getSessionMap().get("usuario");
        if(a==null) return false;
        if (a.getTipo() == 0){
            return true;
        }
        return false;   
    }
    
    /**
     * Metodo que revisa si un usuario conectado es comentarista
     * @return true -- si es comentarista, false -- en otro caso
     */
       public boolean esComentarista (){
        FacesContext context = FacesContext.getCurrentInstance();
        Usuario a = (Usuario) context.getExternalContext().getSessionMap().get("usuario");
        if(a==null) return false;
        if (a.getTipo() == 1){
            return true;
        }
        return false;   
    }
       
    /**
     * Metodo que revisa si un usuario conectado es informador
     * @return true -- si es informador, false -- en otro caso
     */   
      public boolean esInformador (){
        FacesContext context = FacesContext.getCurrentInstance();
        Usuario a = (Usuario) context.getExternalContext().getSessionMap().get("usuario");
        if(a==null) return false;
        if (a.getTipo() == 2){
            return true;
        }
        return false;   
    }
    
      /**
       * Metodo que obtiene el usuario Conectado
       * @return a -- el usuario que se encuentra conectado
       */
    public Usuario elUsuario(){
        FacesContext context = FacesContext.getCurrentInstance();
        Usuario a = (Usuario) context.getExternalContext().getSessionMap().get("usuario");
        return a;
    }
    
    /**
     * Metodo que obtiene la fotografia del usuario que esta conectado.
     * @return a -- la fotografia del usuario conectado
     */
    public StreamedContent getFotografia(){
        FacesContext context = FacesContext.getCurrentInstance();
        Usuario a = (Usuario) context.getExternalContext().getSessionMap().get("usuario");
        if (a.getImagen() != null) {
            return new ByteArrayContent(a.getFotografia());
        }
        return null;
    }
}

