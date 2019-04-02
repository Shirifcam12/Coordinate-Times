package modelo;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(catalog = "base", schema = "Base", name = "usuario")
public class Usuario {

    @Id
    @Column(name = "idUsuario")
    private int id;

    @Column(name = "nombreUsuario")
    private String nombre;

    @Column(name = "contraseña")
    private String contraseña;

    @Column(name = "correo")
    private String correo;
    
    @Column(name = "tipo")
    private String tipo;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getContrasena() {
        return contraseña;
    }

    public void setContrasena(String contrasena) {
        this.contraseña = contrasena;
    }
    
    public String getTipo(){
        return tipo;
    }
	
    public void setTipo(String tipo){
	this.tipo = tipo;
    }

}
