package Dominio.DTO;

/**
 * Objeto de transferencia de datos (DTO) que representa a un veterinario.
 * Se utiliza para transportar información entre capas sin exponer directamente
 * la entidad {@link Dominio.Entidades.Veterinario}.
 *
 * @author Luis
 */
public class DTOVeterinario {

    /** Identificador único del veterinario. */
    private int idVeterinario;

    /** Nombre(s) del veterinario. */
    private String nombre;

    /** Apellido paterno. */
    private String apellidoP;

    /** Apellido materno. */
    private String apellidoM;

    /** Cédula profesional. */
    private String cedulaProfesional;

    /** Especialidad médica. */
    private String especialidad;

    /** Número de teléfono de contacto. */
    private String telefono;

    /**
     * Constructor por defecto.
     */
    public DTOVeterinario() {
    }

    /**
     * Constructor parametrizado que inicializa todos los atributos del DTO.
     *
     * @param idVeterinario identificador único.
     * @param nombre nombre(s).
     * @param apellidoP apellido paterno.
     * @param apellidoM apellido materno.
     * @param cedulaProfesional cédula profesional.
     * @param especialidad especialidad médica.
     * @param telefono teléfono de contacto.
     */
    public DTOVeterinario(int idVeterinario, String nombre, String apellidoP, String apellidoM,
                          String cedulaProfesional, String especialidad, String telefono) {
        this.idVeterinario = idVeterinario;
        this.nombre = nombre;
        this.apellidoP = apellidoP;
        this.apellidoM = apellidoM;
        this.cedulaProfesional = cedulaProfesional;
        this.especialidad = especialidad;
        this.telefono = telefono;
    }

    /** @return el identificador único del veterinario. */
    public int getIdVeterinario() { return idVeterinario; }

    /** @param idVeterinario el nuevo identificador. */
    public void setIdVeterinario(int idVeterinario) { this.idVeterinario = idVeterinario; }

    /** @return el nombre del veterinario. */
    public String getNombre() { return nombre; }

    /** @param nombre el nuevo nombre. */
    public void setNombre(String nombre) { this.nombre = nombre; }

    /** @return el apellido paterno. */
    public String getApellidoP() { return apellidoP; }

    /** @param apellidoP el nuevo apellido paterno. */
    public void setApellidoP(String apellidoP) { this.apellidoP = apellidoP; }

    /** @return el apellido materno. */
    public String getApellidoM() { return apellidoM; }

    /** @param apellidoM el nuevo apellido materno. */
    public void setApellidoM(String apellidoM) { this.apellidoM = apellidoM; }

    /** @return la cédula profesional. */
    public String getCedulaProfesional() { return cedulaProfesional; }

    /** @param cedulaProfesional la nueva cédula. */
    public void setCedulaProfesional(String cedulaProfesional) { this.cedulaProfesional = cedulaProfesional; }

    /** @return la especialidad médica. */
    public String getEspecialidad() { return especialidad; }

    /** @param especialidad la nueva especialidad. */
    public void setEspecialidad(String especialidad) { this.especialidad = especialidad; }

    /** @return el teléfono de contacto. */
    public String getTelefono() { return telefono; }

    /** @param telefono el nuevo teléfono. */
    public void setTelefono(String telefono) { this.telefono = telefono; }

    /**
     * Devuelve una representación en texto del DTO con todos sus campos.
     *
     * @return cadena con los valores de los atributos.
     */
    @Override
    public String toString() {
        return "VeterinarioDTO{" + "idVeterinario=" + idVeterinario + ", nombre=" + nombre + ", apellidoP=" + apellidoP + ", apellidoM=" + apellidoM + ", cedulaProfesional=" + cedulaProfesional + ", especialidad=" + especialidad + ", telefono=" + telefono + '}';
    }
}
