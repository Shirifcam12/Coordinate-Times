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

@ManagedBean
@RequestScoped
public class RegisterController {

    private Usuario user = new Usuario();
    private Utility u = new Utility();

    public Usuario getUser() {
        return user;
    }

    public void setUser(Usuario user) {
        this.user = user;
    }

    public RegisterController() {
        FacesContext.getCurrentInstance()
                .getViewRoot()
                .setLocale(new Locale("es-Mx"));
    }
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
