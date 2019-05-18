package controlador;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import modelo.Usuario;
import modelo.Utility;

/**
 *
 * @author Luna Menguante
 */
public class Validacion extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String hash = request.getParameter("val");
        Utility util = new Utility();
        System.out.println("Hash: " + hash);
        if (hash != null) {
            Usuario u = util.obtenUsuario(hash);
            u.setActivo(true);
            util.actualizaUsuario(u);
        }
        response.sendRedirect("principal.xhtml");
        return;
    }

}
