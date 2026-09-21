/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Dominio.Entidades;

/**
 * Entidad que representa a un veterinario dentro del sistema.
 * <p>
 * Cada veterinario puede estar asociado a múltiples consultas médicas.
 * </p>
 *
 * @author Jorge
 */
public class Veterinario {
    /** Identificador único del veterinario. */
    private int idVeterinario;
    /** Nombre(s) del veterinario. */
    private String nombre;

    /** Apellido paterno. */
    private String apellidoP;

    /** Apellido materno. */
    private String apellidoM;

    /** Cédula profesional del veterinario. */
    private String cedulaProfesional;

    /** Especialidad médica del veterinario. */
    private String especialidad;

    /** Número de teléfono de contacto. */
    private String telefono;

    /**
     * Constructor por defecto.
     */
    public Veterinario() {
    }

    /**
     * Constructor parametrizado que inicializa todos los atributos del veterinario.
     *
     * @param idVeterinario identificador único.
     * @param nombre nombre(s).
     * @param apellidoP apellido paterno.
     * @param apellidoM apellido materno.
     * @param cedulaProfesional cédula profesional.
     * @param especialidad especialidad médica.
     * @param telefono teléfono de contacto.
     */
    public Veterinario(int idVeterinario, String nombre, String apellidoP, String apellidoM, String cedulaProfesional, String especialidad, String telefono) {
        this.idVeterinario = idVeterinario;
        this.nombre = nombre;
        this.apellidoP = apellidoP;
        this.apellidoM = apellidoM;
        this.cedulaProfesional = cedulaProfesional;
        this.especialidad = especialidad;
        this.telefono = telefono;
    }

    /**
     * Obtiene el identificador único.
     * @return el identificador.
     */
    public int getIdVeterinario() { 
        return idVeterinario; 
    }

    /**
     * Establece el identificador único.
     * @param idVeterinario el nuevo identificador.
     */
    public void setIdVeterinario(int idVeterinario) { 
        this.idVeterinario = idVeterinario; 
    }

    /**
     * Obtiene el nombre.
     * @return el nombre.
     */
    public String getNombre() { 
        return nombre; 
    }

    /**
     * Establece el nombre.
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
     * Obtiene la cédula profesional.
     * @return la cédula profesional.
     */
    public String getCedulaProfesional() { 
        return cedulaProfesional; 
    }

    /**
     * Establece la cédula profesional.
     * @param cedulaProfesional la nueva cédula.
     */
    public void setCedulaProfesional(String cedulaProfesional) { 
        this.cedulaProfesional = cedulaProfesional; 
    }

    /**
     * Obtiene la especialidad.
     * @return la especialidad.
     */
    public String getEspecialidad() {
        return especialidad; 
    }

    /**
     * Establece la especialidad.
     * @param especialidad la nueva especialidad.
     */
    public void setEspecialidad(String especialidad) { 
        this.especialidad = especialidad; 
    }

    /**
     * Obtiene el teléfono.
     * @return el teléfono.
     */
    public String getTelefono() { 
        return telefono; 
    }

    /**
     * Establece el teléfono.
     * @param telefono el nuevo teléfono.
     */
    public void setTelefono(String telefono) { 
        this.telefono = telefono; 
    }
}
