/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Dominio.DTO;

import java.util.List;

/**
 * Objeto de transferencia de datos (DTO) que representa a un dueño de mascota.
 * Se utiliza para transportar información entre capas sin exponer directamente
 * la entidad {@link Dominio.Entidades.Duenio}.
 *
 * @author Luis
 */
public class DTODuenio {

    /** Identificador único del dueño. */
    private int idDueno;

    /** Nombre(s) del dueño. */
    private String nombre;

    /** Apellido paterno. */
    private String apellidoP;

    /** Apellido materno. */
    private String apellidoM;

    /** Dirección de residencia. */
    private String direccion;

    /** Correo electrónico de contacto. */
    private String email;

    /** Lista de números de teléfono. */
    private List<String> telefonos;

    /**
     * Constructor por defecto.
     */
    public DTODuenio() {
    }

    /**
     * Constructor parametrizado que inicializa todos los atributos del DTO.
     *
     * @param idDueno identificador único.
     * @param nombre nombre(s).
     * @param apellidoP apellido paterno.
     * @param apellidoM apellido materno.
     * @param direccion dirección de residencia.
     * @param email correo electrónico.
     * @param telefonos lista de teléfonos.
     */
    public DTODuenio(int idDueno, String nombre, String apellidoP, String apellidoM,
                     String direccion, String email, List<String> telefonos) {
        this.idDueno = idDueno;
        this.nombre = nombre;
        this.apellidoP = apellidoP;
        this.apellidoM = apellidoM;
        this.direccion = direccion;
        this.email = email;
        this.telefonos = telefonos;
    }

    /** @return el identificador único del dueño. */
    public int getIdDueno() { 
        return idDueno; 
    }

    /** @param idDueno el nuevo identificador. */
    public void setIdDueno(int idDueno) { 
        this.idDueno = idDueno;
    }

    /** @return el nombre del dueño. */
    public String getNombre() { 
        return nombre; 
    }

    /** @param nombre el nuevo nombre. */
    public void setNombre(String nombre) { 
        this.nombre = nombre; 
    }

    /** @return el apellido paterno. */
    public String getApellidoP() { 
        return apellidoP; 
    }

    /** @param apellidoP el nuevo apellido paterno. */
    public void setApellidoP(String apellidoP) { 
        this.apellidoP = apellidoP;
    }

    /** @return el apellido materno. */
    public String getApellidoM() { 
        return apellidoM;
    }

    /** @param apellidoM el nuevo apellido materno. */
    public void setApellidoM(String apellidoM) { 
        this.apellidoM = apellidoM; 
    }

    /** @return la dirección de residencia. */
    public String getDireccion() { 
        return direccion; 
    }

    /** @param direccion la nueva dirección. */
    public void setDireccion(String direccion) {
        this.direccion = direccion; 
    }

    /** @return el correo electrónico. */
    public String getEmail() { return email; }

    /** @param email el nuevo correo electrónico. */
    public void setEmail(String email) { 
        this.email = email; 
    }

    /** @return la lista de teléfonos. */
    public List<String> getTelefonos() { 
        return telefonos; 
    }

    /** @param telefonos la nueva lista de teléfonos. */
    public void setTelefonos(List<String> telefonos) { 
        this.telefonos = telefonos; 
    }
}