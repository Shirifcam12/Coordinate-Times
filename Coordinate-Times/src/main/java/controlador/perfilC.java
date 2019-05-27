package controlador;

import java.util.Locale;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import modelo.Usuario;
import modelo.Base;
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
}