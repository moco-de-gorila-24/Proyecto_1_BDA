/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Dominio.Entidades;

import java.math.BigDecimal;
import java.sql.Timestamp;


/**
 * Entidad que representa una consulta veterinaria realizada a una mascota.
 * <p>
 * Cada consulta está asociada a una mascota ({@code idMascota}) y a un
 * veterinario ({@code idVeterinario}).
 * </p>
 *
 * @author Jorge
 */
public class Consulta {
   /** Identificador único de la consulta. */
    private int idConsulta;

    /** Fecha y hora en que se realizó la consulta. */
    private Timestamp fechaHora;

    /** Motivo por el cual se realizó la consulta. */
    private String motivo;

    /** Diagnóstico emitido por el veterinario. */
    private String diagnostico;

    /** Tratamiento indicado para la mascota. */
    private String tratamiento;

    /** Costo de la consulta. */
    private BigDecimal costo;

    /** Identificador de la mascota atendida. */
    private int idMascota;

    /** Identificador del veterinario que atendió la consulta. */
    private int idVeterinario;

    /**
     * Constructor por defecto.
     */
    public Consulta() {
    }
    
    /**
     * Constructor parametrizado que inicializa todos los atributos de la consulta.
     *
     * @param idConsulta identificador único.
     * @param fechaHora fecha y hora de la consulta.
     * @param motivo motivo de la consulta.
     * @param diagnostico diagnóstico emitido.
     * @param tratamiento tratamiento indicado.
     * @param costo costo de la consulta.
     * @param idMascota identificador de la mascota.
     * @param idVeterinario identificador del veterinario.
     */
    public Consulta(int idConsulta, Timestamp fechaHora, String motivo, String diagnostico, String tratamiento, BigDecimal costo, int idMascota, int idVeterinario) {
        this.idConsulta = idConsulta;
        this.fechaHora = fechaHora;
        this.motivo = motivo;
        this.diagnostico = diagnostico;
        this.tratamiento = tratamiento;
        this.costo = costo;
        this.idMascota = idMascota;
        this.idVeterinario = idVeterinario;
    }

    /**
     * Obtiene el identificador único de la consulta.
     * @return el identificador.
     */
    public int getIdConsulta() { 
        return idConsulta; 
    }

    /**
     * Establece el identificador único de la consulta.
     * @param idConsulta el nuevo identificador.
     */
    public void setIdConsulta(int idConsulta) { 
        this.idConsulta = idConsulta; 
    }

    /**
     * Obtiene la fecha y hora de la consulta.
     * @return la fecha y hora.
     */
    public Timestamp getFechaHora() { 
        return fechaHora; 
    }

    /**
     * Establece la fecha y hora de la consulta.
     * @param fechaHora la nueva fecha y hora.
     */
    public void setFechaHora(Timestamp fechaHora) { 
        this.fechaHora = fechaHora; 
    }

    /**
     * Obtiene el motivo de la consulta.
     * @return el motivo.
     */
    public String getMotivo() { 
        return motivo; 
    }

    /**
     * Establece el motivo de la consulta.
     * @param motivo el nuevo motivo.
     */
    public void setMotivo(String motivo) { 
        this.motivo = motivo; 
    }

    /**
     * Obtiene el diagnóstico.
     * @return el diagnóstico.
     */
    public String getDiagnostico() { 
        return diagnostico; 
    }

    /**
     * Establece el diagnóstico.
     * @param diagnostico el nuevo diagnóstico.
     */
    public void setDiagnostico(String diagnostico) { 
        this.diagnostico = diagnostico; 
    }

    /**
     * Obtiene el tratamiento.
     * @return el tratamiento.
     */
    public String getTratamiento() { 
        return tratamiento; 
    }

    /**
     * Establece el tratamiento.
     * @param tratamiento el nuevo tratamiento.
     */
    public void setTratamiento(String tratamiento) { 
        this.tratamiento = tratamiento; 
    }

    /**
     * Obtiene el costo de la consulta.
     * @return el costo.
     */
    public BigDecimal getCosto() { 
        return costo; 
    }

    /**
     * Establece el costo de la consulta.
     * @param costo el nuevo costo.
     */
    public void setCosto(BigDecimal costo) { 
        this.costo = costo; 
    }

    /**
     * Obtiene el identificador de la mascota.
     * @return el id de la mascota.
     */
    public int getIdMascota() { 
        return idMascota; 
    }

    /**
     * Establece el identificador de la mascota.
     * @param idMascota el nuevo id.
     */
    public void setIdMascota(int idMascota) { 
        this.idMascota = idMascota; 
    }

    /**
     * Obtiene el identificador del veterinario.
     * @return el id del veterinario.
     */
    public int getIdVeterinario() {
        return idVeterinario; 
    }

    /**
     * Establece el identificador del veterinario.
     * @param idVeterinario el nuevo id.
     */
    public void setIdVeterinario(int idVeterinario) { 
        this.idVeterinario = idVeterinario; 
    }
}
