/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package DAO;

import Dominio.Entidades.Consulta;
import java.util.List;

/**
 * Interfaz que define las operaciones CRUD disponibles para la entidad
 * {@link Consulta} sobre la base de datos.
 *
 * @author Jorge
 */
public interface IConsultaDAO {

    /**
     * Inserta una nueva consulta en la base de datos.
     *
     * @param consulta objeto {@link Consulta} con los datos a registrar.
     * @return {@code true} si la inserción fue exitosa, {@code false} en caso contrario.
     */
    boolean insertar(Consulta consulta);

    /**
     * Actualiza los datos de una consulta existente.
     *
     * @param consulta objeto {@link Consulta} con los datos actualizados.
     * @return {@code true} si la actualización fue exitosa, {@code false} en caso contrario.
     */
    boolean actualizar(Consulta consulta);

    /**
     * Elimina una consulta de la base de datos por su identificador.
     *
     * @param id identificador único de la consulta.
     * @return {@code true} si la eliminación fue exitosa, {@code false} en caso contrario.
     */
    boolean eliminar(int id);

    /**
     * Consulta una consulta específica por su identificador.
     *
     * @param id identificador único de la consulta.
     * @return el objeto {@link Consulta} encontrado, o {@code null} si no existe.
     */
    Consulta consultar(int id);

    /**
     * Recupera todas las consultas almacenadas en la base de datos.
     *
     * @return una lista con todas las consultas registradas.
     */
    List<Consulta> consultarTodos();
}
