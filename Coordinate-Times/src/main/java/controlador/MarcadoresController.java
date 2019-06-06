/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.servlet.ServletContext;
import modelo.HibernateUtil;

import modelo.Marcador;
import modelo.Tema;
import modelo.Usuario;
import modelo.UtilityM;
import modelo.UtilityT;
import org.hibernate.Query;

import org.primefaces.event.map.OverlaySelectEvent;
import org.primefaces.model.map.DefaultMapModel;
import org.primefaces.model.map.LatLng;
import org.primefaces.model.map.MapModel;
import org.primefaces.model.map.Marker;

/**
 * Bean manejado que realiza todas las funciones que tengan que ver con los marcadores
 * @author Luna Menguante
 */
@ManagedBean
@ViewScoped
public class MarcadoresController implements Serializable {
    private MapModel model;
    private String descripcion;
    private String datos;
    private double latitud;
    private double longitud;
    private Tema tema;
    private UtilityM util;
    private UtilityT utilt;
    private String title;
    private String data;
    private static int id;
    public static boolean a = false;
    
    @PostConstruct
    /**
     * Metodo que inicializa el mapa con los marcadores que se encuentren en la base de datos.
     */
    public void init() {
        util = new UtilityM();
        utilt =new UtilityT();
         ServletContext servletContext = (ServletContext) FacesContext.getCurrentInstance().getExternalContext().getContext();
         
        if(BuscarPorTema.getT().isEmpty()){
          model =  new DefaultMapModel();
        List<Marcador> marcadores = util.obtenMarcadores();
        marcadores.forEach((marcador) -> {
            String co = "resources/img/"+this.obtenerColor(marcador.getIdTema()) +".svg";
            Marker a = new Marker(new LatLng(marcador.getLatitud(), marcador.getLongitud()),
                    marcador.getDescripcion(),
                    marcador.getDatos());
            a.setIcon(co);
            model.addOverlay(a);
         });
        }else{
            if(a == true){
                model = new DefaultMapModel();
           List<Marcador> marcadores = this.obtenerMarcadores(BuscarPorTema.getT().get(0).getIdT());
              marcadores.forEach((marcador) -> {
                  String co = "resources/img/"+this.obtenerColor(marcador.getIdTema()) +".svg";
                  Marker a = new Marker(new LatLng(marcador.getLatitud(), marcador.getLongitud()),
                    marcador.getDescripcion(),
                    marcador.getDatos());
                  a.setIcon(co);
            model.addOverlay(a);
         });
            }
            if(a==false){
                model = new DefaultMapModel();
            List<Marcador> marcadores = util.obtenMarcadores();
            marcadores.forEach((marcador) -> {
            String co = "resources/img/"+this.obtenerColor(marcador.getIdTema()) +".svg";
            Marker a = new Marker(new LatLng(marcador.getLatitud(), marcador.getLongitud()),
                    marcador.getDescripcion(),
                    marcador.getDatos());
                 a.setIcon(co);
            model.addOverlay(a);
         });   
            }
        }
    }
    /**
     * Metodo que regresa la representación del mapa
     * @return model -- la representación del mapa
     */
    public MapModel getModel() {
        return model;
    }
    
    /**
     * Metodo que asigna una nueva representación del mapa, al mapa
     * @param model -- la nueva representación
     */
    public void setModel(MapModel model) {
        this.model = model;
    }
    
    /**
     * Metodo que regresa el id del marcador seleccionado
     * @return id -- el id del marcador seleccionado
     */
    public static int getId() {
        return id;
    }
    
    /**
     * Metodo que asigna una nueva id al id del marcador seleccionado
     * @param id -- el nuevo id del marcador seleccionado
     */
    public void setId(int id) {
        this.id = id;
    }
   
    /**
     * Metodo que obtiene la descripcion del marcador seleccionado
     * @return descripcion -- la descripción del marcador seleccionado
     */
    public String getDescripcion() {
        return descripcion;
    }
    
    /**
     * Metodo que asigna una nueva descripción al marcador seleccionado
     * @param descripcion  -- la nueva descripción
     */
    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    /**
     * Metodo que obtiene los datos utiles del marcador seleccionado
     * @return datos -- los datos del marcador seleccionado
     */
    public String getDatos() {
        return datos;
    }

    /**
     * Metodo que asgina nuevos datos utiles al marcador seleccionado
     * @param datos -- los nuevos datos del marcador seleccionado
     */
    public void setDatos(String datos) {
        this.datos = datos;
    }

    /**
     * Metodo que obtiene la latitud del marcador seleccionado
     * @return latitud -- la nueva latitud del marcador seleccionado
     */
    public double getLatitud() {
        return latitud;
    }

    /**
     * Metodo que asigna una nueva latitud al marcador seleccionado
     * @param latitud -- la nueva latitud del marcador seleccionado
     */
    public void setLatitud(double latitud) {
        this.latitud = latitud;
    }

    /**
     * Metodo que obtiene la longitud del marcador seleccionado
     * @return longitud -- la longitud del marcador seleccionado
     */
    public double getLongitud() {
        return longitud;
    }

    /**
     * Metodo que asigna una nueva longitud al marcador seleccionado
     * @param longitud -- la nueva longitud del marcador seleccionado
     */
    public void setLongitud(double longitud) {
        this.longitud = longitud;
    }

    /**
     * Metodo que obtiene el tema del marcador seleccionado
     * @return tema -- el tema del marcador seleccionado
     */
    public Tema getTema() {
        return tema;
    }

