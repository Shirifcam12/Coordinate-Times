package modelo;

import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author ricardo
 */
@ManagedBean
public class Colores {
     
    private String console; 
    private String color;

    private List<String> colores;  

     
   public Colores() {
        colores = new ArrayList<String>();
        colores.add("amarillo");
        colores.add("amarillo-dot");
        colores.add("azul");
        colores.add("azul-dot");
        colores.add("verde");
        colores.add("verde-dot");
        colores.add("azulclaro");
        colores.add("azulc-dot");
        colores.add("naranja");
        colores.add("naranja-dot");
        colores.add("rosa");
        colores.add("rosa-dot");
        colores.add("morado");
        colores.add("morado-dot");
        colores.add("rojo");
        colores.add("rojo-dot");
    }
 
    public String getConsole() {
        return console;
    }
 
    public void setConsole(String console) {
        this.console = console;
    }
 
    public List<String> getColores() {
        return colores;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }
    
}