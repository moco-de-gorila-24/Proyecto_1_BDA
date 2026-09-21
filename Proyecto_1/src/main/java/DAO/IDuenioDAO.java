/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package DAO;

import Dominio.Entidades.Duenio;
import java.util.List;

/**
 * Interfaz que define las operaciones CRUD disponibles para la entidad
 * {@link Duenio} sobre la base de datos.
 *
 * @author Jorge
 */
public interface IDuenioDAO {

    /**
     * Actualiza los datos de un dueño existente.
     *
     * @param duenio objeto {@link Duenio} con los datos actualizados.
     * @return {@code true} si la actualización fue exitosa, {@code false} en caso contrario.
     */
    boolean actualizar(Duenio duenio);

    /**
     * Inserta un nuevo dueño en la base de datos, incluyendo sus teléfonos.
     *
     * @param duenio objeto {@link Duenio} con los datos a registrar.
     * @return {@code true} si la inserción fue exitosa, {@code false} en caso contrario.
     */
    boolean insertar(Duenio duenio);

    /**
     * Elimina un dueño y sus teléfonos asociados por su identificador.
     *
     * @param id identificador único del dueño.
     * @return {@code true} si la eliminación fue exitosa, {@code false} en caso contrario.
     */
    boolean eliminar(int id);

    /**
     * Consulta un dueño específico por su identificador.
     *
     * @param id identificador único del dueño.
     * @return el objeto {@link Duenio} encontrado con sus teléfonos, o {@code null} si no existe.
     */
    Duenio consultar(int id);

    /**
     * Recupera todos los dueños almacenados en la base de datos.
     *
     * @return una lista con todos los dueños registrados.
     */
    List<Duenio> consultarTodos();
}