    /**
     * Metodo que asigna el nuevo tema al marcador seleccionado
     * @param tema -- el nuevo tema del marcador seleccionado
     */
    public void setTema(Tema tema) {
        this.tema = tema;
    }

    /**
     * Metodo que obtiene el utilityM de los marcadores
     * @return util -- el utilityM de los marcadores
     */
    public UtilityM getUtil() {
        return util;
    }

    /**
     * Metodo que asigna un nuevo utilityM
     * @param util -- el nuevo utilityM
     */
    public void setUtil(UtilityM util) {
        this.util = util;
    }

    /**
     * Metodo que obtiene la lista de temas de los marcadores
     * @return -- la lista de temas que contiene a los marcadores
     */
    public List<Tema> getTemas() {
      return utilt.obtenTemas();
    }
    
    /**
     * Metodo que obtiene el titulo del marcador seleccionado
     * @return title -- el titulo del marcador seleccionado
     */
    public String getTitle() {
        return title;
    }

    /**
     * Metodo que asigna un nuevo titulo al marcador seleccionado
     * @param title -- nuevo titulo del marcador seleccionado
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * Metodo que obtiene todo los datos del marcador seleccionado
     * @return data -- los tados del marcador seleccionado
     */
    public String getData() {
        return data;
    }

    /**
     * Metodo que obtiene el utilityT de los temas
     * @return utilt-- el utilityT de los temas
     */
    public UtilityT getUtilt() {
        return utilt;
    }

    /**
     * Metodo que asigna un nuevo utilityT de los temas
     * @param utilt -- el nuevo utilityT de los temas
     */
    public void setUtilt(UtilityT utilt) {
        this.utilt = utilt;
    }
    

    /**
     * Metodo que asigna los nuevos datos al marcador seleccionado
     * @param data -- los nuevos datos del marcador seleccionado
     */
    public void setData(String data) {
        this.data = data;
    }

    /**
     * Metodo qoe obtiene el valor de a el cual es un auxiliar que permite ver si se selecciono la opcion 
     * mostrarMarcadores del tema
     * @return a -- el valor auxiliar
     */
    public boolean isA() {
        return a;
    }

    /**
     * Metodo que asigna el valor auxiliar a
     * @param a -- el nuevo valor axiliar
     */
    public void setA(boolean a) {
        this.a = a;
    }
    
    /**
     * Metodo que muesta los marcadores que son especificamente del tema.
     * @return el redireccionamiento a la pagina principal con los marcadores del tema
     */
    public String mostrarMarcadores(){
        a = true;
        return "principal?faces-redirect=true";
    }
    
    /**
     * Metodo que agrega un nuevo marcador al mapa
     * @return el redireccionamiento a la pagina principal con el nuevo marcador
     */
    public String addMarker() {
        Marker marker = new Marker(new LatLng(latitud, longitud), descripcion);
        model.addOverlay(marker);
        Marcador m = new Marcador();
        m.setDescripcion(descripcion);
        m.setDatos(datos);
        m.setLatitud(latitud);
        m.setLongitud(longitud);
        m.setIdTema(BuscarPorTema.getT().get(0).getIdT());
        util.guardaMarcador(m);
        model.addOverlay(new Marker(new LatLng(latitud, longitud),
                descripcion,
                datos));
        FacesContext.getCurrentInstance()
                .addMessage(null,
                        new FacesMessage(FacesMessage.SEVERITY_INFO,
                                "Marcador añadido", "Lat:" + latitud + ", Lng:" + longitud));
        
      return "principal?faces-redirect=true";
    }

    /**
     * Metodo que al momento de seleccionar un marcador se muestre todos los temas que tiene el mismo
     * @param event -- el evento de seleccionar el marcador
     */
    public void onMarkerSelect(OverlaySelectEvent event) {
        Marker marker = (Marker) event.getOverlay();
        data = (String) marker.getData();
        title = (String) marker.getTitle();
        id = obtenerID();
    }
    
    /**
     * Metodo que elmina un marcador de la base de datos y del mapa
     * @return el redireccionamiento a la pagina principal sin el marcador
     */
       public String eliminaMarcador() {
        util.eliminaMarcadorPorTitulo(title);
        Integer idx = -1;
        for (int i = 0; i < model.getMarkers().size(); i++) {
            if (model.getMarkers().get(i).getTitle().equals(title)) {
                idx = i;
            }
        }
        if (idx >= 0) {
            model.getMarkers().remove(idx);
        }
        return "principal?faces-redirect=true";
    }
       
       /**
        * Metodo que obtiene el identificador del marcador que fue seleccionado
        * @return -- el identificador del marcador
        */
       public int obtenerID(){
          return util.buscarMarcadorPorTitulo(title).getIdMarcador();
       }
       
       /**
        * Metodo que obtiene todos los marcadores dado el identificador de un tema
        * @param itm -- el identificador del tema
        * @return marcadores -- la lista de todos los marcadores
        */
       public List<Marcador> obtenerMarcadores(int itm){
            Query query = HibernateUtil.getCurrentSession().createQuery("FROM Marcador m WHERE m.idTema = :itm");
            query.setParameter("itm", itm);
            List<Marcador> marcadores =  query.list();
            return marcadores;
       }
       
       /**
        * Metodo que obtiene el color del tema dado su identificador
        * @param idt -- el identificador del tema
        * @return co -- el color de el tema
        */
       public String obtenerColor(int idt){
            Query query = HibernateUtil.getCurrentSession().createQuery("FROM Tema t WHERE t.idT = :idt");
            query.setParameter("idt", idt);
            List<Tema> tema = query.list();
            String co = tema.get(0).getIdC();
            return co;
       }
}
