package controlador;

import java.io.IOException;
import java.util.Locale;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import modelo.Usuario;
import modelo.Base;
import static modelo.listener.UsuarioInsertEventListener.enviaMensajeInf;
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
/**
 * Método constructor de la clase perfilC
 * 
 */
        public perfilC() {
            FacesContext.getCurrentInstance()
                .getViewRoot()
                .setLocale(new Locale("es-Mx"));
        }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
        
        /**
         * Metodo que se encarga de eliminar un perfil 
         * @return null si el registro es correcto y un mensaje de error en otro caso
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
        
        
        public String editaPerfil(){
            ub.elUsuario().setNombre(nombre);
            FacesContext.getCurrentInstance().addMessage(null,
            new FacesMessage(FacesMessage.SEVERITY_INFO, "Se ha editado correctamenten el perfil", ""));
            u.editaU(ub.elUsuario());            
            return "perfil?faces-redirect=true";
        }
        public String registra(Usuario ua){
            ua.setTipo(2);
            FacesContext.getCurrentInstance().addMessage(null,
            new FacesMessage(FacesMessage.SEVERITY_INFO, "Se ha registrado el informador correctamente", ""));
            u.editaU(ua);
            enviaMensajeInf(ua);
            return "comentaristas?faces-redirect=true";
        }
}