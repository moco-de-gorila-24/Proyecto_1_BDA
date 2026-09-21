/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Dominio.Entidades;

import java.util.List;

/**
 * Entidad que representa a un dueño de mascota dentro del sistema veterinario.
 * <p>
 * Un dueño puede tener múltiples números de teléfono y puede estar asociado
 * a una o más mascotas.
 * </p>
 *
 * @author ACER
 */
public class Duenio {
    /** Identificador único del dueño. */
    private int idDueno;
    
     /** Nombre(s) del dueño. */
    private String nombre;
    
    /** Apellido paterno del dueño. */
    private String apellidoP;
    
    /** Apellido materno del dueño. */
    private String apellidoM;
    
    /** Dirección de residencia del dueño. */
    private String direccion;
    
    /** Correo electrónico de contacto del dueño. */
    private String email;
    
    /** Lista de números de teléfono asociados al dueño. */
    private List<String> telefonos;

    /**
     * Constructor por defecto.
     */
    public Duenio() {
    }
    
    /**
     * Constructor parametrizado que inicializa todos los atributos del dueño.
     *
     * @param idDueno identificador único.
     * @param nombre nombre(s).
     * @param apellidoP apellido paterno.
     * @param apellidoM apellido materno.
     * @param direccion dirección de residencia.
     * @param email correo electrónico.
     * @param telefonos lista de teléfonos.
     */
    public Duenio(int idDueno, String nombre, String apellidoP, String apellidoM, String direccion, String email, List<String> telefonos) {
        this.idDueno = idDueno;
        this.nombre = nombre;
        this.apellidoP = apellidoP;
        this.apellidoM = apellidoM;
        this.direccion = direccion;
        this.email = email;
        this.telefonos = telefonos;
    }
    
    /**
     * Obtiene el identificador único del dueño.
     * @return el identificador.
     */
    public int getIdDueno() {
        return idDueno;
    }
    
     /**
     * Establece el identificador único del dueño.
     * @param idDueno el nuevo identificador.
     */
    public void setIdDueno(int idDueno) {
        this.idDueno = idDueno;
    }

    /**
     * Obtiene el nombre del dueño.
     * @return el nombre.
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * Establece el nombre del dueño.
     * @param nombre el nuevo nombre.
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * Obtiene el apellido paterno.
     * @return el apellido paterno.
     */
    public String getApellidoP() {
        return apellidoP;
    }
/**
     * Establece el apellido paterno.
     * @param apellidoP el nuevo apellido paterno.
     */
    public void setApellidoP(String apellidoP) { 
        this.apellidoP = apellidoP; 
    }

    /**
     * Obtiene el apellido materno.
     * @return el apellido materno.
     */
    public String getApellidoM() { 
        return apellidoM; 
    }

    /**
     * Establece el apellido materno.
     * @param apellidoM el nuevo apellido materno.
     */
    public void setApellidoM(String apellidoM) { 
        this.apellidoM = apellidoM; 
    }

    /**
     * Obtiene la dirección de residencia.
     * @return la dirección.
     */
    public String getDireccion() { 
        return direccion; 
    }

    /**
     * Establece la dirección de residencia.
     * @param direccion la nueva dirección.
     */
    public void setDireccion(String direccion) { 
        this.direccion = direccion; 
    }

    /**
     * Obtiene el correo electrónico.
     * @return el email.
     */
    public String getEmail() { 
        return email; 
    }

    /**
     * Establece el correo electrónico.
     * @param email el nuevo email.
     */
    public void setEmail(String email) { 
        this.email = email; 
    }

    /**
     * Obtiene la lista de teléfonos.
     * @return la lista de teléfonos.
     */
    public List<String> getTelefonos() { 
        return telefonos; 
    }

    /**
     * Establece la lista de teléfonos.
     * @param telefonos la nueva lista de teléfonos.
     */
    public void setTelefonos(List<String> telefonos) { 
        this.telefonos = telefonos; 
    }
}
