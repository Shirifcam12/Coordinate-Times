package controlador;

import java.util.Locale;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import modelo.Tema;
import modelo.Utility;
import modelo.Base;
import modelo.Usuario;
/**
 * Bean manejado para eliminar temas
 * @author Luna Menguante
 */
@ManagedBean
@RequestScoped
public class temaC{
        private Tema tema = new Tema();
        private Base u = new Base();
        private int usuario;
        
/**
 * Método que devuelve el tema guardado en el bean
 * @return El tema almacenado en el bean
 */

        public Tema getTema() {
            return tema;
        }
/**
 * Método que asigna el tema guardado en el bean
 * @param tema-el tema a guardar en el bean
 */
        public void setTema(Tema tema) {
            this.tema = tema;
        }

/**
 * Método constructor de la clase perfilC
 * 
 */
        public temaC() {
            FacesContext.getCurrentInstance()
                .getViewRoot()
                .setLocale(new Locale("es-Mx"));
        }
/**
         * Metodo que se encarga de eliminar un tema
         * @return null si el registro es correcto y un mensaje de error en otro caso
         */	
	public String eliminaTema(){		
            
		if(UsuarioBean.getUsuario1() == null){
                    FacesContext.getCurrentInstance().addMessage(null
                                                         , new FacesMessage(FacesMessage.SEVERITY_ERROR, "Inicia sesion para poder acceder a esta funcion", ""));

                }else{
                usuario = UsuarioBean.getUsuario1().getTipo();
                if(usuario != 2 && usuario != 0){
			FacesContext.getCurrentInstance().addMessage(null
                                                         , new FacesMessage(FacesMessage.SEVERITY_ERROR, "No dispones de permisos para eliminar Temas", ""));
		} else{
			FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_INFO, "Se ha eliminado correctamenten el tema", ""));
            
              u.eliminarT(BuscarPorTema.getT().get(0));
                        
                        tema = null;
		}
        }		
		return null;
	}


}