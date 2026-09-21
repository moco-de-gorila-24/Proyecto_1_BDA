/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Dominio.DTO;

import java.sql.Date;

/**
 * Objeto de transferencia de datos (DTO) que representa a una mascota.
 * Se utiliza para transportar información entre capas sin exponer directamente
 * la entidad {@link Dominio.Entidades.Mascota}.
 *
 * @author Luis
 */
public class DTOMascota {

    /** Identificador único de la mascota. */
    private int idMascota;

    /** Nombre de la mascota. */
    private String nombre;

    /** Especie de la mascota. */
    private String especie;

    /** Fecha de nacimiento. */
    private Date fechaNacimiento;

    /** Sexo de la mascota ('M' o 'F'). */
    private String sexo;

    /** Raza de la mascota. */
    private String raza;

    /** Identificador del dueño al que pertenece. */
    private int idDueno;

    /**
     * Constructor por defecto.
     */
    public DTOMascota() {
    }

    /**
     * Constructor parametrizado que inicializa todos los atributos del DTO.
     *
     * @param idMascota identificador único.
     * @param nombre nombre.
     * @param especie especie.
     * @param fechaNacimiento fecha de nacimiento.
     * @param sexo sexo.
     * @param raza raza.
     * @param idDueno identificador del dueño.
     */
    public DTOMascota(int idMascota, String nombre, String especie, Date fechaNacimiento,
                      String sexo, String raza, int idDueno) {
        this.idMascota = idMascota;
        this.nombre = nombre;
        this.especie = especie;
        this.fechaNacimiento = fechaNacimiento;
        this.sexo = sexo;
        this.raza = raza;
        this.idDueno = idDueno;
    }

    /** @return el identificador único de la mascota. */
    public int getIdMascota() { 
        return idMascota; 
    }

    /** @param idMascota el nuevo identificador. */
    public void setIdMascota(int idMascota) { 
        this.idMascota = idMascota; 
    }

    /** @return el nombre de la mascota. */
    public String getNombre() { 
        return nombre; 
    }

    /** @param nombre el nuevo nombre. */
    public void setNombre(String nombre) { 
        this.nombre = nombre; 
    }

    /** @return la especie. */
    public String getEspecie() { 
        return especie; 
    }

    /** @param especie la nueva especie. */
    public void setEspecie(String especie) { 
        this.especie = especie; 
    }

    /** @return la fecha de nacimiento. */
    public Date getFechaNacimiento() { 
        return fechaNacimiento; 
    }

    /** @param fechaNacimiento la nueva fecha. */
    public void setFechaNacimiento(Date fechaNacimiento) { 
        this.fechaNacimiento = fechaNacimiento;
    }

    /** @return el sexo. */
    public String getSexo() {
        return sexo; 
    }

    /** @param sexo el nuevo sexo. */
    public void setSexo(String sexo) { 
        this.sexo = sexo; 
    }

    /** @return la raza. */
    public String getRaza() { 
        return raza; 
    }

    /** @param raza la nueva raza. */
    public void setRaza(String raza) { 
        this.raza = raza; 
    }

    /** @return el identificador del dueño. */
    public int getIdDueno() { 
        return idDueno; 
    }

    /** @param idDueno el nuevo id del dueño. */
    public void setIdDueno(int idDueno) { 
        this.idDueno = idDueno; 
    }
}