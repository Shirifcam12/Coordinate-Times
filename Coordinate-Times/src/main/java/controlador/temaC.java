package controlador;

import java.util.Locale;
import java.util.Random;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import modelo.Tema;
import modelo.Utility;
import modelo.Base;
import modelo.Colores;
import modelo.Marcador;
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
        private UsuarioBean ub = new UsuarioBean();
        private Colores color = new Colores();
        
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

    public Colores getColor() {
        return color;
    }

    public void setColor(Colores color) {
        this.color = color;
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
          if(!(ub.esAdministrador() || ub.esInformador())){
                 FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_ERROR, "No dispones de permisos para eliminar Temas", ""));        
                }
          if(ub.elUsuario().getId() != BuscarPorTema.getT().get(0).getIdU()){
              FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_ERROR, "No es tu tema", "")); 
              }
                  else{
			FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_INFO, "Se ha eliminado correctamenten el Tema", ""));
                        u.eliminarT(BuscarPorTema.getT().get(0));
                        tema = null;
		}
        return BuscarPorTema.seCancelo1();
	}
        
       public String agregaTema(){
          if(!(ub.esInformador())){
                 FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_ERROR, "No dispones de permisos para eliminar Temas", ""));        
                }else{
          Tema t = new Tema();
          FacesContext context = FacesContext.getCurrentInstance();
          Usuario a = (Usuario) context.getExternalContext().getSessionMap().get("usuario");
          t.setIdU(a.getId());
          t.setnombreT(BuscarPorTema.getNombreg());
          Random r = new Random();
          t.setIdC(color.getColores().get(r.nextInt(color.getColores().size())));
          u.agregarTema(t);
		}
        return BuscarPorTema.seCancelo1();
	}
}