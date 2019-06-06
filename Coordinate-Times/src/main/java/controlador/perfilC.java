package controlador;

import java.io.IOException;
import java.util.Locale;
import modelo.Base;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Locale;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import modelo.Usuario;
import modelo.Utility;
import static modelo.listener.UsuarioInsertEventListener.enviaMensajeInf;
import org.primefaces.model.UploadedFile;
/**
 * Bean manejado para eliminar perfiles
 * @author Luna Menguante
 */
@ManagedBean
@RequestScoped
public class perfilC{

        private Integer usuario;
        private Base u = new Base();
        private UsuarioBean ub = new UsuarioBean();
        private String nombre;
        private String contraseña;
        private UploadedFile fotografia;
/**
 * Método constructor de la clase perfilC
 * 
 */
        public perfilC() {
            FacesContext.getCurrentInstance()
                .getViewRoot()
                .setLocale(new Locale("es-Mx"));
        }

    /**
     * Metodo que obtiene el nombre del usuario
     * @return nombre -- el nombre del usuario
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * Metodo que asigna un nuevo nombre del usuario
     * @param nombre  -- el nuevo nombre del usuario
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    
    /**
     * Metodo que obtiene la fotografia del usuario
     * @return fotografia -- la fotografia del usuario
     */
    public UploadedFile getFotografia() {
        return fotografia;
    }
    
    /**
     * Metodo que asigna una nueva fotografia al usuario
     * @param fotografia -- la nueva fotografia del usuario
     */
    public void setFotografia(UploadedFile fotografia) {
        this.fotografia = fotografia;
    }

    /**
     * Metodo que obtiene la contraseña de un usuario
     * @return contraseña -- la contraseña del usuario
     */
    public String getContraseña() {
        return contraseña;
    }

    /**
     * Metodo que asigna una nueva contraseña al usuario
     * @param contraseña -- la nueva contraseña
     */
    public void setContraseña(String contraseña) {
        this.contraseña = contraseña;
    }
    
        
        /**
         * Metodo que se encarga de eliminar un perfil 
         * @return al redireccionamiento de la pagina principal y un mensaje de error en otro caso
         */
	public String eliminaPerfil(){
          if(ub.esAdministrador() && ub.elUsuario().equals(BuscarPorPerfil.getUs().get(0))){
                 FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_ERROR, "No puedes eliminarte de esta forma Administrador, tendras que eliminarte directamente de la base", ""));  
                }else{
			FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_INFO, "Se ha eliminado correctamenten el perfil", ""));
                        u.eliminarU(BuscarPorPerfil.getUs().get(0));
                        usuario = null;
                        return "principal?faces-redirect=true";
		}
                
        return null;
	}
        /**
         * Metodo que se encarga de eliminar tu perfil
         * @return al redireccionamiento a la pagina principal, un mensaje de error en otro caso
         * @throws IOException -- Por si ocurre alguna excepción al eliminar tu perfil
         */
        public String eliminaTuPerfil() throws IOException{
            Usuario us = ub.elUsuario();
            if(ub.esAdministrador()){
                FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_ERROR, "No puedes eliminarte de esta forma Administrador, tendras que eliminarte directamente de la base", ""));
            }else{
                FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_INFO, "Se ha eliminado correctamenten tu perfil", ""));
                u.eliminarU(us);
               ub.cerrarSesion(); 
               return "principal?faces-redirect=true";
            }
            return null;
        }
        
        /**
         * Metodo que realiza la edicion del Perfil del usuario conectado
         * @return al redireccionamiento del perfil del usuario, con los cambios echos
         */
        public String editaPerfil(){
            if(!nombre.equals("")){
            ub.elUsuario().setNombre(nombre);
            }
            if(fotografia!=null){
            ub.elUsuario().setFotografia(fotografia.getContents());
            }
            if(!contraseña.equals("")){
            ub.elUsuario().setContraseña(contraseña);
            }
            FacesContext.getCurrentInstance().addMessage(null,
            new FacesMessage(FacesMessage.SEVERITY_INFO, "Se ha editado correctamenten el perfil", ""));
            u.editaU(ub.elUsuario());            
            return "perfil?faces-redirect=true";
        }
        
        /**
         * Metodo que registra un informador
         * @param ua -- el usuario que es comentarista
         * @return al redireccionamiento en donde se encuentra todos los usuarios que son comentaristas
         */
        public String registra(Usuario ua){
            ua.setTipo(2);
            FacesContext.getCurrentInstance().addMessage(null,
            new FacesMessage(FacesMessage.SEVERITY_INFO, "Se ha registrado el informador correctamente", ""));
            u.editaU(ua);
            enviaMensajeInf(ua);
            return "comentaristas?faces-redirect=true";
        }
}