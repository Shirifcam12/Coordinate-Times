package controlador;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.Locale;
import java.util.Random;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.servlet.ServletContext;
import modelo.Tema;
import modelo.UtilityM;
import modelo.Base;
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
        private String color;
        private UtilityM util = new UtilityM();
        
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

  /**
   * Metodo que obtiene el color de un tema
   * @return color -- el color del tema
   */
    public String getColor() {
        return color;
    }

    /**
     * Metodo que asigna un nuevo color al tema
     * @param color -- el nuevo color del tema
     */
    public void setColor(String color) {
        this.color = color;
    }



/**
 * Constructor por omision del TemaC.
 * 
 */
        public temaC() {
            FacesContext.getCurrentInstance()
                .getViewRoot()
                .setLocale(new Locale("es-Mx"));
        }
        /**
         * Metodo que se encarga de eliminar un tema
         * @return al redireccionamiento con la busqueda del tema cancelado
         */	
        public String eliminaTema(){
          if(!(ub.esAdministrador() || ub.esInformador())){
                 FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_ERROR, "No dispones de permisos para eliminar Temas", ""));        
                }
          if(ub.elUsuario().getId() != BuscarPorTema.getT().get(0).getIdU() && ub.esInformador()){
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
        
        /**
         * Metodo que agrega un tema a la base de datos
         * @return el redireccionamiento, al cancelar la busqueda del tema no encontrado
         */
       public String agregaTema(){
          if(!(ub.esInformador())){
                 FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_ERROR, "No dispones de permisos para eliminar Temas", ""));        
                }else{
          Tema t = new Tema();
          FacesContext context = FacesContext.getCurrentInstance();
          Usuario a = (Usuario) context.getExternalContext().getSessionMap().get("usuario");
          t.setIdU(a.getId());
          t.setnombreT(BuscarPorTema.getNombreg().toUpperCase());
          t.setIdC(color);
          creaIcono(color,50,50);
          u.agregarTema(t);
          Marcador m = new Marcador();
          m.setDescripcion("Marcador Inicial del Tema: "+t.getnombreT());
          m.setDatos("Marcador por default de " + t.getnombreT());
          m.setLatitud(29.525+(t.getIdT()*((.002))));
          m.setLongitud(-111.225 + (t.getIdT()*((.002))));
          m.setIdTema(t.getIdT());
          util.guardaMarcador(m);
          }
        return BuscarPorTema.seCancelo1();
	}
       
       /**
        * Metodo que crea el icono del mapa dado el color del tema
        * @param color -- el color del tema
        * @param largo -- el largo del marcador
        * @param ancho -- el ancho del marcador
        */
       private void creaIcono(String color,int largo,int ancho){
        String s = "<?xml version=\"1.0\" encoding=\"UTF-8\" ?>\n";
        s+="<!DOCTYPE svg PUBLIC \"-//W3C//DTD SVG 1.1//EN\" \"http://www.w3.org/Graphics/SVG/1.1/DTD/svg11.dtd\">\n";
        s+="<svg width=\""+largo+"\" height=\""+ancho+"\" version=\"1.1\" id=\"Capa_1\" xmlns=\"http://www.w3.org/2000/svg\" xmlns:xlink=\"http://www.w3.org/1999/xlink\" x=\"0px\" y=\"0px\" style=\"enable-background:new 0 0 512 512;\" xml:space=\"preserve\">\n<g>\n";
        int x =largo/2;
        int y = (ancho/3);
        int radio = ((largo+ancho)/2)/4;

        int[] p ={x-radio,y,x+radio,y,x,(y*3)};
        s+= creaPoligono(p,"#"+color);
        s+=creaCirculo(x,y,radio,"#"+color,true);
        s+=creaCirculo(x,y,radio/2,"black",true);

        s+="</g>\n"+"</svg>";
       
        try {
             ServletContext servletContext = (ServletContext) FacesContext.getCurrentInstance().getExternalContext().getContext();
            String destino = (servletContext.getRealPath("/"))+"resources/img/";
            System.out.println(destino);
            FileOutputStream fileOut = new FileOutputStream(new File(destino + color+".svg"));
            OutputStreamWriter osOut = new OutputStreamWriter(fileOut);
            BufferedWriter out = new BufferedWriter(osOut);
            out.write(s);
            out.close();
        } catch (IOException ioe) {
            System.out.println("No pude guardar en el archivo" );
//            System.exit(1);
        }


    }

    /**
     * Metodo que crea el circulo del marcador
     * @param x -- el eje x del circulo
     * @param y -- el eje y del circulo
     * @param r -- el radio del circulo
     * @param color  -- el color del circulo
     * @param stroke -- el trazo del circulo
     * @return s -- el circulo del marcador
     */
    private String creaCirculo(int x ,int y , int r,String color,boolean stroke){
        String s = stroke ? "<circle cx=\""+x+"\" cy=\"" +y+"\"  r=\"" + r + "\" stroke=\"white\" stroke-width=\"1\"  fill=\"" + color + "\" />\n" : "<circle cx=\""+x+"\" cy=\"" +y+"\"  r=\"" + r + "\" stroke=\"black\" stroke-width=\"0\"  fill=\"" + color + "\" />\n";
        return  s;

    }

    /**
     * Metodo que crea la figura del marcador
     * @param puntos -- los puntos que tendra el poligono del marcador
     * @param color -- el color del marcador
     * @return p -- el poligono del marcador
     */
    private String creaPoligono(int[] puntos,String color){
        String p = "";
        if(puntos.length%2 != 0)
          return "Los puntos estan mal";
        for(int i=0;i<puntos.length;i+=2){
          p+=puntos[i]+","+puntos[i+1]+" ";
        }
        return "<polygon points=\""+p+"\" \n style=\" fill:" +color+";stroke:black;stroke-width:1;\" /> \n";
    }
}