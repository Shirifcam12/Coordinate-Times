package modelo.listener;

import java.text.MessageFormat;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.faces.context.FacesContext;
import javax.mail.MessagingException;
import javax.servlet.http.HttpServletRequest;

import controlador.EnviarEmail;
import modelo.Usuario;
import controlador.RegisterController;

import org.hibernate.event.spi.PostInsertEvent;
import org.hibernate.event.spi.PostInsertEventListener;
import org.hibernate.persister.entity.EntityPersister;

/**
 *
 * @author miguel
 */
public class UsuarioInsertEventListener implements PostInsertEventListener {

    private static UsuarioInsertEventListener INSTANCE;
    private static final String MENSAJE = "Hola <b>{0}</b><br/>\nPara validar tu registro, sigue el siguiente enlace: <br/>"
            + "<a href=\"http://localhost:8080/Coordinate-Times/validacion?val={2}\">https://localhost:8080/Coordinate-Times/validacion?val={2}</a><br/>\n"
            + "\n\n¡Gracias!<br/>\n\n"
            + "Atentamente: El equipo de Luna Menguante &#169;";

    private static final String ASUNTO = "Validación de registro en Coordinate Times";

    public static UsuarioInsertEventListener getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new UsuarioInsertEventListener();
        }
        return INSTANCE;
    }

    @Override
    public void onPostInsert(PostInsertEvent pie) {
        final Object entity = pie.getEntity();
        if (entity instanceof Usuario) {
            enviaMensaje((Usuario) entity);
        }
    }

    @Override
    public boolean requiresPostCommitHanding(EntityPersister ep) {
        return false;
    }

    public static void enviaMensaje(Usuario usuario) {
        try {
            MessageFormat format = new MessageFormat(MENSAJE);
            final Object[] array = {usuario.getNombre(), HOST(), usuario.getActivacion()};
            String mensaje = format.format(array);
            EnviarEmail.sendEmail(usuario.getCorreo(), ASUNTO, mensaje);
        } catch (MessagingException ex) {
            Logger.getLogger(RegisterController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private static String HOST() {
        HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
        String ipAddress = request.getHeader("X-FORWARDED-FOR");
        if (ipAddress == null) {
            ipAddress = request.getRemoteHost();
        }
        return ipAddress;
    }

}
