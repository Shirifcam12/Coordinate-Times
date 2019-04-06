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

@ManagedBean
@RequestScoped
public class temaC{
		private Tema tema = new Tema();
        private Base u = new Base();
        private int usuario;


        public Tema getTema() {
            return tema;
        }

        public void setTema(Tema tema) {
            this.tema = tema;
        }


        public temaC() {
            FacesContext.getCurrentInstance()
                .getViewRoot()
                .setLocale(new Locale("es-Mx"));
        }
	
	public String eliminaTema(){		
				if(UsuarioBean.getUsuario() != null){
                    FacesContext.getCurrentInstance().addMessage(null
                                                         , new FacesMessage(FacesMessage.SEVERITY_ERROR, "Inicia sesion para poder acceder a esta funcion", ""));

                }else{
                usuario = UsuarioBean.getUsuario().getTipo();
                if(usuario != 2 || usuario != 0){
			FacesContext.getCurrentInstance().addMessage(null
                                                         , new FacesMessage(FacesMessage.SEVERITY_ERROR, "No dispones de permisos para eliminar Temas", ""));
		} else{
			FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_INFO, "Se ha eliminado correctamenten el tema", ""));
                        u.eliminarT(BuscarPorTema.getTema());
                        tema = null;
		}
        }		
		return null;
	}


}