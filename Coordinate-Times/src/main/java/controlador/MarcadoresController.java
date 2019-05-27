/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import modelo.Marcador;
import modelo.Tema;
import modelo.UtilityM;
import modelo.UtilityT;

import org.primefaces.event.map.OverlaySelectEvent;
import org.primefaces.model.map.DefaultMapModel;
import org.primefaces.model.map.LatLng;
import org.primefaces.model.map.MapModel;
import org.primefaces.model.map.Marker;

/**
 *
 * @author ricardo
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
    
    @PostConstruct
    public void init() {
        util = new UtilityM();
        utilt =new UtilityT();
        model = new DefaultMapModel();
        List<Marcador> marcadores = util.obtenMarcadores();
        marcadores.forEach((marcador) -> {
            model.addOverlay(new Marker(new LatLng(marcador.getLatitud(), marcador.getLongitud()),
                    marcador.getDescripcion(),
                    marcador.getDatos()));
        });
    }

    public MapModel getModel() {
        return model;
    }

    public static int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setModel(MapModel model) {
        this.model = model;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getDatos() {
        return datos;
    }

    public void setDatos(String datos) {
        this.datos = datos;
    }

    public double getLatitud() {
        return latitud;
    }

    public void setLatitud(double latitud) {
        this.latitud = latitud;
    }

    public double getLongitud() {
        return longitud;
    }

    public void setLongitud(double longitud) {
        this.longitud = longitud;
    }

    public Tema getTema() {
        return tema;
    }

    public void setTema(Tema tema) {
        this.tema = tema;
    }

    public UtilityM getUtil() {
        return util;
    }

    public void setUtil(UtilityM util) {
        this.util = util;
    }

    public List<Tema> getTemas() {
      return utilt.obtenTemas();
    }
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getData() {
        return data;
    }

    public UtilityT getUtilt() {
        return utilt;
    }

    public void setUtilt(UtilityT utilt) {
        this.utilt = utilt;
    }
    

    public void setData(String data) {
        this.data = data;
    }
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

    public void onMarkerSelect(OverlaySelectEvent event) {
        Marker marker = (Marker) event.getOverlay();
        data = (String) marker.getData();
        title = (String) marker.getTitle();
        id = obtenerID();
    }
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
       public int obtenerID(){
          return util.buscarMarcadorPorTitulo(title).getIdMarcador();
       }

}
