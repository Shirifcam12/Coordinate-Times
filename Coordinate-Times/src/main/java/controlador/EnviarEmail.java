package controlador;

import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

/**
 * Clase para poder enviar correos
 * @author Luna Menguante
 */
public class EnviarEmail {

    // Permitir envío en apps no seguras
    // https://myaccount.google.com/u/3/lesssecureapps
    private static final String SENDER = "lunamenguanteis1220@gmail.com";
    private static final String PASSWORD = "Reprobaremos";
/**
 * Metodo que envia un correo con un destinatario, un asunto y un contenido
 * @param to -- el destinatario
 * @param title -- El asunto del correo
 * @param html -- El cuerpo del correo
 * @throws MessagingException -- si ocurre una excepción al momento de enviar un correo
 */
    public static void sendEmail(String to, String title, String html)
            throws MessagingException {
        Session session = createSession();
        MimeMessage message = new MimeMessage(session);
        prepareEmailMessage(message, to, title, html);
        System.out.println("Enviando correo!");
        Transport.send(message);
    }

    /**
     * Metodo que abre la sesion de un correo
     * @return PasswordAuthentication -- La session iniciada del correo con la contraseña
     */
    private static Session createSession() {
        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");
        Session session = Session.getInstance(props, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(SENDER, PASSWORD);
            }
        });
        return session;
    }
    /**
     * Metodo que crea el todo el contenido de un mensaje
     * @param message -- el mensaje que le vamos a pasar
     * @param to -- el destinatario el cual va dirigido
     * @param title -- el asunto del correo
     * @param html -- el cuerpo del correo
     * @throws MessagingException 
     */
    private static void prepareEmailMessage(MimeMessage message, String to, String title, String html)
            throws MessagingException {
        message.setContent(html, "text/html; charset=utf-8");
        message.setFrom(new InternetAddress(SENDER));
        message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(to));
        message.setSubject(title);
    }

}
