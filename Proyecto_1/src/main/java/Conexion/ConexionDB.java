/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Conexion;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Implementación concreta de {@link IConexion} encargada de gestionar la conexión
 * con la base de datos MySQL del sistema veterinario.
 * <p>
 * Utiliza el driver JDBC de MySQL y establece la conexión hacia la base de datos
 * {@code veterinaria} en {@code localhost:3306}.
 * </p>
 *
 * @author ACER
 */
public class ConexionDB implements IConexion{
     /** Usuario de la base de datos. */
    public String user = "root";
    
    /** Contraseña del usuario de la base de datos. */
    public String pass = "ITSON";
    
    /** Cadena de conexión JDBC utilizada para conectarse a la base de datos. */
    public String cadenaConexion;

    /**
     * Constructor parametrizado que permite especificar usuario y contraseña.
     *
     * @param user usuario de la base de datos.
     * @param pass contraseña del usuario.
     */
    public ConexionDB(String user, String pass) {
        this.user = user;
        this.pass = pass;
        this.cadenaConexion = "jdbc:mysql://localhost:3306/veterinaria";
    }

    /**
     * Constructor por defecto que inicializa la conexión con las credenciales
     * predeterminadas ({@code root}/{@code ITSON}).
     */
    public ConexionDB() {
        this.user = "root";
        this.pass = "LMayli77";
        this.cadenaConexion = "jdbc:mysql://localhost:3306/veterinaria";
    }

    /**
     * Establece una conexión JDBC con la base de datos configurada.
     *
     * @return un objeto {@link Connection} activo, o {@code null} si la conexión falla.
     */
    @Override
    public Connection crearConexionBD() {
        try {
            return DriverManager.getConnection(cadenaConexion, user, pass);
        } catch (SQLException ex) {
            System.out.println("Error al conectar con la base de datos: " + ex.getMessage());
        }
        return null;
    }
}
