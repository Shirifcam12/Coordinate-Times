package modelo;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;
/**
 * Bean manejado que parsea a la base el campo Usuario
 * @author Luna Menguante
 */
@Entity
@Table(catalog = "base", schema = "Base", name = "usuario")
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idusuario")
    private int id;

    @Column(name = "nombreusuario")
    private String nombre;

    @Column(name = "contraseña")
    private String contraseña;

    @Column(name = "correo")
    private String correo;
    
    @Column(name = "tipo")
    private int tipo;

/**
 * Método que devuelve el id asociado al usuario guardado en el bean
 * @return el id asociado al usuario guardado en el bean
 */
    public int getId() {
        return id;
    }
/**
 * Método que asigna el id del usuario a guardar en el bean
 * @param id-el id del usuario a guardar en el bean
 */
    public void setId(int id) {
        this.id = id;
    }
 /**
 * Método que devuelve el nombre del usuario guardado en el bean
 * @return el nombre del usuario guardado en el bean
 */ 
    public String getNombre() {
        return nombre;
    }
 /**
 * Método que asigna el nombre del usuario a guardar en el bean
 * @param nombre-el nombre del usuario a guardar en el bean
 */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
/**
 * Método que devuelve el correo del usuario guardado en el bean
 * @return el correo del usuario guardado en el bean
 */
    public String getCorreo() {
        return correo;
    }
 /**
 * Método que asigna el correo del usuario a guardar en el bean
 * @param correo-el correo del usuario a guardar en el bean
 */
    public void setCorreo(String correo) {
        this.correo = correo;
    }
/**
 * Método que devuelve la contraseña del usuario guardado en el bean
 * @return la contraseña del usuario guardado en el bean
 */
    public String getContrasena() {
        return contraseña;
    }
 /**
 * Método que asigna la contraseña del usuario a guardar en el bean
 * @param contrasena-la contraseña del usuario a guardar en el bean
 */
    public void setContrasena(String contrasena) {
        this.contraseña = contrasena;
    }
 /**
 * Método que devuelve el tipo del usuario guardado en el bean
 * @return el tipo del usuario guardado en el bean
 */   
    public int getTipo(){
        return tipo;
    }
 /**
 * Método que asigna el tipo del usuario a guardar en el bean
 * @param tipo-el tipo del usuario a guardar en el bean
 */	
    public void setTipo(int tipo){
	this.tipo = tipo;
    }
/**
 * Metodo equals de la clase usuario
 * @param u el usuario con el cual queremos comparar al objeto actual
 * @return true si las cualidades del objeto son iguales, false eoc
 */    
    public boolean equals(Usuario u){
        if(this.id == u.id && this.nombre == u.nombre && this.correo == u.correo 
            && this.contraseña == u.contraseña && this.tipo == u.tipo){
            return true;
        }else{
            return false;
        }
    }
}
