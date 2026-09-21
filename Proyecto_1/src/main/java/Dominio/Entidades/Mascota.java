/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Dominio.Entidades;

import java.sql.Date;

/**
 * Entidad que representa a una mascota registrada en el sistema veterinario.
 * <p>
 * Cada mascota está asociada a un dueño mediante {@code idDueno}.
 * </p>
 *
 * @author ACER
 * @version 1.0
 * @since 2026
 */
public class Mascota {

    /** Identificador único de la mascota. */
    private int idMascota;

    /** Nombre de la mascota. */
    private String nombre;

    /** Especie de la mascota (perro, gato, etc.). */
    private String especie;

    /** Fecha de nacimiento de la mascota. */
    private Date fechaNacimiento;

    /** Sexo de la mascota ('M' o 'F'). */
    private String sexo;

    /** Raza de la mascota. */
    private String raza;

    /** Identificador del dueño al que pertenece la mascota. */
    private int idDueno;

    /**
     * Constructor por defecto.
     */
    public Mascota() {
    }

    /**
     * Constructor parametrizado que inicializa todos los atributos de la mascota.
     *
     * @param idMascota identificador único.
     * @param nombre nombre.
     * @param especie especie.
     * @param fechaNacimiento fecha de nacimiento.
     * @param sexo sexo.
     * @param raza raza.
     * @param idDueno identificador del dueño.
     */
    public Mascota(int idMascota, String nombre, String especie, Date fechaNacimiento, String sexo, String raza, int idDueno) {
        this.idMascota = idMascota;
        this.nombre = nombre;
        this.especie = especie;
        this.fechaNacimiento = fechaNacimiento;
        this.sexo = sexo;
        this.raza = raza;
        this.idDueno = idDueno;
    }

    /**
     * Obtiene el identificador único de la mascota.
     * @return el identificador.
     */
    public int getIdMascota() { 
        return idMascota; 
    }

    /**
     * Establece el identificador único de la mascota.
     * @param idMascota el nuevo identificador.
     */
    public void setIdMascota(int idMascota) { 
        this.idMascota = idMascota; 
    }

    /**
     * Obtiene el nombre de la mascota.
     * @return el nombre.
     */
    public String getNombre() { 
        return nombre; 
    }

    /**
     * Establece el nombre de la mascota.
     * @param nombre el nuevo nombre.
     */
    public void setNombre(String nombre) { 
        this.nombre = nombre; 
    }

    /**
     * Obtiene la especie.
     * @return la especie.
     */
    public String getEspecie() { 
        return especie; 
    }

    /**
     * Establece la especie.
     * @param especie la nueva especie.
     */
    public void setEspecie(String especie) { 
        this.especie = especie; 
    }

    /**
     * Obtiene la fecha de nacimiento.
     * @return la fecha de nacimiento.
     */
    public Date getFechaNacimiento() { 
        return fechaNacimiento; 
    }

    /**
     * Establece la fecha de nacimiento.
     * @param fechaNacimiento la nueva fecha.
     */
    public void setFechaNacimiento(Date fechaNacimiento) { 
        this.fechaNacimiento = fechaNacimiento; 
    }

    /**
     * Obtiene el sexo.
     * @return el sexo.
     */
    public String getSexo() { 
        return sexo; 
    }

    /**
     * Establece el sexo.
     * @param sexo el nuevo sexo.
     */
    public void setSexo(String sexo) { 
        this.sexo = sexo; 
    }

    /**
     * Obtiene la raza.
     * @return la raza.
     */
    public String getRaza() { 
        return raza; 
    }

    /**
     * Establece la raza.
     * @param raza la nueva raza.
     */
    public void setRaza(String raza) {
        this.raza = raza; 
    }

    /**
     * Obtiene el identificador del dueño.
     * @return el id del dueño.
     */
    public int getIdDueno() { 
        return idDueno; 
    }

    /**
     * Establece el identificador del dueño.
     * @param idDueno el nuevo id del dueño.
     */
    public void setIdDueno(int idDueno) { 
        this.idDueno = idDueno; 
    }
}