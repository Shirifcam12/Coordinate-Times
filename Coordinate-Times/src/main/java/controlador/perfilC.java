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
               Usuario condicion = UsuarioBean.getUsuario1();
                if(UsuarioBean.getUsuario1() == null){
                    FacesContext.getCurrentInstance().addMessage(null
                                                         , new FacesMessage(FacesMessage.SEVERITY_ERROR, "Inicia sesion para poder acceder a esta funcion", ""));

                }else{
                usuario = UsuarioBean.getUsuario1().getTipo();		
                if(usuario != 0){
			FacesContext.getCurrentInstance().addMessage(null
                                                         , new FacesMessage(FacesMessage.SEVERITY_ERROR, "No tienes los permisos necesarios", ""));
		}else{
                 if(condicion.getCorreo()==(BuscarPorPerfil.getUs().get(0)).getCorreo()){
                 FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_ERROR, "No puedes eliminarte a ti mismo", ""));        
                }else{
			FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_INFO, "Se ha eliminado correctamenten el perfil", ""));
                        u.eliminarU(BuscarPorPerfil.getUs().get(0));
                        usuario = null;
		}
                }
                }
        return null;
	}

}