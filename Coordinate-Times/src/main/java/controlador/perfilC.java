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

        private String nombre;
        private String correo;
        private String descripcion;
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

        /* Método que devuelve el nombre guardado en el bean
        * @return El nombre almacenado en el bean
        */
        public String getNombre(){
                return nombre;
        }
        /**
        * Método que asigna el nombre a guardar en el bean
        * @param nombre el correo a almacenar
        */
        public void setNombre(String nombre){
            this.nombre = nombre;
        }

        /* Método que devuelve el nombre guardado en el bean
        * @return El nombre almacenado en el bean
        */
        public String getCorreo(){
                return correo;
        }
        /**
        * Método que asigna el nombre a guardar en el bean
        * @param nombre el correo a almacenar
        */
        public void setCorreo(String correo){
            this.correo = correo;
        }

        /* Método que devuelve el nombre guardado en el bean
        * @return El nombre almacenado en el bean
        */
        public String getDescripcion(){
                return descripcion;
        }
        /**
        * Método que asigna el tipo a guardar en el bean
        * @param tipo el correo a almacenar
        */
        public void setDescripcion(String descripcion){
            this.descripcion = descripcion;
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
                    new FacesMessage(FacesMessage.SEVERITY_ERROR, "No puedes eliminarte a ti mismo, para eliminar tu perfil seleccionar la opcion Eliminar mi Perfil", ""));        
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

        /**
         * Metodo que se encarga de eliminar tu perfil 
         * @return null si el registro es correcto y un mensaje de error en otro caso
         */
        public String eliminaTuPerfil(){
            Usuario condicion = UsuarioBean.getUsuario1();
            if(UsuarioBean.getUsuario1() == null){
                FacesContext.getCurrentInstance().addMessage(null
                                                         , new FacesMessage(FacesMessage.SEVERITY_ERROR, "Inicia sesion para poder acceder a esta funcion", ""));

            }else{
                usuario = UsuarioBean.getUsuario1().getTipo();      
                if(usuario == 0){
                    FacesContext.getCurrentInstance().addMessage(null
                                                         , new FacesMessage(FacesMessage.SEVERITY_ERROR, "Los administradores no se pueden eliminar ellos mismos, favor de contactar con el propietario", ""));
                }else{
                    if(condicion.getCorreo()==(BuscarPorPerfil.getUs().get(0)).getCorreo()){
                        FacesContext.getCurrentInstance().addMessage(null,
                        new FacesMessage(FacesMessage.SEVERITY_INFO, "Se ha eliminado correctamenten el perfil", ""));
                        u.eliminarU(BuscarPorPerfil.getUs().get(0));
                        usuario = null;
                    }else{
                        FacesContext.getCurrentInstance().addMessage(null,
                            new FacesMessage(FacesMessage.SEVERITY_ERROR, "A ocurido un error favor de contactar a un administrador", "")); 
                    }
                }
            }
            return null;
        }

                /**
         * Metodo que se encarga de eliminar un perfil 
         * @return null si el registro es correcto y un mensaje de error en otro caso
         */
        public String editaPerfil(){
            Usuario condicion = UsuarioBean.getUsuario1();
            if(UsuarioBean.getUsuario1() == null){
                FacesContext.getCurrentInstance().addMessage(null
                                                         , new FacesMessage(FacesMessage.SEVERITY_ERROR, "Inicia sesion para poder acceder a esta funcion", ""));

            }else{
                if(condicion.getCorreo()==(BuscarPorPerfil.getUs().get(0)).getCorreo()){
                    (BuscarPorPerfil.getUs().get(0)).setNombre(nombre);
                    (BuscarPorPerfil.getUs().get(0)).setCorreo(correo);
                    FacesContext.getCurrentInstance().addMessage(null,
                        new FacesMessage(FacesMessage.SEVERITY_INFO, "Se ha editado correctamenten el perfil", ""));
                    u.editaU(BuscarPorPerfil.getUs().get(0));
                    usuario = null;
                }else{
                    FacesContext.getCurrentInstance().addMessage(null,
                        new FacesMessage(FacesMessage.SEVERITY_ERROR, "A ocurido un error favor de contactar a un administrador", ""));        

                }
            }
            return null;
        }
}
