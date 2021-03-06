package controlador;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Locale;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import modelo.Usuario;
import modelo.Utility;
import static modelo.listener.UsuarioInsertEventListener.enviaMensaje;
import org.primefaces.model.UploadedFile;
/**
 * Bean manejado para Registrar usuarios 
 * @author Luna Menguante
 */
@ManagedBean
@RequestScoped
public class RegisterController {


    private Usuario user = new Usuario();
    private Utility u = new Utility();
    private String confirmacionPassword;
    private UploadedFile fotografia;
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
 * Metodo que obtiene la confirmacion de la contraseña
 * @return confirmacionPassword -- la confirmacion de la contraseña
 */
    public String getConfirmacionPassword() {
        return confirmacionPassword;
    }

    /**
     * Metodo que asigna una nueva confirmacion de contraseña del usuario
     * @param confirmacionPassword  -- la nueva confirmacion de la contraseña
     */
    public void setConfirmacionPassword(String confirmacionPassword) {
        this.confirmacionPassword = confirmacionPassword;
    }

    /**
     * Metodo que obtiene la fotografia del usuario 
     * @return fotografia -- la fotografia del usuario
     */
    public UploadedFile getFotografia() {
        return fotografia;
    }

    /**
     * Metodo que asigna una nueva fotografia al usuario
     * @param fotografia -- la nueva fotografia del usuario
     */
    public void setFotografia(UploadedFile fotografia) {
        this.fotografia = fotografia;
    }
    
/**
 * Constructor por Omision del RegisterController
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
    public String registrar() {
        if (!user.getContraseña().equals(confirmacionPassword)) {
            FacesContext.getCurrentInstance()
                    .addMessage("registroForm:growl",
                            new FacesMessage(FacesMessage.SEVERITY_ERROR,
                                    "Error!", "Fallo de registro: Las contraseñas deben coincidir"));
        }else{
        Pattern pattern = Pattern
                .compile("^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
                        + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$");
           Matcher mather = pattern.matcher(user.getCorreo());
            if (fotografia != null) {
                user.setFotografia(fotografia.getContents());
            } else {
                user.setFotografia(null);
            }
            String hash = md5(user.getNombre() + user.getContraseña() + user.getCorreo());
            user.setActivacion(hash);
           if(mather.find() == true){
                        FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_INFO, "Se ha enviado un correo de confirmación", ""));
            u.guardaUsuario(user);
            enviaMensaje(user);
            user = null;
            fotografia = null;
           }else{
               FacesContext.getCurrentInstance().addMessage(null
                                                         , new FacesMessage(FacesMessage.SEVERITY_ERROR, "El correo no es valido", ""));
           }
        }
        return "registro.xhtml?faces-redirect=true";
    
}
    
    /**
     * Metodo que nos ayuda hashear los datos de un usuario
     * @param entrada -- los datos del usuario
     * @return los datos del usuario hasheados
     */
        public static String md5(String entrada) {
        try {
            char[] CONSTS_HEX = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f'};
            MessageDigest digest = MessageDigest.getInstance("MD5");
            byte[] salida = digest.digest(entrada.getBytes());
            StringBuilder strbCadenaMD5 = new StringBuilder(2 * salida.length);
            for (int i = 0; i < salida.length; i++) {
                int bajo = (int) (salida[i] & 0x0f);
                int alto = (int) ((salida[i] & 0xf0) >> 4);
                strbCadenaMD5.append(CONSTS_HEX[alto]);
                strbCadenaMD5.append(CONSTS_HEX[bajo]);
            }
            return strbCadenaMD5.toString();
        } catch (NoSuchAlgorithmException ex) {
            Logger.getLogger(RegisterController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
}
