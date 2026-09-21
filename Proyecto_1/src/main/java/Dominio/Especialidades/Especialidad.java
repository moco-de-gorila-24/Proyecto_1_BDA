/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Enum.java to edit this template
 */
package Dominio.Especialidades;

/**
 *
 * @author ACER
 */
public enum Especialidad {
      
    MEDICINA_GENERAL("Medicina General"),
    CIRUGIA("Cirugía"),
    NUTRICION("Nutrición");

    private final String nombre;

    Especialidad(String nombre) {
        this.nombre = nombre;
    }

    @Override
    public String toString() {
        return nombre;
    }
}

