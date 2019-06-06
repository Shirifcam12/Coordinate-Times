package modelo;

import java.io.Serializable;
import javax.faces.context.FacesContext;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedNativeQueries;
import javax.persistence.NamedNativeQuery;
import javax.persistence.Table;
import javax.persistence.Transient;
import org.primefaces.model.ByteArrayContent;
import org.primefaces.model.StreamedContent;
/**
 * Bean manejado que parsea a la base el campo Usuario
 * @author Luna Menguante
 */
@Entity
@Table(catalog = "base", schema = "Base", name = "usuario")
@NamedNativeQueries({
    @NamedNativeQuery(name = "Usuario.findByCorreoPassword",
            query = "select * from Base.obten_usuario(:correo, :contrasena)",
            resultClass = Usuario.class),
    @NamedNativeQuery(name = "Usuario.findByHash",
            query = "select * from Base.usuario where activacion = :hash",
            resultClass = Usuario.class)})
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idusuario")
    private int id;

    @Column(name = "nombreusuario")
    private String nombre;

    @Column(name = "contrasena")
    private String contraseña;

    @Column(name = "correo")
    private String correo;
    
    @Column(name = "tipo")
    private int tipo;
    
    @Column(name="fotografia")
    private byte[] fotografia;
    
    @Column(name = "activo")
    private boolean activo;
    
    @Column(name = "activacion")
    private String activacion;
    

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

    /**
     * Metodo que obtiene la fotografia del usuario
     * @return fotografia -- la forografia del usuario
     */
    public byte[] getFotografia() {
        return fotografia;
    }
    
    /**
     * Metodo que muestra el contenido de una imagen
     * @return el contenido de la fotografia
     */
    public StreamedContent getImagen(){
        if (fotografia != null) {
            return new ByteArrayContent(fotografia);
        }
        return null;
    }

    /**
     * Metodo que asigna una nueva fotografia al Usuario
     * @param fotografia -- fotografia nueva del Usuario
     */
    public void setFotografia(byte[] fotografia) {
        this.fotografia = fotografia;
    }

    /**
     * Metodo que obtiene si esta activo el usuario
     * @return activo -- el valor activo del usuario
     */
    public boolean isActivo() {
        return activo;
    }

    /**
     * Metodo que asigna un nuevo valor de activo al usuario
     * @param activo -- el nuevo valor de activo del usuario
     */
    public void setActivo(boolean activo) {
        this.activo = activo;
    }

    /**
     * Metodo que obtiene la activación del usuario
     * @return activacion -- la activación del usuario
     */
    public String getActivacion() {
        return activacion;
    }

    /**
     * Metodo que asigna una nueva activación al usuario
     * @param activacion -- la nueva activación del usuario
     */
    public void setActivacion(String activacion) {
        this.activacion = activacion;
    }

    /**
     * Metodo que obtiene la contraseña del usuario
     * @return contraseña -- la contraseña del usuario
     */
    public String getContraseña() {
        return contraseña;
    }

    /**
     * Metodo que asigna una nueva contraseña al usuario
     * @param contraseña -- la nueva contraseña del usuario
     */
    public void setContraseña(String contraseña) {
        this.contraseña = contraseña;
    }
    
}
