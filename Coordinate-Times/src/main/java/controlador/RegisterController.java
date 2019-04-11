package controlador;

import java.util.Locale;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import modelo.Usuario;
import modelo.Utility;
/**
 * Bean manejado para Registrar usurios 
 * @author Luna Menguante
 */
@ManagedBean
@RequestScoped
public class RegisterController {

    private Usuario user = new Usuario();
    private Utility u = new Utility();
/**
 * Método que devuelve el usuario guardado en el bean
 * @return El nombre almacenado en el bean
 */
    public Usuario getUser() {
        return user;
    }
/**
 * Método que asigna el usuario a guardar en el bean
 * @param user- El nombre a almacenar en el bean
 */
    public void setUser(Usuario user) {
        this.user = user;
    }
/**
 * Método constructor de la clase RegisterController
 * 
 */
    public RegisterController() {
        FacesContext.getCurrentInstance()
                .getViewRoot()
                .setLocale(new Locale("es-Mx"));
    }
    
    /**
 * Método que  nos ayuda a registrar un usuario
 * @return null si el registro es correcto y un mensaje de error en otro caso
 */
    public String addUser() {
        Pattern pattern = Pattern
                .compile("^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
                        + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$");
            user.setTipo(1);
           Matcher mather = pattern.matcher(user.getCorreo());
           if(mather.find() == true){
            u.save(user);
            user = null;  
           }else{
               FacesContext.getCurrentInstance().addMessage(null
                                                         , new FacesMessage(FacesMessage.SEVERITY_ERROR, "El correo no es valido", ""));
           }
        return null;
    }
}
