package controlador;

import java.io.IOException;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import modelo.Usuario;
import modelo.Utility;

/**
 *
 * @author LunaMenguante
 */
@SessionScoped
@ManagedBean
public class LoginController {

    private String correo;
    private String contraseña;
    private final Utility utility;

    public LoginController() {
        utility = new Utility();
    }

    public String login() {
        Usuario u = utility.obtenUsuario(correo, contraseña);
        if (u != null) {
            if (!u.isActivo()) {
                FacesContext.getCurrentInstance()
                        .addMessage("loginGrowl",
                                new FacesMessage(FacesMessage.SEVERITY_ERROR,
                                        "Error!", "Verificar correo de validación"));
            }
            FacesContext context = FacesContext.getCurrentInstance();
            context.getExternalContext().getSessionMap().put("usuario", u);
            return "principal.xhtml?faces-redirect=true";
        }
        FacesContext.getCurrentInstance()
                .addMessage("loginGrowl",
                        new FacesMessage(FacesMessage.SEVERITY_ERROR,
                                "Error!", "Error al iniciar sesión, verificar usuario y/o contraseña"));
        return "principal.xhtml?faces-redirect=true";
    }

    /**
     * Cierra la sesión http del usuario.
     *
     * @return La página de inicio.
     */
    public void logout() throws IOException {
        FacesContext context = FacesContext.getCurrentInstance();
        context.getExternalContext().invalidateSession();
        context.getExternalContext().redirect("principal.xhtml?faces-redirect=true");
        return;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getContraseña() {
        return contraseña;
    }

    public void setContraseña(String contraseña) {
        this.contraseña = contraseña;
    }

}