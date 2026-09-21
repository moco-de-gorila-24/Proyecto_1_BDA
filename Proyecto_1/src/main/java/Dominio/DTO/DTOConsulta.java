package Dominio.DTO;

import java.math.BigDecimal;
import java.sql.Timestamp;

/**
 * Objeto de transferencia de datos (DTO) que representa una consulta veterinaria.
 * Se utiliza para transportar información entre capas sin exponer directamente
 * la entidad {@link Dominio.Entidades.Consulta}.
 *
 * @author Luis
 */
public class DTOConsulta {

    /** Identificador único de la consulta. */
    private int idConsulta;

    /** Fecha y hora de la consulta. */
    private Timestamp fechaHora;

    /** Motivo de la consulta. */
    private String motivo;

    /** Diagnóstico emitido. */
    private String diagnostico;

    /** Tratamiento indicado. */
    private String tratamiento;

    /** Costo de la consulta. */
    private BigDecimal costo;

    /** Identificador de la mascota atendida. */
    private int idMascota;

    /** Identificador del veterinario que atendió. */
    private int idVeterinario;

    /**
     * Constructor por defecto.
     */
    public DTOConsulta() {
    }

    /**
     * Constructor parametrizado que inicializa todos los atributos del DTO.
     *
     * @param idConsulta identificador único.
     * @param fechaHora fecha y hora.
     * @param motivo motivo.
     * @param diagnostico diagnóstico.
     * @param tratamiento tratamiento.
     * @param costo costo.
     * @param idMascota identificador de la mascota.
     * @param idVeterinario identificador del veterinario.
     */
    public DTOConsulta(int idConsulta, Timestamp fechaHora, String motivo, String diagnostico,
                       String tratamiento, BigDecimal costo, int idMascota, int idVeterinario) {
        this.idConsulta = idConsulta;
        this.fechaHora = fechaHora;
        this.motivo = motivo;
        this.diagnostico = diagnostico;
        this.tratamiento = tratamiento;
        this.costo = costo;
        this.idMascota = idMascota;
        this.idVeterinario = idVeterinario;
    }

    /** @return el identificador único de la consulta. */
    public int getIdConsulta() { 
        return idConsulta; 
    }

    /** @param idConsulta el nuevo identificador. */
    public void setIdConsulta(int idConsulta) { 
        this.idConsulta = idConsulta; 
    }

    /** @return la fecha y hora de la consulta. */
    public Timestamp getFechaHora() { 
        return fechaHora; 
    }

    /** @param fechaHora la nueva fecha y hora. */
    public void setFechaHora(Timestamp fechaHora) { 
        this.fechaHora = fechaHora; 
    }

    /** @return el motivo. */
    public String getMotivo() { 
        return motivo; 
    }

    /** @param motivo el nuevo motivo. */
    public void setMotivo(String motivo) { 
        this.motivo = motivo; 
    }

    /** @return el diagnóstico. */
    public String getDiagnostico() { 
        return diagnostico; 
    }

    /** @param diagnostico el nuevo diagnóstico. */
    public void setDiagnostico(String diagnostico) { 
        this.diagnostico = diagnostico; 
    }

    /** @return el tratamiento. */
    public String getTratamiento() { 
        return tratamiento; }

    /** @param tratamiento el nuevo tratamiento. */
    public void setTratamiento(String tratamiento) { 
        this.tratamiento = tratamiento; 
    }

    /** @return el costo. */
    public BigDecimal getCosto() { 
        return costo; 
    }

    /** @param costo el nuevo costo. */
    public void setCosto(BigDecimal costo) { 
        this.costo = costo; 
    }

    /** @return el identificador de la mascota. */
    public int getIdMascota() { 
        return idMascota; 
    }

    /** @param idMascota el nuevo id. */
    public void setIdMascota(int idMascota) { 
        this.idMascota = idMascota; 
    }

    /** @return el identificador del veterinario. */
    public int getIdVeterinario() { 
        return idVeterinario; 
    }

    /** @param idVeterinario el nuevo id. */
    public void setIdVeterinario(int idVeterinario) { 
        this.idVeterinario = idVeterinario;
    }
}