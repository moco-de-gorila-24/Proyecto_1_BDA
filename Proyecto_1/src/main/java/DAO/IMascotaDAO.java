/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package DAO;

import Dominio.Entidades.Mascota;
import java.util.List;

/**
 * Interfaz que define las operaciones CRUD disponibles para la entidad
 * {@link Mascota} sobre la base de datos.
 *
 * @author ACER
 */
public interface IMascotaDAO {

    /**
     * Inserta una nueva mascota en la base de datos.
     *
     * @param mascota objeto {@link Mascota} con los datos a registrar.
     * @return {@code true} si la inserción fue exitosa, {@code false} en caso contrario.
     */
    boolean insertar(Mascota mascota);

    /**
     * Actualiza los datos de una mascota existente.
     *
     * @param mascota objeto {@link Mascota} con los datos actualizados.
     * @return {@code true} si la actualización fue exitosa, {@code false} en caso contrario.
     */
    boolean actualizar(Mascota mascota);

    /**
     * Elimina una mascota de la base de datos por su identificador.
     *
     * @param id identificador único de la mascota.
     * @return {@code true} si la eliminación fue exitosa, {@code false} en caso contrario.
     */
    boolean eliminar(int id);

    /**
     * Consulta una mascota específica por su identificador.
     *
     * @param id identificador único de la mascota.
     * @return el objeto {@link Mascota} encontrado, o {@code null} si no existe.
     */
    Mascota consultar(int id);

    /**
     * Recupera todas las mascotas almacenadas en la base de datos.
     *
     * @return una lista con todas las mascotas registradas.
     */
    List<Mascota> consultarTodos();
}
