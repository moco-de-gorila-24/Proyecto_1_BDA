package Dominio.DTO;
/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

import java.math.BigDecimal;
import java.sql.Timestamp;

/**
 *
 * @author Luis
 */
public class DTOConsulta {
    private int idConsulta;
    private Timestamp fechaHora;
    private String motivo;
    private String diagnostico;
    private String tratamiento;
    private BigDecimal costo;
    private int idMascota;
    private int idVeterinario;

    public DTOConsulta() {
    }

    public DTOConsulta(int idConsulta, Timestamp fechaHora, String motivo, String diagnostico, String tratamiento, BigDecimal costo, int idMascota, int idVeterinario) {
        this.idConsulta = idConsulta;
        this.fechaHora = fechaHora;
        this.motivo = motivo;
        this.diagnostico = diagnostico;
        this.tratamiento = tratamiento;
        this.costo = costo;
        this.idMascota = idMascota;
        this.idVeterinario = idVeterinario;
    }

    public int getIdConsulta() {
        return idConsulta;
    }

    public void setIdConsulta(int idConsulta) {
        this.idConsulta = idConsulta;
    }

    public Timestamp getFechaHora() {
        return fechaHora;
    }

    public void setFechaHora(Timestamp fechaHora) {
        this.fechaHora = fechaHora;
    }

    public String getMotivo() {
        return motivo;
    }

    public void setMotivo(String motivo) {
        this.motivo = motivo;
    }

    public String getDiagnostico() {
        return diagnostico;
    }

    public void setDiagnostico(String diagnostico) {
        this.diagnostico = diagnostico;
    }

    public String getTratamiento() {
        return tratamiento;
    }

    public void setTratamiento(String tratamiento) {
        this.tratamiento = tratamiento;
    }

    public BigDecimal getCosto() {
        return costo;
    }

    public void setCosto(BigDecimal costo) {
        this.costo = costo;
    }

    public int getIdMascota() {
        return idMascota;
    }

    public void setIdMascota(int idMascota) {
        this.idMascota = idMascota;
    }

    public int getIdVeterinario() {
        return idVeterinario;
    }

    public void setIdVeterinario(int idVeterinario) {
        this.idVeterinario = idVeterinario;
    }
}
