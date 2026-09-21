/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package DAO;

import Dominio.Entidades.Veterinario;
import java.util.List;

/**
 * Interfaz que define las operaciones CRUD disponibles para la entidad
 * {@link Veterinario} sobre la base de datos.
 *
 * @author ACER
 */
public interface IVeterinarioDAO {

    /**
     * Inserta un nuevo veterinario en la base de datos.
     *
     * @param veterinario objeto {@link Veterinario} con los datos a registrar.
     * @return {@code true} si la inserción fue exitosa, {@code false} en caso contrario.
     */
    boolean insertar(Veterinario veterinario);

    /**
     * Actualiza los datos de un veterinario existente.
     *
     * @param veterinario objeto {@link Veterinario} con los datos actualizados.
     * @return {@code true} si la actualización fue exitosa, {@code false} en caso contrario.
     */
    boolean actualizar(Veterinario veterinario);

    /**
     * Elimina un veterinario de la base de datos por su identificador.
     *
     * @param id identificador único del veterinario.
     * @return {@code true} si la eliminación fue exitosa, {@code false} en caso contrario.
     */
    boolean eliminar(int id);

    /**
     * Consulta un veterinario específico por su identificador.
     *
     * @param id identificador único del veterinario.
     * @return el objeto {@link Veterinario} encontrado, o {@code null} si no existe.
     */
    Veterinario consultar(int id);

    /**
     * Recupera todos los veterinarios almacenados en la base de datos.
     *
     * @return una lista con todos los veterinarios registrados.
     */
    List<Veterinario> consultarTodos();
}
