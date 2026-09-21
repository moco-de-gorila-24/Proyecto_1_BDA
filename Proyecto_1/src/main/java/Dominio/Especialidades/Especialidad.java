/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Enum.java to edit this template
 */
package Dominio.Especialidades;

/**
 * Enumeración que define las especialidades médicas disponibles para los
 * veterinarios del sistema.
 *
 * @author ACER
 */
public enum Especialidad {

    /** Especialidad en medicina general veterinaria. */
    MEDICINA_GENERAL("Medicina General"),

    /** Especialidad en cirugía veterinaria. */
    CIRUGIA("Cirugía"),

    /** Especialidad en nutrición animal. */
    NUTRICION("Nutrición");

    /** Nombre legible de la especialidad. */
    private final String nombre;

    /**
     * Constructor del enum que asigna el nombre legible.
     *
     * @param nombre nombre que se mostrará al usuario.
     */
    Especialidad(String nombre) {
        this.nombre = nombre;
    }

    /**
     * Devuelve el nombre legible de la especialidad.
     *
     * @return el nombre de la especialidad.
     */
    @Override
    public String toString() {
        return nombre;
    }
}

