package controlador;

import java.util.Locale;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import modelo.Usuario;
import modelo.Base;

@ManagedBean
@RequestScoped
public class perfilC{

        private Integer usuario;
        private Base u = new Base();

        public perfilC() {
            FacesContext.getCurrentInstance()
                .getViewRoot()
                .setLocale(new Locale("es-Mx"));
        }
	
	public String eliminaPerfil(){
                
                if(UsuarioBean.getUsuario() != null){
                    FacesContext.getCurrentInstance().addMessage(null
                                                         , new FacesMessage(FacesMessage.SEVERITY_ERROR, "Inicia sesion para poder acceder a esta funcion", ""));

                }else{
                usuario = UsuarioBean.getUsuario().getTipo();		
                if(!usuario.equals(0)){
			FacesContext.getCurrentInstance().addMessage(null
                                                         , new FacesMessage(FacesMessage.SEVERITY_ERROR, "El perfil a eliminar no existe", ""));
		} else{
			FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_INFO, "Se ha eliminado correctamenten el perfil", ""));
                        u.eliminarU(UsuarioBean.getUsuarioElimina());
                        usuario = null;
		}
                }
        return null;
	}

}