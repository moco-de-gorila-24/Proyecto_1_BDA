/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package Conexion;

import java.sql.Connection;

/**
 * Interfaz que define el contrato para establecer conexiones a la base de datos.
 * <p>
 * Cualquier clase que implemente esta interfaz debe proporcionar una forma
 * concreta de crear una conexión JDBC hacia el gestor de base de datos MySQL.
 * </p>
 *
 * @author Jorge
 */
public interface IConexion {
    /**
     * Crea y devuelve una nueva conexión JDBC a la base de datos.
     * @return un objeto {@link Connection} si la conexión es exitosa,
     * o {@code null} si ocurre un error al conectar.
     */
    public Connection crearConexionBD();
}
